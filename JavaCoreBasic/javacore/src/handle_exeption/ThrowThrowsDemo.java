package java_core_basic.handle_exeption;

class ThrowThrowsDemo {
    // khai báo: phương thức này CÓ THỂ ném IOException
    public static void readFile(String path) throws java.io.IOException {
        if (path == null) {
            // ném trực tiếp exception
            throw new java.io.IOException("Path is null!");
        }
        System.out.println("Reading file: " + path);
    }

    public static void main(String[] args) {
        try {
            readFile(null);
        } catch (java.io.IOException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
    }
}
