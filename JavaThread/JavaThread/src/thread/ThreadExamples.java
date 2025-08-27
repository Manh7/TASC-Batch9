package thread;

import java.util.*;
import java.util.concurrent.*;

/**
 * Tổng hợp ví dụ Thread cơ bản:
 * - Process vs Thread (giải thích + in info)
 * - 3 cách tạo Thread (Thread, Runnable, ExecutorService/Callable)
 * - Multi-thread: so sánh thời gian tuần tự vs song song (mô phỏng I/O)
 * - Cách chờ hoàn thành: join(), Future.get()
 * - Cảnh báo về giới hạn thread
 */
public class ThreadExamples {

    // ========================= 1) PROCESS vs THREAD =========================
    static void demoProcessVsThread() {
        System.out.println("== 1) Process vs Thread ==");
        // Process: chương trình đang chạy (JVM), có PID (truy cập được từ Java 9+)
        String pid = ProcessHandle.current().pid() + "";
        System.out.println("Process (JVM) PID = " + pid);
        // Thread: luồng trong process; mỗi thread có tên & ID
        System.out.println("Current thread = " + Thread.currentThread().getName()
                + " (id=" + Thread.currentThread().getId() + ")");
        System.out.println();
    }

    // ========================= 2) CÁCH TẠO THREAD ==========================
    static void demoCreateThread() throws Exception {
        System.out.println("== 2) Tạo Thread theo 3 cách ==");

        // 2.1: Kế thừa Thread
        class MyThread extends Thread {
            public void run() {
                System.out.println("[Thread] Hello from " + getName());
            }
        }
        Thread t1 = new MyThread();

        // 2.2: Implement Runnable
        Runnable job = () -> System.out.println("[Runnable] Hello from " + Thread.currentThread().getName());
        Thread t2 = new Thread(job, "Runnable-Worker");

        // 2.3: ExecutorService + Callable (hiện đại, có trả kết quả, quản lý pool)
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Callable<String> task = () -> {
            Thread.sleep(200);
            return "[Callable] result from " + Thread.currentThread().getName();
        };
        Future<String> f = pool.submit(task);

        t1.start();
        t2.start();

        // Chờ hoàn thành (xem mục 5 chi tiết hơn)
        t1.join();
        t2.join();
        System.out.println(f.get());
        pool.shutdown();
        System.out.println();
    }

    // ============ 3) MULTI-THREAD: Ưu/nhược & so sánh thời gian ============
    // Mô phỏng 10 tác vụ I/O (mỗi tác vụ "ngủ" 200ms). Multi-thread thường nhanh hơn.
    static void demoSequentialVsParallel() throws Exception {
        System.out.println("== 3) Multi-thread: tuần tự vs song song (mô phỏng I/O) ==");
        int N = 10;

        // tuần tự (synchronous)
        long t0 = System.nanoTime();
        for (int i = 0; i < N; i++) {
            fakeIO(200); // block 200ms
        }
        long t1 = System.nanoTime();
        long sequentialMs = (t1 - t0) / 1_000_000;
        System.out.println("Sequential ~ " + sequentialMs + " ms");

        // song song bằng ExecutorService (pool 5 thread)
        ExecutorService pool = Executors.newFixedThreadPool(5);
        List<Callable<Void>> tasks = new ArrayList<>();
        for (int i = 0; i < N; i++) tasks.add(() -> { fakeIO(200); return null; });

        long p0 = System.nanoTime();
        pool.invokeAll(tasks);               // chờ toàn bộ xong
        pool.shutdown();
        long p1 = System.nanoTime();
        long parallelMs = (p1 - p0) / 1_000_000;
        System.out.println("Parallel (5 threads) ~ " + parallelMs + " ms");

        System.out.println("→ Multi-thread giúp giảm tổng thời gian cho tác vụ I/O chờ đợi.");
        System.out.println("  Nhược điểm: code phức tạp hơn, cần đồng bộ để tránh race condition.\n");
    }

    // Helper mô phỏng I/O
    static void fakeIO(long millis) {
        try { Thread.sleep(millis); } catch (InterruptedException ignored) {}
    }

    // ================== 4) RACE CONDITION & SYNCHRONIZED ===================
    static void demoRaceCondition() throws Exception {
        System.out.println("== 4) Race condition & synchronized ==");
        class Counter {
            private int count = 0;
            void inc() { count++; }                 // ❌ không synchronized
            synchronized void incSafe() { count++; } // ✅ synchronized
            int get() { return count; }
        }

        final Counter c1 = new Counter();
        final Counter c2 = new Counter();

        Runnable r = () -> {
            for (int i = 0; i < 10000; i++) {
                c1.inc();       // có thể mất tăng do race
                c2.incSafe();   // an toàn
            }
        };

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start(); t2.start();
        t1.join();  t2.join();

        System.out.println("Counter không synchronized (mong đợi 20000) → " + c1.get());
        System.out.println("Counter synchronized (mong đợi 20000)       → " + c2.get());
        System.out.println();
    }

    // ============= 5) BIẾT THREAD ĐÃ HOÀN THÀNH (join, Future) ============
    static void demoCompletion() throws Exception {
        System.out.println("== 5) Chờ hoàn thành: join() & Future.get() ==");

        Thread t = new Thread(() -> fakeIO(300));
        t.start();
        t.join(); // main chờ
        System.out.println("Thread t đã hoàn thành (join).");

        ExecutorService pool = Executors.newSingleThreadExecutor();
        Future<String> f = pool.submit(() -> { fakeIO(200); return "xong"; });
        System.out.println("Kết quả Future = " + f.get()); // block đến khi có kết quả
        pool.shutdown();
        System.out.println();
    }

    // =================== 6) GIỚI HẠN SỐ THREAD TRONG JVM ===================
    static void demoThreadLimitNote() {
        System.out.println("== 6) Giới hạn số thread (ghi chú) ==");
        System.out.println("""
            • Java không đặt giới hạn cứng về số thread; giới hạn phụ thuộc RAM, -Xss (kích thước stack mỗi thread), và HĐH.
            • Tạo quá nhiều thread gây tốn bộ nhớ và chi phí context switching → hiệu năng giảm, thậm chí OutOfMemoryError.
            • Thực tế nên dùng Thread Pool (ExecutorService) để kiểm soát số lượng thread phù hợp với số lõi CPU & workload.
            """);
    }

    public static void main(String[] args) throws Exception {
//        demoProcessVsThread();      // (1)
//        demoCreateThread();         // (2)
//        demoSequentialVsParallel(); // (3)
//        demoRaceCondition();        // (4)
        demoCompletion();           // (5)
//        demoThreadLimitNote();      // (6)
    }
}

