package CompletableFuture;

import java.util.concurrent.*;

public class ApplyAndApplyAsyncExample {
    public static void main(String[] args) {
        ThreadPoolExecutor executor= new ThreadPoolExecutor(1,1,1, TimeUnit.SECONDS,new ArrayBlockingQueue<>(1), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
        //thenApply way 1
        CompletableFuture<String> future1=CompletableFuture.supplyAsync(()->{
            try{
                Thread.sleep(3000);
            }catch (Exception e){}
            System.out.println("future1 thread name which is getting from ThreadPoolExecutor is "+Thread.currentThread().getName());
            return "something in future1";
        },executor);
        CompletableFuture<String> future2=future1.thenApply((String k)->{
            System.out.println("future2 thread name is "+Thread.currentThread().getName());
            return k+" something in future2";
        });
//        try{
//            System.out.println(applyThenSync.get());
//        }catch (Exception e){
//
//        }
        //in chaining applyThen and supply Async uses same thread
        CompletableFuture<String> future3=CompletableFuture.supplyAsync(()->{
            try{
                Thread.sleep(4000);
            }catch (Exception e){}
            System.out.println("future3  name is "+Thread.currentThread().getName());
            return "something in future3";
        }).thenApplyAsync((String k)->{
            System.out.println("future4 thread name is "+Thread.currentThread().getName());
            return k+" something in future4";
        });
//        try{
//            System.out.println(supplyANdApply.get());
//        }catch (Exception e){
//
//        }
        CompletableFuture<String> future5=CompletableFuture.supplyAsync(()->{
            try{
                Thread.sleep(5000);
            }catch (Exception e){}
            System.out.println("future5 Thread from ThreadPoolExecutor thread name is "+Thread.currentThread().getName());
            return "something in future5";
        }).thenApplyAsync((String k)->{
            System.out.println("future6  thread name is "+Thread.currentThread().getName());
            return k+" something in future6";
        },executor);
    }
}
