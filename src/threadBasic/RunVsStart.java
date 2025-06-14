package threadBasic;
public class RunVsStart {
    public static void main(String[] args) {
        for(int i=1;i<=5;i++){
            Test test1=new Test();
            test1.setName("Test1-" + i);
            //start prints the threads simultaneously all
            //here there is no linear manner like
            // if 1 prints 5 times any of the thread can be invoked from Thread 1to5
            test1.start();
        }
        for(int i=1;i<=5;i++){
            Test test2=new Test();
            test2.setName("Test2");
            //run executes the run method in Test class
            // and doesn't create a new thread and runs in main thread in linear manner
            //but it can run parallelly with the above threads so we may get undesired result
            test2.run();
        }
    }
}

class Test extends Thread{
    public void run(){
        System.out.println("START " + Thread.currentThread().getName());
        for(int i=1;i<=3;i++){
            System.out.println("  " + i+" "+Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("END " + Thread.currentThread().getName());
    }
}


