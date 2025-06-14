package ExecutorOperations;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Shutdown {
    public static void main(String[] args) {
        ExecutorService obj= Executors.newFixedThreadPool(5);
        obj.submit(()->{
            try{
                Thread.sleep(5000);
            }catch (Exception e){}
            System.out.println("Task1 completed");
        });
        obj.shutdown();

        System.out.println("main thread");

        obj.submit(()->{
            try{
                Thread.sleep(5000);
            }catch (Exception e){}
            System.out.println("Task2");
        });
        //Task 2 will nt happen
    }
}
