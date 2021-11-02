package ub.cse.juav.mvm.payloads;

import ub.cse.juav.payloads.DumbyAStarRunnable;

public class AStarPayload {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Starting AStar");
        DumbyAStarRunnable app1 = new DumbyAStarRunnable(-1,900,900);
        app1.run();
        System.out.println("Finished AStar");
    }
}
