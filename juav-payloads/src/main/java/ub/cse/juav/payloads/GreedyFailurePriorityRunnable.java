package ub.cse.juav.payloads;

import com.fiji.fivm.ThreadPriority;
import ub.cse.juav.jni.FijiJniSwitch;

import javax.realtime.RealtimeThread;

public class GreedyFailurePriorityRunnable implements Runnable{
    private static void doTheSleep(long ms) {
        try {
            System.out.println("sleeping for "+ms);
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        if (FijiJniSwitch.usingFiji)
            RealtimeThread.currentRealtimeThread().setPriority(ThreadPriority.FIFO_MAX-1);
        else
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

        Thread.currentThread().setName("GREEDY-THREAD-HIGH-PRIORITY");
        long sleepMs = 30_000;
        while(true) {
            if (sleepMs>0)
                doTheSleep(sleepMs);
            if(sleepMs > 2)
                sleepMs = sleepMs/2;
            else {
                    System.out.println("no sleep");
                    sleepMs = 0;
                    while (true)
                        try {
                            ;
                        } catch (Throwable t) {}
            }
        }
    }
}
