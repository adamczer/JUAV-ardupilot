package ub.cse.juav.jni;

public class HalSitlNative {
    //HAL SITL
    public static native void nativeInitizationPriorToControlLoop();

    public static native void nativeHalSitlInnerLoopAfterCallBacks();

    public static native boolean getHalSitlSchedulerShouldReboot();

    public static native boolean getHalSitlSchedulerShouldExit();

    public static native void sitlFillStackNan();
    //HAL SITL
}
