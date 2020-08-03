//defines set in building of lib check makefile in jni
//For SITL
//#define CONFIG_HAL_BOARD HAL_BOARD_SITL
//#define CONFIG_HAL_BOARD_SUBTYPE HAL_BOARD_SUBTYPE_NONE
//For Erlebrain
//#define CONFIG_HAL_BOARD HAL_BOARD_LINUX
//#define CONFIG_HAL_BOARD_SUBTYPE HAL_BOARD_SUBTYPE_LINUX_ERLEBRAIN2

#include <ArdupilotNativeCommon.h>

#include <AP_Scheduler/AP_Scheduler.h>
#include "Copter.h"

AP_Scheduler * scheduler = AP_Scheduler::get_singleton();

// AP SCHEDULER vv
  void ArdupilotNative_nativeApSchedulerPriorToFastLoop
    () {
        scheduler->juavNativeApSchedulerPriorToFastLoop();
    }
  void ArdupilotNative_nativeApSchedulerPostToFastLoop
    () {
        scheduler->juavNativeApSchedulerPostFastLoop();
    }

  float ArdupilotNative_nativeApSchedulerGetLoopPeriodS
    () {
       return scheduler->get_loop_period_s();
    }

  void ArdupilotNative_setHalUtilPersistentDataSchedulerTask
    (int persistent_data_scheduler_task) {
        hal.util->persistent_data.scheduler_task = persistent_data_scheduler_task;
    }
// AP SCHEDULER ^^
// COPTER vv
   void ArdupilotNative_nativeFastLoopBeforeAttitudeController
     () {
        copter.juavNativeFastLoopBeforeAttitudeController();
     }

   void ArdupilotNative_nativeFastLoopAfterAttitudeController
     () {
       copter.juavNativeFastLoopAfterAttitudeController();
     }

   void ArdupilotNative_surfaceTracingInvalidateForLogging
     () {
        copter.juavSurfaceTracingInvalidateForLogging();
     }
   int ArdupilotNative_getFlightModeNumber
     () {
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

   void ArdupilotNative_callNativeFlightMode
     () {
        copter.juavGetNativeCurrentFlightMode()->run();
     }
// COPTER ^^

// MODE STABILIZE

void ArdupilotNative_nativeRunBeforeStabilizationCallAttitudeController
  () {
  ModeStabilize* stabilize = dynamic_cast<ModeStabilize*>(copter.juavGetNativeCurrentFlightMode());
    stabilize->juavRunBeforeStabilizationCallAttitudeController();
  }

void ArdupilotNative_nativeRunAfterStabilizationCallAttitudeController
  () {
  ModeStabilize* stabilize = dynamic_cast<ModeStabilize*>(copter.juavGetNativeCurrentFlightMode());
      stabilize->juavRunAfterStabilizationCallAttitudeController();
  }

float ArdupilotNative_getStabilizationModeTargetYawRate
  () {
  ModeStabilize* stabilize = dynamic_cast<ModeStabilize*>(copter.juavGetNativeCurrentFlightMode());
        return stabilize->juavStabilizeGetTargetYawRate();
  }

float ArdupilotNative_getStabilizationModeTargetPitch
  () {
  ModeStabilize* stabilize = dynamic_cast<ModeStabilize*>(copter.juavGetNativeCurrentFlightMode());
          return stabilize->juavStabilizeGetTargetPitch();
  }

float ArdupilotNative_getStabilizationModeTargetRoll
  () {
  ModeStabilize* stabilize = dynamic_cast<ModeStabilize*>(copter.juavGetNativeCurrentFlightMode());
          return stabilize->juavStabilizeGetTargetRoll();
  }

// MODE STABILIZE

//AC ATTITUDE CONTROL
static float attitudeTargetQuat [4];
float * ArdupilotNative_getAttitudeTargetQuat
  () {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    Quaternion quat = attitudeController->get_attitude_target_quat();
    attitudeTargetQuat[0] = quat.q1;
    attitudeTargetQuat[1] = quat.q2;
    attitudeTargetQuat[2] = quat.q3;
    attitudeTargetQuat[3] = quat.q4;
    return attitudeTargetQuat;
  }

static float attitudeTargetEulerAngle [3];
float * ArdupilotNative_nativeGetAttitudeTargetEulerAngle
  () {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    Vector3f vec = attitudeController->juavGetAttitudeTargetEulerAngle();
    attitudeTargetEulerAngle[0] = vec.x;
    attitudeTargetEulerAngle[1] = vec.y;
    attitudeTargetEulerAngle[2] = vec.z;
    return attitudeTargetEulerAngle;
  }
static float attitudeTargetEulerRate [3];
float * ArdupilotNative_nativeGetAttitudeTargetEulerRate
  () {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
     Vector3f vec = attitudeController->juavGetAttitudeTargetEulerRate();
     attitudeTargetEulerRate[0] = vec.x;
     attitudeTargetEulerRate[1] = vec.y;
     attitudeTargetEulerRate[2] = vec.z;
     return attitudeTargetEulerRate;
  }

static float attitudeTargetAngVel [3];
float * ArdupilotNative_nativeGetAttitudeTargetAngVel
  () {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    Vector3f vec = attitudeController->juavGetAttitudeTargetAngVel();
     attitudeTargetAngVel[0] = vec.x;
     attitudeTargetAngVel[1] = vec.y;
     attitudeTargetAngVel[2] = vec.z;
     return attitudeTargetAngVel;
  }

bool ArdupilotNative_nativeGetRateBfFfEnabled
  () {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    return attitudeController->get_bf_feedforward();
  }

float ArdupilotNative_nativeGetAccelRollMax
  () {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
     return attitudeController->get_accel_roll_max();
  }

float ArdupilotNative_nativeGetAccelPitchMax
  () {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    return attitudeController->get_accel_pitch_max();
  }

float ArdupilotNative_nativeGetAccelYawMax
  () {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    return attitudeController->get_accel_yaw_max();
  }

float ArdupilotNative_nativeGetAttitudeInputTc
  () {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    return attitudeController->juavGetAttitudeInputTc();
  }

float ArdupilotNative_nativeAttitudeGetDt
  () {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    return attitudeController->juavGetAttitudeDt();
  }

float ArdupilotNative_nativeGetAngVelRollMax
  () {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    return attitudeController->juavGetAttitudeAngVelRollMax();
  }

float ArdupilotNative_nativeGetAngVelPitchMax
  () {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    return attitudeController->juavGetAttitudeAngVelPitchMax();
  }
float ArdupilotNative_nativeGetAngVelYawMax
  () {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    return attitudeController->juavGetAttitudeAngVelYawMax();
  }


void ArdupilotNative_nativeSetAttitudeTargetQuat
  (float q1, float q2, float q3, float q4) {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    attitudeController->juavSetAttitudeTargetQuat(q1,q2,q3,q4);
  }

void ArdupilotNative_nativeSetAttitudeTargetEulerAngle
  (float x, float y, float z) {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    attitudeController->juavSetAttitudeTargetEulerAngle(x, y, z);
  }

void ArdupilotNative_nativeSetAttitudeTargetEulerRate
  (float x, float y, float z) {
      AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
      attitudeController->juavSetAttitudeTargetEulerRate(x,y,z);
  }

void ArdupilotNative_nativeSetAttitudeTargetAngVel
  (float x, float y, float z) {
        AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
        attitudeController->juavSetAttitudeTargetAngVel(x,y,z);
    }
static float attitudeRateTargetAngVel [3];
float * ArdupilotNative_nativeGetRateTargetAngVel
  () {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    Vector3f vec = attitudeController->juavGetAttitudeRateTargetAngVel();    
     attitudeRateTargetAngVel[0] = vec.x;
     attitudeRateTargetAngVel[1] = vec.y;
     attitudeRateTargetAngVel[2] = vec.z;
     return attitudeRateTargetAngVel;
  }

float ArdupilotNative_nativeGetAttitudeThrustErrorAngle
  () {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    return attitudeController->juavGetAttitudeThrustErrorAngle();
  }
static float AhrsGetQuatBodyToNed [4];
float * ArdupilotNative_getAhrsGetQuatBodyToNed
  () {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    Quaternion quat;
    attitudeController->juavAttitudeGetAhrs().get_quat_body_to_ned(quat);
    AhrsGetQuatBodyToNed[0] = quat.q1;
    AhrsGetQuatBodyToNed[1] = quat.q2;
    AhrsGetQuatBodyToNed[2] = quat.q3;
    AhrsGetQuatBodyToNed[3] = quat.q4;
    return AhrsGetQuatBodyToNed;
  }

void ArdupilotNative_nativeSetAttitudeThrustAngle
  (float newThrustAngle) {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
        return attitudeController->juavSetAttitudeThrustAngle(newThrustAngle);
  }

float ArdupilotNative_nativeAttitudeGetPAngleYawKp
  () {
   AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
   AC_P& p = attitudeController->get_angle_yaw_p();
   return p.kP();
  }

float ArdupilotNative_nativeAttitudeGetPAngleRollKp
  () {
   AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
   AC_P& p = attitudeController->get_angle_roll_p();
   return p.kP();
  }
float ArdupilotNative_nativeAttitudeGetPAnglePitchKp
  () {
   AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
   AC_P& p = attitudeController->get_angle_pitch_p();
   return p.kP();
  }

bool ArdupilotNative_nativeAttitudeUseSqrtController
  () {
  AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
  return attitudeController->juavAttitudeUseSqrtController();
  }
static float attitudeGetAhrsGyro [3];
float * ArdupilotNative_nativeAttitudeGetAhrsGyro
  () {
  AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
     Vector3f vec = attitudeController->juavAttitudeGetAhrs().get_gyro();
     attitudeGetAhrsGyro[0] = vec.x;
     attitudeGetAhrsGyro[1] = vec.y;
     attitudeGetAhrsGyro[2] = vec.z;
     return attitudeGetAhrsGyro;
  }

void ArdupilotNative_nativeSetAttitudeFeedForwardScalar
  (float newFeedForwardScalar) {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    attitudeController->juavAttitudeSetFeedForwardScalar(newFeedForwardScalar);
  }

float ArdupilotNative_nativeGetAttitudeFeedForwardScalar
  () {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    return attitudeController->juavAttitudeGetFeedForwardScalar();
  }

void ArdupilotNative_nativeSetAttitudeAngError
  (float w, float x, float y, float z) {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    attitudeController->juavAttitudeSetAttitudeAngError(w,x,y,z);
  }

void ArdupilotNative_nativeAttitudeSetRateTargetAngVel
  (float x, float y, float z) {
      AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
      attitudeController->juavSetAttitudeRateTargetAngVel(x,y,z);
  }

void ArdupilotNative_nativeAttitudeSetThrustErrorAngle
  (float newThrustErrorAngle) {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
          attitudeController->juavSetAttitudeThrustErrorAngle(newThrustErrorAngle);
  }

  float ArdupilotNative_nativeAttitudeGetSlewYaw
    () {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
             return attitudeController->juavAttitudeGetSlewYaw();
    }

  //AP VEHICLE
  void ArdupilotNative_nativeSetAPVehicleSchedulerGDt
    (float newGDt) {
        AP_Vehicle& vehicle = *AP_Vehicle::get_singleton();
        vehicle.juavSetAPVehicleSchedulerGDt(newGDt);
    }
  //AP VEHICLE

  // MODE RTL
  bool ArdupilotNative_nativeModeRtlIsMotorsArmed
    () {
    Mode* mode = copter.juavGetNativeCurrentFlightMode();
    return mode->juavModeIsMotorsArmed();
    }

  bool ArdupilotNative_nativeModeRtlIsStateComplete
     () {
     ModeRTL* mode = dynamic_cast<ModeRTL*>(copter.juavGetNativeCurrentFlightMode());
     return mode->state_complete();
     }

  // TEST
  void ArdupilotNative_runAttitudeControllerQuatTest
    () {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
        attitudeController->attitude_controller_run_quat();
    }


void ArdupilotNative_inputEulerAngleRollPitchEulerRateYaw
  (float eulerRollAngleCd, float eulerPitchAngleCd, float eulerYawRateCds) {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
        attitudeController->input_euler_angle_roll_pitch_euler_rate_yaw(eulerRollAngleCd,eulerPitchAngleCd,eulerYawRateCds);
  }
   // TEST

    // MODE LOITER
void ArdupilotNative_nativeLoiterRunPriorToAttitudeControl
  () {
    ModeLoiter* loiter = dynamic_cast<ModeLoiter*>(copter.juavGetNativeCurrentFlightMode());
     loiter->juavLoiterRunPriorToAttitudeControl();
  }

void ArdupilotNative_nativeLoiterRunAfterAttitudeControl
  () {
  ModeLoiter* loiter = dynamic_cast<ModeLoiter*>(copter.juavGetNativeCurrentFlightMode());
     loiter->juavLoiterRunAfterAttitudeControl();
  }

float ArdupilotNative_nativeLoiterGetTargetYawRate
  () {
    ModeLoiter* loiter = dynamic_cast<ModeLoiter*>(copter.juavGetNativeCurrentFlightMode());
    return loiter->juavLoiterGetTargetYawRate();
  }

float ArdupilotNative_nativeLoiterGetTargetPitch
  () {
  ModeLoiter* loiter = dynamic_cast<ModeLoiter*>(copter.juavGetNativeCurrentFlightMode());
      return loiter->juavLoiterGetTargetPitch();
  }

float ArdupilotNative_nativeLoiterGetTargetRoll
  () {
  ModeLoiter* loiter = dynamic_cast<ModeLoiter*>(copter.juavGetNativeCurrentFlightMode());
      return loiter->juavLoiterGetTargetRoll();
  }

// MODE GUIDED

int ArdupilotNative_nativeGuidedGetMode
  () {
    ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
         return guided->mode();
  }

void ArdupilotNative_nativeGuidedTakeoffRun
  () {
    ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
             guided->juavGuidedTakeOffRun();
  }


void ArdupilotNative_nativeGuidedPosControlRunPriorToAttitude
  () {
    ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
               guided->juavGuidedPosControlPriorToAttitude();
  }

int ArdupilotNative_nativeGuidedGetAutoYawMode
  () {
  ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
  return guided->juavGetAutoYaw().mode();
  }

float ArdupilotNative_nativeGuidedGetWpNavRoll
  () {
    Mode* mode = copter.juavGetNativeCurrentFlightMode();
    return mode->juavGetWpNav()->get_roll();
  }

float ArdupilotNative_nativeGuidedGetWpNavPitch
  () {
      Mode* mode = copter.juavGetNativeCurrentFlightMode();
      return mode->juavGetWpNav()->get_pitch();
  }

float ArdupilotNative_nativeGuidedGetTargetYawRate
  () {
    ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
      return guided->juavGuidedGetTargetYawRate();
  }

float ArdupilotNative_nativeGuidedGetAutoYawRateCds
  () {
  ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
  return guided->juavGetAutoYaw().rate_cds();
  }

float ArdupilotNative_nativeGuidedGetAutoYawYaw
  () {
ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
  return guided->juavGetAutoYaw().yaw();
  }


void ArdupilotNative_nativeGuidedVelControlRunPriorToAttitude
  () {
    ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
      return guided->juavGuidedVelControlRunPriorToAttitude();
  }

float ArdupilotNative_nativeGuidedGetPosControlRoll
  () {
    Mode* mode = copter.juavGetNativeCurrentFlightMode();
          return mode->juavGetPosControl()->get_roll();
  }

float ArdupilotNative_nativeGuidedGetPosControlPitch
  () {
    Mode* mode = copter.juavGetNativeCurrentFlightMode();
          return mode->juavGetPosControl()->get_pitch();
  }

void ArdupilotNative_nativeGuidedPosVelControlRunPriorToAttitude
  () {
    ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
      return guided->juavGuidedPosVelControlRunPriorToAttitude();
  }

void ArdupilotNative_nativeGuidedAngleControlRunPriorToAttitude
  () {
        ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
      guided->juavGuidedAngleControlRunPriorToAttitude();
  }

float ArdupilotNative_nativeGuidedGetAngleControlRunRollIn
  () {
    ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
          return guided->juavGuidedGetAngleControlRunRollIn();
  }

float ArdupilotNative_nativeGuidedGetAngleControlRunPitchIn
  () {
  ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
            return guided->juavGuidedGetAngleControlRunPitchIn();
  }

bool ArdupilotNative_nativeGuidedIsAngleStateUseYawRate
  () {
    ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
                return guided->juavGuidedIsAngleStateUseYawRate();
  }

float ArdupilotNative_nativeGuidedGetAngleControlRunYawRateIn
  () {
    ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
                    return guided->juavGuidedGetAngleControlRunYawRateIn();
  }

float ArdupilotNative_nativeGuidedGetAngleControlRunYawIn
  () {
    ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
                        return guided->juavGuidedGetAngleControlRunYawIn();
  }

void ArdupilotNative_nativeGuidedAngleControlRunAfterAttitude
  () {
  ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
        guided->juavGuidedAngleControlRunAfterAttitude();
  }
//MODE GUIDED