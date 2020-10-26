package ub.cse.juav.copter;

import ub.cse.juav.copter.modes.*;
import ub.cse.juav.jni.FijiJniSwitch;
import ub.cse.juav.jni.HalLinuxNativeWrapper;

import java.util.*;
import javax.realtime.*;

public class HalLinuxClass {
    void run(final int argc, final String[] argv, final List<Callback> callbacks){
        if (FijiJniSwitch.usingFiji) {
            RelativeTime rt = new RelativeTime(Long.parseLong(argv[1]),Integer.parseInt(argv[2]));
            RealtimeThread t = new RealtimeThread(new PriorityParameters(Integer.parseInt(argv[3])),new PeriodicParameters(rt)){
                @Override
                public void run() {
                    halLinuxNativeInitizationPriorToControlLoop(argc, argv);
                    while (!halLinuxShouldExit()) {
                        for (Callback c:callbacks) {
                            c.loop();
                        }
                        currentRealtimeThread().waitForNextPeriod();
                    }
                    halLinuxNativeAfterShouldExit();
                }
            };
            t.start();
        } else {
            halLinuxNativeInitizationPriorToControlLoop(argc, argv);
            while (!halLinuxShouldExit()) {
                for (Callback c : callbacks) {
                    c.loop();
                }
            }
            halLinuxNativeAfterShouldExit();
        }
    }

    private void halLinuxNativeAfterShouldExit() {
        HalLinuxNativeWrapper.nativeHalLinuxAfterShouldExit();
    }

    private boolean halLinuxShouldExit() {
        return HalLinuxNativeWrapper.nativeHalLinuxShouldExit();
    }

    private void halLinuxNativeInitizationPriorToControlLoop(int argc, String[] argv) {
        HalLinuxNativeWrapper.nativeHalLinuxInitializationPriorToControlLoop();
    }


    public static void main(String[] args) {

        System.loadLibrary("JuavErleCopterJni");
        if(Arrays.asList(args).contains("fiji"))
            FijiJniSwitch.usingFiji=true;

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
        HalLinuxClass hal = new HalLinuxClass();
        hal.run(args.length, args, callbacks);
    }
}
