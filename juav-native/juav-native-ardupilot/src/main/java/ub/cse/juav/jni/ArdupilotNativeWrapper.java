package ub.cse.juav.jni;

import com.fiji.fivm.r1.GodGiven;
import com.fiji.fivm.r1.Import;

public class ArdupilotNativeWrapper {
    //AP SCHEDULER
    public static void nativeApSchedulerPriorToFastLoop() {
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeApSchedulerPriorToFastLoop();
        } else
            ArdupilotNative.nativeApSchedulerPriorToFastLoop();

    }
    @Import
    @GodGiven
    public static native double ArdupilotNative_nativeApSchedulerPriorToFastLoop();

    public static void nativeApSchedulerPostToFastLoop() {
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeApSchedulerPostToFastLoop();
        } else
            ArdupilotNative.nativeApSchedulerPostToFastLoop();
    }

    @Import
    @GodGiven
    public static native double ArdupilotNative_nativeApSchedulerPostToFastLoop();

    public static void setHalUtilPersistentDataSchedulerTask(int i) {
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_setHalUtilPersistentDataSchedulerTask(i);
        } else
            ArdupilotNative.setHalUtilPersistentDataSchedulerTask(i);
    }

    @Import
    @GodGiven
    public static native double ArdupilotNative_setHalUtilPersistentDataSchedulerTask(int i);

    public static float nativeApSchedulerGetLoopPeriodS() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeApSchedulerGetLoopPeriodS();
        } else
            return ArdupilotNative.nativeApSchedulerGetLoopPeriodS();
    }

    @Import
    @GodGiven
    public static native float ArdupilotNative_nativeApSchedulerGetLoopPeriodS();

    //AP SCHEDULER

    //COPTER
    public static void nativeFastLoopBeforeAttitudeController() {
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeFastLoopBeforeAttitudeController();
        } else
            ArdupilotNative.nativeFastLoopBeforeAttitudeController();
    }

    @Import
    @GodGiven
    public static native void ArdupilotNative_nativeFastLoopBeforeAttitudeController();

    public static void surfaceTracingInvalidateForLogging() {
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_surfaceTracingInvalidateForLogging();
        } else
            ArdupilotNative.surfaceTracingInvalidateForLogging();
    }

    @Import
    @GodGiven
    public static native void ArdupilotNative_surfaceTracingInvalidateForLogging();

    public static int getFlightModeNumber() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_getFlightModeNumber();
        } else
            return ArdupilotNative.getFlightModeNumber();
    }

    @Import
    @GodGiven
    public static native int ArdupilotNative_getFlightModeNumber();

    public static void callNativeFlightMode() {
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_callNativeFlightMode();
        } else
            ArdupilotNative.callNativeFlightMode();
    }

    @Import
    @GodGiven
    public static native void ArdupilotNative_callNativeFlightMode();

    public static void nativeFastLoopAfterAttitudeController() {
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeFastLoopAfterAttitudeController();
        } else
            ArdupilotNative.nativeFastLoopAfterAttitudeController();
    }

    @Import
    @GodGiven
    public static native void ArdupilotNative_nativeFastLoopAfterAttitudeController();
    
    //COPTER
    // MODE STABILIZE

    public static void nativeRunBeforeStabilizationCallAttitudeController() {
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeRunBeforeStabilizationCallAttitudeController();
        } else
            ArdupilotNative.nativeRunBeforeStabilizationCallAttitudeController();
    }

    @Import
    @GodGiven
    public static native void ArdupilotNative_nativeRunBeforeStabilizationCallAttitudeController();

    public static void nativeRunAfterStabilizationCallAttitudeController() {
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeRunAfterStabilizationCallAttitudeController();
        } else
            ArdupilotNative.nativeRunAfterStabilizationCallAttitudeController();
    }

    @Import
    @GodGiven
    public static native void ArdupilotNative_nativeRunAfterStabilizationCallAttitudeController();

    public static float getStabilizationModeTargetYawRate() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_getStabilizationModeTargetYawRate();
        } else
            return ArdupilotNative.getStabilizationModeTargetYawRate();
    }

    @Import
    @GodGiven
    public static native float ArdupilotNative_getStabilizationModeTargetYawRate();

    public static float getStabilizationModeTargetPitch() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_getStabilizationModeTargetPitch();
        } else
            return ArdupilotNative.getStabilizationModeTargetPitch();
    }

    @Import
    @GodGiven
    public static native float ArdupilotNative_getStabilizationModeTargetPitch();

    public static float getStabilizationModeTargetRoll() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_getStabilizationModeTargetRoll();
        } else
            return ArdupilotNative.getStabilizationModeTargetRoll();
    }

    @Import
    @GodGiven
    public static native float ArdupilotNative_getStabilizationModeTargetRoll();

    
    // MODE STABILIZE
    // AC ATTITUDE CONTROL

    public static float[] getAttitudeTargetQuat() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_getAttitudeTargetQuat();
        } else
            return ArdupilotNative.getAttitudeTargetQuat();
    }

    @Import
    @GodGiven
    public static native float[] ArdupilotNative_getAttitudeTargetQuat();

    public static float[] nativeGetAttitudeTargetEulerAngle() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGetAttitudeTargetEulerAngle();
        } else
            return ArdupilotNative.nativeGetAttitudeTargetEulerAngle();
    }

    @Import
    @GodGiven
    public static native float[] ArdupilotNative_nativeGetAttitudeTargetEulerAngle();

    public static float[] nativeGetAttitudeTargetEulerRate() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGetAttitudeTargetEulerRate();
        } else
            return ArdupilotNative.nativeGetAttitudeTargetEulerRate();
    }

    @Import
    @GodGiven
    public static native float[] ArdupilotNative_nativeGetAttitudeTargetEulerRate();

    public static float[] nativeGetAttitudeTargetAngVel() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGetAttitudeTargetAngVel();
        } else
            return ArdupilotNative.nativeGetAttitudeTargetAngVel();
    }

    @Import
    @GodGiven
    public static native float[] ArdupilotNative_nativeGetAttitudeTargetAngVel();

    public static boolean nativeGetRateBfFfEnabled() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGetRateBfFfEnabled();
        } else
            return ArdupilotNative.nativeGetRateBfFfEnabled();
    }

    @Import
    @GodGiven
    public static native boolean ArdupilotNative_nativeGetRateBfFfEnabled();

    public static float nativeGetAccelRollMax() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGetAccelRollMax();
        } else
            return ArdupilotNative.nativeGetAccelRollMax();
    }

    @Import
    @GodGiven
    public static native float ArdupilotNative_nativeGetAccelRollMax();

    public static float nativeGetAccelPitchMax() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGetAccelPitchMax();
        } else
            return ArdupilotNative.nativeGetAccelPitchMax();
    }

    @Import
    @GodGiven
    public static native float ArdupilotNative_nativeGetAccelPitchMax();

    public static float nativeGetAccelYawMax() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGetAccelYawMax();
        } else
            return ArdupilotNative.nativeGetAccelYawMax();
    }

    @Import
    @GodGiven
    public static native float ArdupilotNative_nativeGetAccelYawMax();

    public static float nativeGetAttitudeInputTc() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGetAttitudeInputTc();
        } else
            return ArdupilotNative.nativeGetAttitudeInputTc();
    }

    @Import
    @GodGiven
    public static native float ArdupilotNative_nativeGetAttitudeInputTc();

    public static float nativeAttitudeGetDt() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeAttitudeGetDt();
        } else
            return ArdupilotNative.nativeAttitudeGetDt();
    }

    @Import
    @GodGiven
    public static native float ArdupilotNative_nativeAttitudeGetDt();

    public static float nativeGetAngVelRollMax() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGetAngVelRollMax();
        } else
            return ArdupilotNative.nativeGetAngVelRollMax();
    }

    @Import
    @GodGiven
    public static native float ArdupilotNative_nativeGetAngVelRollMax();

    public static float nativeGetAngVelPitchMax() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGetAngVelPitchMax();
        } else
            return ArdupilotNative.nativeGetAngVelPitchMax();
    }

    @Import
    @GodGiven
    public static native float ArdupilotNative_nativeGetAngVelPitchMax();

    public static float nativeGetAngVelYawMax() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGetAngVelYawMax();
        } else
            return ArdupilotNative.nativeGetAngVelYawMax();
    }

    @Import
    @GodGiven
    public static native float ArdupilotNative_nativeGetAngVelYawMax();

    public static void nativeSetAttitudeTargetQuat(float w, float x, float y, float z) {
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeSetAttitudeTargetQuat(w, x, y, z);
        } else
            ArdupilotNative.nativeSetAttitudeTargetQuat(w, x, y, z);
    }

    @Import
    @GodGiven
    public static native void ArdupilotNative_nativeSetAttitudeTargetQuat(float w, float x, float y, float z);

    public static void nativeSetAttitudeTargetEulerAngle(float x, float y, float z) {
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeSetAttitudeTargetEulerAngle(x, y, z);
        } else
            ArdupilotNative.nativeSetAttitudeTargetEulerAngle(x, y, z);
    }

    @Import
    @GodGiven
    public static native void ArdupilotNative_nativeSetAttitudeTargetEulerAngle(float x, float y, float z);

    public static void nativeSetAttitudeTargetEulerRate(float x, float y, float z) {
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeSetAttitudeTargetEulerRate(x, y, z);
        } else
            ArdupilotNative.nativeSetAttitudeTargetEulerRate(x, y, z);
    }

    @Import
    @GodGiven
    public static native void ArdupilotNative_nativeSetAttitudeTargetEulerRate(float x, float y, float z);

    public static void nativeSetAttitudeTargetAngVel(float x, float y, float z) {
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeSetAttitudeTargetAngVel(x ,y ,z);
        } else
            ArdupilotNative.nativeSetAttitudeTargetAngVel(x, y, z);
    }

    @Import
    @GodGiven
    public static native void ArdupilotNative_nativeSetAttitudeTargetAngVel(float x, float y, float z);

    public static float[] nativeGetRateTargetAngVel() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGetRateTargetAngVel();
        } else
            return ArdupilotNative.nativeGetRateTargetAngVel();
    }

    @Import
    @GodGiven
    public static native float[] ArdupilotNative_nativeGetRateTargetAngVel();

    public static float nativeGetAttitudeThrustErrorAngle() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGetAttitudeThrustErrorAngle();
        } else
            return ArdupilotNative.nativeGetAttitudeThrustErrorAngle();
    }

    @Import
    @GodGiven
    public static native float ArdupilotNative_nativeGetAttitudeThrustErrorAngle();

    public static float[] getAhrsGetQuatBodyToNed() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_getAhrsGetQuatBodyToNed();
        } else
            return ArdupilotNative.getAhrsGetQuatBodyToNed();
    }

    @Import
    @GodGiven
    public static native float[] ArdupilotNative_getAhrsGetQuatBodyToNed();

    public static void nativeSetAttitudeThrustAngle(float newThrustAngle) {
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeSetAttitudeThrustAngle(newThrustAngle);
        } else
            ArdupilotNative.nativeSetAttitudeThrustAngle(newThrustAngle);
    }

    @Import
    @GodGiven
    public static native void ArdupilotNative_nativeSetAttitudeThrustAngle(float newThrustAngle);

    public static float nativeAttitudeGetPAngleYawKp() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeAttitudeGetPAngleYawKp();
        } else
            return ArdupilotNative.nativeAttitudeGetPAngleYawKp();
    }

    @Import
    @GodGiven
    public static native float ArdupilotNative_nativeAttitudeGetPAngleYawKp();

    public static float nativeAttitudeGetPAngleRollKp() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeAttitudeGetPAngleRollKp();
        } else
            return ArdupilotNative.nativeAttitudeGetPAngleRollKp();
    }

    @Import
    @GodGiven
    public static native float ArdupilotNative_nativeAttitudeGetPAngleRollKp();

    public static float nativeAttitudeGetPAnglePitchKp() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeAttitudeGetPAnglePitchKp();
        } else
            return ArdupilotNative.nativeAttitudeGetPAnglePitchKp();
    }

    @Import
    @GodGiven
    public static native float ArdupilotNative_nativeAttitudeGetPAnglePitchKp();

    public static boolean nativeAttitudeUseSqrtController() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeAttitudeUseSqrtController();
        } else
            return ArdupilotNative.nativeAttitudeUseSqrtController();
    }

    @Import
    @GodGiven
    public static native boolean ArdupilotNative_nativeAttitudeUseSqrtController();

    public static float[] nativeAttitudeGetAhrsGyro() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeAttitudeGetAhrsGyro();
        } else
            return ArdupilotNative.nativeAttitudeGetAhrsGyro();
    }

    @Import
    @GodGiven
    public static native float[] ArdupilotNative_nativeAttitudeGetAhrsGyro();

    public static void nativeSetAttitudeFeedForwardScalar(float feedforwardScalar) {
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeSetAttitudeFeedForwardScalar(feedforwardScalar);
        } else
            ArdupilotNative.nativeSetAttitudeFeedForwardScalar(feedforwardScalar);
    }

    @Import
    @GodGiven
    public static native void ArdupilotNative_nativeSetAttitudeFeedForwardScalar(float feedforwardScalar);

    public static float nativeGetAttitudeFeedForwardScalar() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGetAttitudeFeedForwardScalar();
        } else
            return ArdupilotNative.nativeGetAttitudeFeedForwardScalar();
    }

    @Import
    @GodGiven
    public static native float ArdupilotNative_nativeGetAttitudeFeedForwardScalar();

    public static void nativeSetAttitudeAngError(float w, float x, float y, float z) {
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeSetAttitudeAngError(w, x, y, z);
        } else
            ArdupilotNative.nativeSetAttitudeAngError(w, x, y, z);
    }

    @Import
    @GodGiven
    public static native void ArdupilotNative_nativeSetAttitudeAngError(float w, float x, float y, float z);

    public static void nativeAttitudeSetRateTargetAngVel(float x, float y, float z) {
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeAttitudeSetRateTargetAngVel(x,y,z);
        } else
            ArdupilotNative.nativeAttitudeSetRateTargetAngVel(x, y, z);
    }

    @Import
    @GodGiven
    public static native void ArdupilotNative_nativeAttitudeSetRateTargetAngVel(float x, float y, float z);

    public static void nativeAttitudeSetThrustErrorAngle(float thisThrustErrorAngle) {
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeAttitudeSetThrustErrorAngle(thisThrustErrorAngle);
        } else
            ArdupilotNative.nativeAttitudeSetThrustErrorAngle(thisThrustErrorAngle);
    }

    @Import
    @GodGiven
    public static native void ArdupilotNative_nativeAttitudeSetThrustErrorAngle(float thisThrustErrorAngle);

    public static float nativeAttitudeGetSlewYaw() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeAttitudeGetSlewYaw();
        } else
            return ArdupilotNative.nativeAttitudeGetSlewYaw();
    }

    @Import
    @GodGiven
    public static native float ArdupilotNative_nativeAttitudeGetSlewYaw();
    
    // AC ATTITUDE CONTROL

    // AP VEHICLE

    public static void nativeSetAPVehicleSchedulerGDt(float f) {
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeSetAPVehicleSchedulerGDt(f);
        } else
            ArdupilotNative.nativeSetAPVehicleSchedulerGDt(f);
    }

    @Import
    @GodGiven
    public static native void ArdupilotNative_nativeSetAPVehicleSchedulerGDt(float f);

    // AP VEHICLE

    // MODE RTL

    public static boolean nativeModeRtlIsMotorsArmed() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeModeRtlIsMotorsArmed();
        } else
            return ArdupilotNative.nativeModeRtlIsMotorsArmed();
    }

    @Import
    @GodGiven
    public static native boolean ArdupilotNative_nativeModeRtlIsMotorsArmed();

    public static boolean nativeModeRtlIsStateComplete() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeModeRtlIsStateComplete();
        } else
            return ArdupilotNative.nativeModeRtlIsStateComplete();
    }

    @Import
    @GodGiven
    public static native boolean ArdupilotNative_nativeModeRtlIsStateComplete();

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
    public static native void ArdupilotNative_runAttitudeControllerQuatTest();

    public static void inputEulerAngleRollPitchEulerRateYaw(float eulerRollAngleCd, float eulerPitchAngleCd, float eulerYawRateCds) {
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_inputEulerAngleRollPitchEulerRateYaw(eulerRollAngleCd, eulerPitchAngleCd, eulerYawRateCds);
        } else
            ArdupilotNative.inputEulerAngleRollPitchEulerRateYaw(eulerRollAngleCd, eulerPitchAngleCd, eulerYawRateCds);
    }

    @Import
    @GodGiven
    public static native void ArdupilotNative_inputEulerAngleRollPitchEulerRateYaw(float eulerRollAngleCd, float eulerPitchAngleCd, float eulerYawRateCds);
    
    //TEST
    //MODE LOITER
    public static void nativeLoiterRunPriorToAttitudeControl() {
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeLoiterRunPriorToAttitudeControl();
        } else
            ArdupilotNative.nativeLoiterRunPriorToAttitudeControl();
    }

    @Import
    @GodGiven
    public static native void ArdupilotNative_nativeLoiterRunPriorToAttitudeControl();

    public static void nativeLoiterRunAfterAttitudeControl() {
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeLoiterRunAfterAttitudeControl();
        } else
            ArdupilotNative.nativeLoiterRunAfterAttitudeControl();
    }

    @Import
    @GodGiven
    public static native void ArdupilotNative_nativeLoiterRunAfterAttitudeControl();

    public static float nativeLoiterGetTargetYawRate() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeLoiterGetTargetYawRate();
        } else
            return ArdupilotNative.nativeLoiterGetTargetYawRate();
    }

    @Import
    @GodGiven
    public static native float ArdupilotNative_nativeLoiterGetTargetYawRate();

    public static float nativeLoiterGetTargetPitch() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeLoiterGetTargetPitch();
        } else
            return ArdupilotNative.nativeLoiterGetTargetPitch();
    }

    @Import
    @GodGiven
    public static native float ArdupilotNative_nativeLoiterGetTargetPitch();

    public static float nativeLoiterGetTargetRoll() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeLoiterGetTargetRoll();
        } else
            return ArdupilotNative.nativeLoiterGetTargetRoll();
    }

    @Import
    @GodGiven
    public static native float ArdupilotNative_nativeLoiterGetTargetRoll();
    //MODE LOITER

    //MODE GUIDED

    public static int nativeGuidedGetMode() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGuidedGetMode();
        } else
            return ArdupilotNative.nativeGuidedGetMode();
    }

    @Import
    @GodGiven
    public static native int ArdupilotNative_nativeGuidedGetMode();

    public static void nativeGuidedTakeoffRun() {
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeGuidedTakeoffRun();
        } else
            ArdupilotNative.nativeGuidedTakeoffRun();
    }

    @Import
    @GodGiven
    public static native void ArdupilotNative_nativeGuidedTakeoffRun();

    public static void nativeGuidedPosControlRunPriorToAttitude() {
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeGuidedPosControlRunPriorToAttitude();
        } else
            ArdupilotNative.nativeGuidedPosControlRunPriorToAttitude();
    }

    @Import
    @GodGiven
    public static native void ArdupilotNative_nativeGuidedPosControlRunPriorToAttitude();

    public static int nativeGuidedGetAutoYawMode() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGuidedGetAutoYawMode();
        } else
            return ArdupilotNative.nativeGuidedGetAutoYawMode();
    }

    @Import
    @GodGiven
    public static native int ArdupilotNative_nativeGuidedGetAutoYawMode();

    public static float nativeGuidedGetWpNavRoll() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGuidedGetWpNavRoll();
        } else
            return ArdupilotNative.nativeGuidedGetWpNavRoll();
    }

    @Import
    @GodGiven
    public static native float ArdupilotNative_nativeGuidedGetWpNavRoll();

    public static float nativeGuidedGetWpNavPitch() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGuidedGetWpNavPitch();
        } else
            return ArdupilotNative.nativeGuidedGetWpNavPitch();
    }

    @Import
    @GodGiven
    public static native float ArdupilotNative_nativeGuidedGetWpNavPitch();

    public static float nativeGuidedGetTargetYawRate() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGuidedGetTargetYawRate();
        } else
            return ArdupilotNative.nativeGuidedGetTargetYawRate();
    }

    @Import
    @GodGiven
    public static native float ArdupilotNative_nativeGuidedGetTargetYawRate();

    public static float nativeGuidedGetAutoYawRateCds() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGuidedGetAutoYawRateCds();
        } else
            return ArdupilotNative.nativeGuidedGetAutoYawRateCds();
    }

    @Import
    @GodGiven
    public static native float ArdupilotNative_nativeGuidedGetAutoYawRateCds();

    public static float nativeGuidedGetAutoYawYaw() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGuidedGetAutoYawYaw();
        } else
            return ArdupilotNative.nativeGuidedGetAutoYawYaw();
    }

    @Import
    @GodGiven
    public static native float ArdupilotNative_nativeGuidedGetAutoYawYaw();

    public static void nativeGuidedVelControlRunPriorToAttitude() {
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeGuidedVelControlRunPriorToAttitude();
        } else
            ArdupilotNative.nativeGuidedVelControlRunPriorToAttitude();
    }

    @Import
    @GodGiven
    public static native void ArdupilotNative_nativeGuidedVelControlRunPriorToAttitude();

    public static float nativeGuidedGetPosControlRoll() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGuidedGetPosControlRoll();
        } else
            return ArdupilotNative.nativeGuidedGetPosControlRoll();
    }

    @Import
    @GodGiven
    public static native float ArdupilotNative_nativeGuidedGetPosControlRoll();

    public static float nativeGuidedGetPosControlPitch() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGuidedGetPosControlPitch();
        } else
            return ArdupilotNative.nativeGuidedGetPosControlPitch();
    }

    @Import
    @GodGiven
    public static native float ArdupilotNative_nativeGuidedGetPosControlPitch();

    public static void nativeGuidedPosVelControlRunPriorToAttitude() {
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeGuidedPosVelControlRunPriorToAttitude();
        } else
            ArdupilotNative.nativeGuidedPosVelControlRunPriorToAttitude();
    }

    @Import
    @GodGiven
    public static native void ArdupilotNative_nativeGuidedPosVelControlRunPriorToAttitude();

    public static void nativeGuidedAngleControlRunPriorToAttitude() {
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeGuidedAngleControlRunPriorToAttitude();
        } else
            ArdupilotNative.nativeGuidedAngleControlRunPriorToAttitude();
    }

    @Import
    @GodGiven
    public static native void ArdupilotNative_nativeGuidedAngleControlRunPriorToAttitude();

    public static float nativeGuidedGetAngleControlRunRollIn() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGuidedGetAngleControlRunRollIn();
        } else
            return ArdupilotNative.nativeGuidedGetAngleControlRunRollIn();
    }

    @Import
    @GodGiven
    public static native float ArdupilotNative_nativeGuidedGetAngleControlRunRollIn();

    public static float nativeGuidedGetAngleControlRunPitchIn() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGuidedGetAngleControlRunPitchIn();
        } else
            return ArdupilotNative.nativeGuidedGetAngleControlRunPitchIn();
    }

    @Import
    @GodGiven
    public static native float ArdupilotNative_nativeGuidedGetAngleControlRunPitchIn();

    public static boolean nativeGuidedIsAngleStateUseYawRate() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGuidedIsAngleStateUseYawRate();
        } else
            return ArdupilotNative.nativeGuidedIsAngleStateUseYawRate();
    }

    @Import
    @GodGiven
    public static native boolean ArdupilotNative_nativeGuidedIsAngleStateUseYawRate();

    public static float nativeGuidedGetAngleControlRunYawRateIn() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGuidedGetAngleControlRunYawRateIn();
        } else
            return ArdupilotNative.nativeGuidedGetAngleControlRunYawRateIn();
    }

    @Import
    @GodGiven
    public static native float ArdupilotNative_nativeGuidedGetAngleControlRunYawRateIn();

    public static float nativeGuidedGetAngleControlRunYawIn() {
        if(FijiJniSwitch.usingFiji) {
            return ArdupilotNative_nativeGuidedGetAngleControlRunYawIn();
        } else
            return ArdupilotNative.nativeGuidedGetAngleControlRunYawIn();
    }

    @Import
    @GodGiven
    public static native float ArdupilotNative_nativeGuidedGetAngleControlRunYawIn();

    public static void nativeGuidedAngleControlRunAfterAttitude() {
        if(FijiJniSwitch.usingFiji) {
            ArdupilotNative_nativeGuidedAngleControlRunAfterAttitude();
        } else
            ArdupilotNative.nativeGuidedAngleControlRunAfterAttitude();
    }

    @Import
    @GodGiven
    public static native void ArdupilotNative_nativeGuidedAngleControlRunAfterAttitude();
    //MODE GUIDED
}
