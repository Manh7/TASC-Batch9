import model.LogEntry;
import service.LogExporter;
import service.LogSearcher;
import service.LogSearcher2;

import java.io.File;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

public class App {
    private static final Scanner sc = new Scanner(System.in);
    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final Set<String> VALID_LEVELS = Set.of("INFO", "WARN", "ERROR", "DEBUG", "TRACE");

    public static void main(String[] args) throws Exception {
        LogSearcher searcher = new LogSearcher();
        LogSearcher2 searcher2 = new LogSearcher2();


        // 1. Nhập đường dẫn file log và kiểm tra tồn tại
        File file = getValidLogFile();
//        Path path = getValidLogFilePath();

        // 2. Nhập log level hợp lệ
        String level = getValidLogLevel();

        // 3. Nhập thời gian bắt đầu
        LocalDateTime start = getValidDate("Nhập thời gian bắt đầu (yyyy-MM-dd HH:mm:ss hoặc để trống): ");

        // 4. Nhập thời gian kết thúc
        LocalDateTime end = getValidDate("Nhập thời gian kết thúc (yyyy-MM-dd HH:mm:ss hoặc để trống): ");

        // Nếu nhập cả start & end, cần đảm bảo start <= end
        if (start != null && end != null && start.isAfter(end)) {
            System.out.println("❌ Lỗi: Thời gian bắt đầu không thể lớn hơn thời gian kết thúc!");
            return;
        }

        // 5. Nhập từ khóa tìm kiếm
        System.out.print("Nhập từ khóa trong message (hoặc để trống): ");
        String keyword = sc.nextLine().trim();
        if (keyword.isBlank()) keyword = null;

        // 6. Thực hiện tìm kiếm logs
        long startTime = System.currentTimeMillis();
        List<LogEntry> results = searcher.search(file, level, start, end, keyword);
        long endTime = System.currentTimeMillis();

        System.out.println("Tìm thấy " + results.size() + " kết quả trong " + (endTime - startTime) + " ms");

        System.out.print("Nhập tên file .txt để xuất kết quả: ");
        String output = sc.nextLine();
        long startExport = System.currentTimeMillis();
        LogExporter.export(results, output);
        long endExport = System.currentTimeMillis();
        System.out.println("Đã xuất kết quả ra file: " + output + " trong "+ (endExport - startExport) +" ms");
    }

    private static File getValidLogFile() {
        while(true){
            System.out.print("Nhập đường dẫn file log: ");
            String filePath = sc.nextLine().trim();
            File file = new File(filePath);
            if(file.exists() && file.isFile() && file.canRead())
                return file;
            else{
                System.out.println("❌ Lỗi: File không tồn tại hoặc không đọc được. Vui lòng nhập lại!");
            }
        }
    }

//    private static Path getValidLogFilePath() {
//        while(true){
//            System.out.print("Nhập đường dẫn file log: ");
//            String filePath = sc.nextLine().trim();
//            Path path = Path.of(filePath);
//                return path;
//        }
//    }

    private static String getValidLogLevel() {
        while(true){
            System.out.print("Nhập log level (INFO/WARN/ERROR/DEBUG/TRACE hoặc để trống):");
            String logLevel = sc.nextLine().trim().toUpperCase();
            if(logLevel.isBlank() || VALID_LEVELS.contains(logLevel)){
                return logLevel.isBlank() ? null : logLevel;
            }
            else
                System.out.println("❌ Lỗi: Chỉ chấp nhận INFO, WARN, DEBUG, TRACE hoặc ERROR!");
        }
    }

    private static LocalDateTime getValidDate(String message) {
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();
            if (input.isBlank()) return null;

            try {
                return LocalDateTime.parse(input, fmt);
            } catch (DateTimeParseException e) {
                System.out.println("❌ Sai định dạng ngày giờ! Vui lòng nhập theo định dạng yyyy-MM-dd HH:mm:ss");
            }
        }
    }
}

