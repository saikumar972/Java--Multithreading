package executorsAndFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class RunnableExecutor {
    public static void main(String[] args) {
        ThreadPoolExecutor executor= new ThreadPoolExecutor(2,4,1, TimeUnit.MINUTES,new ArrayBlockingQueue<>(2), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
        List<Integer> list= new ArrayList<>();
        Future<List<Integer>> futureResult=executor.submit(()->{
            list.add(200);
            list.add(300);
        },list);

        try {
            System.out.println(futureResult.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
           //
        }
        System.out.println("list is"+ list);

        //2nd approach
        ThreadPoolExecutor executor2= new ThreadPoolExecutor(2,4,1, TimeUnit.MINUTES,new ArrayBlockingQueue<>(2), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
        List<Integer> list2=new ArrayList<>();
        Future<List<Integer>> futureOutput=executor2.submit(new CustomRunnable(list2),list2);
        try {
            System.out.println(futureOutput.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            //
        }
        System.out.println("list is"+ list2);
    }
}
class CustomRunnable implements Runnable{
    public List<Integer> list;

    public CustomRunnable(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        list.add(200);
        list.add(300);
        list.add(69);
    }
}