#ifndef _Included_ArdupilotNativeCommon
#define _Included_ArdupilotNativeCommon

#ifdef __cplusplus
extern "C" {
#endif

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
float ArdupilotNative_nativeApSchedulerGetLoopPeriodS
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
float ArdupilotNative_getStabilizationModeTargetYawRate
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    getStabilizationModeTargetPitch
 * Signature: ()F
 */
float ArdupilotNative_getStabilizationModeTargetPitch
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    getStabilizationModeTargetRoll
 * Signature: ()F
 */
float ArdupilotNative_getStabilizationModeTargetRoll
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    getAttitudeTargetQuat
 * Signature: ()[F
 */
float * ArdupilotNative_getAttitudeTargetQuat
  ();
//FIJI natives eaiser then pointers
float ArdupilotNative_getAttitudeTargetQuatW();
float ArdupilotNative_getAttitudeTargetQuatX();
float ArdupilotNative_getAttitudeTargetQuatY();
float ArdupilotNative_getAttitudeTargetQuatZ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGetAttitudeTargetEulerAngle
 * Signature: ()[F
 */
float * ArdupilotNative_nativeGetAttitudeTargetEulerAngle
  ();

//FIJI
float ArdupilotNative_nativeGetAttitudeTargetEulerAngleX();
float ArdupilotNative_nativeGetAttitudeTargetEulerAngleY();
float ArdupilotNative_nativeGetAttitudeTargetEulerAngleZ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGetAttitudeTargetEulerRate
 * Signature: ()[F
 */
float * ArdupilotNative_nativeGetAttitudeTargetEulerRate
  ();

//FIJI
float ArdupilotNative_nativeGetAttitudeTargetEulerRateX();
float ArdupilotNative_nativeGetAttitudeTargetEulerRateY();
float ArdupilotNative_nativeGetAttitudeTargetEulerRateZ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGetAttitudeTargetAngVel
 * Signature: ()[F
 */
float * ArdupilotNative_nativeGetAttitudeTargetAngVel
  ();

//FIJI
float  ArdupilotNative_nativeGetAttitudeTargetAngVelX();
float  ArdupilotNative_nativeGetAttitudeTargetAngVelY();
float  ArdupilotNative_nativeGetAttitudeTargetAngVelZ();

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
float ArdupilotNative_nativeGetAccelRollMax
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGetAccelPitchMax
 * Signature: ()F
 */
float ArdupilotNative_nativeGetAccelPitchMax
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGetAccelYawMax
 * Signature: ()F
 */
float ArdupilotNative_nativeGetAccelYawMax
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGetAttitudeInputTc
 * Signature: ()F
 */
float ArdupilotNative_nativeGetAttitudeInputTc
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeAttitudeGetDt
 * Signature: ()F
 */
float ArdupilotNative_nativeAttitudeGetDt
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGetAngVelRollMax
 * Signature: ()F
 */
float ArdupilotNative_nativeGetAngVelRollMax
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGetAngVelPitchMax
 * Signature: ()F
 */
float ArdupilotNative_nativeGetAngVelPitchMax
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGetAngVelYawMax
 * Signature: ()F
 */
float ArdupilotNative_nativeGetAngVelYawMax
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeSetAttitudeTargetQuat
 * Signature: (FFFF)V
 */
void ArdupilotNative_nativeSetAttitudeTargetQuat
  ( float, float, float, float);

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeSetAttitudeTargetEulerAngle
 * Signature: (FFF)V
 */
void ArdupilotNative_nativeSetAttitudeTargetEulerAngle
  ( float, float, float);

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeSetAttitudeTargetEulerRate
 * Signature: (FFF)V
 */
void ArdupilotNative_nativeSetAttitudeTargetEulerRate
  ( float, float, float);

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeSetAttitudeTargetAngVel
 * Signature: (FFF)V
 */
void ArdupilotNative_nativeSetAttitudeTargetAngVel
  ( float, float, float);

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGetRateTargetAngVel
 * Signature: ()[F
 */
float * ArdupilotNative_nativeGetRateTargetAngVel
  ();
float ArdupilotNative_nativeGetRateTargetAngVelX();
float ArdupilotNative_nativeGetRateTargetAngVelY();
float ArdupilotNative_nativeGetRateTargetAngVelZ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGetAttitudeThrustErrorAngle
 * Signature: ()F
 */
float ArdupilotNative_nativeGetAttitudeThrustErrorAngle
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    getAhrsGetQuatBodyToNed
 * Signature: ()[F
 */
float * ArdupilotNative_getAhrsGetQuatBodyToNed
  ();
float ArdupilotNative_getAhrsGetQuatBodyToNedW();
float ArdupilotNative_getAhrsGetQuatBodyToNedX();
float ArdupilotNative_getAhrsGetQuatBodyToNedY();
float ArdupilotNative_getAhrsGetQuatBodyToNedZ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeSetAttitudeThrustAngle
 * Signature: (F)V
 */
void ArdupilotNative_nativeSetAttitudeThrustAngle
  ( float);

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeAttitudeGetPAngleYawKp
 * Signature: ()F
 */
float ArdupilotNative_nativeAttitudeGetPAngleYawKp
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeAttitudeGetPAngleRollKp
 * Signature: ()F
 */
float ArdupilotNative_nativeAttitudeGetPAngleRollKp
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeAttitudeGetPAnglePitchKp
 * Signature: ()F
 */
float ArdupilotNative_nativeAttitudeGetPAnglePitchKp
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
float   ArdupilotNative_nativeAttitudeGetAhrsGyroX();
float   ArdupilotNative_nativeAttitudeGetAhrsGyroY();
float   ArdupilotNative_nativeAttitudeGetAhrsGyroZ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeSetAttitudeFeedForwardScalar
 * Signature: (F)V
 */
void ArdupilotNative_nativeSetAttitudeFeedForwardScalar
  ( float);

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGetAttitudeFeedForwardScalar
 * Signature: ()F
 */
float ArdupilotNative_nativeGetAttitudeFeedForwardScalar
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeSetAttitudeAngError
 * Signature: (FFFF)V
 */
void ArdupilotNative_nativeSetAttitudeAngError
  ( float, float, float, float);

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeAttitudeSetRateTargetAngVel
 * Signature: (FFF)V
 */
void ArdupilotNative_nativeAttitudeSetRateTargetAngVel
  ( float, float, float);

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeAttitudeSetThrustErrorAngle
 * Signature: (F)V
 */
void ArdupilotNative_nativeAttitudeSetThrustErrorAngle
  ( float);

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeAttitudeGetSlewYaw
 * Signature: ()F
 */
float ArdupilotNative_nativeAttitudeGetSlewYaw
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeSetAPVehicleSchedulerGDt
 * Signature: (F)V
 */
void ArdupilotNative_nativeSetAPVehicleSchedulerGDt
  ( float);

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
  ( float, float, float);

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
float ArdupilotNative_nativeLoiterGetTargetYawRate
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeLoiterGetTargetPitch
 * Signature: ()F
 */
float ArdupilotNative_nativeLoiterGetTargetPitch
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeLoiterGetTargetRoll
 * Signature: ()F
 */
float ArdupilotNative_nativeLoiterGetTargetRoll
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
float ArdupilotNative_nativeGuidedGetWpNavRoll
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGuidedGetWpNavPitch
 * Signature: ()F
 */
float ArdupilotNative_nativeGuidedGetWpNavPitch
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGuidedGetTargetYawRate
 * Signature: ()F
 */
float ArdupilotNative_nativeGuidedGetTargetYawRate
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGuidedGetAutoYawRateCds
 * Signature: ()F
 */
float ArdupilotNative_nativeGuidedGetAutoYawRateCds
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGuidedGetAutoYawYaw
 * Signature: ()F
 */
float ArdupilotNative_nativeGuidedGetAutoYawYaw
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
float ArdupilotNative_nativeGuidedGetPosControlRoll
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGuidedGetPosControlPitch
 * Signature: ()F
 */
float ArdupilotNative_nativeGuidedGetPosControlPitch
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
float ArdupilotNative_nativeGuidedGetAngleControlRunRollIn
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGuidedGetAngleControlRunPitchIn
 * Signature: ()F
 */
float ArdupilotNative_nativeGuidedGetAngleControlRunPitchIn
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
float ArdupilotNative_nativeGuidedGetAngleControlRunYawRateIn
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGuidedGetAngleControlRunYawIn
 * Signature: ()F
 */
float ArdupilotNative_nativeGuidedGetAngleControlRunYawIn
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