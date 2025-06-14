package customLocks;


import java.util.concurrent.Semaphore;

public class SemaphoreLockExample {
    public static void main(String[] args) {
        Example example1= new Example();
        Example example2= new Example();
        Example example3= new Example();
        Example example4= new Example();
        Semaphore lock = new Semaphore(2);

        Thread t1= new Thread(()->{
            example1.producer(lock);
        },"Thread -1");

        Thread t2= new Thread(()->{
            example1.producer(lock);
        },"Thread -2");
        Thread t3= new Thread(()->{
            example1.producer(lock);
        },"Thread -3");

        Thread t4= new Thread(()->{
            example1.producer(lock);
        },"Thread -4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
class Example{
    public void producer(Semaphore lock){
        try{
            lock.acquire();
            System.out.println("Lock acquired by "+Thread.currentThread().getName());
            Thread.sleep(2000);
        }catch (Exception e){
            //
        }finally {
            System.out.println("Lock release by "+Thread.currentThread().getName());
            lock.release();
        }
    }
}