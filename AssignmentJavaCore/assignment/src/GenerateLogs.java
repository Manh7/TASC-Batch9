import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class GenerateLogs {
    private static final String[] LEVELS = {"INFO", "WARN", "ERROR", "DEBUG", "TRACE"};
    private static final String[] SERVICES = {"AuthService", "PaymentService", "OrderService", "UserService", "NotificationService"};
    private static final String[] MESSAGES = {
            "User logged in successfully",
            "Payment successfully processed for order",
            "Order failed due to insufficient balance",
            "Session expired, please re-login",
            "Email sent to customer successfully",
            "Database connection lost, retrying..."
    };

    public static void main(String[] args) {
        String filePath = "system_logs.txt";
        int totalLogs = 500_000;  // Số dòng logs cần tạo
        Random random = new Random();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 1; i <= totalLogs; i++) {
                LocalDateTime timestamp = LocalDateTime.now().minusSeconds(random.nextInt(60 * 60 * 24 * 30)); // Logs trong 30 ngày gần nhất
                String level = LEVELS[random.nextInt(LEVELS.length)];
                String service = SERVICES[random.nextInt(SERVICES.length)];
                String message = MESSAGES[random.nextInt(MESSAGES.length)] + " #" + i;

                String logLine = String.format("%s [%s] [%s] - %s",
                        timestamp.format(formatter), level, service, message);

                writer.write(logLine);
                writer.newLine();
            }
            System.out.println("✅ Đã tạo file logs thành công: " + filePath);
        } catch (IOException e) {
            System.err.println("❌ Lỗi khi tạo file logs: " + e.getMessage());
        }
    }
}
