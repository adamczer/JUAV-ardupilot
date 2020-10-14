package ub.cse.juav.copter;

import ub.cse.juav.copter.modes.Mode;
import ub.cse.juav.jni.ArdupilotNativeWrapper;
import com.fiji.fivm.r1.fivmRuntime;
import ub.cse.juav.jni.FijiJniSwitch;
import java.util.Map;

public class Copter {
    Map<Integer,Mode> modes;
    public static boolean LOG_TIMING = true;

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
                if (FijiJniSwitch.usingFiji) {
		    fivmRuntime.logPrint(Long.toString(time1));
		    fivmRuntime.logPrint(", ");
		    fivmRuntime.logPrint(Long.toString(time2));
            fivmRuntime.logPrint(", ");
            fivmRuntime.logPrint(Float.toString(ArdupilotNativeWrapper.nativeGetCurrentLatitude()));
            fivmRuntime.logPrint(", ");
            fivmRuntime.logPrint(Float.toString(ArdupilotNativeWrapper.nativeGetCurrentLongitude()));
            fivmRuntime.logPrint(", ");
            fivmRuntime.logPrint(Float.toString(ArdupilotNativeWrapper.nativeGetCurrentAltitude()));
		    fivmRuntime.logPrint("\n");
                } else {
                    System.out.format("%d, %d, %s, %s, %s\n", time1, time2,String.format("%.20f",ArdupilotNativeWrapper.nativeGetCurrentLatitude()),String.format("%.20f",ArdupilotNativeWrapper.nativeGetCurrentLongitude()),String.format("%.20f",ArdupilotNativeWrapper.nativeGetCurrentAltitude()));
                }
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
