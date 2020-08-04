package ub.cse.juav.jni;

import com.fiji.fivm.r1.GodGiven;
import com.fiji.fivm.r1.Import;
import com.fiji.fivm.r1.Pointer;
import com.fiji.fivm.r1.fivmRuntime;

public class HalSitlNativeWrapper {
    //HAL SITL

    public static void nativeInitizationPriorToControlLoop(String arduSrc) {

        if(FijiJniSwitch.usingFiji) {
            Pointer cString = fivmRuntime.getCString(arduSrc);
            HalSitlNative_nativeInitizationPriorToControlLoop(cString);
        } else
            HalSitlNative.nativeInitizationPriorToControlLoop(arduSrc);
    }

    @Import
    @GodGiven
    private static native void HalSitlNative_testArraysSet(Pointer floatArray);
    @Import
    @GodGiven
    private static native Pointer HalSitlNative_testArraysGet(Pointer floatArray);
    @Import
    @GodGiven
    private static native void HalSitlNative_testArraysPrint();

    @Import
    @GodGiven
    private static native void HalSitlNative_nativeInitizationPriorToControlLoop(Pointer arduSrcStr);

    public static void nativeHalSitlInnerLoopAfterCallBacks() {

        if(FijiJniSwitch.usingFiji) {
            HalSitlNative_nativeHalSitlInnerLoopAfterCallBacks();
        } else
            HalSitlNative.nativeHalSitlInnerLoopAfterCallBacks();
    }

    @Import
    @GodGiven
    private static native void HalSitlNative_nativeHalSitlInnerLoopAfterCallBacks();

    public static boolean getHalSitlSchedulerShouldReboot() {

        if(FijiJniSwitch.usingFiji) {
            return HalSitlNative_getHalSitlSchedulerShouldReboot();
        } else
            return HalSitlNative.getHalSitlSchedulerShouldReboot();
    }

    @Import
    @GodGiven
    private static native boolean HalSitlNative_getHalSitlSchedulerShouldReboot();

    public static boolean getHalSitlSchedulerShouldExit() {

        if(FijiJniSwitch.usingFiji) {
            return HalSitlNative_getHalSitlSchedulerShouldExit();
        } else
            return HalSitlNative.getHalSitlSchedulerShouldExit();
    }

    @Import
    @GodGiven
    private static native boolean HalSitlNative_getHalSitlSchedulerShouldExit();

    public static void sitlFillStackNan() {

        if(FijiJniSwitch.usingFiji) {
            HalSitlNative_sitlFillStackNan();
        } else
            HalSitlNative.sitlFillStackNan();
    }

    @Import
    @GodGiven
    private static native void HalSitlNative_sitlFillStackNan();
    //HAL SITL
}
