package ExecutorOperations;

import java.util.concurrent.*;

public class Schedule {
    public static void main(String[] args) {
        ScheduledExecutorService service= Executors.newScheduledThreadPool(5);
        service.schedule(()->{
            System.out.println("edo vokati");
        },5, TimeUnit.SECONDS);
        //callable
        Future<?> obj=service.schedule(()->{
            return "callable wala";
        },5, TimeUnit.SECONDS);
        try{
            System.out.println(obj.get());
        }catch (Exception e){

        }
        //schedule some time and print after 1 sec it will print every 5 sec
        Future<?> obj1=service.scheduleAtFixedRate(()->{
            System.out.println("continuous");
        },1,5, TimeUnit.SECONDS);
        //to cancel
        try{
            Thread.sleep(10000);
            obj1.cancel(true);
        }catch (Exception e){
            
        }
    }
}
