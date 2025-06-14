package customLocks;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
    public static void main(String[] args) {
        Resource resource1= new Resource();
        Resource resource2=new Resource();
        Resource resource3=new Resource();
        ReadWriteLock lock= new ReentrantReadWriteLock();
        Thread t1=new Thread(()->{
            resource1.producer(lock);
        },"Thread 1");
        Thread t2=new Thread(()->{
            resource2.producer(lock);
        },"Thread 2");
        Thread t3=new Thread(()->{
            resource3.consumer(lock);
        },"Thread 3");
        t1.start();
        t2.start();
        t3.start();
    }
}

class Resource{
    boolean isItemAvailable=false;
    public void producer(ReadWriteLock lock){
        try{
            lock.readLock().lock();
            System.out.println("read lock acquired by "+Thread.currentThread().getName());
            Thread.sleep(2000);
        }catch (Exception e){
            //
        }finally {
            System.out.println("read lock released by "+Thread.currentThread().getName());
            lock.readLock().unlock();
        }
    }
    public void consumer(ReadWriteLock lock){
        try{
            lock.writeLock().lock();
            System.out.println("write lock acquired by "+Thread.currentThread().getName());
            isItemAvailable= true;
            Thread.sleep(2000);
        }catch (Exception e){
            //
        }finally {
            System.out.println("write lock released by "+Thread.currentThread().getName());
            lock.writeLock().unlock();
        }
    }
}
