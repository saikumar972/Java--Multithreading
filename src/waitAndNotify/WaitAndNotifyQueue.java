package waitAndNotify;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class WaitAndNotifyQueue {
    public static void main(String[] args) {
        SharedQueue sharedQueue= new SharedQueue(3);
        Thread producerThread=new Thread(()->{
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            for(int i=0;i<8;i++){
                sharedQueue.producer(i);
            }
        },"producerThread");
        Thread consumerThread=new Thread(()->{
            for (int i = 0; i < 8; i++) {
                sharedQueue.consumer();
            }
        });
        producerThread.start();
        consumerThread.start();
    }
}
class SharedQueue{
    Queue<Integer> queue;
    final int capacity;
    public SharedQueue(int capacity) {
        this.queue = new LinkedList<>();
        this.capacity=capacity;
    }
    public synchronized void producer(int k){
        while (queue.size()==capacity){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        queue.offer(k);
        System.out.println("produced "+k);
        notify();
    }
    public synchronized  void consumer(){
        while(queue.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        int value = queue.poll();
        System.out.println("Item gets consumed "+value);
        notify();
    }
}
