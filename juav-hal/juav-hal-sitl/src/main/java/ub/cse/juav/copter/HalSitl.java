package ub.cse.juav.copter;

import ub.cse.juav.copter.modes.*;
import ub.cse.juav.jni.ArdupilotNative;
import ub.cse.juav.jni.HalSitlNative;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HalSitl {
    void run(int argc, String[] argv, List<Callback> callbacks) {
        nativeInitizationPriorToControlLoop();


        while (!getHalSitlSchedulerShouldReboot()){
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
        actuallyReboot();
    }

    private void halSitlInnerLoopAfterCallbacks() {
        HalSitlNative.nativeHalSitlInnerLoopAfterCallBacks();
    }

    private void fillStackNan() {
        HalSitlNative.sitlFillStackNan();
    }

    private void nativeInitizationPriorToControlLoop() {
        String juavSrc = System.getenv("ARDU_SRC");
        HalSitlNative.nativeInitizationPriorToControlLoop(juavSrc);
    }

    private boolean getHalSitlSchedulerShouldReboot() {
        return HalSitlNative.getHalSitlSchedulerShouldReboot();
    }

    private boolean getHalSitlSchedulerShouldExit() {
        return HalSitlNative.getHalSitlSchedulerShouldExit();
    }

    private void actuallyReboot() {
//        actually_reboot
        //todo native
        throw new IllegalStateException("unimplemented");
    }

    public static void main(String[] args) {
        System.loadLibrary("JuavSitlJni");

        AcAttitudeControl acAttitudeControl = new AcAttitudeControl();
        Map<Integer,Mode> modes = new HashMap<>();
        modes.put(0,new ModeStabilize(acAttitudeControl));
        modes.put(5,new ModeLoiter(acAttitudeControl));
        modes.put(4,new ModeGuided(acAttitudeControl));
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
}
