package ub.cse.juav.copter;

import ub.cse.juav.copter.modes.Mode;
import ub.cse.juav.jni.ArdupilotNativeWrapper;
import com.fiji.fivm.r1.fivmRuntime;
import ub.cse.juav.jni.FijiJniSwitch;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.lang.String;

public class Copter {
    Map<Integer,Mode> modes;
    public static boolean LOG_TIMING = true;
    FileOutputStream timingLog;

    public Copter() {
        if (LOG_TIMING) {
            try {
                if (FijiJniSwitch.usingFiji)
                    timingLog = new FileOutputStream("jUAV-fiji.log",true);
                else
                    timingLog = new FileOutputStream("jUAV-java.log",true);
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
    float lastLat =0,lastLong=0;
    int flushCount = 0;
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
                float lat = ArdupilotNativeWrapper.nativeGetCurrentLatitude();
                float lon = ArdupilotNativeWrapper.nativeGetCurrentLongitude();
//                if(lastLat!=lat || lastLong !=lon) {
                {
                    lastLong = lon;
                    lastLat = lat;
                    try {
                        timingLog.write((modes.get(mode).getClass().getSimpleName() + ": " +
                                time1 + ", " + time2 + ", " +
                                lat * 1e10 + ", " +
                                lon * 1e10 + ", " +
                                ArdupilotNativeWrapper.nativeGetCurrentAltitude() * 1e10 + "\n").getBytes()
                        );
                        if(flushCount++%100==0)
                            timingLog.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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
