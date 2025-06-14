package CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AcceptAndAcceptAsyncExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //accept is the last stage since it returns ntg
        CompletableFuture<String> f1=CompletableFuture.supplyAsync(()->{
            return "sai kumar";
        });

        CompletableFuture<Void> f2=f1.thenAcceptAsync((k)->{
            System.out.println("The end "+k+" ................");
        });
        //System.out.println(f2.get()); -> returns null
        f2.get();
    }

}
