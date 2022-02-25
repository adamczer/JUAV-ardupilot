package ub.cse.juav.mvm.payloads;


import ub.cse.juav.payloads.LandOnColorThingRunnable;

public class LandOnColorThingPayload {
    public static void main(String[] args) throws InterruptedException {
        System.loadLibrary("NativeUtil");
        System.out.println("Start the LandOnColorThing Executions...");
        LandOnColorThingRunnable app1 = new LandOnColorThingRunnable(true);
        app1.run();
        System.out.println("Finish the LandOnColorThing Executions...");
    }
}
