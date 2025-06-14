package threadBasic;

public class SynchronizationExample {
    public static void main(String[] args) {
        ThreadSample t=new ThreadSample();
        Thread t1=new Thread(t::task1);
        Thread t2=new Thread(t::task2);
        Thread t3=new Thread(t::task3);
        t1.start();
        t2.start();
        t3.start();
    }
}
class ThreadSample{
    public synchronized void task1(){
        try{
            System.out.println("Task1 before sleep Synchronized block ");
            Thread.sleep(50000);

            System.out.println("Task1 after sleep Synchronized block ");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void task2(){
        System.out.println("Task2 before Synchronization");
        synchronized(this){
            System.out.println("Task2 in synchronization");
        }
    }
    public void task3(){
        System.out.println("Task 3");
    }
}
