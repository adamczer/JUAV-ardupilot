package ub.cse.juav.mvm.payloads;


import ub.cse.juav.payloads.LandOnWhiteThingRunnable;

public class LandOnWhiteThingPayload {
    public static void main(String[] args) throws InterruptedException {
        System.loadLibrary("NativeUtil");
        System.out.println("Start the LandOnWhiteThing Executions...");
        LandOnWhiteThingRunnable app1 = new LandOnWhiteThingRunnable(true,true);
        app1.run();
        System.out.println("Finish the LandOnWhiteThing Executions...");
    }
}
