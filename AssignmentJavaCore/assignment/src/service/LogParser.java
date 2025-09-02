package service;

import model.LogEntry;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogParser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static LogEntry parse(String line) {
        try {
            int firstBracket = line.indexOf("[");
            String timestampStr = line.substring(0, firstBracket).trim();
            LocalDateTime timestamp = LocalDateTime.parse(timestampStr, FORMATTER);

            int secondBracket = line.indexOf("]", firstBracket) + 1;
            String level = line.substring(firstBracket + 1, secondBracket - 1);

            int thirdBracket = line.indexOf("[", secondBracket);
            int fourthBracket = line.indexOf("]", thirdBracket);
            String service = line.substring(thirdBracket + 1, fourthBracket);

            String message = line.substring(fourthBracket + 3);

            return new LogEntry(timestamp, level, service, message);
        } catch (Exception e) {
            return null; // Bỏ qua các dòng log bị lỗi format
        }
    }
}

