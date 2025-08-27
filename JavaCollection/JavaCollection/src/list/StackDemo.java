package list;

import java.util.Stack;

public class StackDemo {
    public static void main(String[] args) {
        Stack<String> stack1= new Stack<>();

        stack1.push("Hoàng");
        stack1.push("Mạnh");
        stack1.push("Hưng");
        stack1.push("Linh");

        System.out.println("-phần tử ở đỉnh:");
        System.out.println(stack1.peek());

        System.out.println("-danh sách:");
        while(!stack1.isEmpty()) {
            System.out.println(stack1.pop());
        }

    }
}
