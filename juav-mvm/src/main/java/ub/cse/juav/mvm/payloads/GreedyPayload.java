package ub.cse.juav.mvm.payloads;

import ub.cse.juav.payloads.GreedyFailureRunnable;

public class GreedyPayload{
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Start the GreedyPayload Executions...");
        GreedyFailureRunnable app1 = new GreedyFailureRunnable();
        app1.run();
        System.out.println("Finish the GreedyPayload Executions...");
    }
}
