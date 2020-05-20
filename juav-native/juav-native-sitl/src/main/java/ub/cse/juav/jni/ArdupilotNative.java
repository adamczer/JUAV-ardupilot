package ub.cse.juav.jni;

public class ArdupilotNative {
    public static native void nativeInitizationPriorToControlLoop();

    public static void main(String[] args) {
        System.load("/home/adamczer/code/juav2/juav-native/juav-native-sitl/jni/lib/libArduCopterSitl.so");
        System.load("/home/adamczer/code/juav2/juav-native/juav-native-sitl/jni/lib/libJuavSitlJni.so");

        ArdupilotNative.nativeInitizationPriorToControlLoop();
    }

    public static void loadSitlLibraries() {
        System.load("/home/adamczer/code/juav2/juav-native/juav-native-sitl/jni/lib/libArduCopterSitl.so");
        System.load("/home/adamczer/code/juav2/juav-native/juav-native-sitl/jni/libJuavSitlJni.so");
    }
}
