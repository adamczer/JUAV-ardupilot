package ub.cse.juav.mvm.payloads;


import ub.cse.juav.payloads.LandOnColorThingRunnable;

public class LandOnColorThingCameraPayload {
    public static void main(String[] args) throws InterruptedException {
        System.loadLibrary("NativeUtil");
        System.out.println("Start the LandOnColorThingCamera Executions...");
        LandOnColorThingRunnable app1 = new LandOnColorThingRunnable(false);
        app1.run();
        System.out.println("Finish the LandOnColorThingCamera Executions...");
    }
}
