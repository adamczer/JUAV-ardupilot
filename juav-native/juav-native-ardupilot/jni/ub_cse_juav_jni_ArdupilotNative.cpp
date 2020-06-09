#define CONFIG_HAL_BOARD HAL_BOARD_SITL
#define CONFIG_HAL_BOARD_SUBTYPE HAL_BOARD_SUBTYPE_NONE

#include <jni.h>
#include <ub_cse_juav_jni_ArdupilotNative.h>

//#include <AP_HAL/AP_HAL.h>
#include <AP_HAL_SITL/HAL_SITL_Class.h>
#include <AP_Vehicle/AP_Vehicle.h>
#include <AP_Scheduler/AP_Scheduler.h>
#include "Copter.h"

const AP_HAL::HAL &hal = HAL_SITL();
AP_Vehicle * ud = AP_Vehicle::get_singleton();
AP_Scheduler * scheduler = AP_Scheduler::get_singleton();
const HAL_SITL* sitl = dynamic_cast<const HAL_SITL*>(&hal);

// HAL SITL vv
JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeInitizationPriorToControlLoop
  (JNIEnv * env, jclass thisClass) {
   char *args[] = {
         "/home/adamczer/code/ardupilot/build/sitl/bin/arducopter",
         "-S",
         "-I0",
         "--model",
         "+",
         "--speedup",
         "1",
         "--defaults",
         "/home/adamczer/code/ardupilot/Tools/autotest/default_params/copter.parm"
        };
     sitl->juavNativeInitizationPriorToControlLoop(9,args,ud);
  }

  JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeHalSitlInnerLoopAfterCallBacks
    (JNIEnv * env, jclass thisClass) {
     sitl->juavNativeHalSitlAfterCallBacks();
    }

  JNIEXPORT jboolean JNICALL Java_ub_cse_juav_jni_ArdupilotNative_getHalSitlSchedulerShouldReboot
    (JNIEnv * env, jclass thisClass) {
        return sitl->juavGetHalSitlSchedulerShouldReboot();
    }

  JNIEXPORT jboolean JNICALL Java_ub_cse_juav_jni_ArdupilotNative_getHalSitlSchedulerShouldExit
    (JNIEnv * env, jclass thisClass) {
        return sitl->juavGetHalSitlSchedulerShouldExit();
    }

  JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_sitlFillStackNan
    (JNIEnv * env, jclass thisClass) {
        sitl->juavSitlFillStackNan();
    }

// HAL SITL ^^
// AP SCHEDULER vv
  JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeApSchedulerPriorToFastLoop
    (JNIEnv * env, jclass thisClass) {
        scheduler->juavNativeApSchedulerPriorToFastLoop();
    }
  JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeApSchedulerPostToFastLoop
    (JNIEnv * env, jclass thisClass) {
        scheduler->juavNativeApSchedulerPostFastLoop();
    }

  JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeApSchedulerGetLoopPeriodS
    (JNIEnv * env, jclass thisClass) {
       return scheduler->get_loop_period_s();
    }

  JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_setHalUtilPersistentDataSchedulerTask
    (JNIEnv * env, jclass thisClass, jint persistent_data_scheduler_task) {
        hal.util->persistent_data.scheduler_task = persistent_data_scheduler_task;
    }
// AP SCHEDULER ^^
// COPTER vv
   JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeFastLoopBeforeAttitudeController
     (JNIEnv * env, jclass thisClass) {
        copter.juavNativeFastLoopBeforeAttitudeController();
     }

   JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeFastLoopAfterAttitudeController
     (JNIEnv * env, jclass thisClass) {
       copter.juavNativeFastLoopAfterAttitudeController();
     }

   JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_surfaceTracingInvalidateForLogging
     (JNIEnv * env, jclass) {
        copter.juavSurfaceTracingInvalidateForLogging();
     }
   JNIEXPORT jint JNICALL Java_ub_cse_juav_jni_ArdupilotNative_getFlightModeNumber
     (JNIEnv * env, jclass thisClass) {
        const char * flightmodeName = copter.juavGetNativeCurrentFlightMode()->name();
            if (strcmp(flightmodeName,"STABILIZE") == 0)
                return 0;
            else if (strcmp(flightmodeName,"ACRO") == 0)
                return 1;
            else if (strcmp(flightmodeName,"ALT_HOLD") == 0)
                return 2;
            else if (strcmp(flightmodeName,"AUTO") == 0)
                return 3;
            else if (strcmp(flightmodeName,"GUIDED") == 0)
                return 4;
            else if (strcmp(flightmodeName,"LOITER") == 0)
                return 5;
            else if (strcmp(flightmodeName,"RTL") == 0)
                return 6;
            else if (strcmp(flightmodeName,"CIRCLE") == 0)
                return 7;
            else if (strcmp(flightmodeName,"LAND") == 0)
                return 9;
            else if (strcmp(flightmodeName,"DRIFT") == 0)
                return 11;
            else if (strcmp(flightmodeName,"SPORT") == 0)
                return 13;
            else if (strcmp(flightmodeName,"FLIP") == 0)
                return 14;
            else if (strcmp(flightmodeName,"AUTOTUNE") == 0)
                return 15;
            else if (strcmp(flightmodeName,"POSHOLD") == 0)
                return 16;
            else if (strcmp(flightmodeName,"BRAKE") == 0)
                return 17;
            else if (strcmp(flightmodeName,"THROW") == 0)
                return 18;
            else if (strcmp(flightmodeName,"AVOID_ADSB") == 0)
                return 19;
            else if (strcmp(flightmodeName,"GUIDED_NOGPS") == 0)
                return 20;
            else if (strcmp(flightmodeName,"SMART_RTL") == 0)
                return 21;
            else if (strcmp(flightmodeName,"FLOWHOLD") == 0)
                return 22;
            else if (strcmp(flightmodeName,"FOLLOW") == 0)
                return 23;
            else if (strcmp(flightmodeName,"ZIGZAG") == 0)
                return 24;
            else if (strcmp(flightmodeName,"SYSTEMID") == 0)
                return 25;
            else if (strcmp(flightmodeName,"AUTOROTATE") == 0)
                return 26;
            else
                return -1;
     }

   JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_callNativeFlightMode
     (JNIEnv * env, jclass thisClass) {
        copter.juavGetNativeCurrentFlightMode()->run();
     }
// COPTER ^^

// MODE STABILIZE

JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeRunBeforeStabilizationCallAttitudeController
  (JNIEnv * env, jclass thisClass) {
  ModeStabilize* stabilize = dynamic_cast<ModeStabilize*>(copter.juavGetNativeCurrentFlightMode());
    stabilize->juavRunBeforeStabilizationCallAttitudeController();
  }

JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeRunAfterStabilizationCallAttitudeController
  (JNIEnv * env, jclass thisClass) {
  ModeStabilize* stabilize = dynamic_cast<ModeStabilize*>(copter.juavGetNativeCurrentFlightMode());
      stabilize->juavRunAfterStabilizationCallAttitudeController();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_getStabilizationModeTargetYawRate
  (JNIEnv * env, jclass thisClass) {
  ModeStabilize* stabilize = dynamic_cast<ModeStabilize*>(copter.juavGetNativeCurrentFlightMode());
        return stabilize->juavStabilizeGetTargetYawRate();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_getStabilizationModeTargetPitch
  (JNIEnv * env, jclass thisClass) {
  ModeStabilize* stabilize = dynamic_cast<ModeStabilize*>(copter.juavGetNativeCurrentFlightMode());
          return stabilize->juavStabilizeGetTargetPitch();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_getStabilizationModeTargetRoll
  (JNIEnv * env, jclass thisClass) {
  ModeStabilize* stabilize = dynamic_cast<ModeStabilize*>(copter.juavGetNativeCurrentFlightMode());
          return stabilize->juavStabilizeGetTargetRoll();
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
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    Quaternion quat = attitudeController->get_attitude_target_quat();
    float arr [4] = {quat.q1,quat.q2,quat.q3,quat.q4};
    return convertToJavaFloatArray(arr, 4, env);
  }

JNIEXPORT jfloatArray JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGetAttitudeTargetEulerAngle
  (JNIEnv * env, jclass thisClass) {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    Vector3f vec = attitudeController->juavGetAttitudeTargetEulerAngle();
    float arr [3] = {vec.x,vec.y,vec.z};
    return convertToJavaFloatArray(arr, 3, env);
  }

JNIEXPORT jfloatArray JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGetAttitudeTargetEulerRate
  (JNIEnv * env, jclass thisClass) {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
     Vector3f vec = attitudeController->juavGetAttitudeTargetEulerRate();
     float arr [3] = {vec.x,vec.y,vec.z};
     return convertToJavaFloatArray(arr, 3, env);
  }

JNIEXPORT jfloatArray JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGetAttitudeTargetAngVel
  (JNIEnv * env, jclass thisClass) {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    Vector3f vec = attitudeController->juavGetAttitudeTargetAngVel();
    float arr [3] = {vec.x,vec.y,vec.z};
    return convertToJavaFloatArray(arr, 3, env);
  }

JNIEXPORT jboolean JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGetRateBfFfEnabled
  (JNIEnv * env, jclass thisClass) {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    return attitudeController->get_bf_feedforward();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGetAccelRollMax
  (JNIEnv * env, jclass thisClass) {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
     return attitudeController->get_accel_roll_max();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGetAccelPitchMax
  (JNIEnv * env, jclass thisClass) {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    return attitudeController->get_accel_pitch_max();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGetAccelYawMax
  (JNIEnv * env, jclass thisClass) {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    return attitudeController->get_accel_yaw_max();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGetAttitudeInputTc
  (JNIEnv * env, jclass thisClass) {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    return attitudeController->juavGetAttitudeInputTc();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeAttitudeGetDt
  (JNIEnv * env, jclass thisClass) {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    return attitudeController->juavGetAttitudeDt();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGetAngVelRollMax
  (JNIEnv * env, jclass thisClass) {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    return attitudeController->juavGetAttitudeAngVelRollMax();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGetAngVelPitchMax
  (JNIEnv * env, jclass thisClass) {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    return attitudeController->juavGetAttitudeAngVelPitchMax();
  }
JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGetAngVelYawMax
  (JNIEnv * env, jclass thisClass) {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    return attitudeController->juavGetAttitudeAngVelYawMax();
  }


JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeSetAttitudeTargetQuat
  (JNIEnv * env, jclass thisClass, jfloat q1, jfloat q2, jfloat q3, jfloat q4) {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    attitudeController->juavSetAttitudeTargetQuat(q1,q2,q3,q4);
  }

JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeSetAttitudeTargetEulerAngle
  (JNIEnv * env, jclass thisClass, jfloat x, jfloat y, jfloat z) {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    attitudeController->juavSetAttitudeTargetEulerAngle(x, y, z);
  }

JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeSetAttitudeTargetEulerRate
  (JNIEnv * env, jclass thisClass, jfloat x, jfloat y, jfloat z) {
      AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
      attitudeController->juavSetAttitudeTargetEulerRate(x,y,z);
  }

JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeSetAttitudeTargetAngVel
  (JNIEnv * env, jclass thisClass, jfloat x, jfloat y, jfloat z) {
        AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
        attitudeController->juavSetAttitudeTargetAngVel(x,y,z);
    }

JNIEXPORT jfloatArray JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGetRateTargetAngVel
  (JNIEnv * env, jclass thisClass) {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    Vector3f vec = attitudeController->juavGetAttitudeRateTargetAngVel();
    float arr [3] = {vec.x,vec.y,vec.z};
    return convertToJavaFloatArray(arr, 3, env);
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGetAttitudeThrustErrorAngle
  (JNIEnv * env, jclass thisClass) {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    return attitudeController->juavGetAttitudeThrustErrorAngle();
  }

JNIEXPORT jfloatArray JNICALL Java_ub_cse_juav_jni_ArdupilotNative_getAhrsGetQuatBodyToNed
  (JNIEnv * env, jclass thisClass) {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    Quaternion quat;
    attitudeController->juavAttitudeGetAhrs().get_quat_body_to_ned(quat);
    float arr [4] = {quat.q1,quat.q2,quat.q3,quat.q4};
    return convertToJavaFloatArray(arr, 4, env);
  }

JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeSetAttitudeThrustAngle
  (JNIEnv * env, jclass thisClass, jfloat newThrustAngle) {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
        return attitudeController->juavSetAttitudeThrustAngle(newThrustAngle);
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeAttitudeGetPAngleYawKp
  (JNIEnv * env, jclass thisClass) {
   AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
   AC_P& p = attitudeController->get_angle_yaw_p();
   return p.kP();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeAttitudeGetPAngleRollKp
  (JNIEnv * env, jclass thisClass) {
   AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
   AC_P& p = attitudeController->get_angle_roll_p();
   return p.kP();
  }
JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeAttitudeGetPAnglePitchKp
  (JNIEnv * env, jclass thisClass) {
   AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
   AC_P& p = attitudeController->get_angle_pitch_p();
   return p.kP();
  }

JNIEXPORT jboolean JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeAttitudeUseSqrtController
  (JNIEnv * env, jclass thisClass) {
  AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
  return attitudeController->juavAttitudeUseSqrtController();
  }

JNIEXPORT jfloatArray JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeAttitudeGetAhrsGyro
  (JNIEnv * env, jclass thisClass) {
  AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
      Vector3f vec = attitudeController->juavAttitudeGetAhrs().get_gyro();
      float arr [3] = {vec.x,vec.y,vec.z};
      return convertToJavaFloatArray(arr, 3, env);
  }

JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeSetAttitudeFeedForwardScalar
  (JNIEnv * env, jclass thisClass, jfloat newFeedForwardScalar) {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    attitudeController->juavAttitudeSetFeedForwardScalar(newFeedForwardScalar);
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGetAttitudeFeedForwardScalar
  (JNIEnv * env, jclass thisClass) {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    return attitudeController->juavAttitudeGetFeedForwardScalar();
  }

JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeSetAttitudeAngError
  (JNIEnv * env, jclass thisClass, jfloat w, jfloat x, jfloat y, jfloat z) {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    attitudeController->juavAttitudeSetAttitudeAngError(w,x,y,z);
  }

JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeAttitudeSetRateTargetAngVel
  (JNIEnv * env, jclass thisClass, jfloat x, jfloat y, jfloat z) {
      AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
      attitudeController->juavSetAttitudeRateTargetAngVel(x,y,z);
  }

JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeAttitudeSetThrustErrorAngle
  (JNIEnv * env, jclass thisClass, jfloat newThrustErrorAngle) {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
          attitudeController->juavSetAttitudeThrustErrorAngle(newThrustErrorAngle);
  }

  JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeAttitudeGetSlewYaw
    (JNIEnv * env, jclass thisClass) {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
             return attitudeController->juavAttitudeGetSlewYaw();
    }

  //AP VEHICLE
  JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeSetAPVehicleSchedulerGDt
    (JNIEnv * env, jclass thisClass, jfloat newGDt) {
        AP_Vehicle& vehicle = *AP_Vehicle::get_singleton();
        vehicle.juavSetAPVehicleSchedulerGDt(newGDt);
    }
  //AP VEHICLE

  // MODE RTL
  JNIEXPORT jboolean JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeModeRtlIsMotorsArmed
    (JNIEnv * env, jclass thisClass) {
    Mode* mode = copter.juavGetNativeCurrentFlightMode();
    return mode->juavModeIsMotorsArmed();
    }

  JNIEXPORT jboolean JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeModeRtlIsStateComplete
     (JNIEnv * env, jclass thisClass) {
     ModeRTL* mode = dynamic_cast<ModeRTL*>(copter.juavGetNativeCurrentFlightMode());
     return mode->state_complete();
     }

  // TEST
  JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_runAttitudeControllerQuatTest
    (JNIEnv * env, jclass thisClass) {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
        attitudeController->attitude_controller_run_quat();
    }


JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_inputEulerAngleRollPitchEulerRateYaw
  (JNIEnv * env, jclass thisClass, jfloat eulerRollAngleCd, jfloat eulerPitchAngleCd, jfloat eulerYawRateCds) {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
        attitudeController->input_euler_angle_roll_pitch_euler_rate_yaw(eulerRollAngleCd,eulerPitchAngleCd,eulerYawRateCds);
  }
   // TEST

    // MODE LOITER
JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeLoiterRunPriorToAttitudeControl
  (JNIEnv * env, jclass thisClass) {
    ModeLoiter* loiter = dynamic_cast<ModeLoiter*>(copter.juavGetNativeCurrentFlightMode());
     loiter->juavLoiterRunPriorToAttitudeControl();
  }

JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeLoiterRunAfterAttitudeControl
  (JNIEnv * env, jclass thisClass) {
  ModeLoiter* loiter = dynamic_cast<ModeLoiter*>(copter.juavGetNativeCurrentFlightMode());
     loiter->juavLoiterRunAfterAttitudeControl();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeLoiterGetTargetYawRate
  (JNIEnv * env, jclass thisClass) {
    ModeLoiter* loiter = dynamic_cast<ModeLoiter*>(copter.juavGetNativeCurrentFlightMode());
    return loiter->juavLoiterGetTargetYawRate();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeLoiterGetTargetPitch
  (JNIEnv * env, jclass thisClass) {
  ModeLoiter* loiter = dynamic_cast<ModeLoiter*>(copter.juavGetNativeCurrentFlightMode());
      return loiter->juavLoiterGetTargetPitch();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeLoiterGetTargetRoll
  (JNIEnv * env, jclass thisClass) {
  ModeLoiter* loiter = dynamic_cast<ModeLoiter*>(copter.juavGetNativeCurrentFlightMode());
      return loiter->juavLoiterGetTargetRoll();
  }

// MODE GUIDED

JNIEXPORT jint JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedGetMode
  (JNIEnv * env, jclass thisClass) {
    ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
         return guided->mode();
  }

JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedTakeoffRun
  (JNIEnv * env, jclass thisClass) {
    ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
             guided->juavGuidedTakeOffRun();
  }


JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedPosControlRunPriorToAttitude
  (JNIEnv * env, jclass thisClass) {
    ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
               guided->juavGuidedPosControlPriorToAttitude();
  }

JNIEXPORT jint JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedGetAutoYawMode
  (JNIEnv * env, jclass thisClass) {
  ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
  return guided->juavGetAutoYaw().mode();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedGetWpNavRoll
  (JNIEnv * env, jclass thisClass) {
    Mode* mode = copter.juavGetNativeCurrentFlightMode();
    return mode->juavGetWpNav()->get_roll();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedGetWpNavPitch
  (JNIEnv * env, jclass thisClass) {
      Mode* mode = copter.juavGetNativeCurrentFlightMode();
      return mode->juavGetWpNav()->get_pitch();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedGetTargetYawRate
  (JNIEnv * env, jclass thisClass) {
    ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
      return guided->juavGuidedGetTargetYawRate();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedGetAutoYawRateCds
  (JNIEnv * env, jclass thisClass) {
  ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
  return guided->juavGetAutoYaw().rate_cds();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedGetAutoYawYaw
  (JNIEnv * env, jclass thisClass) {
ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
  return guided->juavGetAutoYaw().yaw();
  }


JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedVelControlRunPriorToAttitude
  (JNIEnv * env, jclass thisClass) {
    ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
      return guided->juavGuidedVelControlRunPriorToAttitude();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedGetPosControlRoll
  (JNIEnv * env, jclass thisClass) {
    Mode* mode = copter.juavGetNativeCurrentFlightMode();
          return mode->juavGetPosControl()->get_roll();
  }

/*
 * Class:     ub_cse_juav_jni_ArdupilotNative
 * Method:    nativeGuidedGetPosControlPitch
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedGetPosControlPitch
  (JNIEnv * env, jclass thisClass) {
    Mode* mode = copter.juavGetNativeCurrentFlightMode();
          return mode->juavGetPosControl()->get_pitch();
  }

JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedPosVelControlRunPriorToAttitude
  (JNIEnv * env, jclass thisClass) {
    ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
      return guided->juavGuidedPosVelControlRunPriorToAttitude();
  }

JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedAngleControlRunPriorToAttitude
  (JNIEnv * env, jclass thisClass) {
        ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
      guided->juavGuidedAngleControlRunPriorToAttitude();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedGetAngleControlRunRollIn
  (JNIEnv * env, jclass thisClass) {
    ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
          return guided->juavGuidedGetAngleControlRunRollIn();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedGetAngleControlRunPitchIn
  (JNIEnv * env, jclass thisClass) {
  ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
            return guided->juavGuidedGetAngleControlRunPitchIn();
  }

JNIEXPORT jboolean JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedIsAngleStateUseYawRate
  (JNIEnv * env, jclass thisClass) {
    ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
                return guided->juavGuidedIsAngleStateUseYawRate();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedGetAngleControlRunYawRateIn
  (JNIEnv * env, jclass thisClass) {
    ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
                    return guided->juavGuidedGetAngleControlRunYawRateIn();
  }

JNIEXPORT jfloat JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedGetAngleControlRunYawIn
  (JNIEnv * env, jclass thisClass) {
    ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
                        return guided->juavGuidedGetAngleControlRunYawIn();
  }

JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeGuidedAngleControlRunAfterAttitude
  (JNIEnv * env, jclass thisClass) {
  ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
        guided->juavGuidedAngleControlRunAfterAttitude();
  }
