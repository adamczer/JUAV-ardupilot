package ub.cse.juav.jni;

import com.fiji.fivm.r1.GodGiven;
import com.fiji.fivm.r1.Import;
import com.fiji.fivm.r1.Pointer;

import java.nio.ByteBuffer;

public class ArdupilotNativeWrapper {
    private static float floatFromIntWrapping(int i){
        byte[] array = ByteBuffer.allocate(4).putInt(i).array();
        Float f = ByteBuffer.wrap(array).getFloat();
        return f.floatValue();
    }
    private static int floatToIntWrapping(float f){
        byte[] array = ByteBuffer.allocate(4).putFloat(f).array();
        Integer i = ByteBuffer.wrap(array).getInt();
        return i.intValue();
    }
    //AP SCHEDULER
    public static void nativeApSchedulerPriorToFastLoop() {
        //fivmRuntime.logPrint("\nnativeApSchedulerPriorToFastLoop");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeApSchedulerPriorToFastLoop();
        } else
            ArdupilotNative.nativeApSchedulerPriorToFastLoop();

    }
    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeApSchedulerPriorToFastLoop();

    public static void nativeApSchedulerPostToFastLoop() {
        //fivmRuntime.logPrint("\nnativeApSchedulerPostToFastLoop");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeApSchedulerPostToFastLoop();
        } else
            ArdupilotNative.nativeApSchedulerPostToFastLoop();
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeApSchedulerPostToFastLoop();

    public static void setHalUtilPersistentDataSchedulerTask(int i) {
        //fivmRuntime.logPrint("\nsetHalUtilPersistentDataSchedulerTask");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_setHalUtilPersistentDataSchedulerTask(i);
        } else
            ArdupilotNative.setHalUtilPersistentDataSchedulerTask(i);
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_setHalUtilPersistentDataSchedulerTask(int i);

    public static float nativeApSchedulerGetLoopPeriodS() {
        //fivmRuntime.logPrint("\nnativeApSchedulerGetLoopPeriodS");
        if(FijiJniSwitch.usingFiji) {
            return floatFromIntWrapping(ArdupilotNative_nativeApSchedulerGetLoopPeriodS());
        } else
            return ArdupilotNative.nativeApSchedulerGetLoopPeriodS();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeApSchedulerGetLoopPeriodS();

    //AP SCHEDULER

    //COPTER
    public static void nativeFastLoopBeforeAttitudeController() {
        //fivmRuntime.logPrint("\nnativeFastLoopBeforeAttitudeController");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeFastLoopBeforeAttitudeController();
        } else
            ArdupilotNative.nativeFastLoopBeforeAttitudeController();
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeFastLoopBeforeAttitudeController();

    public static void surfaceTracingInvalidateForLogging() {
        //fivmRuntime.logPrint("\nsurfaceTracingInvalidateForLogging");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_surfaceTracingInvalidateForLogging();
        } else
            ArdupilotNative.surfaceTracingInvalidateForLogging();
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_surfaceTracingInvalidateForLogging();

    public static int getFlightModeNumber() {
        //fivmRuntime.logPrint("\ngetFlightModeNumber");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_getFlightModeNumber();
        } else
            return ArdupilotNative.getFlightModeNumber();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_getFlightModeNumber();

    public static void callNativeFlightMode() {
        //fivmRuntime.logPrint("\ncallNativeFlightMode");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_callNativeFlightMode();
        } else
            ArdupilotNative.callNativeFlightMode();
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_callNativeFlightMode();

    public static void nativeFastLoopAfterAttitudeController() {
        //fivmRuntime.logPrint("\nnativeFastLoopAfterAttitudeController");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeFastLoopAfterAttitudeController();
        } else
            ArdupilotNative.nativeFastLoopAfterAttitudeController();
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeFastLoopAfterAttitudeController();

    //COPTER
    // MODE STABILIZE

    public static void nativeRunBeforeStabilizationCallAttitudeController() {
        //fivmRuntime.logPrint("\nnativeRunBeforeStabilizationCallAttitudeController");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeRunBeforeStabilizationCallAttitudeController();
        } else
            ArdupilotNative.nativeRunBeforeStabilizationCallAttitudeController();
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeRunBeforeStabilizationCallAttitudeController();

    public static void nativeRunAfterStabilizationCallAttitudeController() {
        //fivmRuntime.logPrint("\nnativeRunAfterStabilizationCallAttitudeController");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeRunAfterStabilizationCallAttitudeController();
        } else
            ArdupilotNative.nativeRunAfterStabilizationCallAttitudeController();
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeRunAfterStabilizationCallAttitudeController();

    public static float getStabilizationModeTargetYawRate() {
        //fivmRuntime.logPrint("\ngetStabilizationModeTargetYawRate");
        if(FijiJniSwitch.usingFiji) {
            return floatFromIntWrapping(ArdupilotNative_getStabilizationModeTargetYawRate());
        } else
            return ArdupilotNative.getStabilizationModeTargetYawRate();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_getStabilizationModeTargetYawRate();

    public static float getStabilizationModeTargetPitch() {
        //fivmRuntime.logPrint("\ngetStabilizationModeTargetPitch");
        if(FijiJniSwitch.usingFiji) {
            return floatFromIntWrapping(ArdupilotNative_getStabilizationModeTargetPitch());
        } else
            return ArdupilotNative.getStabilizationModeTargetPitch();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_getStabilizationModeTargetPitch();

    public static float getStabilizationModeTargetRoll() {
        //fivmRuntime.logPrint("\ngetStabilizationModeTargetRoll");
        if(FijiJniSwitch.usingFiji) {
            return floatFromIntWrapping(ArdupilotNative_getStabilizationModeTargetRoll());
        } else
            return ArdupilotNative.getStabilizationModeTargetRoll();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_getStabilizationModeTargetRoll();


    // MODE STABILIZE
    // AC ATTITUDE CONTROL
    public static float[] getAttitudeTargetQuat() {
//        fivmRuntime.logPrint("\ngetAttitudeTargetQuat");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_getAttitudeTargetQuat();
            float[] ret = new float[4];
            ret[0]=floatFromIntWrapping(ArdupilotNative_getAttitudeTargetQuatW());
            ret[1]=floatFromIntWrapping(ArdupilotNative_getAttitudeTargetQuatX());
            ret[2]=floatFromIntWrapping(ArdupilotNative_getAttitudeTargetQuatY());
            ret[3]=floatFromIntWrapping(ArdupilotNative_getAttitudeTargetQuatZ());
            return ret;
         } else
            return ArdupilotNative.getAttitudeTargetQuat();
    }

    @Import
    @GodGiven
    private static native Pointer ArdupilotNative_getAttitudeTargetQuat();
    @Import
    @GodGiven
    public static native int ArdupilotNative_getAttitudeTargetQuatW();
    @Import
    @GodGiven
    private static native int ArdupilotNative_getAttitudeTargetQuatX();
    @Import
    @GodGiven
    private static native int ArdupilotNative_getAttitudeTargetQuatY();
    @Import
    @GodGiven
    private static native int ArdupilotNative_getAttitudeTargetQuatZ();

    public static float[] nativeGetAttitudeTargetEulerAngle() {
        //fivmRuntime.logPrint("\nnativeGetAttitudeTargetEulerAngle");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeGetAttitudeTargetEulerAngle();
            float[] ret = new float[3];
            ret[0]=floatFromIntWrapping(ArdupilotNative_nativeGetAttitudeTargetEulerAngleX());
            ret[1]=floatFromIntWrapping(ArdupilotNative_nativeGetAttitudeTargetEulerAngleY());
            ret[2]=floatFromIntWrapping(ArdupilotNative_nativeGetAttitudeTargetEulerAngleZ());
            return ret;
        } else
            return ArdupilotNative.nativeGetAttitudeTargetEulerAngle();
    }

    @Import
    @GodGiven
    private static native Pointer ArdupilotNative_nativeGetAttitudeTargetEulerAngle();
    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGetAttitudeTargetEulerAngleX();
    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGetAttitudeTargetEulerAngleY();
    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGetAttitudeTargetEulerAngleZ();

    public static float[] nativeGetAttitudeTargetEulerRate() {
        //fivmRuntime.logPrint("\nnativeGetAttitudeTargetEulerRate");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeGetAttitudeTargetEulerRate();
            float[] ret = new float[3];
            ret[0]=floatFromIntWrapping(ArdupilotNative_nativeGetAttitudeTargetEulerRateX());
            ret[1]=floatFromIntWrapping(ArdupilotNative_nativeGetAttitudeTargetEulerRateY());
            ret[2]=floatFromIntWrapping(ArdupilotNative_nativeGetAttitudeTargetEulerRateZ());
            return ret;
        } else
            return ArdupilotNative.nativeGetAttitudeTargetEulerRate();
    }

    @Import
    @GodGiven
    private static native Pointer ArdupilotNative_nativeGetAttitudeTargetEulerRate();
    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGetAttitudeTargetEulerRateX();
    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGetAttitudeTargetEulerRateY();
    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGetAttitudeTargetEulerRateZ();

    public static float[] nativeGetAttitudeTargetAngVel() {
        //fivmRuntime.logPrint("\nnativeGetAttitudeTargetAngVel");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeGetAttitudeTargetAngVel();
            float[] ret = new float[3];
            ret[0]=floatFromIntWrapping(ArdupilotNative_nativeGetAttitudeTargetAngVelX());
            ret[1]=floatFromIntWrapping(ArdupilotNative_nativeGetAttitudeTargetAngVelY());
            ret[2]=floatFromIntWrapping(ArdupilotNative_nativeGetAttitudeTargetAngVelZ());
            return ret;
        } else
            return ArdupilotNative.nativeGetAttitudeTargetAngVel();
    }

    @Import
    @GodGiven
    private static native Pointer ArdupilotNative_nativeGetAttitudeTargetAngVel();
    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGetAttitudeTargetAngVelX();
    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGetAttitudeTargetAngVelY();
    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGetAttitudeTargetAngVelZ();

    public static boolean nativeGetRateBfFfEnabled() {
        //fivmRuntime.logPrint("\nnativeGetRateBfFfEnabled");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGetRateBfFfEnabled();
        } else
            return ArdupilotNative.nativeGetRateBfFfEnabled();
    }

    @Import
    @GodGiven
    private static native boolean ArdupilotNative_nativeGetRateBfFfEnabled();

    public static float nativeGetAccelRollMax() {
        //fivmRuntime.logPrint("\nnativeGetAccelRollMax");
        if(FijiJniSwitch.usingFiji) {
            return floatFromIntWrapping(ArdupilotNative_nativeGetAccelRollMax());
        } else
            return ArdupilotNative.nativeGetAccelRollMax();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGetAccelRollMax();

    public static float nativeGetAccelPitchMax() {
        //fivmRuntime.logPrint("\nnativeGetAccelPitchMax");
        if(FijiJniSwitch.usingFiji) {
            return floatFromIntWrapping(ArdupilotNative_nativeGetAccelPitchMax());
        } else
            return ArdupilotNative.nativeGetAccelPitchMax();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGetAccelPitchMax();

    public static float nativeGetAccelYawMax() {
        //fivmRuntime.logPrint("\nnativeGetAccelYawMax");
        if(FijiJniSwitch.usingFiji) {
            return floatFromIntWrapping(ArdupilotNative_nativeGetAccelYawMax());
        } else
            return ArdupilotNative.nativeGetAccelYawMax();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGetAccelYawMax();

    public static float nativeGetAttitudeInputTc() {
        //fivmRuntime.logPrint("\nnativeGetAttitudeInputTc");
        if(FijiJniSwitch.usingFiji) {
            return floatFromIntWrapping(ArdupilotNative_nativeGetAttitudeInputTc());
        } else
            return ArdupilotNative.nativeGetAttitudeInputTc();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGetAttitudeInputTc();

    public static float nativeAttitudeGetDt() {
        //fivmRuntime.logPrint("\nnativeAttitudeGetDt");
        if(FijiJniSwitch.usingFiji) {
            return floatFromIntWrapping(ArdupilotNative_nativeAttitudeGetDt());
        } else
            return ArdupilotNative.nativeAttitudeGetDt();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeAttitudeGetDt();

    public static float nativeGetAngVelRollMax() {
        //fivmRuntime.logPrint("\nnativeGetAngVelRollMax");
        if(FijiJniSwitch.usingFiji) {
            return floatFromIntWrapping(ArdupilotNative_nativeGetAngVelRollMax());
        } else
            return ArdupilotNative.nativeGetAngVelRollMax();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGetAngVelRollMax();

    public static float nativeGetAngVelPitchMax() {
        //fivmRuntime.logPrint("\nnativeGetAngVelPitchMax");
        if(FijiJniSwitch.usingFiji) {
            return floatFromIntWrapping(ArdupilotNative_nativeGetAngVelPitchMax());
        } else
            return ArdupilotNative.nativeGetAngVelPitchMax();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGetAngVelPitchMax();

    public static float nativeGetAngVelYawMax() {
        //fivmRuntime.logPrint("\nnativeGetAngVelYawMax");
        if(FijiJniSwitch.usingFiji) {
            return floatFromIntWrapping(ArdupilotNative_nativeGetAngVelYawMax());
        } else
            return ArdupilotNative.nativeGetAngVelYawMax();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGetAngVelYawMax();

    public static void nativeSetAttitudeTargetQuat(float w, float x, float y, float z) {
        //fivmRuntime.logPrint("\nnativeSetAttitudeTargetQuat");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeSetAttitudeTargetQuat(floatToIntWrapping(w), floatToIntWrapping(x), floatToIntWrapping(y), floatToIntWrapping(z));
        } else
            ArdupilotNative.nativeSetAttitudeTargetQuat(w, x, y, z);
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeSetAttitudeTargetQuat(int w, int x, int y, int z);

    public static void nativeSetAttitudeTargetEulerAngle(float x, float y, float z) {
        //fivmRuntime.logPrint("\nnativeSetAttitudeTargetEulerAngle");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeSetAttitudeTargetEulerAngle(floatToIntWrapping(x), floatToIntWrapping(y), floatToIntWrapping(z));
        } else
            ArdupilotNative.nativeSetAttitudeTargetEulerAngle(x, y, z);
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeSetAttitudeTargetEulerAngle(int x, int y, int z);

    public static void nativeSetAttitudeTargetEulerRate(float x, float y, float z) {
        //fivmRuntime.logPrint("\nnativeSetAttitudeTargetEulerRate");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeSetAttitudeTargetEulerRate(floatToIntWrapping(x), floatToIntWrapping(y), floatToIntWrapping(z));
        } else
            ArdupilotNative.nativeSetAttitudeTargetEulerRate(x, y, z);
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeSetAttitudeTargetEulerRate(int x, int y, int z);

    public static void nativeSetAttitudeTargetAngVel(float x, float y, float z) {
        //fivmRuntime.logPrint("\nnativeSetAttitudeTargetAngVel");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeSetAttitudeTargetAngVel(floatToIntWrapping(x) ,floatToIntWrapping(y) ,floatToIntWrapping(z));
        } else
            ArdupilotNative.nativeSetAttitudeTargetAngVel(x, y, z);
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeSetAttitudeTargetAngVel(int x, int y, int z);

    public static float[] nativeGetRateTargetAngVel() {
        //fivmRuntime.logPrint("\nnativeGetRateTargetAngVel");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeGetRateTargetAngVel();
            float[] ret = new float[3];
            ret[0] = floatFromIntWrapping(ArdupilotNative_nativeGetRateTargetAngVelX());
            ret[1] = floatFromIntWrapping(ArdupilotNative_nativeGetRateTargetAngVelY());
            ret[2] = floatFromIntWrapping(ArdupilotNative_nativeGetRateTargetAngVelZ());
            return ret;
        } else
            return ArdupilotNative.nativeGetRateTargetAngVel();
    }

    @Import
    @GodGiven
    private static native Pointer ArdupilotNative_nativeGetRateTargetAngVel();
    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGetRateTargetAngVelX();
    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGetRateTargetAngVelY();
    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGetRateTargetAngVelZ();

    public static float nativeGetAttitudeThrustErrorAngle() {
        //fivmRuntime.logPrint("\nnativeGetAttitudeThrustErrorAngle");
        if(FijiJniSwitch.usingFiji) {
            return floatFromIntWrapping(ArdupilotNative_nativeGetAttitudeThrustErrorAngle());
        } else
            return ArdupilotNative.nativeGetAttitudeThrustErrorAngle();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGetAttitudeThrustErrorAngle();

    public static float[] getAhrsGetQuatBodyToNed() {
        //fivmRuntime.logPrint("\ngetAhrsGetQuatBodyToNed");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_getAhrsGetQuatBodyToNed();
            float[] ret = new float[4];
            ret[0] = floatFromIntWrapping(ArdupilotNative_getAhrsGetQuatBodyToNedW());
            ret[1] = floatFromIntWrapping(ArdupilotNative_getAhrsGetQuatBodyToNedX());
            ret[2] = floatFromIntWrapping(ArdupilotNative_getAhrsGetQuatBodyToNedY());
            ret[3] = floatFromIntWrapping(ArdupilotNative_getAhrsGetQuatBodyToNedZ());
            return ret;
        } else
            return ArdupilotNative.getAhrsGetQuatBodyToNed();
    }

    @Import
    @GodGiven
    private static native Pointer ArdupilotNative_getAhrsGetQuatBodyToNed();
    @Import
    @GodGiven
    private static native int ArdupilotNative_getAhrsGetQuatBodyToNedW();
    @Import
    @GodGiven
    private static native int ArdupilotNative_getAhrsGetQuatBodyToNedX();
    @Import
    @GodGiven
    private static native int ArdupilotNative_getAhrsGetQuatBodyToNedY();
    @Import
    @GodGiven
    private static native int ArdupilotNative_getAhrsGetQuatBodyToNedZ();

    public static void nativeSetAttitudeThrustAngle(float newThrustAngle) {
        //fivmRuntime.logPrint("\nnativeSetAttitudeThrustAngle");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeSetAttitudeThrustAngle(floatToIntWrapping(newThrustAngle));
        } else
            ArdupilotNative.nativeSetAttitudeThrustAngle(newThrustAngle);
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeSetAttitudeThrustAngle(int newThrustAngle);

    public static float nativeAttitudeGetPAngleYawKp() {
        //fivmRuntime.logPrint("\nnativeAttitudeGetPAngleYawKp");
        if(FijiJniSwitch.usingFiji) {
            return floatFromIntWrapping(ArdupilotNative_nativeAttitudeGetPAngleYawKp());
        } else
            return ArdupilotNative.nativeAttitudeGetPAngleYawKp();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeAttitudeGetPAngleYawKp();

    public static float nativeAttitudeGetPAngleRollKp() {
        //fivmRuntime.logPrint("\nnativeAttitudeGetPAngleRollKp");
        if(FijiJniSwitch.usingFiji) {
            return floatFromIntWrapping(ArdupilotNative_nativeAttitudeGetPAngleRollKp());
        } else
            return ArdupilotNative.nativeAttitudeGetPAngleRollKp();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeAttitudeGetPAngleRollKp();

    public static float nativeAttitudeGetPAnglePitchKp() {
        //fivmRuntime.logPrint("\nnativeAttitudeGetPAnglePitchKp");
        if(FijiJniSwitch.usingFiji) {
            return floatFromIntWrapping(ArdupilotNative_nativeAttitudeGetPAnglePitchKp());
        } else
            return ArdupilotNative.nativeAttitudeGetPAnglePitchKp();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeAttitudeGetPAnglePitchKp();

    public static boolean nativeAttitudeUseSqrtController() {
        //fivmRuntime.logPrint("\nnativeAttitudeUseSqrtController");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeAttitudeUseSqrtController();
        } else
            return ArdupilotNative.nativeAttitudeUseSqrtController();
    }

    @Import
    @GodGiven
    private static native boolean ArdupilotNative_nativeAttitudeUseSqrtController();

    public static float[] nativeAttitudeGetAhrsGyro() {
        //fivmRuntime.logPrint("\nnativeAttitudeGetAhrsGyro");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeAttitudeGetAhrsGyro();
            float[] ret = new float[3];
            ret[0] = floatFromIntWrapping(ArdupilotNative_nativeAttitudeGetAhrsGyroX());
            ret[1] = floatFromIntWrapping(ArdupilotNative_nativeAttitudeGetAhrsGyroY());
            ret[2] = floatFromIntWrapping(ArdupilotNative_nativeAttitudeGetAhrsGyroZ());
            return ret;
        } else
            return ArdupilotNative.nativeAttitudeGetAhrsGyro();
    }

    @Import
    @GodGiven
    private static native Pointer ArdupilotNative_nativeAttitudeGetAhrsGyro();

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeAttitudeGetAhrsGyroX();
    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeAttitudeGetAhrsGyroY();
    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeAttitudeGetAhrsGyroZ();

    public static void nativeSetAttitudeFeedForwardScalar(float feedforwardScalar) {
        //fivmRuntime.logPrint("\nnativeSetAttitudeFeedForwardScalar");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeSetAttitudeFeedForwardScalar(floatToIntWrapping(feedforwardScalar));
        } else
            ArdupilotNative.nativeSetAttitudeFeedForwardScalar(feedforwardScalar);
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeSetAttitudeFeedForwardScalar(int feedforwardScalar);

    public static float nativeGetAttitudeFeedForwardScalar() {
        //fivmRuntime.logPrint("\nnativeGetAttitudeFeedForwardScalar");
        if(FijiJniSwitch.usingFiji) {
            return floatFromIntWrapping(ArdupilotNative_nativeGetAttitudeFeedForwardScalar());
        } else
            return ArdupilotNative.nativeGetAttitudeFeedForwardScalar();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGetAttitudeFeedForwardScalar();

    public static void nativeSetAttitudeAngError(float w, float x, float y, float z) {
        //fivmRuntime.logPrint("\nnativeSetAttitudeAngError");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeSetAttitudeAngError(floatToIntWrapping(w), floatToIntWrapping(x), floatToIntWrapping(y), floatToIntWrapping(z));
        } else
            ArdupilotNative.nativeSetAttitudeAngError(w, x, y, z);
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeSetAttitudeAngError(int w, int x, int y, int z);

    public static void nativeAttitudeSetRateTargetAngVel(float x, float y, float z) {
        //fivmRuntime.logPrint("\nnativeAttitudeSetRateTargetAngVel");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeAttitudeSetRateTargetAngVel(floatToIntWrapping(x),floatToIntWrapping(y),floatToIntWrapping(z));
        } else
            ArdupilotNative.nativeAttitudeSetRateTargetAngVel(x, y, z);
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeAttitudeSetRateTargetAngVel(int x, int y, int z);

    public static void nativeAttitudeSetThrustErrorAngle(float thisThrustErrorAngle) {
        //fivmRuntime.logPrint("\nnativeAttitudeSetThrustErrorAngle");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeAttitudeSetThrustErrorAngle(floatToIntWrapping(thisThrustErrorAngle));
        } else
            ArdupilotNative.nativeAttitudeSetThrustErrorAngle(thisThrustErrorAngle);
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeAttitudeSetThrustErrorAngle(int thisThrustErrorAngle);

    public static float nativeAttitudeGetSlewYaw() {
        //fivmRuntime.logPrint("\nnativeAttitudeGetSlewYaw");
        if(FijiJniSwitch.usingFiji) {
            return floatFromIntWrapping(ArdupilotNative_nativeAttitudeGetSlewYaw());
        } else
            return ArdupilotNative.nativeAttitudeGetSlewYaw();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeAttitudeGetSlewYaw();

    // AC ATTITUDE CONTROL

    // AP VEHICLE

    public static void nativeSetAPVehicleSchedulerGDt(float f) {
        //fivmRuntime.logPrint("\nnativeSetAPVehicleSchedulerGDt");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeSetAPVehicleSchedulerGDt(floatToIntWrapping(f));
        } else
            ArdupilotNative.nativeSetAPVehicleSchedulerGDt(f);
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeSetAPVehicleSchedulerGDt(int f);

    // AP VEHICLE

    // MODE RTL

    public static boolean nativeModeRtlIsMotorsArmed() {
        //fivmRuntime.logPrint("\nnativeModeRtlIsMotorsArmed");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeModeRtlIsMotorsArmed();
        } else
            return ArdupilotNative.nativeModeRtlIsMotorsArmed();
    }

    @Import
    @GodGiven
    private static native boolean ArdupilotNative_nativeModeRtlIsMotorsArmed();

    public static boolean nativeModeRtlIsStateComplete() {
        //fivmRuntime.logPrint("\nnativeModeRtlIsStateComplete");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeModeRtlIsStateComplete();
        } else
            return ArdupilotNative.nativeModeRtlIsStateComplete();
    }

    @Import
    @GodGiven
    private static native boolean ArdupilotNative_nativeModeRtlIsStateComplete();

    // MODE RTL

    //TEST

    public static void runAttitudeControllerQuatTest() {
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_runAttitudeControllerQuatTest();
        } else
            ArdupilotNative.runAttitudeControllerQuatTest();
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_runAttitudeControllerQuatTest();

    public static void inputEulerAngleRollPitchEulerRateYaw(float eulerRollAngleCd, float eulerPitchAngleCd, float eulerYawRateCds) {
        //fivmRuntime.logPrint("\ninputEulerAngleRollPitchEulerRateYaw");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_inputEulerAngleRollPitchEulerRateYaw(floatToIntWrapping(eulerRollAngleCd), floatToIntWrapping(eulerPitchAngleCd), floatToIntWrapping(eulerYawRateCds));
        } else
            ArdupilotNative.inputEulerAngleRollPitchEulerRateYaw(eulerRollAngleCd, eulerPitchAngleCd, eulerYawRateCds);
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_inputEulerAngleRollPitchEulerRateYaw(int eulerRollAngleCd, int eulerPitchAngleCd, int eulerYawRateCds);

    //TEST
    //MODE LOITER
    public static void nativeLoiterRunPriorToAttitudeControl() {
        //fivmRuntime.logPrint("\nnativeLoiterRunPriorToAttitudeControl");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeLoiterRunPriorToAttitudeControl();
        } else
            ArdupilotNative.nativeLoiterRunPriorToAttitudeControl();
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeLoiterRunPriorToAttitudeControl();

    public static void nativeLoiterRunAfterAttitudeControl() {
        //fivmRuntime.logPrint("\nnativeLoiterRunAfterAttitudeControl");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeLoiterRunAfterAttitudeControl();
        } else
            ArdupilotNative.nativeLoiterRunAfterAttitudeControl();
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeLoiterRunAfterAttitudeControl();

    public static float nativeLoiterGetTargetYawRate() {
        //fivmRuntime.logPrint("\nnativeLoiterGetTargetYawRate");
        if(FijiJniSwitch.usingFiji) {
            return floatFromIntWrapping(ArdupilotNative_nativeLoiterGetTargetYawRate());
        } else
            return ArdupilotNative.nativeLoiterGetTargetYawRate();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeLoiterGetTargetYawRate();

    public static float nativeLoiterGetTargetPitch() {
        //fivmRuntime.logPrint("\nnativeLoiterGetTargetPitch");
        if(FijiJniSwitch.usingFiji) {
            return floatFromIntWrapping(ArdupilotNative_nativeLoiterGetTargetPitch());
        } else
            return ArdupilotNative.nativeLoiterGetTargetPitch();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeLoiterGetTargetPitch();

    public static float nativeLoiterGetTargetRoll() {
        //fivmRuntime.logPrint("\nnativeLoiterGetTargetRoll");
        if(FijiJniSwitch.usingFiji) {
            return floatFromIntWrapping(ArdupilotNative_nativeLoiterGetTargetRoll());
        } else
            return ArdupilotNative.nativeLoiterGetTargetRoll();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeLoiterGetTargetRoll();
    //MODE LOITER

    //MODE GUIDED

    public static int nativeGuidedGetMode() {
        //fivmRuntime.logPrint("\nnativeGuidedGetMode");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGuidedGetMode();
        } else
            return ArdupilotNative.nativeGuidedGetMode();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGuidedGetMode();

    public static void nativeGuidedTakeoffRun() {
        //fivmRuntime.logPrint("\nnativeGuidedTakeoffRun");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeGuidedTakeoffRun();
        } else
            ArdupilotNative.nativeGuidedTakeoffRun();
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeGuidedTakeoffRun();

    public static void nativeGuidedPosControlRunPriorToAttitude() {
        //fivmRuntime.logPrint("\nnativeGuidedPosControlRunPriorToAttitude");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeGuidedPosControlRunPriorToAttitude();
        } else
            ArdupilotNative.nativeGuidedPosControlRunPriorToAttitude();
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeGuidedPosControlRunPriorToAttitude();

    public static int nativeGuidedGetAutoYawMode() {
        //fivmRuntime.logPrint("\nnativeGuidedGetAutoYawMode");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGuidedGetAutoYawMode();
        } else
            return ArdupilotNative.nativeGuidedGetAutoYawMode();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGuidedGetAutoYawMode();

    public static float nativeGetWpNavRoll() {
        //fivmRuntime.logPrint("\nnativeGuidedGetWpNavRoll");
        if(FijiJniSwitch.usingFiji) {
            return floatFromIntWrapping(ArdupilotNative_nativeGetWpNavRoll());
        } else
            return ArdupilotNative.nativeGetWpNavRoll();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGetWpNavRoll();

    public static float nativeGetWpNavPitch() {
        //fivmRuntime.logPrint("\nnativeGuidedGetWpNavPitch");
        if(FijiJniSwitch.usingFiji) {
            return floatFromIntWrapping(ArdupilotNative_nativeGetWpNavPitch());
        } else
            return ArdupilotNative.nativeGetWpNavPitch();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGetWpNavPitch();

    public static float nativeGuidedGetTargetYawRate() {
        //fivmRuntime.logPrint("\nnativeGuidedGetTargetYawRate");
        if(FijiJniSwitch.usingFiji) {
            return floatFromIntWrapping(ArdupilotNative_nativeGuidedGetTargetYawRate());
        } else
            return ArdupilotNative.nativeGuidedGetTargetYawRate();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGuidedGetTargetYawRate();

    public static float nativeGuidedGetAutoYawRateCds() {
        //fivmRuntime.logPrint("\nnativeGuidedGetAutoYawRateCds");
        if(FijiJniSwitch.usingFiji) {
            return floatFromIntWrapping(ArdupilotNative_nativeGuidedGetAutoYawRateCds());
        } else
            return ArdupilotNative.nativeGuidedGetAutoYawRateCds();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGuidedGetAutoYawRateCds();

    public static float nativeGuidedGetAutoYawYaw() {
        //fivmRuntime.logPrint("\nnativeGuidedGetAutoYawYaw");
        if(FijiJniSwitch.usingFiji) {
            return floatFromIntWrapping(ArdupilotNative_nativeGuidedGetAutoYawYaw());
        } else
            return ArdupilotNative.nativeGuidedGetAutoYawYaw();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGuidedGetAutoYawYaw();

    public static void nativeGuidedVelControlRunPriorToAttitude() {
        //fivmRuntime.logPrint("\nnativeGuidedVelControlRunPriorToAttitude");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeGuidedVelControlRunPriorToAttitude();
        } else
            ArdupilotNative.nativeGuidedVelControlRunPriorToAttitude();
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeGuidedVelControlRunPriorToAttitude();

    public static float nativeGuidedGetPosControlRoll() {
        //fivmRuntime.logPrint("\nnativeGuidedGetPosControlRoll");
        if(FijiJniSwitch.usingFiji) {
            return floatFromIntWrapping(ArdupilotNative_nativeGuidedGetPosControlRoll());
        } else
            return ArdupilotNative.nativeGuidedGetPosControlRoll();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGuidedGetPosControlRoll();

    public static float nativeGuidedGetPosControlPitch() {
        //fivmRuntime.logPrint("\nnativeGuidedGetPosControlPitch");
        if(FijiJniSwitch.usingFiji) {
            return floatFromIntWrapping(ArdupilotNative_nativeGuidedGetPosControlPitch());
        } else
            return ArdupilotNative.nativeGuidedGetPosControlPitch();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGuidedGetPosControlPitch();

    public static void nativeGuidedPosVelControlRunPriorToAttitude() {
        //fivmRuntime.logPrint("\nnativeGuidedPosVelControlRunPriorToAttitude");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeGuidedPosVelControlRunPriorToAttitude();
        } else
            ArdupilotNative.nativeGuidedPosVelControlRunPriorToAttitude();
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeGuidedPosVelControlRunPriorToAttitude();

    public static void nativeGuidedAngleControlRunPriorToAttitude() {
        //fivmRuntime.logPrint("\nnativeGuidedAngleControlRunPriorToAttitude");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeGuidedAngleControlRunPriorToAttitude();
        } else
            ArdupilotNative.nativeGuidedAngleControlRunPriorToAttitude();
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeGuidedAngleControlRunPriorToAttitude();

    public static float nativeGuidedGetAngleControlRunRollIn() {
        //fivmRuntime.logPrint("\nnativeGuidedGetAngleControlRunRollIn");
        if(FijiJniSwitch.usingFiji) {
            return floatFromIntWrapping(ArdupilotNative_nativeGuidedGetAngleControlRunRollIn());
        } else
            return ArdupilotNative.nativeGuidedGetAngleControlRunRollIn();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGuidedGetAngleControlRunRollIn();

    public static float nativeGuidedGetAngleControlRunPitchIn() {
        //fivmRuntime.logPrint("\nnativeGuidedGetAngleControlRunPitchIn");
        if(FijiJniSwitch.usingFiji) {
            return floatFromIntWrapping(ArdupilotNative_nativeGuidedGetAngleControlRunPitchIn());
        } else
            return ArdupilotNative.nativeGuidedGetAngleControlRunPitchIn();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGuidedGetAngleControlRunPitchIn();

    public static boolean nativeGuidedIsAngleStateUseYawRate() {
        //fivmRuntime.logPrint("\nnativeGuidedIsAngleStateUseYawRate");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGuidedIsAngleStateUseYawRate();
        } else
            return ArdupilotNative.nativeGuidedIsAngleStateUseYawRate();
    }

    @Import
    @GodGiven
    private static native boolean ArdupilotNative_nativeGuidedIsAngleStateUseYawRate();

    public static float nativeGuidedGetAngleControlRunYawRateIn() {
        //fivmRuntime.logPrint("\nnativeGuidedGetAngleControlRunYawRateIn");
        if(FijiJniSwitch.usingFiji) {
            return floatFromIntWrapping(ArdupilotNative_nativeGuidedGetAngleControlRunYawRateIn());
        } else
            return ArdupilotNative.nativeGuidedGetAngleControlRunYawRateIn();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGuidedGetAngleControlRunYawRateIn();

    public static float nativeGuidedGetAngleControlRunYawIn() {
        //fivmRuntime.logPrint("\nnativeGuidedGetAngleControlRunYawIn");
        if(FijiJniSwitch.usingFiji) {
            return floatFromIntWrapping(ArdupilotNative_nativeGuidedGetAngleControlRunYawIn());
        } else
            return ArdupilotNative.nativeGuidedGetAngleControlRunYawIn();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGuidedGetAngleControlRunYawIn();

    public static void nativeGuidedAngleControlRunAfterAttitude() {
        //fivmRuntime.logPrint("\nnativeGuidedAngleControlRunAfterAttitude");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeGuidedAngleControlRunAfterAttitude();
        } else
            ArdupilotNative.nativeGuidedAngleControlRunAfterAttitude();
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeGuidedAngleControlRunAfterAttitude();

    //MODE GUIDED

    //MODE AUTO
    public static int nativeGetModeAutoMode() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGetModeAutoMode();
        } else
           return ArdupilotNative.nativeGetModeAutoMode();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGetModeAutoMode();

    public static void nativeAutoModeTakeoffRun() {                      if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeAutoModeTakeoffRun();
        } else
            ArdupilotNative.nativeAutoModeTakeoffRun();
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeAutoModeTakeoffRun();

    public static void nativeAutoModeLandRun() {
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeAutoModeLandRun();
        } else
            ArdupilotNative.nativeAutoModeLandRun();
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeAutoModeLandRun();

    public static void nativeAutoModeRtlRun() {
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeAutoModeRtlRun();
        } else
            ArdupilotNative.nativeAutoModeRtlRun();
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeAutoModeRtlRun();

    public static void nativeAutoModeNavGuidedRun() {
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeAutoModeNavGuidedRun();
        } else
            ArdupilotNative.nativeAutoModeNavGuidedRun();
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeAutoModeNavGuidedRun();

    public static void nativeAutoModeLoiterToAltRun() {
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeAutoModeLoiterToAltRun();
        } else
            ArdupilotNative.nativeAutoModeLoiterToAltRun();
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeAutoModeLoiterToAltRun();
    //

    public static void modeAutoWpRunPriorToAttitudeControl() {
        if(FijiJniSwitch.usingFiji) {            ArdupilotNative_nativeAutoModeWpRunPriorToAttitudeControl();
        } else
        ArdupilotNative.nativeAutoModeWpRunPriorToAttitudeControl();
    }
    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeAutoModeWpRunPriorToAttitudeControl();

    public static int nativeAutoModeGetAutoYawMode() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeAutoModeGetAutoYawMode();
        } else
        return ArdupilotNative.nativeAutoModeGetAutoYawMode();
    }
    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeAutoModeGetAutoYawMode();

    public static float nativeAutoModeGetTargetYawRate() {
        if(FijiJniSwitch.usingFiji) {
            return floatFromIntWrapping(ArdupilotNative_nativeAutoModeGetTargetYawRate());
        } else
            return ArdupilotNative.nativeAutoModeGetTargetYawRate();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeAutoModeGetTargetYawRate();

    public static float nativeAutoModeGetAutoYawYaw() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeAutoModeGetAutoYawYaw();
        } else
        return ArdupilotNative.nativeAutoModeGetAutoYawYaw();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeAutoModeGetAutoYawYaw();

    public static void nativeAutoModeCircleRunPriorToAttitude() {
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeAutoModeCircleRunPriorToAttitude();
        } else
        ArdupilotNative.nativeAutoModeCircleRunPriorToAttitude();
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeAutoModeCircleRunPriorToAttitude();

    public static float nativeGetCopterCircleNavRoll() {

        if(FijiJniSwitch.usingFiji) {
            return floatFromIntWrapping(ArdupilotNative_nativeGetCopterCircleNavRoll());
        } else
            return ArdupilotNative.nativeGetCopterCircleNavRoll();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGetCopterCircleNavRoll();

    public static float nativeGetCopterCircleNavPitch() {
        if(FijiJniSwitch.usingFiji) {
            return floatFromIntWrapping(ArdupilotNative_nativeGetCopterCircleNavPitch());
        } else
            return ArdupilotNative.nativeGetCopterCircleNavPitch();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGetCopterCircleNavPitch();

    public static float nativeGetCopterCircleNavYaw() {
        if(FijiJniSwitch.usingFiji) {
            return floatFromIntWrapping(ArdupilotNative_nativeGetCopterCircleNavYaw());
        } else
            return ArdupilotNative.nativeGetCopterCircleNavYaw();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGetCopterCircleNavYaw();

    public static void nativeAutoModeSplineRunPriorToAttitude() {
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeAutoModeSplineRunPriorToAttitude();
        } else
        ArdupilotNative.nativeAutoModeSplineRunPriorToAttitude();
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeAutoModeSplineRunPriorToAttitude();

    public static void nativeAutoModeLoiterRunPriorToAttitude() {
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeAutoModeLoiterRunPriorToAttitude();
        } else
            ArdupilotNative.nativeAutoModeLoiterRunPriorToAttitude();
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeAutoModeLoiterRunPriorToAttitude();

    public static void nativeAutoModePayloadPlaceRun() {
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeAutoModePayloadPlaceRun();
        } else
        ArdupilotNative.nativeAutoModePayloadPlaceRun();
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeAutoModePayloadPlaceRun();

    public static boolean nativeAutoModeIsNavGuidedEnabled() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeAutoModeIsNavGuidedEnabled();
    } else
        return ArdupilotNative.nativeAutoModeIsNavGuidedEnabled();
    }

    @Import
    @GodGiven
    private static native boolean ArdupilotNative_nativeAutoModeIsNavGuidedEnabled();

    //MODE AUTO


    //positional information

    public static void nativeGetLatestGpsReading() {
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeGetLatestGpsReading();
        } else
            ArdupilotNative.nativeGetLatestGpsReading();
    }
    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeGetLatestGpsReading();

    public static float nativeGetCurrentLongitude() {
        int ret;
        if(FijiJniSwitch.usingFiji) {
            ret = ArdupilotNative_nativeGetCurrentLongitude();
        } else
            ret = ArdupilotNative.nativeGetCurrentLongitude();
        return floatFromIntWrapping(ret);
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGetCurrentLongitude();

    public static float nativeGetCurrentLatitude() {
        int ret;
        if(FijiJniSwitch.usingFiji) {
            ret = ArdupilotNative_nativeGetCurrentLatitude();
        } else
            ret = ArdupilotNative.nativeGetCurrentLatitude();
        return floatFromIntWrapping(ret);
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGetCurrentLatitude();

    public static float nativeGetCurrentAltitude() {
        int ret;
        if(FijiJniSwitch.usingFiji) {
            ret = ArdupilotNative_nativeGetCurrentAltitude();
        } else
            ret = ArdupilotNative.nativeGetCurrentAltitude();
        return floatFromIntWrapping(ret);
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGetCurrentAltitude();
}