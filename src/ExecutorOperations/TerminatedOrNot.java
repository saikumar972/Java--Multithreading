package ExecutorOperations;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TerminatedOrNot {
    public static void main(String[] args) {
        ExecutorService obj= Executors.newFixedThreadPool(5);
        obj.submit(()->{
            try{
                Thread.sleep(5000);
            }catch (Exception e){}
            System.out.println("em led dolla");
        });
        obj.shutdown();
        //it pause the main thread for 2 sec and check whether the above sub,it is shutdown or not
        //it just states state
        try{
            boolean Terminated=obj.awaitTermination(2, TimeUnit.SECONDS);
            System.out.println("Is terminated "+Terminated);
        } catch (Exception e) {

        }
        System.out.println("main thread");
    }
}
