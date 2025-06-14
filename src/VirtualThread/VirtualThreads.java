package VirtualThread;

import java.util.List;
import java.util.stream.IntStream;

public class VirtualThreads {
    public static void main(String[] args) throws InterruptedException {
        List<Thread> list = IntStream.range(0, 10)
                .mapToObj(index -> Thread.ofVirtual().unstarted(() -> {
                            if (index == 0) {
                                System.out.println("VirtualThread 1 " + Thread.currentThread());
                            }
                            //blocking the thread for 1sec
                            try {
                                Thread.sleep(1000);
                            } catch (Exception e) {

                            }
                            //it assign to diff platform thread
                            if (index == 0) {
                                System.out.println("VirtualThread 2 " + Thread.currentThread());
                            }
                        })

                ).toList();
        list.forEach(Thread::start);
        for(Thread thread:list){
            thread.join();
        }
    }
}
