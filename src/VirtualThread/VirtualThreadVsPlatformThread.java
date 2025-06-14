package VirtualThread;

import java.util.stream.IntStream;

public class VirtualThreadVsPlatformThread {
    public static void main(String[] args) throws InterruptedException {
        //int start=0;
        int rangeClosed=100000;
        long start=System.currentTimeMillis();
        var virtualThreads=IntStream.range(0,rangeClosed)
                .mapToObj(index->Thread.ofVirtual().unstarted(()->{

                })).toList();
        virtualThreads.forEach(Thread::start);
        for(Thread t:virtualThreads){
            t.join();
        }
        long end=System.currentTimeMillis();
        System.out.println("virtual completed in "+(end-start));
        System.out.println("----------------------");
        int rangeClosed2=100000;
        long start2=System.currentTimeMillis();
        var platformThreads=IntStream.range(0,rangeClosed)
                .mapToObj(index->Thread.ofPlatform().unstarted(()->{

                })).toList();
        platformThreads.forEach(Thread::start);
        for(Thread t:platformThreads){
            t.join();
        }
        long end2=System.currentTimeMillis();
        System.out.println("platform completed in "+(end2-start2));
    }
}
