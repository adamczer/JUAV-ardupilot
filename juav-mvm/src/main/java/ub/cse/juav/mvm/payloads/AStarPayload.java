package ub.cse.juav.mvm.payloads;

import ub.cse.juav.payloads.DumbyAStarRunnable;

public class AStarPayload {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting AStar");
        DumbyAStarRunnable app1 = new DumbyAStarRunnable(-1,900,900);
        Thread t = new Thread(app1);
        t.start();
        t.join();
        System.out.println("Finished AStar");
    }
}
