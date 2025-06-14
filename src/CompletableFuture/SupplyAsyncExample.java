package CompletableFuture;

import java.util.concurrent.*;

public class SupplyAsyncExample {
    public static void main(String[] args) {
        ThreadPoolExecutor executor= new ThreadPoolExecutor(1,1,1, TimeUnit.SECONDS,new ArrayBlockingQueue<>(1), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
        CompletableFuture<String> completableFuture=CompletableFuture.supplyAsync(()->{
            return "The thread is executor thread "+Thread.currentThread().getName();
        },executor);
        try{
            System.out.println(completableFuture.get());
        }catch (Exception e){

        }
        CompletableFuture<String> completableFuture2=CompletableFuture.supplyAsync(()->{
            return "The thread is from fork join pool "+Thread.currentThread().getName();
        });
        try{
            System.out.println(completableFuture2.get());
        }catch (Exception e){

        }
    }
}
