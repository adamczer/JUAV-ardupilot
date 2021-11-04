package ub.cse.juav.copter;

import ub.cse.juav.copter.modes.*;
import ub.cse.juav.jni.FijiJniSwitch;
import ub.cse.juav.jni.HalSitlNativeWrapper;
import ub.cse.juav.payloads.DumbyAStarRunnable;
import ub.cse.juav.payloads.DumbyMem;
import ub.cse.juav.payloads.GreedyFailureRunnable;
import ub.cse.juav.payloads.LandOnColorThingRunnable;
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
                    Thread.currentThread().setName("COPTER-THREAD");
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
            Thread.currentThread().setName("COPTER-THREAD");
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
        List<String> argList = Arrays.asList(args);
        if(argList.contains("java"))
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
        if(argList.contains("fiji") ||
           argList.contains("java")) {
           System.out.println("RUNNING PAYLOAD MANAGER -> not using MVM");
           PayloadManager pm = new PayloadManager();
           if (argList.contains("greedy"))
               pm.addPayload(new Payload(new GreedyFailureRunnable(), "greedy"));
           else if (argList.contains("astar"))
               pm.addPayload(new Payload(new DumbyAStarRunnable(500,900,900), "astar"));
           else if (argList.contains("mem"))
               pm.addPayload(new Payload(new DumbyMem(), "mem"));
           else if (argList.contains("color")) {
               System.loadLibrary("NativeUtil");
               pm.addPayload(new Payload(new LandOnColorThingRunnable(true), "LandOnColor"));
           }
           pm.start();
       } else{
           System.out.println("SYSTEM using MVM");
       }
        // ^^ Start payloads using payload manager ie one VM ^^
        halSitl.run(args.length, args, callbacks);
    }
}
