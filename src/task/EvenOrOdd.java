package task;

import java.util.concurrent.CompletableFuture;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class EvenOrOdd {
    private static Object object;
    private static IntPredicate evenPredicate=e->e%2==0;
    private static IntPredicate oddPredicate=e->e%2!=0;
    public static void printResult(IntPredicate condition){
        IntStream.rangeClosed(1,10)
                .filter(condition)
                .forEach(EvenOrOdd::execute);
    }
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture.runAsync(()->{
            EvenOrOdd.printResult(oddPredicate);
        });
        CompletableFuture.runAsync(()->{
            EvenOrOdd.printResult(evenPredicate);
        });
        Thread.sleep(1000);
    }
    public static void execute(int i){
        try{
            System.out.println(i+" Executed by "+Thread.currentThread().getName());
            object.notify();
            object.wait();
        }catch (Exception e){
            //log
        }

    }
}
