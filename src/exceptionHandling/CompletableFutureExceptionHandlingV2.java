package exceptionHandling;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureExceptionHandlingV2 {
    public static void main(String[] args) {
        CompletableFuture<String> future1=CompletableFuture.supplyAsync(()->{
            throwException();
            return "task 1 completed";
        });
         CompletableFuture<String> future2=CompletableFuture.supplyAsync(()->{
            return "task 2 completed";
        });
        CompletableFuture<String> futureResult=future1.thenCombine(future2, (result1, result2)->{
            return result1+" "+result2;
        }).handle((result,exception)->{
            if(exception!=null){
                System.out.println("Exception occured at "+exception.getMessage());
                return "Default result";
            }else{
                return result;
            }
        });
        try{ System.out.println(futureResult.get());}
        catch(Exception e){}
    }
    public static void throwException(){
        System.out.println("Exception occured in exception method");
        throw new RuntimeException("Exception occured in future1");
    }
}
