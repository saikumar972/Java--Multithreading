package threadBasic;

public class ExceptionsInThread{
    public static void main(String[] args) {
        for(int i=0;i<5;i++){
            Thread2 thread2=new Thread2(i);
            thread2.start();
            //thread2.run();
            //Even though exception happens thread will start running the remaining part
            //if we do thread.run() since its executes in main thread so if exception occurs the execution stops
        }
    }
}

 class Thread2 extends Thread{
    //creating constructor
    int threadNumber;
    public  Thread2(int threadNumber){
        this.threadNumber=threadNumber;
    }
    @Override
    public void run(){
        for(int i=0;i<=4;i++){
            System.out.println("Number is "+i+" corresponding thread "+threadNumber);
            if(threadNumber==3){
                throw new RuntimeException();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

