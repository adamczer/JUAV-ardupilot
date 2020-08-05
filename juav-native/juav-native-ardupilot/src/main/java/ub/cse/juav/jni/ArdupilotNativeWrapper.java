package ub.cse.juav.jni;

import com.fiji.fivm.r1.GodGiven;
import com.fiji.fivm.r1.Import;
import com.fiji.fivm.r1.Pointer;
import com.fiji.fivm.r1.fivmRuntime;

public class ArdupilotNativeWrapper {
    //AP SCHEDULER
    public static void nativeApSchedulerPriorToFastLoop() {
        fivmRuntime.logPrint("nativeApSchedulerPriorToFastLoop");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeApSchedulerPriorToFastLoop();
        } else
            ArdupilotNative.nativeApSchedulerPriorToFastLoop();

    }
    @Import
    @GodGiven
    private static native double ArdupilotNative_nativeApSchedulerPriorToFastLoop();

    public static void nativeApSchedulerPostToFastLoop() {
        fivmRuntime.logPrint("nativeApSchedulerPostToFastLoop");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeApSchedulerPostToFastLoop();
        } else
            ArdupilotNative.nativeApSchedulerPostToFastLoop();
    }

    @Import
    @GodGiven
    private static native double ArdupilotNative_nativeApSchedulerPostToFastLoop();

    public static void setHalUtilPersistentDataSchedulerTask(int i) {
        fivmRuntime.logPrint("setHalUtilPersistentDataSchedulerTask");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_setHalUtilPersistentDataSchedulerTask(i);
        } else
            ArdupilotNative.setHalUtilPersistentDataSchedulerTask(i);
    }

    @Import
    @GodGiven
    private static native double ArdupilotNative_setHalUtilPersistentDataSchedulerTask(int i);

    public static float nativeApSchedulerGetLoopPeriodS() {
        fivmRuntime.logPrint("nativeApSchedulerGetLoopPeriodS");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeApSchedulerGetLoopPeriodS();
        } else
            return ArdupilotNative.nativeApSchedulerGetLoopPeriodS();
    }

    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeApSchedulerGetLoopPeriodS();

    //AP SCHEDULER

    //COPTER
    public static void nativeFastLoopBeforeAttitudeController() {
        fivmRuntime.logPrint("nativeFastLoopBeforeAttitudeController");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeFastLoopBeforeAttitudeController();
        } else
            ArdupilotNative.nativeFastLoopBeforeAttitudeController();
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeFastLoopBeforeAttitudeController();

    public static void surfaceTracingInvalidateForLogging() {
        fivmRuntime.logPrint("surfaceTracingInvalidateForLogging");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_surfaceTracingInvalidateForLogging();
        } else
            ArdupilotNative.surfaceTracingInvalidateForLogging();
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_surfaceTracingInvalidateForLogging();

    public static int getFlightModeNumber() {
        fivmRuntime.logPrint("getFlightModeNumber");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_getFlightModeNumber();
        } else
            return ArdupilotNative.getFlightModeNumber();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_getFlightModeNumber();

    public static void callNativeFlightMode() {
        fivmRuntime.logPrint("callNativeFlightMode");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_callNativeFlightMode();
        } else
            ArdupilotNative.callNativeFlightMode();
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_callNativeFlightMode();

    public static void nativeFastLoopAfterAttitudeController() {
        fivmRuntime.logPrint("nativeFastLoopAfterAttitudeController");
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
        fivmRuntime.logPrint("nativeRunBeforeStabilizationCallAttitudeController");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeRunBeforeStabilizationCallAttitudeController();
        } else
            ArdupilotNative.nativeRunBeforeStabilizationCallAttitudeController();
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeRunBeforeStabilizationCallAttitudeController();

    public static void nativeRunAfterStabilizationCallAttitudeController() {
        fivmRuntime.logPrint("nativeRunAfterStabilizationCallAttitudeController");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeRunAfterStabilizationCallAttitudeController();
        } else
            ArdupilotNative.nativeRunAfterStabilizationCallAttitudeController();
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeRunAfterStabilizationCallAttitudeController();

    public static float getStabilizationModeTargetYawRate() {
        fivmRuntime.logPrint("getStabilizationModeTargetYawRate");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_getStabilizationModeTargetYawRate();
        } else
            return ArdupilotNative.getStabilizationModeTargetYawRate();
    }

    @Import
    @GodGiven
    private static native float ArdupilotNative_getStabilizationModeTargetYawRate();

    public static float getStabilizationModeTargetPitch() {
        fivmRuntime.logPrint("getStabilizationModeTargetPitch");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_getStabilizationModeTargetPitch();
        } else
            return ArdupilotNative.getStabilizationModeTargetPitch();
    }

    @Import
    @GodGiven
    private static native float ArdupilotNative_getStabilizationModeTargetPitch();

    public static float getStabilizationModeTargetRoll() {
        fivmRuntime.logPrint("getStabilizationModeTargetRoll");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_getStabilizationModeTargetRoll();
        } else
            return ArdupilotNative.getStabilizationModeTargetRoll();
    }

    @Import
    @GodGiven
    private static native float ArdupilotNative_getStabilizationModeTargetRoll();


    // MODE STABILIZE
    // AC ATTITUDE CONTROL

    public static float[] getAttitudeTargetQuat() {
        fivmRuntime.logPrint("getAttitudeTargetQuat");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_getAttitudeTargetQuat();
            float[] ret = new float[4];
            ret[0]=ArdupilotNative_getAttitudeTargetQuatW();
            ret[1]=ArdupilotNative_getAttitudeTargetQuatX();
            ret[2]=ArdupilotNative_getAttitudeTargetQuatY();
            ret[3]=ArdupilotNative_getAttitudeTargetQuatZ();
//            fivmRuntime.logPrint("\nTest-valu = "+ArdupilotNative_getAttitudeTargetQuatW()+"\n");
            System.exit(0);
            return ret;
        } else
            return ArdupilotNative.getAttitudeTargetQuat();
    }

    @Import
    @GodGiven
    private static native Pointer ArdupilotNative_getAttitudeTargetQuat();
    @Import
    @GodGiven
    private static native float ArdupilotNative_getAttitudeTargetQuatW();
    @Import
    @GodGiven
    private static native float ArdupilotNative_getAttitudeTargetQuatX();
    @Import
    @GodGiven
    private static native float ArdupilotNative_getAttitudeTargetQuatY();
    @Import
    @GodGiven
    private static native float ArdupilotNative_getAttitudeTargetQuatZ();

    public static float[] nativeGetAttitudeTargetEulerAngle() {
        fivmRuntime.logPrint("nativeGetAttitudeTargetEulerAngle");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeGetAttitudeTargetEulerAngle();
            float[] ret = new float[3];
            ret[0]=ArdupilotNative_nativeGetAttitudeTargetEulerAngleX();
            ret[1]=ArdupilotNative_nativeGetAttitudeTargetEulerAngleY();
            ret[2]=ArdupilotNative_nativeGetAttitudeTargetEulerAngleZ();
            return ret;
        } else
            return ArdupilotNative.nativeGetAttitudeTargetEulerAngle();
    }

    @Import
    @GodGiven
    private static native Pointer ArdupilotNative_nativeGetAttitudeTargetEulerAngle();
    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeGetAttitudeTargetEulerAngleX();
    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeGetAttitudeTargetEulerAngleY();
    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeGetAttitudeTargetEulerAngleZ();

    public static float[] nativeGetAttitudeTargetEulerRate() {
        fivmRuntime.logPrint("nativeGetAttitudeTargetEulerRate");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeGetAttitudeTargetEulerRate();
            float[] ret = new float[3];
            ret[0]=ArdupilotNative_nativeGetAttitudeTargetEulerRateX();
            ret[1]=ArdupilotNative_nativeGetAttitudeTargetEulerRateY();
            ret[2]=ArdupilotNative_nativeGetAttitudeTargetEulerRateZ();
            return ret;
        } else
            return ArdupilotNative.nativeGetAttitudeTargetEulerRate();
    }

    @Import
    @GodGiven
    private static native Pointer ArdupilotNative_nativeGetAttitudeTargetEulerRate();
    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeGetAttitudeTargetEulerRateX();
    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeGetAttitudeTargetEulerRateY();
    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeGetAttitudeTargetEulerRateZ();

    public static float[] nativeGetAttitudeTargetAngVel() {
        fivmRuntime.logPrint("nativeGetAttitudeTargetAngVel");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeGetAttitudeTargetAngVel();
            float[] ret = new float[3];
            ret[0]=ArdupilotNative_nativeGetAttitudeTargetAngVelX();
            ret[1]=ArdupilotNative_nativeGetAttitudeTargetAngVelY();
            ret[2]=ArdupilotNative_nativeGetAttitudeTargetAngVelZ();
            return ret;
        } else
            return ArdupilotNative.nativeGetAttitudeTargetAngVel();
    }

    @Import
    @GodGiven
    private static native Pointer ArdupilotNative_nativeGetAttitudeTargetAngVel();
    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeGetAttitudeTargetAngVelX();
    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeGetAttitudeTargetAngVelY();
    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeGetAttitudeTargetAngVelZ();

    public static boolean nativeGetRateBfFfEnabled() {
        fivmRuntime.logPrint("nativeGetRateBfFfEnabled");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGetRateBfFfEnabled();
        } else
            return ArdupilotNative.nativeGetRateBfFfEnabled();
    }

    @Import
    @GodGiven
    private static native boolean ArdupilotNative_nativeGetRateBfFfEnabled();

    public static float nativeGetAccelRollMax() {
        fivmRuntime.logPrint("nativeGetAccelRollMax");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGetAccelRollMax();
        } else
            return ArdupilotNative.nativeGetAccelRollMax();
    }

    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeGetAccelRollMax();

    public static float nativeGetAccelPitchMax() {
        fivmRuntime.logPrint("nativeGetAccelPitchMax");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGetAccelPitchMax();
        } else
            return ArdupilotNative.nativeGetAccelPitchMax();
    }

    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeGetAccelPitchMax();

    public static float nativeGetAccelYawMax() {
        fivmRuntime.logPrint("nativeGetAccelYawMax");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGetAccelYawMax();
        } else
            return ArdupilotNative.nativeGetAccelYawMax();
    }

    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeGetAccelYawMax();

    public static float nativeGetAttitudeInputTc() {
        fivmRuntime.logPrint("nativeGetAttitudeInputTc");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGetAttitudeInputTc();
        } else
            return ArdupilotNative.nativeGetAttitudeInputTc();
    }

    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeGetAttitudeInputTc();

    public static float nativeAttitudeGetDt() {
        fivmRuntime.logPrint("nativeAttitudeGetDt");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeAttitudeGetDt();
        } else
            return ArdupilotNative.nativeAttitudeGetDt();
    }

    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeAttitudeGetDt();

    public static float nativeGetAngVelRollMax() {
        fivmRuntime.logPrint("nativeGetAngVelRollMax");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGetAngVelRollMax();
        } else
            return ArdupilotNative.nativeGetAngVelRollMax();
    }

    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeGetAngVelRollMax();

    public static float nativeGetAngVelPitchMax() {
        fivmRuntime.logPrint("nativeGetAngVelPitchMax");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGetAngVelPitchMax();
        } else
            return ArdupilotNative.nativeGetAngVelPitchMax();
    }

    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeGetAngVelPitchMax();

    public static float nativeGetAngVelYawMax() {
        fivmRuntime.logPrint("nativeGetAngVelYawMax");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGetAngVelYawMax();
        } else
            return ArdupilotNative.nativeGetAngVelYawMax();
    }

    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeGetAngVelYawMax();

    public static void nativeSetAttitudeTargetQuat(float w, float x, float y, float z) {
        fivmRuntime.logPrint("nativeSetAttitudeTargetQuat");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeSetAttitudeTargetQuat(w, x, y, z);
        } else
            ArdupilotNative.nativeSetAttitudeTargetQuat(w, x, y, z);
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeSetAttitudeTargetQuat(float w, float x, float y, float z);

    public static void nativeSetAttitudeTargetEulerAngle(float x, float y, float z) {
        fivmRuntime.logPrint("nativeSetAttitudeTargetEulerAngle");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeSetAttitudeTargetEulerAngle(x, y, z);
        } else
            ArdupilotNative.nativeSetAttitudeTargetEulerAngle(x, y, z);
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeSetAttitudeTargetEulerAngle(float x, float y, float z);

    public static void nativeSetAttitudeTargetEulerRate(float x, float y, float z) {
        fivmRuntime.logPrint("nativeSetAttitudeTargetEulerRate");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeSetAttitudeTargetEulerRate(x, y, z);
        } else
            ArdupilotNative.nativeSetAttitudeTargetEulerRate(x, y, z);
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeSetAttitudeTargetEulerRate(float x, float y, float z);

    public static void nativeSetAttitudeTargetAngVel(float x, float y, float z) {
        fivmRuntime.logPrint("nativeSetAttitudeTargetAngVel");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeSetAttitudeTargetAngVel(x ,y ,z);
        } else
            ArdupilotNative.nativeSetAttitudeTargetAngVel(x, y, z);
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeSetAttitudeTargetAngVel(float x, float y, float z);

    public static float[] nativeGetRateTargetAngVel() {
        fivmRuntime.logPrint("nativeGetRateTargetAngVel");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeGetRateTargetAngVel();
            float[] ret = new float[3];
            ret[0] = ArdupilotNative_nativeGetRateTargetAngVelX();
            ret[1] = ArdupilotNative_nativeGetRateTargetAngVelY();
            ret[2] = ArdupilotNative_nativeGetRateTargetAngVelZ();
            return ret;
        } else
            return ArdupilotNative.nativeGetRateTargetAngVel();
    }

    @Import
    @GodGiven
    private static native Pointer ArdupilotNative_nativeGetRateTargetAngVel();
    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeGetRateTargetAngVelX();
    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeGetRateTargetAngVelY();
    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeGetRateTargetAngVelZ();

    public static float nativeGetAttitudeThrustErrorAngle() {
        fivmRuntime.logPrint("nativeGetAttitudeThrustErrorAngle");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGetAttitudeThrustErrorAngle();
        } else
            return ArdupilotNative.nativeGetAttitudeThrustErrorAngle();
    }

    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeGetAttitudeThrustErrorAngle();

    public static float[] getAhrsGetQuatBodyToNed() {
        fivmRuntime.logPrint("getAhrsGetQuatBodyToNed");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_getAhrsGetQuatBodyToNed();
            float[] ret = new float[4];
            ret[0] = ArdupilotNative_getAhrsGetQuatBodyToNedW();
            ret[1] = ArdupilotNative_getAhrsGetQuatBodyToNedX();
            ret[2] = ArdupilotNative_getAhrsGetQuatBodyToNedY();
            ret[3] = ArdupilotNative_getAhrsGetQuatBodyToNedZ();
            return ret;
        } else
            return ArdupilotNative.getAhrsGetQuatBodyToNed();
    }

    @Import
    @GodGiven
    private static native Pointer ArdupilotNative_getAhrsGetQuatBodyToNed();
    @Import
    @GodGiven
    private static native float ArdupilotNative_getAhrsGetQuatBodyToNedW();
    @Import
    @GodGiven
    private static native float ArdupilotNative_getAhrsGetQuatBodyToNedX();
    @Import
    @GodGiven
    private static native float ArdupilotNative_getAhrsGetQuatBodyToNedY();
    @Import
    @GodGiven
    private static native float ArdupilotNative_getAhrsGetQuatBodyToNedZ();

    public static void nativeSetAttitudeThrustAngle(float newThrustAngle) {
        fivmRuntime.logPrint("nativeSetAttitudeThrustAngle");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeSetAttitudeThrustAngle(newThrustAngle);
        } else
            ArdupilotNative.nativeSetAttitudeThrustAngle(newThrustAngle);
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeSetAttitudeThrustAngle(float newThrustAngle);

    public static float nativeAttitudeGetPAngleYawKp() {
        fivmRuntime.logPrint("nativeAttitudeGetPAngleYawKp");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeAttitudeGetPAngleYawKp();
        } else
            return ArdupilotNative.nativeAttitudeGetPAngleYawKp();
    }

    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeAttitudeGetPAngleYawKp();

    public static float nativeAttitudeGetPAngleRollKp() {
        fivmRuntime.logPrint("nativeAttitudeGetPAngleRollKp");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeAttitudeGetPAngleRollKp();
        } else
            return ArdupilotNative.nativeAttitudeGetPAngleRollKp();
    }

    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeAttitudeGetPAngleRollKp();

    public static float nativeAttitudeGetPAnglePitchKp() {
        fivmRuntime.logPrint("nativeAttitudeGetPAnglePitchKp");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeAttitudeGetPAnglePitchKp();
        } else
            return ArdupilotNative.nativeAttitudeGetPAnglePitchKp();
    }

    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeAttitudeGetPAnglePitchKp();

    public static boolean nativeAttitudeUseSqrtController() {
        fivmRuntime.logPrint("nativeAttitudeUseSqrtController");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeAttitudeUseSqrtController();
        } else
            return ArdupilotNative.nativeAttitudeUseSqrtController();
    }

    @Import
    @GodGiven
    private static native boolean ArdupilotNative_nativeAttitudeUseSqrtController();

    public static float[] nativeAttitudeGetAhrsGyro() {
        fivmRuntime.logPrint("nativeAttitudeGetAhrsGyro");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeAttitudeGetAhrsGyro();
            float[] ret = new float[3];
            ret[0] = ArdupilotNative_nativeAttitudeGetAhrsGyroX();
            ret[1] = ArdupilotNative_nativeAttitudeGetAhrsGyroY();
            ret[2] = ArdupilotNative_nativeAttitudeGetAhrsGyroZ();
            return ret;
        } else
            return ArdupilotNative.nativeAttitudeGetAhrsGyro();
    }

    @Import
    @GodGiven
    private static native Pointer ArdupilotNative_nativeAttitudeGetAhrsGyro();

    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeAttitudeGetAhrsGyroX();
    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeAttitudeGetAhrsGyroY();
    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeAttitudeGetAhrsGyroZ();

    public static void nativeSetAttitudeFeedForwardScalar(float feedforwardScalar) {
        fivmRuntime.logPrint("nativeSetAttitudeFeedForwardScalar");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeSetAttitudeFeedForwardScalar(feedforwardScalar);
        } else
            ArdupilotNative.nativeSetAttitudeFeedForwardScalar(feedforwardScalar);
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeSetAttitudeFeedForwardScalar(float feedforwardScalar);

    public static float nativeGetAttitudeFeedForwardScalar() {
        fivmRuntime.logPrint("nativeGetAttitudeFeedForwardScalar");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGetAttitudeFeedForwardScalar();
        } else
            return ArdupilotNative.nativeGetAttitudeFeedForwardScalar();
    }

    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeGetAttitudeFeedForwardScalar();

    public static void nativeSetAttitudeAngError(float w, float x, float y, float z) {
        fivmRuntime.logPrint("nativeSetAttitudeAngError");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeSetAttitudeAngError(w, x, y, z);
        } else
            ArdupilotNative.nativeSetAttitudeAngError(w, x, y, z);
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeSetAttitudeAngError(float w, float x, float y, float z);

    public static void nativeAttitudeSetRateTargetAngVel(float x, float y, float z) {
        fivmRuntime.logPrint("nativeAttitudeSetRateTargetAngVel");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeAttitudeSetRateTargetAngVel(x,y,z);
        } else
            ArdupilotNative.nativeAttitudeSetRateTargetAngVel(x, y, z);
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeAttitudeSetRateTargetAngVel(float x, float y, float z);

    public static void nativeAttitudeSetThrustErrorAngle(float thisThrustErrorAngle) {
        fivmRuntime.logPrint("nativeAttitudeSetThrustErrorAngle");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeAttitudeSetThrustErrorAngle(thisThrustErrorAngle);
        } else
            ArdupilotNative.nativeAttitudeSetThrustErrorAngle(thisThrustErrorAngle);
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeAttitudeSetThrustErrorAngle(float thisThrustErrorAngle);

    public static float nativeAttitudeGetSlewYaw() {
        fivmRuntime.logPrint("nativeAttitudeGetSlewYaw");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeAttitudeGetSlewYaw();
        } else
            return ArdupilotNative.nativeAttitudeGetSlewYaw();
    }

    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeAttitudeGetSlewYaw();

    // AC ATTITUDE CONTROL

    // AP VEHICLE

    public static void nativeSetAPVehicleSchedulerGDt(float f) {
        fivmRuntime.logPrint("nativeSetAPVehicleSchedulerGDt");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeSetAPVehicleSchedulerGDt(f);
        } else
            ArdupilotNative.nativeSetAPVehicleSchedulerGDt(f);
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeSetAPVehicleSchedulerGDt(float f);

    // AP VEHICLE

    // MODE RTL

    public static boolean nativeModeRtlIsMotorsArmed() {
        fivmRuntime.logPrint("nativeModeRtlIsMotorsArmed");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeModeRtlIsMotorsArmed();
        } else
            return ArdupilotNative.nativeModeRtlIsMotorsArmed();
    }

    @Import
    @GodGiven
    private static native boolean ArdupilotNative_nativeModeRtlIsMotorsArmed();

    public static boolean nativeModeRtlIsStateComplete() {
        fivmRuntime.logPrint("nativeModeRtlIsStateComplete");
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
        fivmRuntime.logPrint("inputEulerAngleRollPitchEulerRateYaw");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_inputEulerAngleRollPitchEulerRateYaw(eulerRollAngleCd, eulerPitchAngleCd, eulerYawRateCds);
        } else
            ArdupilotNative.inputEulerAngleRollPitchEulerRateYaw(eulerRollAngleCd, eulerPitchAngleCd, eulerYawRateCds);
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_inputEulerAngleRollPitchEulerRateYaw(float eulerRollAngleCd, float eulerPitchAngleCd, float eulerYawRateCds);

    //TEST
    //MODE LOITER
    public static void nativeLoiterRunPriorToAttitudeControl() {
        fivmRuntime.logPrint("nativeLoiterRunPriorToAttitudeControl");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeLoiterRunPriorToAttitudeControl();
        } else
            ArdupilotNative.nativeLoiterRunPriorToAttitudeControl();
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeLoiterRunPriorToAttitudeControl();

    public static void nativeLoiterRunAfterAttitudeControl() {
        fivmRuntime.logPrint("nativeLoiterRunAfterAttitudeControl");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeLoiterRunAfterAttitudeControl();
        } else
            ArdupilotNative.nativeLoiterRunAfterAttitudeControl();
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeLoiterRunAfterAttitudeControl();

    public static float nativeLoiterGetTargetYawRate() {
        fivmRuntime.logPrint("nativeLoiterGetTargetYawRate");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeLoiterGetTargetYawRate();
        } else
            return ArdupilotNative.nativeLoiterGetTargetYawRate();
    }

    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeLoiterGetTargetYawRate();

    public static float nativeLoiterGetTargetPitch() {
        fivmRuntime.logPrint("nativeLoiterGetTargetPitch");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeLoiterGetTargetPitch();
        } else
            return ArdupilotNative.nativeLoiterGetTargetPitch();
    }

    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeLoiterGetTargetPitch();

    public static float nativeLoiterGetTargetRoll() {
        fivmRuntime.logPrint("nativeLoiterGetTargetRoll");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeLoiterGetTargetRoll();
        } else
            return ArdupilotNative.nativeLoiterGetTargetRoll();
    }

    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeLoiterGetTargetRoll();
    //MODE LOITER

    //MODE GUIDED

    public static int nativeGuidedGetMode() {
        fivmRuntime.logPrint("nativeGuidedGetMode");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGuidedGetMode();
        } else
            return ArdupilotNative.nativeGuidedGetMode();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGuidedGetMode();

    public static void nativeGuidedTakeoffRun() {
        fivmRuntime.logPrint("nativeGuidedTakeoffRun");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeGuidedTakeoffRun();
        } else
            ArdupilotNative.nativeGuidedTakeoffRun();
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeGuidedTakeoffRun();

    public static void nativeGuidedPosControlRunPriorToAttitude() {
        fivmRuntime.logPrint("nativeGuidedPosControlRunPriorToAttitude");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeGuidedPosControlRunPriorToAttitude();
        } else
            ArdupilotNative.nativeGuidedPosControlRunPriorToAttitude();
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeGuidedPosControlRunPriorToAttitude();

    public static int nativeGuidedGetAutoYawMode() {
        fivmRuntime.logPrint("nativeGuidedGetAutoYawMode");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGuidedGetAutoYawMode();
        } else
            return ArdupilotNative.nativeGuidedGetAutoYawMode();
    }

    @Import
    @GodGiven
    private static native int ArdupilotNative_nativeGuidedGetAutoYawMode();

    public static float nativeGuidedGetWpNavRoll() {
        fivmRuntime.logPrint("nativeGuidedGetWpNavRoll");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGuidedGetWpNavRoll();
        } else
            return ArdupilotNative.nativeGuidedGetWpNavRoll();
    }

    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeGuidedGetWpNavRoll();

    public static float nativeGuidedGetWpNavPitch() {
        fivmRuntime.logPrint("nativeGuidedGetWpNavPitch");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGuidedGetWpNavPitch();
        } else
            return ArdupilotNative.nativeGuidedGetWpNavPitch();
    }

    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeGuidedGetWpNavPitch();

    public static float nativeGuidedGetTargetYawRate() {
        fivmRuntime.logPrint("nativeGuidedGetTargetYawRate");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGuidedGetTargetYawRate();
        } else
            return ArdupilotNative.nativeGuidedGetTargetYawRate();
    }

    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeGuidedGetTargetYawRate();

    public static float nativeGuidedGetAutoYawRateCds() {
        fivmRuntime.logPrint("nativeGuidedGetAutoYawRateCds");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGuidedGetAutoYawRateCds();
        } else
            return ArdupilotNative.nativeGuidedGetAutoYawRateCds();
    }

    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeGuidedGetAutoYawRateCds();

    public static float nativeGuidedGetAutoYawYaw() {
        fivmRuntime.logPrint("nativeGuidedGetAutoYawYaw");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGuidedGetAutoYawYaw();
        } else
            return ArdupilotNative.nativeGuidedGetAutoYawYaw();
    }

    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeGuidedGetAutoYawYaw();

    public static void nativeGuidedVelControlRunPriorToAttitude() {
        fivmRuntime.logPrint("nativeGuidedVelControlRunPriorToAttitude");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeGuidedVelControlRunPriorToAttitude();
        } else
            ArdupilotNative.nativeGuidedVelControlRunPriorToAttitude();
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeGuidedVelControlRunPriorToAttitude();

    public static float nativeGuidedGetPosControlRoll() {
        fivmRuntime.logPrint("nativeGuidedGetPosControlRoll");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGuidedGetPosControlRoll();
        } else
            return ArdupilotNative.nativeGuidedGetPosControlRoll();
    }

    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeGuidedGetPosControlRoll();

    public static float nativeGuidedGetPosControlPitch() {
        fivmRuntime.logPrint("nativeGuidedGetPosControlPitch");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGuidedGetPosControlPitch();
        } else
            return ArdupilotNative.nativeGuidedGetPosControlPitch();
    }

    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeGuidedGetPosControlPitch();

    public static void nativeGuidedPosVelControlRunPriorToAttitude() {
        fivmRuntime.logPrint("nativeGuidedPosVelControlRunPriorToAttitude");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeGuidedPosVelControlRunPriorToAttitude();
        } else
            ArdupilotNative.nativeGuidedPosVelControlRunPriorToAttitude();
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeGuidedPosVelControlRunPriorToAttitude();

    public static void nativeGuidedAngleControlRunPriorToAttitude() {
        fivmRuntime.logPrint("nativeGuidedAngleControlRunPriorToAttitude");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeGuidedAngleControlRunPriorToAttitude();
        } else
            ArdupilotNative.nativeGuidedAngleControlRunPriorToAttitude();
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeGuidedAngleControlRunPriorToAttitude();

    public static float nativeGuidedGetAngleControlRunRollIn() {
        fivmRuntime.logPrint("nativeGuidedGetAngleControlRunRollIn");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGuidedGetAngleControlRunRollIn();
        } else
            return ArdupilotNative.nativeGuidedGetAngleControlRunRollIn();
    }

    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeGuidedGetAngleControlRunRollIn();

    public static float nativeGuidedGetAngleControlRunPitchIn() {
        fivmRuntime.logPrint("nativeGuidedGetAngleControlRunPitchIn");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGuidedGetAngleControlRunPitchIn();
        } else
            return ArdupilotNative.nativeGuidedGetAngleControlRunPitchIn();
    }

    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeGuidedGetAngleControlRunPitchIn();

    public static boolean nativeGuidedIsAngleStateUseYawRate() {
        fivmRuntime.logPrint("nativeGuidedIsAngleStateUseYawRate");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGuidedIsAngleStateUseYawRate();
        } else
            return ArdupilotNative.nativeGuidedIsAngleStateUseYawRate();
    }

    @Import
    @GodGiven
    private static native boolean ArdupilotNative_nativeGuidedIsAngleStateUseYawRate();

    public static float nativeGuidedGetAngleControlRunYawRateIn() {
        fivmRuntime.logPrint("nativeGuidedGetAngleControlRunYawRateIn");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGuidedGetAngleControlRunYawRateIn();
        } else
            return ArdupilotNative.nativeGuidedGetAngleControlRunYawRateIn();
    }

    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeGuidedGetAngleControlRunYawRateIn();

    public static float nativeGuidedGetAngleControlRunYawIn() {
        fivmRuntime.logPrint("nativeGuidedGetAngleControlRunYawIn");
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGuidedGetAngleControlRunYawIn();
        } else
            return ArdupilotNative.nativeGuidedGetAngleControlRunYawIn();
    }

    @Import
    @GodGiven
    private static native float ArdupilotNative_nativeGuidedGetAngleControlRunYawIn();

    public static void nativeGuidedAngleControlRunAfterAttitude() {
        fivmRuntime.logPrint("nativeGuidedAngleControlRunAfterAttitude");
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeGuidedAngleControlRunAfterAttitude();
        } else
            ArdupilotNative.nativeGuidedAngleControlRunAfterAttitude();
    }

    @Import
    @GodGiven
    private static native void ArdupilotNative_nativeGuidedAngleControlRunAfterAttitude();
    //MODE GUIDED
}
