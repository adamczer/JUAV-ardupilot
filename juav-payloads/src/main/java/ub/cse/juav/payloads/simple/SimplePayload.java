package ub.cse.juav.payloads.simple;

import javax.realtime.RealtimeThread;

public class SimplePayload extends RealtimeThread {
    long duration;

    public SimplePayload(long d) {
        duration = d;
    }

    public void run() {
        //RealtimeThread curThread = RealtimeThread.currentRealtimeThread();
        // set current real time thread priority
        //curThread.setSchedulingParameters(new PriorityParameters(98));
        int cnt = 0;
        while (cnt++ < 10000000) {
            try {
                System.out.println("Wakeup time: " + System.currentTimeMillis());
                RealtimeThread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        System.out.println("Start the SimplePayload Executions...");
        SimplePayload app1 = new SimplePayload(50000000); // Thread will run for 50 ms and records wakeup time
        app1.start();
        System.out.println("Finish the SimplePayload Executions...");
    }
}
