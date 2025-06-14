package ExecutorOperations;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShutDownNow {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(()->{
            try{
                Thread.sleep(150000);
            }catch (Exception e){

            }
            System.out.println("edo ala ala");
        });
        //it will nt the above thread to sleep for 15 sec
        executorService.shutdownNow();
        System.out.println("main thread completed");
    }
}
