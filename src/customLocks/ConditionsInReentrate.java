package customLocks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class ConditionRelease{
    boolean k= false;
    ReentrantLock lock = new ReentrantLock();
    Condition condition= lock.newCondition();
    public void producer(){
        try {
            lock.lock();
            System.out.println("producer acquired the lock "+Thread.currentThread().getName());
            if(k){
                System.out.println("Producer Waiting for the consumer to consume "+ Thread.currentThread().getName());
                condition.await();
            }
            k=true;
            condition.signal();
        }catch (Exception _){

        }finally {
            lock.unlock();
            System.out.println("Producer released the thread "+Thread.currentThread().getName());
        }
    }
    public void consumer(){
        try {
            Thread.sleep(1000);
            lock.lock();
            System.out.println("Consumer acquired the thread "+Thread.currentThread().getName());
            if(!k){
                System.out.println("Consumer Waiting for the producer to produce");
                condition.await();
            }
            k=false;
            condition.signal();
        }catch (Exception e){

        }finally {
            lock.unlock();
            System.out.println("Consumer released the thread "+ Thread.currentThread().getName());
        }
    }
}
public class ConditionsInReentrate {
    public static void main(String[] args) {
        ConditionRelease c1=new ConditionRelease();
        Thread t1= new Thread(()->{
            for(int i=0;i<2;i++)
                c1.producer();
        });
        Thread t2= new Thread(()->{
            for(int i=0;i<2;i++)
                c1.consumer();
        });
        t1.start();
        t2.start();
    }
}
