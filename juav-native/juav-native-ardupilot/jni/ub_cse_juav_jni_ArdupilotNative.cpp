//defines set in building of lib check makefile in jni
//For SITL
//#define CONFIG_HAL_BOARD HAL_BOARD_SITL
//#define CONFIG_HAL_BOARD_SUBTYPE HAL_BOARD_SUBTYPE_NONE
//For Erlebrain
//#define CONFIG_HAL_BOARD HAL_BOARD_LINUX
//#define CONFIG_HAL_BOARD_SUBTYPE HAL_BOARD_SUBTYPE_LINUX_ERLEBRAIN2

#include <jni.h>
#include <ub_cse_juav_jni_ArdupilotNative.h>
#include <ArdupilotNativeCommon.h>
#include <stdlib.h>

// AP SCHEDULER vv
  JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeApSchedulerPriorToFastLoop
    (JNIEnv * env, jclass thisClass) {
        ArdupilotNative_nativeApSchedulerPriorToFastLoop();
    }
  JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeApSchedulerPostToFastLoop
    (JNIEnv * env, jclass thisClass) {
        ArdupilotNative_nativeApSchedulerPostToFastLoop();
    }

  JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeApSchedulerGetLoopPeriodS
    (JNIEnv * env, jclass thisClass) {
       return ArdupilotNative_nativeApSchedulerGetLoopPeriodS();
    }

  JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_setHalUtilPersistentDataSchedulerTask
    (JNIEnv * env, jclass thisClass, jint persistent_data_scheduler_task) {
       ArdupilotNative_setHalUtilPersistentDataSchedulerTask(persistent_data_scheduler_task);
    }
// AP SCHEDULER ^^
// COPTER vv
   JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeFastLoopBeforeAttitudeController
     (JNIEnv * env, jclass thisClass) {
        ArdupilotNative_nativeFastLoopBeforeAttitudeController();
     }

   JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeFastLoopAfterAttitudeController
     (JNIEnv * env, jclass thisClass) {
       ArdupilotNative_nativeFastLoopAfterAttitudeController();
     }

   JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_surfaceTracingInvalidateForLogging
     (JNIEnv * env, jclass) {
        ArdupilotNative_surfaceTracingInvalidateForLogging();
     }
   JNIEXPORT jint JNICALL Java_ub_cse_juav_jni_ArdupilotNative_getFlightModeNumber
     (JNIEnv * env, jclass thisClass) {
        return ArdupilotNative_getFlightModeNumber();
     }

   JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_callNativeFlightMode
     (JNIEnv * env, jclass thisClass) {
        ArdupilotNative_callNativeFlightMode();
     }
// COPTER ^^

// MODE STABILIZE

JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeRunBeforeStabilizationCallAttitudeController
  (JNIEnv * env, jclass thisClass) {
  ArdupilotNative_nativeRunBeforeStabilizationCallAttitudeController();
  }

JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeRunAfterStabilizationCallAttitudeController
  (JNIEnv * env, jclass thisClass) {
  ArdupilotNative_nativeRunAfterStabilizationCallAttitudeController();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_getStabilizationModeTargetYawRate
  (JNIEnv * env, jclass thisClass) {
  return ArdupilotNative_getStabilizationModeTargetYawRate();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_getStabilizationModeTargetPitch
  (JNIEnv * env, jclass thisClass) {
  return ArdupilotNative_getStabilizationModeTargetPitch();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_getStabilizationModeTargetRoll
  (JNIEnv * env, jclass thisClass) {
  return ArdupilotNative_getStabilizationModeTargetRoll();
  }

// MODE STABILIZE

//AC ATTITUDE CONTROL
jfloatArray convertToJavaFloatArray(float arr [], int size, JNIEnv *env) {
    jfloatArray result;
    result = env->NewFloatArray(size);
    float* data;
    data = malloc(sizeof(float) * size);
    for(int i = 0; i<size; i++)
        data[i] = arr[i];
    env->SetFloatArrayRegion(result, 0, size, data);
    free(data);
    return result;
}

JNIEXPORT jfloatArray JNICALL Java_ub_cse_juav_jni_ArdupilotNative_getAttitudeTargetQuat
  (JNIEnv *env, jclass thisClass) {
    float* arr = ArdupilotNative_getAttitudeTargetQuat();
    return convertToJavaFloatArray(arr, 4, env);
  }

JNIEXPORT jfloatArray JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGetAttitudeTargetEulerAngle
  (JNIEnv * env, jclass thisClass) {
    float* arr = ArdupilotNative_nativeGetAttitudeTargetEulerAngle();
    return convertToJavaFloatArray(arr, 3, env);
  }

JNIEXPORT jfloatArray JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGetAttitudeTargetEulerRate
  (JNIEnv * env, jclass thisClass) {
     float * arr = ArdupilotNative_nativeGetAttitudeTargetEulerRate();
     return convertToJavaFloatArray(arr, 3, env);
  }

JNIEXPORT jfloatArray JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGetAttitudeTargetAngVel
  (JNIEnv * env, jclass thisClass) {
    float * arr = ArdupilotNative_nativeGetAttitudeTargetAngVel();
    return convertToJavaFloatArray(arr, 3, env);
  }

JNIEXPORT jboolean JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGetRateBfFfEnabled
  (JNIEnv * env, jclass thisClass) {
    return ArdupilotNative_nativeGetRateBfFfEnabled();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGetAccelRollMax
  (JNIEnv * env, jclass thisClass) {
     return ArdupilotNative_nativeGetAccelRollMax();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGetAccelPitchMax
  (JNIEnv * env, jclass thisClass) {
    return ArdupilotNative_nativeGetAccelPitchMax();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGetAccelYawMax
  (JNIEnv * env, jclass thisClass) {
    return ArdupilotNative_nativeGetAccelYawMax();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGetAttitudeInputTc
  (JNIEnv * env, jclass thisClass) {
    return ArdupilotNative_nativeGetAttitudeInputTc();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeAttitudeGetDt
  (JNIEnv * env, jclass thisClass) {
    return ArdupilotNative_nativeAttitudeGetDt();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGetAngVelRollMax
  (JNIEnv * env, jclass thisClass) {
    return ArdupilotNative_nativeGetAngVelRollMax();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGetAngVelPitchMax
  (JNIEnv * env, jclass thisClass) {
    return ArdupilotNative_nativeGetAngVelPitchMax();
  }
JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGetAngVelYawMax
  (JNIEnv * env, jclass thisClass) {
    return ArdupilotNative_nativeGetAngVelYawMax();
  }


JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeSetAttitudeTargetQuat
  (JNIEnv * env, jclass thisClass, jfloat q1, jfloat q2, jfloat q3, jfloat q4) {
    ArdupilotNative_nativeSetAttitudeTargetQuat(q1,q2,q3,q4);
  }

JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeSetAttitudeTargetEulerAngle
  (JNIEnv * env, jclass thisClass, jfloat x, jfloat y, jfloat z) {
    ArdupilotNative_nativeSetAttitudeTargetEulerAngle(x, y, z);
  }

JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeSetAttitudeTargetEulerRate
  (JNIEnv * env, jclass thisClass, jfloat x, jfloat y, jfloat z) {
      ArdupilotNative_nativeSetAttitudeTargetEulerRate(x,y,z);
  }

JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeSetAttitudeTargetAngVel
  (JNIEnv * env, jclass thisClass, jfloat x, jfloat y, jfloat z) {
        ArdupilotNative_nativeSetAttitudeTargetAngVel(x,y,z);
    }

JNIEXPORT jfloatArray JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGetRateTargetAngVel
  (JNIEnv * env, jclass thisClass) {
    float * arr = ArdupilotNative_nativeGetRateTargetAngVel();
    return convertToJavaFloatArray(arr, 3, env);
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGetAttitudeThrustErrorAngle
  (JNIEnv * env, jclass thisClass) {
    return ArdupilotNative_nativeGetAttitudeThrustErrorAngle();
  }

JNIEXPORT jfloatArray JNICALL Java_ub_cse_juav_jni_ArdupilotNative_getAhrsGetQuatBodyToNed
  (JNIEnv * env, jclass thisClass) {
    float * arr = ArdupilotNative_getAhrsGetQuatBodyToNed();
    return convertToJavaFloatArray(arr, 4, env);
  }

JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeSetAttitudeThrustAngle
  (JNIEnv * env, jclass thisClass, jfloat newThrustAngle) {
       ArdupilotNative_nativeSetAttitudeThrustAngle(newThrustAngle);
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeAttitudeGetPAngleYawKp
  (JNIEnv * env, jclass thisClass) {
   return ArdupilotNative_nativeAttitudeGetPAngleYawKp();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeAttitudeGetPAngleRollKp
  (JNIEnv * env, jclass thisClass) {
   return ArdupilotNative_nativeAttitudeGetPAngleRollKp();
  }
JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeAttitudeGetPAnglePitchKp
  (JNIEnv * env, jclass thisClass) {
   return ArdupilotNative_nativeAttitudeGetPAnglePitchKp();
  }

JNIEXPORT jboolean JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeAttitudeUseSqrtController
  (JNIEnv * env, jclass thisClass) {
  return ArdupilotNative_nativeAttitudeUseSqrtController();
  }

JNIEXPORT jfloatArray JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeAttitudeGetAhrsGyro
  (JNIEnv * env, jclass thisClass) {
      float * arr = ArdupilotNative_nativeAttitudeGetAhrsGyro();
      return convertToJavaFloatArray(arr, 3, env);
  }

JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeSetAttitudeFeedForwardScalar
  (JNIEnv * env, jclass thisClass, jfloat newFeedForwardScalar) {
    ArdupilotNative_nativeSetAttitudeFeedForwardScalar(newFeedForwardScalar);
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGetAttitudeFeedForwardScalar
  (JNIEnv * env, jclass thisClass) {
    return ArdupilotNative_nativeGetAttitudeFeedForwardScalar();
  }

JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeSetAttitudeAngError
  (JNIEnv * env, jclass thisClass, jfloat w, jfloat x, jfloat y, jfloat z) {
    ArdupilotNative_nativeSetAttitudeAngError(w,x,y,z);
  }

JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeAttitudeSetRateTargetAngVel
  (JNIEnv * env, jclass thisClass, jfloat x, jfloat y, jfloat z) {
      ArdupilotNative_nativeAttitudeSetRateTargetAngVel(x,y,z);
  }

JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeAttitudeSetThrustErrorAngle
  (JNIEnv * env, jclass thisClass, jfloat newThrustErrorAngle) {
      ArdupilotNative_nativeAttitudeSetThrustErrorAngle(newThrustErrorAngle);
  }

  JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeAttitudeGetSlewYaw
    (JNIEnv * env, jclass thisClass) {
      return ArdupilotNative_nativeAttitudeGetSlewYaw();
    }

  //AP VEHICLE
  JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeSetAPVehicleSchedulerGDt
    (JNIEnv * env, jclass thisClass, jfloat newGDt) {
        ArdupilotNative_nativeSetAPVehicleSchedulerGDt(newGDt);
    }
  //AP VEHICLE

  // MODE RTL
  JNIEXPORT jboolean JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeModeRtlIsMotorsArmed
    (JNIEnv * env, jclass thisClass) {
    return ArdupilotNative_nativeModeRtlIsMotorsArmed();
    }

  JNIEXPORT jboolean JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeModeRtlIsStateComplete
     (JNIEnv * env, jclass thisClass) {
     return ArdupilotNative_nativeModeRtlIsStateComplete();
     }

  // TEST
  JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_runAttitudeControllerQuatTest
    (JNIEnv * env, jclass thisClass) {
    ArdupilotNative_runAttitudeControllerQuatTest();
    }


JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_inputEulerAngleRollPitchEulerRateYaw
  (JNIEnv * env, jclass thisClass, jfloat eulerRollAngleCd, jfloat eulerPitchAngleCd, jfloat eulerYawRateCds) {
    ArdupilotNative_inputEulerAngleRollPitchEulerRateYaw(eulerRollAngleCd,eulerPitchAngleCd,eulerYawRateCds);
  }
   // TEST

    // MODE LOITER
JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeLoiterRunPriorToAttitudeControl
  (JNIEnv * env, jclass thisClass) {
    ArdupilotNative_nativeLoiterRunPriorToAttitudeControl();
  }

JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeLoiterRunAfterAttitudeControl
  (JNIEnv * env, jclass thisClass) {
  ArdupilotNative_nativeLoiterRunAfterAttitudeControl();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeLoiterGetTargetYawRate
  (JNIEnv * env, jclass thisClass) {
    return ArdupilotNative_nativeLoiterGetTargetYawRate();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeLoiterGetTargetPitch
  (JNIEnv * env, jclass thisClass) {
      return ArdupilotNative_nativeLoiterGetTargetPitch();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeLoiterGetTargetRoll
  (JNIEnv * env, jclass thisClass) {
  return ArdupilotNative_nativeLoiterGetTargetRoll();
  }

// MODE GUIDED

JNIEXPORT jint JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedGetMode
  (JNIEnv * env, jclass thisClass) {
    return ArdupilotNative_nativeGuidedGetMode();
  }

JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedTakeoffRun
  (JNIEnv * env, jclass thisClass) {
    ArdupilotNative_nativeGuidedTakeoffRun();
  }


JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedPosControlRunPriorToAttitude
  (JNIEnv * env, jclass thisClass) {
    ArdupilotNative_nativeGuidedPosControlRunPriorToAttitude();
  }

JNIEXPORT jint JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedGetAutoYawMode
  (JNIEnv * env, jclass thisClass) {
  ArdupilotNative_nativeGuidedGetAutoYawMode();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedGetWpNavRoll
  (JNIEnv * env, jclass thisClass) {
    return ArdupilotNative_nativeGuidedGetWpNavRoll();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedGetWpNavPitch
  (JNIEnv * env, jclass thisClass) {
      return ArdupilotNative_nativeGuidedGetWpNavPitch();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedGetTargetYawRate
  (JNIEnv * env, jclass thisClass) {
    return ArdupilotNative_nativeGuidedGetTargetYawRate();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedGetAutoYawRateCds
  (JNIEnv * env, jclass thisClass) {
  return ArdupilotNative_nativeGuidedGetAutoYawRateCds();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedGetAutoYawYaw
  (JNIEnv * env, jclass thisClass) {
    return ArdupilotNative_nativeGuidedGetAutoYawYaw();
  }


JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedVelControlRunPriorToAttitude
  (JNIEnv * env, jclass thisClass) {
    ArdupilotNative_nativeGuidedVelControlRunPriorToAttitude();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedGetPosControlRoll
  (JNIEnv * env, jclass thisClass) {
    return ArdupilotNative_nativeGuidedGetPosControlRoll();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedGetPosControlPitch
  (JNIEnv * env, jclass thisClass) {
    return ArdupilotNative_nativeGuidedGetPosControlPitch();
  }

JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedPosVelControlRunPriorToAttitude
  (JNIEnv * env, jclass thisClass) {
    ArdupilotNative_nativeGuidedPosVelControlRunPriorToAttitude();
  }

JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedAngleControlRunPriorToAttitude
  (JNIEnv * env, jclass thisClass) {
        ArdupilotNative_nativeGuidedAngleControlRunPriorToAttitude();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedGetAngleControlRunRollIn
  (JNIEnv * env, jclass thisClass) {
    return ArdupilotNative_nativeGuidedGetAngleControlRunRollIn();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedGetAngleControlRunPitchIn
  (JNIEnv * env, jclass thisClass) {
     return ArdupilotNative_nativeGuidedGetAngleControlRunPitchIn();
  }

JNIEXPORT jboolean JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedIsAngleStateUseYawRate
  (JNIEnv * env, jclass thisClass) {
     return ArdupilotNative_nativeGuidedIsAngleStateUseYawRate();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedGetAngleControlRunYawRateIn
  (JNIEnv * env, jclass thisClass) {
       return ArdupilotNative_nativeGuidedGetAngleControlRunYawRateIn();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedGetAngleControlRunYawIn
  (JNIEnv * env, jclass thisClass) {
       return ArdupilotNative_nativeGuidedGetAngleControlRunYawIn();
  }

JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedAngleControlRunAfterAttitude
  (JNIEnv * env, jclass thisClass) {
       ArdupilotNative_nativeGuidedAngleControlRunAfterAttitude();
  }
//MODE GUIDED