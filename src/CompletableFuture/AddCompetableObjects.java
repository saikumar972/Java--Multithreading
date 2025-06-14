package CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class AddCompetableObjects {
    public static void main(String[] args) {
        AddCompetableObjects obj=new AddCompetableObjects();
        CompletableFuture<String> output1=obj.m1();
        CompletableFuture<String> output2=obj.m3();
        CompletableFuture<String> output3=obj.m2();
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(output1, output2, output3);
        voidCompletableFuture.thenRun(()->{
            String o1=output1.join();
            String o2=output2.join();
            String o3= output3.join();
            System.out.println(o1);
            System.out.println(o3);
            System.out.println(o2);
        }).join();
    }
    public CompletableFuture<String> m1(){
        return CompletableFuture.supplyAsync(()->{
            sleeping(2000);
            return "first call";
        });
    }
    public CompletableFuture<String> m2(){
        return CompletableFuture.supplyAsync(()->{
            sleeping(2000);
            return "second call";
        });
    }
    public CompletableFuture<String> m3(){
        return CompletableFuture.supplyAsync(()->{
            sleeping(2000);
            return "third call";
        });
    }

    public void sleeping(long k)  {
       try {
           Thread.sleep(k);
       }catch (Exception e){

       }
    }
}
