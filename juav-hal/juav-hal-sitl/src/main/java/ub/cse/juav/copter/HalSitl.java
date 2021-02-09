package ub.cse.juav.copter;

import ub.cse.juav.copter.modes.*;
import ub.cse.juav.jni.FijiJniSwitch;
import ub.cse.juav.jni.HalSitlNativeWrapper;

import javax.realtime.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class HalSitl {
    void run(int argc, String[] argv, final List<Callback> callbacks) {
        FileOutputStream memoryLog = null;
        try {
            memoryLog= new FileOutputStream("memoryLog.log");
        } catch (IOException e) {
            e.printStackTrace();
        }
        nativeInitizationPriorToControlLoop();
            while (!getHalSitlSchedulerShouldReboot()) {
//                long heapFreeSize = HeapMemory.instance().memoryRemaining();
//                long heapFreeSize = Runtime.getRuntime().freeMemory();
//                long l = ImmortalMemory.instance().memoryRemaining();
//                try {
//                    memoryLog.write(("heapFree = " + heapFreeSize+"\n").getBytes());
//                    memoryLog.write(("imortalFree = " + l+"\n").getBytes());
//                    memoryLog.flush();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                halLogic(callbacks);
                RealtimeThread.currentRealtimeThread().waitForNextPeriod();
            }
            actuallyReboot();
    }

    private void halLogic(List<Callback> callbacks) {
            if (getHalSitlSchedulerShouldExit()){
                System.out.println("Exiting\n");
                System.exit(0);
            }
            fillStackNan();
            for (Callback c:callbacks) {
                c.loop();
            }

            halSitlInnerLoopAfterCallbacks();

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

    public static void main(final String[] args) {

        RealtimeThread realtimeThread = new RealtimeThread(new Runnable() {
            @Override
            public void run() {

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
                halSitl.run(args.length, args, callbacks);
            }
        });
//
        SchedulingParameters p = new PriorityParameters(99);
        realtimeThread.setReleaseParameters(new PeriodicParameters(new RelativeTime(10,0)));
        realtimeThread.setSchedulingParameters(p);
        realtimeThread.start();

    }
}
