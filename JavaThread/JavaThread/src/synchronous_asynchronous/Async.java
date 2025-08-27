package synchronous_asynchronous;

import java.util.concurrent.CompletableFuture;

public class Async {
    public static void ExampleAsync(){
        System.out.println("Start");

        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(5000);// Chờ 5 giây
                System.out.println("Async task completed");
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        });

        for (int i = 0; i < 10; i++) {
            System.out.println("Doing other thing ...");
        }

        future.join();

        System.out.println("End");
    }
}
