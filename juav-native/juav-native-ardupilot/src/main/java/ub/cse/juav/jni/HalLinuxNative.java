package ub.cse.juav.jni;

public class HalLinuxNative {
    //HAL LINUX
    public static native boolean nativeHalLinuxShouldExit();
    public static native void nativeHalLinuxInitializationPriorToControlLoop(int argc, String[] argv);
    public static native void nativeHalLinuxAfterShouldExit();
    //HAL LINUX
}
