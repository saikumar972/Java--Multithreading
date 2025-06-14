package executorsAndFuture;

import java.util.concurrent.*;

public class MethodsInFuture {
    public static void main(String[] args) {
        ThreadPoolExecutor executor= new ThreadPoolExecutor(2,4,1, TimeUnit.MINUTES,new ArrayBlockingQueue<>(2), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
        Future<?> futureObject=executor.submit(()->{
            try{
                Thread.sleep(3000);
            }catch (Exception e){}
            System.out.println("Some operation is performed");
        });
        System.out.println("is Future obj operation over: "+futureObject.isDone());
        try {
            System.out.println("Is the operation over in the 2 seconds: "+futureObject.get(2,TimeUnit.SECONDS));

        } catch (Exception e) {
            //handle exception
        }
        try {
            System.out.println("Result is : "+futureObject.get());
        } catch (Exception e) {
            //handle exception
        }
        System.out.println("is Future obj operation over: "+futureObject.isDone());
        System.out.println("is Future obj operation cancelled: "+futureObject.isCancelled());
    }
}
