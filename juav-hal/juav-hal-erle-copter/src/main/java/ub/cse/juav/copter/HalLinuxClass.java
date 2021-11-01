package ub.cse.juav.copter;

import ub.cse.juav.copter.modes.*;
import ub.cse.juav.jni.FijiJniSwitch;
import ub.cse.juav.jni.HalLinuxNativeWrapper;
import ub.cse.juav.payloads.DumbyAStarRunnable;
import ub.cse.juav.payloads.manager.Payload;
import ub.cse.juav.payloads.manager.PayloadManager;

import java.util.*;
import javax.realtime.*;

public class HalLinuxClass {
    void run(final int argc, final String[] argv, final List<Callback> callbacks){
        if (FijiJniSwitch.usingFiji) {
            RelativeTime rt = new RelativeTime(0,500);
            RealtimeThread t = new RealtimeThread(new PriorityParameters(99),new PeriodicParameters(rt)){
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
        if(Arrays.asList(args).contains("java"))
            FijiJniSwitch.usingFiji=false;

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


        //Start payloads
//        PayloadManager pm = new PayloadManager();
//        pm.addPayload(new Payload(new Runnable() {
//            @Override
//            public void run() {
//                int count = 0;
//                while(true) {
//                    System.out.println(count++);
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        },"test",null,null,null));
//        pm.start();

        hal.run(args.length, args, callbacks);
    }
}
