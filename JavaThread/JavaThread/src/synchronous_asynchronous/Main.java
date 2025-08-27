package synchronous_asynchronous;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int choice ;
        do {
            System.out.println("====== MENU =====");
            System.out.println("1. async");
            System.out.println("2. sync");
            System.out.println("3. synchronized");
            System.out.println("0. Exit");
            System.out.println(" Choice option 0-3 : ");

            choice = scanner.nextInt();

            switch (choice){
                case 1:
                    System.out.println("async");
                    Async.ExampleAsync();
                    break;
                case 2:
                    System.out.println("sync");
                    Sync.ExampleSync();
                    break;
                case 3:
                    System.out.println("synchronized");
                    exampleSynchronized();
                case 0:
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }while (choice !=0 );
    }
    public static void exampleSynchronized() throws InterruptedException {
        Count count = new Count();

        // Tạo hai luồng tăng giá trị đếm
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                count.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                count.increment();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Final count: " + count.getCount());
    }
}

class Count {
    private int count = 0;

    public Count() {
    }

    public synchronized void increment() {
        count++;
    } // nếu không sử dụng synchronized két quả là số ngẫu nhiên

    public int getCount() {
        return count;
    }
}
