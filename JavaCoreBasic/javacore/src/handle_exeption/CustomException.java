package handle_exeption;

// Custom checked exception
class InvalidAgeException extends Exception {
    public InvalidAgeException(String msg) {
        super(msg);
    }
}

public class CustomException {
    static void register(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age must be >= 18");
        }
        System.out.println("Registration success!");
    }

    public static void main(String[] args) {
        try {
            register(16);
        } catch (InvalidAgeException e) {
            System.out.println("Caught custom exception: " + e.getMessage());
        }
    }
}
