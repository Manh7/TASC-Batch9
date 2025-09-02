package service;

import model.LogEntry;
import service.LogParser;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;

public class LogSearcher2 {
    private static final int THREAD_COUNT = 8;
    private static final int CHUNK_SIZE = 50000;
    private static final int QUEUE_CAPACITY = 100;

    public List<LogEntry> search(File file, String level, LocalDateTime start, LocalDateTime end, String keyword) throws Exception {
        ExecutorService consumerPool = Executors.newFixedThreadPool(THREAD_COUNT);
        BlockingQueue<List<String>> queue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);
        List<Future<List<LogEntry>>> futures = new ArrayList<>();

        //  1. Start consumer threads trước
        for (int i = 0; i < THREAD_COUNT; i++) {
            Future<List<LogEntry>> future = consumerPool.submit(() -> {
                List<LogEntry> localResult = new ArrayList<>();
                while (true) {
                    List<String> chunk = queue.take(); // sẽ chờ nếu chưa có
                    if (chunk == LogSearcher2.POISON_PILL) break; // tín hiệu kết thúc

                    for (String line : chunk) {
                        LogEntry entry = LogParser.parse(line);
                        if (entry == null) continue;

                        boolean matchesLevel = (level == null) || entry.getLevel().equalsIgnoreCase(level);
                        boolean matchesTime = (start == null || !entry.getTimestamp().isBefore(start)) &&
                                (end == null || !entry.getTimestamp().isAfter(end));
                        boolean matchesKeyword = (keyword == null) || entry.getMessage().toLowerCase().contains(keyword.toLowerCase());

                        if (matchesLevel && matchesTime && matchesKeyword) {
                            localResult.add(entry);
                        }
                    }
                }
                return localResult;
            });
            futures.add(future);
        }

        // ✅ 2. Producer thread: đọc file & đưa vào queue
        try (BufferedReader br = new BufferedReader(new FileReader(file), 64 * 1024)) {
            List<String> chunk = new ArrayList<>();
            String line;

            while ((line = br.readLine()) != null) {
                chunk.add(line);
                if (chunk.size() >= CHUNK_SIZE) {
                    queue.put(new ArrayList<>(chunk)); // đẩy vào queue
                    chunk.clear();
                }
            }

            if (!chunk.isEmpty()) {
                queue.put(new ArrayList<>(chunk)); // chunk cuối cùng
            }
        }

        // ✅ 3. Gửi POISON_PILL để báo các thread dừng lại
        for (int i = 0; i < THREAD_COUNT; i++) {
            queue.put(POISON_PILL); // gửi tín hiệu dừng
        }

        // ✅ 4. Gom kết quả
        List<LogEntry> results = new ArrayList<>();
        for (Future<List<LogEntry>> f : futures) {
            results.addAll(f.get());
        }

        consumerPool.shutdown();
        return results;
    }

    // POISON_PILL để báo kết thúc
    private static final List<String> POISON_PILL = Collections.emptyList();
}
