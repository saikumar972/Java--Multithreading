package task;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class EvenOrOddUsingExecutor {
    public static void main(String[] args) {
        ExecutorService executorService= Executors.newFixedThreadPool(2);
        IntStream.rangeClosed(1, 10)
                .forEach(number -> {
                    CompletableFuture<Integer> oddCompletableFuture =
                            CompletableFuture.completedFuture(number)
                                    .thenApplyAsync(x -> {
                    if (x % 2 != 0) {
                        System.out.println(x+" executed by "+Thread.currentThread().getName());
                    }
                    return x;
                    },executorService);
                    oddCompletableFuture.join();
                    CompletableFuture<Integer> evenCompletableFuture =
                            CompletableFuture.completedFuture(number)
                                    .thenApplyAsync(x -> {
                                        if (x % 2 == 0) {
                                            System.out.println(x+" executed by "+Thread.currentThread().getName());
                                        }
                                        return x;
                                    },executorService);
                    evenCompletableFuture.join();
                });
        executorService.shutdown();
    }
}
