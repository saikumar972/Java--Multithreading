package threadBasic;

public class RaceCondition {
    public static void main(String[] args) throws Exception{
        Counting counting=new Counting();
        Thread t1=new Thread(new Runnable(){
            @Override
            public void run() {
                for (int i=1;i<=1000;i++){
                    counting.counting();
                }
            }
        });
        Thread t2=new Thread(new Runnable(){
            @Override
            public void run() {
                for (int i=1;i<=1000;i++){
                    counting.counting();
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(counting.count);
    }
}
class Counting{
    int count;
    public synchronized  void counting(){
        count++;
    }
}
