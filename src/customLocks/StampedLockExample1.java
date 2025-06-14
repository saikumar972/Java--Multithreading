package customLocks;

import java.util.concurrent.locks.StampedLock;

public class StampedLockExample1 {
    public static void main(String[] args) {
        Test test1= new Test();
        Test test2= new Test();
        Test test3= new Test();
        StampedLock stampedLock=new StampedLock();
        Thread t1= new Thread(()->{
            test1.producer(stampedLock);
        });
        Thread t2= new Thread(()->{
            test2.producer(stampedLock);
        });
        Thread t3= new Thread(()->{
            test3.consumer(stampedLock);
        });
        t1.start();
        t2.start();
        t3.start();
    }
}
class Test{
    public  boolean isItemAvailable=false;
    public void producer(StampedLock lock){
        long stamp=lock.readLock();
        try{
            System.out.println("Read lock acquired by "+Thread.currentThread().getName());
            Thread.sleep(2000);
        }catch (Exception e){
            //
        }finally {
            System.out.println("Read lock released by "+Thread.currentThread().getName());
            lock.unlock(stamp);
        }
    }
    public void consumer(StampedLock lock){
        long stamp=lock.writeLock();
        try{
            System.out.println("Write lock acquired by "+Thread.currentThread().getName());
            isItemAvailable=true;
            Thread.sleep(2000);
        }catch (Exception e){
            //
        }finally {
            System.out.println("Write lock released by "+Thread.currentThread().getName());
            lock.unlock(stamp);
        }
    }
}
