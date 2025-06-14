package customLocks;
public class SynchronizedProblem {
    public static void main(String[] args) {
        Resources resource1=new Resources();
        Thread t1= new Thread(()->{
            try{
                resource1.method();
            }
            catch (Exception e){

            }
        });
        Resources resource2=new Resources();
        Thread t2= new Thread(()->{
            try{
                resource2.method();
            }
            catch (Exception e){

            }
        });
        t1.start();
        t2.start();
    }
}

class Resources{
    boolean k=false;
    public synchronized void method() throws Exception{
        System.out.println("lock acquired");
        k=true;
        Thread.sleep(2000);
        System.out.println("lock released");
    }
}
