package ub.cse.juav.mvm.payloads;

import com.fiji.fivm.ThreadPriority;
import ub.cse.juav.payloads.GreedyFailurePriorityRunnable;
import ub.cse.juav.payloads.GreedyFailureRunnable;

import javax.realtime.*;

public class GreedyPriorityPayload {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Start the GreedyPayload Executions...");
        final GreedyFailurePriorityRunnable app1 = new GreedyFailurePriorityRunnable();
        RealtimeThread rtt = new RealtimeThread() {
            @Override
            public void run() {
                app1.run();
            }
        };
        rtt.start();
        System.out.println("Finish the GreedyPayload Executions...");
    }
}
