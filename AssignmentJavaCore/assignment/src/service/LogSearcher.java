package service;

import model.LogEntry;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;

public class LogSearcher {
    private static final int THREAD_COUNT = 8; // Số luồng tối đa
    private static final int CHUNK_SIZE = 50000; // Kích thước chunk

    public List<LogEntry> search(File file, String level, LocalDateTime start, LocalDateTime end, String keyword) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        List<Future<List<LogEntry>>> futures = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file), 128 * 1024)) {
            List<String> chunk = new ArrayList<>();
            String line;

            while ((line = br.readLine()) != null) {
                chunk.add(line);
                if (chunk.size() >= CHUNK_SIZE) {
                    futures.add(processChunk(new ArrayList<>(chunk), level, start, end, keyword, executor));
                    chunk.clear();
                }
            }

            // Xử lý chunk cuối cùng
            if (!chunk.isEmpty()) {
                futures.add(processChunk(new ArrayList<>(chunk), level, start, end, keyword, executor));
            }
        }

        // Gom kết quả từ các luồng
        List<LogEntry> results = new ArrayList<>();
        for (Future<List<LogEntry>> f : futures) {
            results.addAll(f.get());
        }

        executor.shutdown();
        return results;
    }

    private Future<List<LogEntry>> processChunk(List<String> lines, String level, LocalDateTime start,
                                                LocalDateTime end, String keyword, ExecutorService executor) {
        return executor.submit(() -> {
            List<LogEntry> matches = new ArrayList<>();
            for (String line : lines) {
                LogEntry entry = LogParser.parse(line);
                if (entry == null) continue;

                boolean matchesLevel = (level == null) || entry.getLevel().equalsIgnoreCase(level);
                boolean matchesTime = (start == null || !entry.getTimestamp().isBefore(start)) &&
                        (end == null || !entry.getTimestamp().isAfter(end));
                boolean matchesKeyword = (keyword == null) || entry.getMessage().toLowerCase().contains(keyword.toLowerCase());

                if (matchesLevel && matchesTime && matchesKeyword) {
                    matches.add(entry);
                }
            }
            return matches;
        });
    }
}

