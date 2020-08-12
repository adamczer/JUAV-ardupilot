#ifndef _Included_ArdupilotNativeCommon
#define _Included_ArdupilotNativeCommon

#ifdef __cplusplus
extern "C" {
#endif

int hideFloatInInt(float ret);
float retriveFloatFromInt(int ret);
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeApSchedulerPriorToFastLoop
 * Signature: ()V
 */
void ArdupilotNative_nativeApSchedulerPriorToFastLoop
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeApSchedulerPostToFastLoop
 * Signature: ()V
 */
void ArdupilotNative_nativeApSchedulerPostToFastLoop
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    setHalUtilPersistentDataSchedulerTask
 * Signature: (I)V
 */
void ArdupilotNative_setHalUtilPersistentDataSchedulerTask
  ( int);

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeApSchedulerGetLoopPeriodS
 * Signature: ()F
 */
int ArdupilotNative_nativeApSchedulerGetLoopPeriodS
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeFastLoopBeforeAttitudeController
 * Signature: ()V
 */
void ArdupilotNative_nativeFastLoopBeforeAttitudeController
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    surfaceTracingInvalidateForLogging
 * Signature: ()V
 */
void ArdupilotNative_surfaceTracingInvalidateForLogging
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    getFlightModeNumber
 * Signature: ()I
 */
int ArdupilotNative_getFlightModeNumber
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    callNativeFlightMode
 * Signature: ()V
 */
void ArdupilotNative_callNativeFlightMode
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeFastLoopAfterAttitudeController
 * Signature: ()V
 */
void ArdupilotNative_nativeFastLoopAfterAttitudeController
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeRunBeforeStabilizationCallAttitudeController
 * Signature: ()V
 */
void ArdupilotNative_nativeRunBeforeStabilizationCallAttitudeController
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeRunAfterStabilizationCallAttitudeController
 * Signature: ()V
 */
void ArdupilotNative_nativeRunAfterStabilizationCallAttitudeController
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    getStabilizationModeTargetYawRate
 * Signature: ()F
 */
int ArdupilotNative_getStabilizationModeTargetYawRate
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    getStabilizationModeTargetPitch
 * Signature: ()F
 */
int ArdupilotNative_getStabilizationModeTargetPitch
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    getStabilizationModeTargetRoll
 * Signature: ()F
 */
int ArdupilotNative_getStabilizationModeTargetRoll
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    getAttitudeTargetQuat
 * Signature: ()[F
 */
float * ArdupilotNative_getAttitudeTargetQuat
  ();
//FIJI natives eaiser then pointers
int ArdupilotNative_getAttitudeTargetQuatW();
int ArdupilotNative_getAttitudeTargetQuatX();
int ArdupilotNative_getAttitudeTargetQuatY();
int ArdupilotNative_getAttitudeTargetQuatZ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGetAttitudeTargetEulerAngle
 * Signature: ()[F
 */
float * ArdupilotNative_nativeGetAttitudeTargetEulerAngle
  ();

//FIJI
int ArdupilotNative_nativeGetAttitudeTargetEulerAngleX();
int ArdupilotNative_nativeGetAttitudeTargetEulerAngleY();
int ArdupilotNative_nativeGetAttitudeTargetEulerAngleZ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGetAttitudeTargetEulerRate
 * Signature: ()[F
 */
float * ArdupilotNative_nativeGetAttitudeTargetEulerRate
  ();

//FIJI
int ArdupilotNative_nativeGetAttitudeTargetEulerRateX();
int ArdupilotNative_nativeGetAttitudeTargetEulerRateY();
int ArdupilotNative_nativeGetAttitudeTargetEulerRateZ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGetAttitudeTargetAngVel
 * Signature: ()[F
 */
float * ArdupilotNative_nativeGetAttitudeTargetAngVel
  ();

//FIJI
int  ArdupilotNative_nativeGetAttitudeTargetAngVelX();
int  ArdupilotNative_nativeGetAttitudeTargetAngVelY();
int  ArdupilotNative_nativeGetAttitudeTargetAngVelZ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGetRateBfFfEnabled
 * Signature: ()Z
 */
bool ArdupilotNative_nativeGetRateBfFfEnabled
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGetAccelRollMax
 * Signature: ()F
 */
int ArdupilotNative_nativeGetAccelRollMax
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGetAccelPitchMax
 * Signature: ()F
 */
int ArdupilotNative_nativeGetAccelPitchMax
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGetAccelYawMax
 * Signature: ()F
 */
int ArdupilotNative_nativeGetAccelYawMax
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGetAttitudeInputTc
 * Signature: ()F
 */
int ArdupilotNative_nativeGetAttitudeInputTc
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeAttitudeGetDt
 * Signature: ()F
 */
int ArdupilotNative_nativeAttitudeGetDt
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGetAngVelRollMax
 * Signature: ()F
 */
int ArdupilotNative_nativeGetAngVelRollMax
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGetAngVelPitchMax
 * Signature: ()F
 */
int ArdupilotNative_nativeGetAngVelPitchMax
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGetAngVelYawMax
 * Signature: ()F
 */
int ArdupilotNative_nativeGetAngVelYawMax
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeSetAttitudeTargetQuat
 * Signature: (FFFF)V
 */
void ArdupilotNative_nativeSetAttitudeTargetQuat
  ( int, int, int, int);

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeSetAttitudeTargetEulerAngle
 * Signature: (FFF)V
 */
void ArdupilotNative_nativeSetAttitudeTargetEulerAngle
  ( int, int, int);

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeSetAttitudeTargetEulerRate
 * Signature: (FFF)V
 */
void ArdupilotNative_nativeSetAttitudeTargetEulerRate
  ( int, int, int);
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeSetAttitudeTargetAngVel
 * Signature: (FFF)V
 */
void ArdupilotNative_nativeSetAttitudeTargetAngVel
  ( int, int, int);
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGetRateTargetAngVel
 * Signature: ()[F
 */
float * ArdupilotNative_nativeGetRateTargetAngVel
  ();
int ArdupilotNative_nativeGetRateTargetAngVelX();
int ArdupilotNative_nativeGetRateTargetAngVelY();
int ArdupilotNative_nativeGetRateTargetAngVelZ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGetAttitudeThrustErrorAngle
 * Signature: ()F
 */
int ArdupilotNative_nativeGetAttitudeThrustErrorAngle
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    getAhrsGetQuatBodyToNed
 * Signature: ()[F
 */
float * ArdupilotNative_getAhrsGetQuatBodyToNed
  ();
int ArdupilotNative_getAhrsGetQuatBodyToNedW();
int ArdupilotNative_getAhrsGetQuatBodyToNedX();
int ArdupilotNative_getAhrsGetQuatBodyToNedY();
int ArdupilotNative_getAhrsGetQuatBodyToNedZ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeSetAttitudeThrustAngle
 * Signature: (F)V
 */
void ArdupilotNative_nativeSetAttitudeThrustAngle
  ( int);
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeAttitudeGetPAngleYawKp
 * Signature: ()F
 */
int ArdupilotNative_nativeAttitudeGetPAngleYawKp
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeAttitudeGetPAngleRollKp
 * Signature: ()F
 */
int ArdupilotNative_nativeAttitudeGetPAngleRollKp
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeAttitudeGetPAnglePitchKp
 * Signature: ()F
 */
int ArdupilotNative_nativeAttitudeGetPAnglePitchKp
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeAttitudeUseSqrtController
 * Signature: ()Z
 */
bool ArdupilotNative_nativeAttitudeUseSqrtController
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeAttitudeGetAhrsGyro
 * Signature: ()[F
 */
float * ArdupilotNative_nativeAttitudeGetAhrsGyro
  ();
int   ArdupilotNative_nativeAttitudeGetAhrsGyroX();
int   ArdupilotNative_nativeAttitudeGetAhrsGyroY();
int   ArdupilotNative_nativeAttitudeGetAhrsGyroZ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeSetAttitudeFeedForwardScalar
 * Signature: (F)V
 */
void ArdupilotNative_nativeSetAttitudeFeedForwardScalar
  ( int);
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGetAttitudeFeedForwardScalar
 * Signature: ()F
 */
int ArdupilotNative_nativeGetAttitudeFeedForwardScalar
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeSetAttitudeAngError
 * Signature: (FFFF)V
 */
void ArdupilotNative_nativeSetAttitudeAngError
  ( int, int, int, int);
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeAttitudeSetRateTargetAngVel
 * Signature: (FFF)V
 */
void ArdupilotNative_nativeAttitudeSetRateTargetAngVel
  ( int, int, int);
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeAttitudeSetThrustErrorAngle
 * Signature: (F)V
 */
void ArdupilotNative_nativeAttitudeSetThrustErrorAngle
  ( int);
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeAttitudeGetSlewYaw
 * Signature: ()F
 */
int ArdupilotNative_nativeAttitudeGetSlewYaw
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeSetAPVehicleSchedulerGDt
 * Signature: (F)V
 */
void ArdupilotNative_nativeSetAPVehicleSchedulerGDt
  ( int);

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeModeRtlIsMotorsArmed
 * Signature: ()Z
 */
bool ArdupilotNative_nativeModeRtlIsMotorsArmed
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeModeRtlIsStateComplete
 * Signature: ()Z
 */
bool ArdupilotNative_nativeModeRtlIsStateComplete
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    runAttitudeControllerQuatTest
 * Signature: ()V
 */
void ArdupilotNative_runAttitudeControllerQuatTest
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    inputEulerAngleRollPitchEulerRateYaw
 * Signature: (FFF)V
 */
void ArdupilotNative_inputEulerAngleRollPitchEulerRateYaw
  ( int, int, int);
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeLoiterRunPriorToAttitudeControl
 * Signature: ()V
 */
void ArdupilotNative_nativeLoiterRunPriorToAttitudeControl
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeLoiterRunAfterAttitudeControl
 * Signature: ()V
 */
void ArdupilotNative_nativeLoiterRunAfterAttitudeControl
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeLoiterGetTargetYawRate
 * Signature: ()F
 */
int ArdupilotNative_nativeLoiterGetTargetYawRate
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeLoiterGetTargetPitch
 * Signature: ()F
 */
int ArdupilotNative_nativeLoiterGetTargetPitch
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeLoiterGetTargetRoll
 * Signature: ()F
 */
int ArdupilotNative_nativeLoiterGetTargetRoll
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGuidedGetMode
 * Signature: ()I
 */
int ArdupilotNative_nativeGuidedGetMode
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGuidedTakeoffRun
 * Signature: ()V
 */
void ArdupilotNative_nativeGuidedTakeoffRun
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGuidedPosControlRunPriorToAttitude
 * Signature: ()V
 */
void ArdupilotNative_nativeGuidedPosControlRunPriorToAttitude
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGuidedGetAutoYawMode
 * Signature: ()I
 */
int ArdupilotNative_nativeGuidedGetAutoYawMode
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGuidedGetWpNavRoll
 * Signature: ()F
 */
int ArdupilotNative_nativeGuidedGetWpNavRoll
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGuidedGetWpNavPitch
 * Signature: ()F
 */
int ArdupilotNative_nativeGuidedGetWpNavPitch
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGuidedGetTargetYawRate
 * Signature: ()F
 */
int ArdupilotNative_nativeGuidedGetTargetYawRate
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGuidedGetAutoYawRateCds
 * Signature: ()F
 */
int ArdupilotNative_nativeGuidedGetAutoYawRateCds
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGuidedGetAutoYawYaw
 * Signature: ()F
 */
int ArdupilotNative_nativeGuidedGetAutoYawYaw
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGuidedVelControlRunPriorToAttitude
 * Signature: ()V
 */
void ArdupilotNative_nativeGuidedVelControlRunPriorToAttitude
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGuidedGetPosControlRoll
 * Signature: ()F
 */
int ArdupilotNative_nativeGuidedGetPosControlRoll
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGuidedGetPosControlPitch
 * Signature: ()F
 */
int ArdupilotNative_nativeGuidedGetPosControlPitch
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGuidedPosVelControlRunPriorToAttitude
 * Signature: ()V
 */
void ArdupilotNative_nativeGuidedPosVelControlRunPriorToAttitude
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGuidedAngleControlRunPriorToAttitude
 * Signature: ()V
 */
void ArdupilotNative_nativeGuidedAngleControlRunPriorToAttitude
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGuidedGetAngleControlRunRollIn
 * Signature: ()F
 */
int ArdupilotNative_nativeGuidedGetAngleControlRunRollIn
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGuidedGetAngleControlRunPitchIn
 * Signature: ()F
 */
int ArdupilotNative_nativeGuidedGetAngleControlRunPitchIn
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGuidedIsAngleStateUseYawRate
 * Signature: ()Z
 */
bool ArdupilotNative_nativeGuidedIsAngleStateUseYawRate
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGuidedGetAngleControlRunYawRateIn
 * Signature: ()F
 */
int ArdupilotNative_nativeGuidedGetAngleControlRunYawRateIn
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGuidedGetAngleControlRunYawIn
 * Signature: ()F
 */
int ArdupilotNative_nativeGuidedGetAngleControlRunYawIn
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGuidedAngleControlRunAfterAttitude
 * Signature: ()V
 */
void ArdupilotNative_nativeGuidedAngleControlRunAfterAttitude
  ();

#ifdef __cplusplus
}
#endif
#endif