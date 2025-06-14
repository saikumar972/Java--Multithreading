package customLocks;

import java.util.concurrent.locks.StampedLock;

public class StampedLockExample2 {
    public static void main(String[] args) {
        Sample sample1= new Sample();
        Sample sample2= new Sample();
        StampedLock stampedLock= new StampedLock();
        Thread t1= new Thread(()->{
            sample1.producer(stampedLock);
        },"Thread-1");
        Thread t2= new Thread(()->{
            sample2.consumer(stampedLock);
        },"Thread-2");
        t1.start();
        t2.start();
    }
}
class Sample{
    int a=10;
    public void producer(StampedLock lock) {
        long stamp = lock.tryOptimisticRead();
        System.out.println("Read lock acquired by " + Thread.currentThread().getName());
        try {
            a = 11;
            Thread.sleep(2000);
            if (lock.validate(stamp)) {
                System.out.println("Change successfully done");
            } else {
                System.out.println("Change went rollback");
                a = 10;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
//        }finally {
//            System.out.println("Read lock released by "+Thread.currentThread().getName());
//            lock.unlock(stamp);
//        }
        }
    }

    public void consumer(StampedLock lock){
        long stamp=lock.writeLock();
        System.out.println("Write lock acquired by "+Thread.currentThread().getName());
        try{
            System.out.println("performing some work");
            a=9;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            System.out.println("Write lock released by "+Thread.currentThread().getName());
            lock.unlockWrite(stamp);
        }
    }
}
