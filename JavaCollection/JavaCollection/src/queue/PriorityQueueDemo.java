package queue;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueDemo {
    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(10);
        queue.add(20);
        queue.add(15);
        queue.add(12);
        queue.add(393);
        queue.add(3);
        queue.offer(15);

        System.out.println(queue);
        System.out.println(queue.peek());

        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }

        // Hàng đợi ưu tiên với thứ tự giảm dần
        PriorityQueue pq = new PriorityQueue<>(Comparator.reverseOrder());

        // Thêm phần tử
        pq.add(11);
        pq.add(22);
        pq.add(33);

        System.out.println("PriorityQueue giảm dần: " + pq);

    }

}
