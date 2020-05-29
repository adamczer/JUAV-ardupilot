package ub.cse.juav.jni;

public class ArdupilotNative {
    //HAL SITL
    public static native void nativeInitizationPriorToControlLoop();

    public static native boolean getHalSitlSchedulerShouldReboot();

    public static native boolean getHalSitlSchedulerShouldExit();

    public static native void sitlFillStackNan();
    //HAL SITL
    //AP SCHEDULER
    public static native void nativeApSchedulerPriorToFastLoop();

    public static native void setHalUtilPersistentDataSchedulerTask(int i);
    //AP SCHEDULER

    //COPTER
    public static native void nativeFastLoopBeforeAttitudeController();

    public static native void surfaceTracingInvalidateForLogging();

    public static native int getFlightModeNumber();

    public static native void callNativeFlightMode();

    //COPTER
    // MODE STABILIZE
    public static native void nativeRunBeforeStabilizationCallAttitudeController();

    public static native void nativeRunAfterStabilizationCallAttitudeController();

    public static native float getStabilizationModeTargetYawRate();

    public static native float getStabilizationModeTargetPitch();

    public static native float getStabilizationModeTargetRoll();
    // MODE STABILIZE
}
