package scenarios;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {
    private final Queue<Integer> queue=new LinkedList<>();
    private final int capacity;
    public ProducerConsumer(int capacity){
        this.capacity=capacity;
    }
    public synchronized void produce(int i){
        while(queue.size()==capacity){
           try{
            wait();
        } 
           catch(InterruptedException e){}
        }queue.add(i);
        notifyAll();
    }
    public synchronized int consume(){
        while(queue.isEmpty()){
           try{
            wait();
        } 
           catch(InterruptedException e){}
        }
        int value=queue.poll();
        notifyAll();
        return value;
    }
    public static void main(String[] args) {
        System.out.println("Main started");
        ProducerConsumer producerConsumer=new ProducerConsumer(5);
        Thread t1=new Thread(()->{
            for(int i=1;i<=5;i++){
                producerConsumer.produce(i);
                System.out.println("pruducer produced "+i);
                try{
                Thread.sleep(100);
                }catch(InterruptedException e){}
            }
        });
         Thread t2=new Thread(()->{
            for(int i=1;i<=5;i++){
                int value=producerConsumer.consume();
                System.out.println("cansumed value "+value);
                try{
                Thread.sleep(200);
                }catch(InterruptedException e){}
            }
        });
        t1.start();
        t2.start();
    }
}
