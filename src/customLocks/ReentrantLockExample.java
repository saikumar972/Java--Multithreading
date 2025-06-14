package customLocks;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    public static void main(String[] args) {
        SharedResource sharedResource= new SharedResource();ReentrantLock reentrantLock= new ReentrantLock();
        Thread t1= new Thread(()->{
            sharedResource.producer(reentrantLock);
        },"thread1");
        SharedResource sharedResource2= new SharedResource();
        Thread t2= new Thread(()->{
            sharedResource2.producer(reentrantLock);
        },"thread2");
        t1.start();
        t2.start();
    }
}

class SharedResource{
    boolean isItemAvailable=false;
    public  void producer(ReentrantLock reentrantLock){
        try {
            reentrantLock.lock();
            System.out.println("producer acquired lock " + Thread.currentThread().getName());
            isItemAvailable = true;
            Thread.sleep(3000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("producer released lock " + Thread.currentThread().getName());
            reentrantLock.unlock();
        }
    }
}
