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
int ArdupilotNative_nativeApSchedulerGetLoopPeriodSGodGiven
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
int ArdupilotNative_getStabilizationModeTargetYawRateGodGiven
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    getStabilizationModeTargetPitch
 * Signature: ()F
 */
float ArdupilotNative_getStabilizationModeTargetPitch
  ();
int ArdupilotNative_getStabilizationModeTargetPitchGodGiven
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    getStabilizationModeTargetRoll
 * Signature: ()F
 */
float ArdupilotNative_getStabilizationModeTargetRoll
  ();
int ArdupilotNative_getStabilizationModeTargetRollGodGiven
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
float ArdupilotNative_nativeGetAccelRollMax
  ();
int ArdupilotNative_nativeGetAccelRollMaxGodGiven
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGetAccelPitchMax
 * Signature: ()F
 */
float ArdupilotNative_nativeGetAccelPitchMax
  ();
int ArdupilotNative_nativeGetAccelPitchMaxGodGiven
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGetAccelYawMax
 * Signature: ()F
 */
float ArdupilotNative_nativeGetAccelYawMax
  ();
int ArdupilotNative_nativeGetAccelYawMaxGodGiven
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGetAttitudeInputTc
 * Signature: ()F
 */
float ArdupilotNative_nativeGetAttitudeInputTc
  ();
int ArdupilotNative_nativeGetAttitudeInputTcGodGiven
  ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeAttitudeGetDt
 * Signature: ()F
 */
float ArdupilotNative_nativeAttitudeGetDt
  ();
int ArdupilotNative_nativeAttitudeGetDtGodGiven
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGetAngVelRollMax
 * Signature: ()F
 */
float ArdupilotNative_nativeGetAngVelRollMax
  ();
int ArdupilotNative_nativeGetAngVelRollMaxGodGiven
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGetAngVelPitchMax
 * Signature: ()F
 */
float ArdupilotNative_nativeGetAngVelPitchMax
  ();
int ArdupilotNative_nativeGetAngVelPitchMaxGodGiven
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGetAngVelYawMax
 * Signature: ()F
 */
float ArdupilotNative_nativeGetAngVelYawMax
  ();
int ArdupilotNative_nativeGetAngVelYawMaxGodGiven
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
int ArdupilotNative_nativeGetRateTargetAngVelX();
int ArdupilotNative_nativeGetRateTargetAngVelY();
int ArdupilotNative_nativeGetRateTargetAngVelZ();

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGetAttitudeThrustErrorAngle
 * Signature: ()F
 */
float ArdupilotNative_nativeGetAttitudeThrustErrorAngle
  ();
int ArdupilotNative_nativeGetAttitudeThrustErrorAngleGodGiven
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
  ( float);

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeAttitudeGetPAngleYawKp
 * Signature: ()F
 */
float ArdupilotNative_nativeAttitudeGetPAngleYawKp
  ();
int ArdupilotNative_nativeAttitudeGetPAngleYawKpGodGiven
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeAttitudeGetPAngleRollKp
 * Signature: ()F
 */
float ArdupilotNative_nativeAttitudeGetPAngleRollKp
  ();
int ArdupilotNative_nativeAttitudeGetPAngleRollKpGodGiven
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeAttitudeGetPAnglePitchKp
 * Signature: ()F
 */
float ArdupilotNative_nativeAttitudeGetPAnglePitchKp
  ();
int ArdupilotNative_nativeAttitudeGetPAnglePitchKpGodGiven
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
  ( float);

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGetAttitudeFeedForwardScalar
 * Signature: ()F
 */
float ArdupilotNative_nativeGetAttitudeFeedForwardScalar
  ();
int ArdupilotNative_nativeGetAttitudeFeedForwardScalarGodGiven
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
int ArdupilotNative_nativeAttitudeGetSlewYawGodGiven
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
int ArdupilotNative_nativeLoiterGetTargetYawRateGodGiven
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeLoiterGetTargetPitch
 * Signature: ()F
 */
float ArdupilotNative_nativeLoiterGetTargetPitch
  ();
int ArdupilotNative_nativeLoiterGetTargetPitchGodGiven
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeLoiterGetTargetRoll
 * Signature: ()F
 */
float ArdupilotNative_nativeLoiterGetTargetRoll
  ();
int ArdupilotNative_nativeLoiterGetTargetRollGodGiven
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
int ArdupilotNative_nativeGuidedGetWpNavRollGodGiven
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGuidedGetWpNavPitch
 * Signature: ()F
 */
float ArdupilotNative_nativeGuidedGetWpNavPitch
  ();
int ArdupilotNative_nativeGuidedGetWpNavPitchGodGiven
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGuidedGetTargetYawRate
 * Signature: ()F
 */
float ArdupilotNative_nativeGuidedGetTargetYawRate
  ();
int ArdupilotNative_nativeGuidedGetTargetYawRateGodGiven
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGuidedGetAutoYawRateCds
 * Signature: ()F
 */
float ArdupilotNative_nativeGuidedGetAutoYawRateCds
  ();
int ArdupilotNative_nativeGuidedGetAutoYawRateCdsGodGiven
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGuidedGetAutoYawYaw
 * Signature: ()F
 */
float ArdupilotNative_nativeGuidedGetAutoYawYaw
  ();
int ArdupilotNative_nativeGuidedGetAutoYawYawGodGiven
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
int ArdupilotNative_nativeGuidedGetPosControlRollGodGiven
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGuidedGetPosControlPitch
 * Signature: ()F
 */
float ArdupilotNative_nativeGuidedGetPosControlPitch
  ();
int ArdupilotNative_nativeGuidedGetPosControlPitchGodGiven
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
int ArdupilotNative_nativeGuidedGetAngleControlRunRollInGodGiven
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGuidedGetAngleControlRunPitchIn
 * Signature: ()F
 */
float ArdupilotNative_nativeGuidedGetAngleControlRunPitchIn
  ();
int ArdupilotNative_nativeGuidedGetAngleControlRunPitchInGodGiven
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
int ArdupilotNative_nativeGuidedGetAngleControlRunYawRateInGodGiven
  ();
/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGuidedGetAngleControlRunYawIn
 * Signature: ()F
 */
float ArdupilotNative_nativeGuidedGetAngleControlRunYawIn
  ();
int ArdupilotNative_nativeGuidedGetAngleControlRunYawInGodGiven
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