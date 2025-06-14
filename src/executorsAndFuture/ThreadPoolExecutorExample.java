package executorsAndFuture;


import java.util.concurrent.*;

public class ThreadPoolExecutorExample {
    public static void main(String[] args) {
        ThreadPoolExecutor executor= new ThreadPoolExecutor(2,4,1, TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(2),new CustomThreadFactory(),new CustomRejectedExecutionHandler());
        executor.allowCoreThreadTimeOut(true);
        for(int i=1;i<=7;i++){
            executor.submit(()->{
                System.out.println("Task executed by "+Thread.currentThread().getName());
            });
        }
    }
}

class CustomThreadFactory implements ThreadFactory{
    @Override
    public Thread newThread(Runnable r) {
        Thread thread=new Thread(r);
        thread.setName("Thread");
        thread.setDaemon(false);
        thread.setPriority(Thread.MIN_PRIORITY);
        return thread;
    }
}

class CustomRejectedExecutionHandler implements RejectedExecutionHandler{

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("Task rejected "+r.toString());
    }
}