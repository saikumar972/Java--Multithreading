package WorkStealingThread;

import java.util.concurrent.*;

class Summing extends RecursiveTask<Integer> {
    int start;
    int end;
    public Summing(int start,int end){
        this.start=start;
        this.end=end;
    }
    @Override
    protected Integer compute() {
        if((end-start)<=4){
            int sum=0;
            for(int i=start;i<=end;i++){
                sum=sum+i;
            }
            return sum;
        }
        else{
            int mid=(start+end)/2;
            Summing leftSum=new Summing(start,mid);
            Summing rightSum=new Summing(mid+1,end);
            leftSum.fork();
            rightSum.fork();
            int leftResult= leftSum.join();
            int rightResult= rightSum.join();
            return leftResult+rightResult;
        }
    }
}
public class WorkStealingThreads {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool= ForkJoinPool.commonPool();
        //ForkJoinTask<Integer> submit = forkJoinPool.submit(new Summing(0, 100));
        Future<Integer> submit2 = forkJoinPool.submit(new Summing(0, 100));
        try{
            System.out.println(submit2.get());
        }catch (Exception e){

        }
    }
}
