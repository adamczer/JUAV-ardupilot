package ub.cse.juav.jni;

public class HalLinuxNative {
    //HAL LINUX
    public static native boolean nativeHalLinuxShouldExit();
    public static native void nativeHalLinuxInitializationPriorToControlLoop();
    public static native void nativeHalLinuxAfterShouldExit();
    //HAL LINUX
}
