package ub.cse.juav.jni;

import ub.cse.juav.math.JuavQuaternion;

public class ArdupilotNative {
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

    public static native float nativeAttitudeGetSlewYaw();
    // AC ATTITUDE CONTROL

    // AP VEHICLE
    public static native void nativeSetAPVehicleSchedulerGDt(float f);

    // AP VEHICLE

    // MODE RTL
    public static native boolean nativeModeRtlIsMotorsArmed();

    public static native boolean nativeModeRtlIsStateComplete();

    // MODE RTL

    //TEST
    public static native void runAttitudeControllerQuatTest();

    public static native void inputEulerAngleRollPitchEulerRateYaw(float eulerRollAngleCd, float eulerPitchAngleCd, float eulerYawRateCds);
    //TEST
    //MODE LOITER
    public static native void nativeLoiterRunPriorToAttitudeControl();
    public static native void nativeLoiterRunAfterAttitudeControl();
    public static native float nativeLoiterGetTargetYawRate();
    public static native float nativeLoiterGetTargetPitch();
    public static native float nativeLoiterGetTargetRoll();
    //MODE LOITER

    //MODE GUIDED
    public static native int nativeGuidedGetMode();
    public static native void nativeGuidedTakeoffRun();


    public static native void nativeGuidedPosControlRunPriorToAttitude();
    public static native int nativeGuidedGetAutoYawMode();
    public static native float nativeGetWpNavRoll();
    public static native float nativeGetWpNavPitch();
    public static native float nativeGuidedGetTargetYawRate();
    public static native float nativeGuidedGetAutoYawRateCds();
    public static native float nativeGuidedGetAutoYawYaw();


    public static native void nativeGuidedVelControlRunPriorToAttitude();
    public static native float nativeGuidedGetPosControlRoll();
    public static native float nativeGuidedGetPosControlPitch();

    public static native void nativeGuidedPosVelControlRunPriorToAttitude();

    public static native void nativeGuidedAngleControlRunPriorToAttitude();
    public static native float nativeGuidedGetAngleControlRunRollIn();
    public static native float nativeGuidedGetAngleControlRunPitchIn();
    public static native boolean nativeGuidedIsAngleStateUseYawRate();
    public static native float nativeGuidedGetAngleControlRunYawRateIn();
    public static native float nativeGuidedGetAngleControlRunYawIn();
    public static native void nativeGuidedAngleControlRunAfterAttitude();
    //MODE GUIDED

    //MODE AUTO

    public static native int nativeGetModeAutoMode();
    public static native void nativeAutoModeTakeoffRun();
    public static native void nativeAutoModeLandRun();
    public static native void nativeAutoModeRtlRun();
    public static native void nativeAutoModeNavGuidedRun();
    public static native void nativeAutoModeLoiterToAltRun();

    public static native float nativeAutoModeGetTargetYawRate();
    public static native float nativeAutoModeGetAutoYawYaw();
    public static native void nativeAutoModeWpRunPriorToAttitudeControl();
    public static native int nativeAutoModeGetAutoYawMode();

    public static native void nativeAutoModeCircleRunPriorToAttitude();
    public static native float nativeGetCopterCircleNavRoll();
    public static native float nativeGetCopterCircleNavPitch();
    public static native float nativeGetCopterCircleNavYaw();

    public static native void nativeAutoModeSplineRunPriorToAttitude();

    public static native void nativeAutoModeLoiterRunPriorToAttitude();

    public static native void nativeAutoModePayloadPlaceRun();

    public static native boolean nativeAutoModeIsNavGuidedEnabled();

    //MODE AUTO

    //positional information

    public static native int nativeGetCurrentLongitude();
    public static native int nativeGetCurrentLatitude();
    public static native int nativeGetCurrentAltitude();
    public static native void nativeGetLatestGpsReading();
}
