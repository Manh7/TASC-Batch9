package thread;

class Counter {
    private int count =0;

    public void increment() {
        count ++;
    }

    public synchronized void safeIncrement() {
        count ++;
    }

    public int getCount(){
        return count;
    }
}

public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException{
        Counter counter1 = new Counter();
        Counter counter2 = new Counter();

        // Tạo 2 thread bằng cách implement Runnable
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter1.increment();  // không synchronized
                counter2.safeIncrement(); // synchronized
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                counter1.increment();
                counter2.safeIncrement();
            }
        });

        // Start threads
        t1.start();
        t2.start();

        // Chờ cả 2 thread hoàn thành
        t1.join();
        t2.join();

        System.out.println("Kết quả counter1 (không synchronized): " + counter1.getCount());
        System.out.println("Kết quả counter2 (có synchronized):    " + counter2.getCount());
    }
}
