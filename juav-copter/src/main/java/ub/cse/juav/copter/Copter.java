package ub.cse.juav.copter;

import ub.cse.juav.copter.modes.Mode;
import ub.cse.juav.jni.ArdupilotNativeWrapper;

import java.util.Map;

public class Copter {
    Map<Integer,Mode> modes;
    public static boolean LOG_TIMING = false;

    public void setModes(Map<Integer,Mode> modes) {
        this.modes = modes;
    }

    // Main loop - 400hz
    public void fast_loop()
    {
        nativeFastLoop1();

        // run the attitude controllers
        update_flight_mode();

        nativeFastLoop2();
    }

    // update_flight_mode - calls the appropriate attitude controllers based on flight mode
// called at 100hz or more
    void update_flight_mode()
    {
        surfaceTracingInvalidateForLogging();  // invalidate surface tracking alt, flight mode will set to true if used

        int mode = getModeNumber();
        if(modes.containsKey(mode)) {
            long time1 = System.nanoTime();
            modes.get(mode).run();
            long time2 = System.nanoTime();
            if (this.LOG_TIMING) {
                System.out.format("%d, %d\n", time1, time2);
            }
        } else {
            callNativeMode();
        }
    }

    private void callNativeMode() {
        ArdupilotNativeWrapper.callNativeFlightMode();
    }

    private int getModeNumber() {
//        STABILIZE = 0 , RTL = 6, else run is native
        return ArdupilotNativeWrapper.getFlightModeNumber();
    }

    private void surfaceTracingInvalidateForLogging() {
        ArdupilotNativeWrapper.surfaceTracingInvalidateForLogging();
    }

    private void nativeFastLoop1() {
        ArdupilotNativeWrapper.nativeFastLoopBeforeAttitudeController();
    }

    private void nativeFastLoop2() {
        ArdupilotNativeWrapper.nativeFastLoopAfterAttitudeController();
    }
}
