package ub.cse.juav.copter;

import ub.cse.juav.copter.modes.Mode;

import java.util.Map;

public class Copter {
    Map<Integer,Mode> modes;

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
            modes.get(mode).run();
        } else {
            callNativeMode(mode);
        }
    }

    private void callNativeMode(int mode) {
        //TODO native call mode natively if un implemented
        throw new IllegalStateException("unimplemented");
    }

    private int getModeNumber() {
        //todo native get the mode number of the current mode, if it is in java then run locally else deligate to the native
//        STABILIZE = 0 , RTL = 6, else run is native
        throw new IllegalStateException("unimplemented");
    }

    private void surfaceTracingInvalidateForLogging() {
        //todo native surface_tracking.invalidate_for_logging()
        throw new IllegalStateException("unimplemented");
    }

    private void nativeFastLoop1() {
      //todo native calls prior to attitude controlers
        throw new IllegalStateException("unimplemented");
    }

    private void nativeFastLoop2() {
        //todo native calls after attitude controlers
        throw new IllegalStateException("unimplemented");
    }
}
