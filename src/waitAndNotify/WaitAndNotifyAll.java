package waitAndNotify;

public class WaitAndNotifyAll {
    public static void main(String[] args) {
        SharedResource sharedResource= new SharedResource();
        Thread producerThread=new Thread(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sharedResource.addItem();
        },"producerThread");
        Thread consumerThread=new Thread(sharedResource::consumeItem,"consumerThread");
        producerThread.start();
        consumerThread.start();
    }
}
class SharedResource{
    boolean itemAvailable=false;
    public synchronized void addItem(){
        System.out.println("Item gets added by "+Thread.currentThread().getName());
        itemAvailable=true;
        notifyAll();
    }
    public synchronized void consumeItem(){
        while(!itemAvailable){
            try {
                System.out.println("consumer is waiting to consume");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("item gets consumed by "+Thread.currentThread().getName());
        itemAvailable=false;
    }
}
