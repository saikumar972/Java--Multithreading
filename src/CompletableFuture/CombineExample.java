package CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CombineExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1=CompletableFuture.supplyAsync(()->{
            return "Sai ";
        });
        CompletableFuture<String> future2=CompletableFuture.supplyAsync(()->{
            return "kumar";
        });
        CompletableFuture<String> CombinedResult=future1.thenCombine(future2,(String s1,String s2)->s1+s2);
        System.out.println(CombinedResult.get());

        CompletableFuture<Integer> length=future1.thenCombineAsync(future2,(String s1,String s2)->s1.length()+s2.length());
        System.out.println(length.get());
    }
}
