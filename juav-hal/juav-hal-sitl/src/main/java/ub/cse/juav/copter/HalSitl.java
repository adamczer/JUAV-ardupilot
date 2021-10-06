package ub.cse.juav.copter;

import ub.cse.juav.copter.modes.*;
import ub.cse.juav.jni.FijiJniSwitch;
import ub.cse.juav.jni.HalSitlNativeWrapper;
import ub.cse.juav.payloads.GreedyFailureRunnable;
import ub.cse.juav.payloads.manager.Payload;
import ub.cse.juav.payloads.manager.PayloadManager;

import javax.realtime.PeriodicParameters;
import javax.realtime.PriorityParameters;
import javax.realtime.RealtimeThread;
import javax.realtime.RelativeTime;
import java.util.*;

public class HalSitl {
    void run(final int argc, final String[] argv, final List<Callback> callbacks) {

        if (FijiJniSwitch.usingFiji) {
            RelativeTime rt = new RelativeTime(0,500);
            RealtimeThread t = new RealtimeThread(new PriorityParameters(99),new PeriodicParameters(rt)){
                @Override
                public void run() {
                    nativeInitizationPriorToControlLoop();
                    while (!getHalSitlSchedulerShouldReboot()) {
                        if (getHalSitlSchedulerShouldExit()) {
                            System.out.println("Exiting\n");
                            System.exit(0);
                        }
                        fillStackNan();
                        for (Callback c : callbacks) {
                            c.loop();
                        }

                        halSitlInnerLoopAfterCallbacks();
                        currentRealtimeThread().waitForNextPeriod();
                    }
                    actuallyReboot();
                }
            };
            t.start();
        } else {
            nativeInitizationPriorToControlLoop();
            while (!getHalSitlSchedulerShouldReboot()) {
                if (getHalSitlSchedulerShouldExit()) {
                    System.out.println("Exiting\n");
                    System.exit(0);
                }
                fillStackNan();
                for (Callback c : callbacks) {
                    c.loop();
                }

                halSitlInnerLoopAfterCallbacks();
            }
            actuallyReboot();
        }
    }

    private void halSitlInnerLoopAfterCallbacks() {
        HalSitlNativeWrapper.nativeHalSitlInnerLoopAfterCallBacks();
    }

    private void fillStackNan() {
        HalSitlNativeWrapper.sitlFillStackNan();
    }

    private void nativeInitizationPriorToControlLoop() {
        String arduSrc = System.getenv("ARDU_SRC");
        HalSitlNativeWrapper.nativeInitizationPriorToControlLoop(arduSrc);
    }

    private boolean getHalSitlSchedulerShouldReboot() {
        return HalSitlNativeWrapper.getHalSitlSchedulerShouldReboot();
    }

    private boolean getHalSitlSchedulerShouldExit() {
        return HalSitlNativeWrapper.getHalSitlSchedulerShouldExit();
    }

    private void actuallyReboot() {
//        actually_reboot
        //todo native
        throw new IllegalStateException("unimplemented");
    }

    public static void main(String[] args) {
        if(Arrays.asList(args).contains("java"))
            FijiJniSwitch.usingFiji=false;

        System.loadLibrary("JuavSitlJni");

        AcAttitudeControl acAttitudeControl = new AcAttitudeControl();
        Map<Integer,Mode> modes = new HashMap<>();
        modes.put(0,new ModeStabilize(acAttitudeControl));
        modes.put(5,new ModeLoiter(acAttitudeControl));
        modes.put(4,new ModeGuided(acAttitudeControl));
        modes.put(3,new ModeAuto(acAttitudeControl));
//        modes.put(6,new ModeRtl(acAttitudeControl)); //broken
        Copter copter = new Copter();
        copter.setModes(modes);
        ApScheduler apScheduler = new ApScheduler();
        apScheduler.setCopter(copter);
        ApVehicle vehicle = new ApVehicle();
        vehicle.setScheduler(apScheduler);
        List<Callback> callbacks = new ArrayList<>();
        callbacks.add(vehicle);
        HalSitl halSitl = new HalSitl();

        // vv Start payloads using payload manager ie one VM vv
       if(!Arrays.asList(args).contains("mvm")) {
           PayloadManager pm = new PayloadManager();
           pm.addPayload(new Payload(new GreedyFailureRunnable(), "greedy"));
           pm.start();
       }
        // ^^ Start payloads using payload manager ie one VM ^^

        // if running multivm (MVM) ensure payload manager disabled.

        halSitl.run(args.length, args, callbacks);
    }
}
