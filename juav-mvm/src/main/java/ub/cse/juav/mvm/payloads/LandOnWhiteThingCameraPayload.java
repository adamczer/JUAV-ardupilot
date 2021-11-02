package ub.cse.juav.mvm.payloads;


import ub.cse.juav.payloads.LandOnWhiteThingRunnable;

public class LandOnWhiteThingCameraPayload {
    public static void main(String[] args) throws InterruptedException {
        System.loadLibrary("NativeUtil");
        System.out.println("Start the LandOnWhiteThingCamera Executions...");
        LandOnWhiteThingRunnable app1 = new LandOnWhiteThingRunnable(true,false);
        app1.run();
        System.out.println("Finish the LandOnWhiteThingCamera Executions...");
    }
}
