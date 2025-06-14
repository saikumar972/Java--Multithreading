package CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RunAsyncDB {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        RunAsyncDB obj= new RunAsyncDB();
        obj.someData();
    }
    public Void someData() throws ExecutionException, InterruptedException {
        Executor executor= Executors.newFixedThreadPool(5);
        System.out.println("Executor Thread name is "+Thread.currentThread().getName());
        CompletableFuture<Void> output=CompletableFuture.runAsync(()->{
            System.out.println("runAsync Thread name is "+Thread.currentThread().getName());
            System.out.println("edaina cheyali");
        },executor);
        return output.get();
    }
}
