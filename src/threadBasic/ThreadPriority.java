package threadBasic;

public class ThreadPriority {
    public static void main(String[] args) throws Exception{
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1;i<=5;i++){
                    System.out.println(i+" "+Thread.currentThread().getPriority());
                }
            }
        });
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=5;i<=10;i++){
                    System.out.println(i);
                }
            }
        },"Thread : 2");
        //we can initialize the thread names as below
        t1.setName("Thread1");
        //t2.setName("Thread2");
        //we can set the priority
        t1.setPriority(1);
        //default priority is norm i.e., 5 and min - 1 and max: 10
        t2.setPriority(Thread.MAX_PRIORITY);
        System.out.println(t1.getName());
        System.out.println(t2.getName());
        System.out.println(t1.getPriority());
        System.out.println(t2.getPriority());
        System.out.println("--------------------------------------");
        t1.start();
        t1.join();
        t2.start();
        t2.join();
    }
}
