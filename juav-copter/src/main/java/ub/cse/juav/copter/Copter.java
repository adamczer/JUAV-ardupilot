package ub.cse.juav.copter;

import ub.cse.juav.copter.modes.Mode;
import ub.cse.juav.jni.ArdupilotNativeWrapper;
import ub.cse.juav.jni.FijiJniSwitch;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;

public class Copter {
    Map<Integer,Mode> modes;
    public static boolean LOG_TIMING = true;
    FileOutputStream timingLog;
    PrintStream ps;

    public Copter() {
        if (LOG_TIMING) {
            try {
                if (FijiJniSwitch.usingFiji)
                    timingLog = new FileOutputStream("jUAV-fiji-" + System.currentTimeMillis() + ".log");
                else
                    timingLog = new FileOutputStream("jUAV-java-" + System.currentTimeMillis() + ".log");
                ps = new PrintStream(timingLog,true);
            } catch (IOException e) {
                throw new IllegalStateException("metrics logging enabled and could not create log file in working dir.",e);
            }
        }
    }

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
                ArdupilotNativeWrapper.nativeGetLatestGpsReading();
                ps.printf("%s : %d, %d, %.20f, %.20f, %.20f\n",modes.get(mode).getClass().getSimpleName(),time1,time2,ArdupilotNativeWrapper.nativeGetCurrentLatitude(),ArdupilotNativeWrapper.nativeGetCurrentLongitude(),ArdupilotNativeWrapper.nativeGetCurrentAltitude());
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
