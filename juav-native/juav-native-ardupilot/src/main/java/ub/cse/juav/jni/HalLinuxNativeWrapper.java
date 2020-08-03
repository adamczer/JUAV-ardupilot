package ub.cse.juav.jni;

import com.fiji.fivm.r1.GodGiven;
import com.fiji.fivm.r1.Import;

public class HalLinuxNativeWrapper {
    //HAL LINUX
    public static boolean nativeHalLinuxShouldExit() {
        if(FijiJniSwitch.usingFiji) {
            return HalLinuxNative_nativeHalLinuxShouldExit();
        } else
            return HalLinuxNative.nativeHalLinuxShouldExit();
    }

    @Import
    @GodGiven
    public static native boolean HalLinuxNative_nativeHalLinuxShouldExit();

    public static void nativeHalLinuxInitializationPriorToControlLoop() {
        if(FijiJniSwitch.usingFiji) {
            HalLinuxNative_nativeHalLinuxInitializationPriorToControlLoop();
        } else
            HalLinuxNative.nativeHalLinuxInitializationPriorToControlLoop();
    }

    @Import
    @GodGiven
    public static native void HalLinuxNative_nativeHalLinuxInitializationPriorToControlLoop();

    public static void nativeHalLinuxAfterShouldExit() {
        if(FijiJniSwitch.usingFiji) {
            HalLinuxNative_nativeHalLinuxAfterShouldExit();
        } else
            HalLinuxNative.nativeHalLinuxAfterShouldExit();
    }

    @Import
    @GodGiven
    public static native void HalLinuxNative_nativeHalLinuxAfterShouldExit();
    //HAL LINUX
}
