package synchronous_asynchronous;

public class Sync {
    public static void ExampleSync(){
        System.out.println("Start");
        try{
            Thread.sleep(5000);
            System.out.println("Task completed");
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("End");
    }
}
