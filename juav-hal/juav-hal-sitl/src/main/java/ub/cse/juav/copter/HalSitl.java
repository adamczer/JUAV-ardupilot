package ub.cse.juav.copter;

import ub.cse.juav.copter.modes.*;
import ub.cse.juav.jni.FijiJniSwitch;
import ub.cse.juav.jni.HalSitlNativeWrapper;

import java.util.*;

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
        HalSitlNativeWrapper.nativeHalSitlInnerLoopAfterCallBacks();
    }

    private void fillStackNan() {
        HalSitlNativeWrapper.sitlFillStackNan();
    }

    private void nativeInitizationPriorToControlLoop() {
        String juavSrc = System.getenv("ARDU_SRC");
        HalSitlNativeWrapper.nativeInitizationPriorToControlLoop(juavSrc);
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
        if(Arrays.asList(args).contains("fiji"))
            FijiJniSwitch.usingFiji=true;

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
