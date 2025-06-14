package threadBasic;

public class CreatingThreadUsingLambda {
    public static void main(String[] args) {
        Thread t1=new Thread(()->{
            for(int i=0;i<5;i++){
                System.out.println("hi");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread t2=new Thread(()->{
            for(int i=0;i<5;i++){
                System.out.println("hello");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t1.start();
        t2.start();

    }
}
