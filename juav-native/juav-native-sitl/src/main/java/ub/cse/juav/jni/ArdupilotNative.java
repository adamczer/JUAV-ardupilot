package ub.cse.juav.jni;

import ub.cse.juav.math.JuavQuaternion;

public class ArdupilotNative {
    //HAL SITL
    public static native void nativeInitizationPriorToControlLoop();

    public static native void nativeHalSitlInnerLoopAfterCallBacks();

    public static native boolean getHalSitlSchedulerShouldReboot();

    public static native boolean getHalSitlSchedulerShouldExit();

    public static native void sitlFillStackNan();
    //HAL SITL
    //AP SCHEDULER
    public static native void nativeApSchedulerPriorToFastLoop();

    public static native void nativeApSchedulerPostToFastLoop();

    public static native void setHalUtilPersistentDataSchedulerTask(int i);

    public static native float nativeApSchedulerGetLoopPeriodS();
    //AP SCHEDULER

    //COPTER
    public static native void nativeFastLoopBeforeAttitudeController();

    public static native void surfaceTracingInvalidateForLogging();

    public static native int getFlightModeNumber();

    public static native void callNativeFlightMode();

    public static native void nativeFastLoopAfterAttitudeController();
    //COPTER
    // MODE STABILIZE
    public static native void nativeRunBeforeStabilizationCallAttitudeController();

    public static native void nativeRunAfterStabilizationCallAttitudeController();

    public static native float getStabilizationModeTargetYawRate();

    public static native float getStabilizationModeTargetPitch();

    public static native float getStabilizationModeTargetRoll();
    // MODE STABILIZE
    // AC ATTITUDE CONTROL
    public static native float[] getAttitudeTargetQuat();

    public static native float[] nativeGetAttitudeTargetEulerAngle();

    public static native float[] nativeGetAttitudeTargetEulerRate();

    public static native float[] nativeGetAttitudeTargetAngVel();

    public static native boolean nativeGetRateBfFfEnabled();

    public static native float nativeGetAccelRollMax();

    public static native float nativeGetAccelPitchMax();

    public static native float nativeGetAccelYawMax();

    public static native float nativeGetAttitudeInputTc();

    public static native float nativeAttitudeGetDt();

    public static native float nativeGetAngVelRollMax();

    public static native float nativeGetAngVelPitchMax();

    public static native float nativeGetAngVelYawMax();

    public static native void nativeSetAttitudeTargetQuat(float w, float x, float y, float z);

    public static native void nativeSetAttitudeTargetEulerAngle(float x, float y, float z);

    public static native void nativeSetAttitudeTargetEulerRate(float x, float y, float z);

    public static native void nativeSetAttitudeTargetAngVel(float x, float y, float z);

    public static native float[] nativeGetRateTargetAngVel();

    public static native float nativeGetAttitudeThrustErrorAngle();

    public static native float[] getAhrsGetQuatBodyToNed();

    public static native void nativeSetAttitudeThrustAngle(float newThrustAngle);

    public static native float nativeAttitudeGetPAngleYawKp();

    public static native float nativeAttitudeGetPAngleRollKp();

    public static native float nativeAttitudeGetPAnglePitchKp();

    public static native boolean nativeAttitudeUseSqrtController();

    public static native float[] nativeAttitudeGetAhrsGyro();

    public static native void nativeSetAttitudeFeedForwardScalar(float feedforwardScalar);

    public static native float nativeGetAttitudeFeedForwardScalar();

    public static native void nativeSetAttitudeAngError(float w, float x, float y, float z);

    public static native void nativeAttitudeSetRateTargetAngVel(float x, float y, float z);

    public static native void nativeAttitudeSetThrustErrorAngle(float thisThrustErrorAngle);
    // AC ATTITUDE CONTROL

    // AP VEHICLE
    public static native void nativeSetAPVehicleSchedulerGDt(float f);

    // AP VEHICLE

    // MODE RTL
    public static native boolean nativeModeRtlIsMotorsArmed();

    public static native boolean nativeModeRtlIsStateComplete();
    // MODE RTL

}
