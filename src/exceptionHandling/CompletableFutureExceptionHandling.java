package exceptionHandling;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureExceptionHandling {
    public static void main(String[] args) {
        CompletableFuture<String> future1=CompletableFuture.supplyAsync(()->{
            throwException();
            return "task 1 completed";
        }).exceptionally((exception)->{
            System.out.println("Exception caught ---"+exception.getMessage());
            //return "exception occured at task 1";
            // Rethrow the exception to propagate it
            throw new RuntimeException(exception);
        });
         CompletableFuture<String> future2=CompletableFuture.supplyAsync(()->{
            return "task 2 completed";
        });
        CompletableFuture<String> result=future1.thenCombine(future2, (result1, result2)->{
            return result1+" "+result2;
        });
        try{ System.out.println(result.get());}
        catch(Exception e){}
    }
    public static void throwException(){
        System.out.println("Exception occured in exception method");
        throw new RuntimeException("Exception occured");
    }
}
