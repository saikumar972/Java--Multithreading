package task;



public class EvenAndOddUsingThread {

    public static void main(String[] args) {
        Object lock=new Object();
        Task task=new Task(lock);
        Thread t1=new Thread(task,"even");
        Thread t2=new Thread(task,"odd");
        t1.start();
        t2.start();
    }
}

class Task implements Runnable{
    private static int count = 1;
    private final Object object;
    public Task(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        while (count <= 10) {
                if (count % 2 == 0 && Thread.currentThread().getName().equals("even")) {
                    synchronized (object) {
                        System.out.println(count + " executed by  " + Thread.currentThread().getName());
                        count++;
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            if (count % 2 != 0 && Thread.currentThread().getName().equals("odd")) {
                synchronized (object) {
                    System.out.println(count + " executed by  " + Thread.currentThread().getName());
                    count++;
                    object.notify();
                }
            }
        }
    }
}