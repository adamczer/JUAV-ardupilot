package ub.cse.juav.jni;

import com.fiji.fivm.r1.GodGiven;
import com.fiji.fivm.r1.Import;

public class HalSitlNativeWrapper {
    //HAL SITL

    public static void nativeInitizationPriorToControlLoop(String arduSrc) {
        if(FijiJniSwitch.usingFiji) {
            HalSitlNative_nativeInitizationPriorToControlLoop();
        } else
            HalSitlNative.nativeInitizationPriorToControlLoop(arduSrc);
    }

    @Import
    @GodGiven
    public static native void HalSitlNative_nativeInitizationPriorToControlLoop();

    public static void nativeHalSitlInnerLoopAfterCallBacks() {
        if(FijiJniSwitch.usingFiji) {
            HalSitlNative_nativeHalSitlInnerLoopAfterCallBacks();
        } else
            HalSitlNative.nativeHalSitlInnerLoopAfterCallBacks();
    }

    @Import
    @GodGiven
    public static native void HalSitlNative_nativeHalSitlInnerLoopAfterCallBacks();

    public static boolean getHalSitlSchedulerShouldReboot() {
        if(FijiJniSwitch.usingFiji) {
            return HalSitlNative_getHalSitlSchedulerShouldReboot();
        } else
            return HalSitlNative.getHalSitlSchedulerShouldReboot();
    }

    @Import
    @GodGiven
    public static native boolean HalSitlNative_getHalSitlSchedulerShouldReboot();

    public static boolean getHalSitlSchedulerShouldExit() {
        if(FijiJniSwitch.usingFiji) {
            return HalSitlNative_getHalSitlSchedulerShouldExit();
        } else
            return HalSitlNative.getHalSitlSchedulerShouldExit();
    }

    @Import
    @GodGiven
    public static native boolean HalSitlNative_getHalSitlSchedulerShouldExit();

    public static void sitlFillStackNan() {
        if(FijiJniSwitch.usingFiji) {
            HalSitlNative_sitlFillStackNan();
        } else
            HalSitlNative.sitlFillStackNan();
    }

    @Import
    @GodGiven
    public static native void HalSitlNative_sitlFillStackNan();
    //HAL SITL
}
