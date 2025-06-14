package threadBasic;

public class ThreadJoin {
    public static void main(String[] args) throws Exception{
        if (Thread.currentThread().getName().equals("main")) {
            System.out.println("This is the main thread!");
        }
        System.out.println(Thread.currentThread().getName());
        Thread t1=new Thread(()->{
            for(int i=0;i<=3;i++){
                System.out.println("t1 "+Thread.currentThread().getId()+"  "+Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread t2=new Thread(()->{
            for(int i=0;i<=3;i++){
                System.out.println("t1 "+Thread.currentThread().getId()+"  "+Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        //in the below code the main thread process last statement also without sync
//        t1.start();
//        t2.start();
//        System.out.println("end");
        //join will make the current thread to wait until the other thread (the one invoked join() is called on) has completed.
        t1.start();
        t1.join();
        t2.start();
        t2.join();
        System.out.println(t1.isAlive());
        System.out.println("main thread is now over "+Thread.currentThread().getName());

    }
}
