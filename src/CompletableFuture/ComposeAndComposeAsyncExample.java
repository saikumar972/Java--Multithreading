package CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ComposeAndComposeAsyncExample {
    public static void main(String[] args) {
        //for chaining and to maintain order
        CompletableFuture<String> future1=CompletableFuture.supplyAsync(()->{
            return "Sai ";
        });
        CompletableFuture<String> future2=future1.thenCompose((String s)->{
           return   CompletableFuture.supplyAsync(()-> s+"kumar");
        });
        try {
            System.out.println(future2.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
