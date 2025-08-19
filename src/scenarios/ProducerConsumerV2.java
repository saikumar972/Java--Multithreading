package scenarios;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerV2 {
    public static void main(String[] args) {
        System.out.println("Main started");
        BlockingQueue<Integer> queue=new ArrayBlockingQueue<>(5);
        Thread t1=new Thread(()->{
            try{
                 for(int i=1;i<=5;i++){
                 queue.put(i);
                 System.out.println("pruducer produced "+i);
                Thread.sleep(100);
            }
            }catch(Exception e){}
           
        });
        Thread t2=new Thread(()->{
            try{
                 for(int i=1;i<=5;i++){
                 int value=queue.take();
                 System.out.println("conumer consumed "+value);
                Thread.sleep(100);
            }
            }catch(Exception e){}
        });
        t1.start();
        t2.start();
    }
}
