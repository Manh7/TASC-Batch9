package queue;

import java.util.ArrayDeque;

public class ArrayDequeDemo {
    public static void main(String[] args) {
        ArrayDeque<Double> deque= new ArrayDeque<>();

        deque.add(5.5);
        deque.addFirst(4D);
        deque.add(6.75);
        deque.addLast(50.5);

        System.out.println("Lấy phần tử ở đầu");
        System.out.println(deque.getFirst());
        deque.peekFirst();

        deque.pollLast();
        deque.removeFirst();


        System.out.println("Queue cuối cùng");
        System.out.println(deque);
    }
}
