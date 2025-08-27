package java_core_basic.string;

public class Core2 {
    public static void main(String[] args){
        // khai báo
        String s = "Trương Đức Mạnh"; // literal

        String k = new String("Hello World");

        // Tạo chuỗi từ mảng ký tự
        char[] charArray = {'H', 'e', 'l', 'l', 'o'};
        String str3 = new String(charArray);
        System.out.println(str3);

        // Tạo chuỗi từ StringBuilder hoặc StringBuffer có tính chất mutable
        StringBuilder sb = new StringBuilder("Programming");
        String str5 = sb.toString();
        System.out.println(str5);

        // tính bất biến
        s.substring(4);
        System.out.println("String sau khi dùng phương thức");
        System.out.println(s);

        // string pool
        String s1 = "Trương Đức Mạnh";
        System.out.println(s==s1); // true vì cùng object trong pool

        String s2 = new String("Trương Đức Mạnh");
        System.out.println(s==s2); // flase vì tạo bằng new không nằm trong pool

        String s3 = s2.intern();
        System.out.println(s==s3); // true vì đưa s3 vào pool bằng intern()

        //so sánh
        System.out.println(s.equals(s2)); // so sánh nội dung

        //duyệt string
        for(int i=0; i<s.length(); i++){
            System.out.println(s.charAt(i));
        }

        // Tách chuỗi dựa trên biểu thức chính quy
        String stringSplit= "Start  Learning     Java";
        String[] parts=stringSplit.trim().split("\\s+");
        for(String x : parts){
            System.out.println(x);
        }
    }
}
