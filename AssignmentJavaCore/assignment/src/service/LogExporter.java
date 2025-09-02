package service;

import model.LogEntry;
import java.io.*;
import java.util.List;

public class LogExporter {
    public static void export(List<LogEntry> logs, String outputPath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            for (LogEntry log : logs) {
                writer.write(log.toString());
                writer.newLine();
            }
        }
    }
}
