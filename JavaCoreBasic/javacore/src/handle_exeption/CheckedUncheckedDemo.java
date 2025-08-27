package handle_exeption;

public class CheckedUncheckedDemo {
    // Checked Exception
    static void checked() throws java.io.IOException {
        throw new java.io.IOException("Checked Exception");
    }

    // Unchecked Exception
    static void unchecked() {
        throw new RuntimeException("Unchecked Exception");
    }

    public static void main(String[] args) {
        try {
            checked(); // bắt buộc phải try-catch hoặc throws
        } catch (Exception e) {
            System.out.println("Handled checked: " + e.getMessage());
        }

        unchecked(); // không bắt buộc, nhưng sẽ crash nếu không catch
    }
}
