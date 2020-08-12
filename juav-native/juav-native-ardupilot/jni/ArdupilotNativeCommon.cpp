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
#include <iostream>
#include <cstring>
using namespace std;

AP_Scheduler * scheduler = AP_Scheduler::get_singleton();

int hideFloatInInt(float ret) {
unsigned char const * p = reinterpret_cast<unsigned char const *>(&ret);
  int value;
  std::memcpy(&value, p, sizeof(int));
  return value;
}

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
  int ArdupilotNative_nativeApSchedulerGetLoopPeriodSGodGiven
      () {
         return hideFloatInInt(ArdupilotNative_nativeApSchedulerGetLoopPeriodS());
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
int ArdupilotNative_getStabilizationModeTargetYawRateGodGiven
  () {
  return hideFloatInInt(ArdupilotNative_getStabilizationModeTargetYawRate());
  }

float ArdupilotNative_getStabilizationModeTargetPitch
  () {
  ModeStabilize* stabilize = dynamic_cast<ModeStabilize*>(copter.juavGetNativeCurrentFlightMode());
          return stabilize->juavStabilizeGetTargetPitch();
  }
int ArdupilotNative_getStabilizationModeTargetPitchGodGiven
  () {
  return hideFloatInInt(ArdupilotNative_getStabilizationModeTargetPitch());
  }


float ArdupilotNative_getStabilizationModeTargetRoll
  () {
  ModeStabilize* stabilize = dynamic_cast<ModeStabilize*>(copter.juavGetNativeCurrentFlightMode());
          return stabilize->juavStabilizeGetTargetRoll();
  }
int ArdupilotNative_getStabilizationModeTargetRollGodGiven
  () {
  return hideFloatInInt(ArdupilotNative_getStabilizationModeTargetRoll());
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

int ArdupilotNative_getAttitudeTargetQuatW
  () {
  float ret = attitudeTargetQuat[0];
  //cout <<endl<<"quat0 about to return from c = "<< ret <<endl;
  return hideFloatInInt(ret);
  }

int ArdupilotNative_getAttitudeTargetQuatX
  () {
    float ret = attitudeTargetQuat[1];
  //cout <<endl<< ret <<endl;

  return hideFloatInInt(ret);
  }
int ArdupilotNative_getAttitudeTargetQuatY
  () {
    float ret = attitudeTargetQuat[2];
  //cout <<endl<< ret <<endl;

  return hideFloatInInt(ret);
  }
int ArdupilotNative_getAttitudeTargetQuatZ
  () {
    float ret = attitudeTargetQuat[3];

  return hideFloatInInt(ret);
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

int ArdupilotNative_nativeGetAttitudeTargetEulerAngleX
  () {
    float ret = attitudeTargetEulerAngle[0];
  return hideFloatInInt(ret);
  }
int ArdupilotNative_nativeGetAttitudeTargetEulerAngleY
  () {
    float ret = attitudeTargetEulerAngle[1];
  return hideFloatInInt(ret);
  }
int ArdupilotNative_nativeGetAttitudeTargetEulerAngleZ
  () {
    float ret = attitudeTargetEulerAngle[2];
  return hideFloatInInt(ret);
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

int ArdupilotNative_nativeGetAttitudeTargetEulerRateX
  () {
    float ret = attitudeTargetEulerRate[0];
  return hideFloatInInt(ret);
  }
int ArdupilotNative_nativeGetAttitudeTargetEulerRateY
  () {
    float ret = attitudeTargetEulerRate[1];
  return hideFloatInInt(ret);
  }
int ArdupilotNative_nativeGetAttitudeTargetEulerRateZ
  () {
    float ret = attitudeTargetEulerRate[2];
  return hideFloatInInt(ret);
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

int ArdupilotNative_nativeGetAttitudeTargetAngVelX
  () {
    float ret = attitudeTargetAngVel[0];
  return hideFloatInInt(ret);
  }
int ArdupilotNative_nativeGetAttitudeTargetAngVelY
  () {
    float ret = attitudeTargetAngVel[1];
  return hideFloatInInt(ret);
  }
int ArdupilotNative_nativeGetAttitudeTargetAngVelZ
  () {
    float ret = attitudeTargetAngVel[2];
  return hideFloatInInt(ret);
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
int ArdupilotNative_nativeGetAccelRollMaxGodGiven
  () {
  return hideFloatInInt(ArdupilotNative_nativeGetAccelRollMax());
  }

float ArdupilotNative_nativeGetAccelPitchMax
  () {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    return attitudeController->get_accel_pitch_max();
  }
int ArdupilotNative_nativeGetAccelPitchMaxGodGiven
  () {
    return hideFloatInInt(ArdupilotNative_nativeGetAccelPitchMax());
  }

float ArdupilotNative_nativeGetAccelYawMax
  () {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    return attitudeController->get_accel_yaw_max();
  }
int ArdupilotNative_nativeGetAccelYawMaxGodGiven
  () {
    return hideFloatInInt(ArdupilotNative_nativeGetAccelYawMax());
  }

float ArdupilotNative_nativeGetAttitudeInputTc
  () {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    return attitudeController->juavGetAttitudeInputTc();
  }
int ArdupilotNative_nativeGetAttitudeInputTcGodGiven
  () {
    return hideFloatInInt(ArdupilotNative_nativeGetAttitudeInputTc());
  }

float ArdupilotNative_nativeAttitudeGetDt
  () {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    return attitudeController->juavGetAttitudeDt();
  }
int ArdupilotNative_nativeAttitudeGetDtGodGiven
  () {
    return hideFloatInInt(ArdupilotNative_nativeAttitudeGetDt());
  }

float ArdupilotNative_nativeGetAngVelRollMax
  () {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    return attitudeController->juavGetAttitudeAngVelRollMax();
  }
int ArdupilotNative_nativeGetAngVelRollMaxGodGiven
  () {
    return hideFloatInInt(ArdupilotNative_nativeGetAngVelRollMax());
  }

float ArdupilotNative_nativeGetAngVelPitchMax
  () {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    return attitudeController->juavGetAttitudeAngVelPitchMax();
  }
int ArdupilotNative_nativeGetAngVelPitchMaxGodGiven
  () {
    return hideFloatInInt(ArdupilotNative_nativeGetAngVelPitchMax());
  }

float ArdupilotNative_nativeGetAngVelYawMax
  () {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    return attitudeController->juavGetAttitudeAngVelYawMax();
  }
int ArdupilotNative_nativeGetAngVelYawMaxGodGiven
  () {
    return hideFloatInInt(ArdupilotNative_nativeGetAngVelYawMax());
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
int ArdupilotNative_nativeGetRateTargetAngVelX
  () {
    float ret = attitudeRateTargetAngVel[0];
  return hideFloatInInt(ret);
  }
int ArdupilotNative_nativeGetRateTargetAngVelY
  () {
    float ret = attitudeRateTargetAngVel[1];
  return hideFloatInInt(ret);
  }
int ArdupilotNative_nativeGetRateTargetAngVelZ
  () {
    float ret = attitudeRateTargetAngVel[2];
  return hideFloatInInt(ret);
  }

float ArdupilotNative_nativeGetAttitudeThrustErrorAngle
  () {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    return attitudeController->juavGetAttitudeThrustErrorAngle();
  }
int ArdupilotNative_nativeGetAttitudeThrustErrorAngleGodGiven
  () {
    return hideFloatInInt(ArdupilotNative_nativeGetAttitudeThrustErrorAngle());
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

int ArdupilotNative_getAhrsGetQuatBodyToNedW
  () {
    float ret = AhrsGetQuatBodyToNed[0];
  return hideFloatInInt(ret);
  }
int ArdupilotNative_getAhrsGetQuatBodyToNedX
  () {
    float ret = AhrsGetQuatBodyToNed[1];
  return hideFloatInInt(ret);
  }
int ArdupilotNative_getAhrsGetQuatBodyToNedY
  () {
    float ret = AhrsGetQuatBodyToNed[2];
  return hideFloatInInt(ret);
  }
int ArdupilotNative_getAhrsGetQuatBodyToNedZ
  () {
    float ret = AhrsGetQuatBodyToNed[3];
  return hideFloatInInt(ret);
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
int ArdupilotNative_nativeAttitudeGetPAngleYawKpGodGiven
  () {
   return hideFloatInInt(ArdupilotNative_nativeAttitudeGetPAngleYawKp());
  }

float ArdupilotNative_nativeAttitudeGetPAngleRollKp
  () {
   AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
   AC_P& p = attitudeController->get_angle_roll_p();
   return p.kP();
  }
int ArdupilotNative_nativeAttitudeGetPAngleRollKpGodGiven
  () {
   return hideFloatInInt(ArdupilotNative_nativeAttitudeGetPAngleRollKp());
  }
float ArdupilotNative_nativeAttitudeGetPAnglePitchKp
  () {
   AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
   AC_P& p = attitudeController->get_angle_pitch_p();
   return p.kP();
  }
int ArdupilotNative_nativeAttitudeGetPAnglePitchKpGodGiven
  () {
   return hideFloatInInt(ArdupilotNative_nativeAttitudeGetPAnglePitchKp());
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

int ArdupilotNative_nativeAttitudeGetAhrsGyroX
  () {
    float ret = attitudeGetAhrsGyro[0];
  return hideFloatInInt(ret);
  }
int ArdupilotNative_nativeAttitudeGetAhrsGyroY
  () {
    float ret = attitudeGetAhrsGyro[1];
  return hideFloatInInt(ret);
  }
int ArdupilotNative_nativeAttitudeGetAhrsGyroZ
  () {
    float ret = attitudeGetAhrsGyro[2];
  return hideFloatInInt(ret);
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
int ArdupilotNative_nativeGetAttitudeFeedForwardScalarGodGiven
  () {
    return hideFloatInInt(ArdupilotNative_nativeGetAttitudeFeedForwardScalar());
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
  int ArdupilotNative_nativeAttitudeGetSlewYawGodGiven
      () {
      return hideFloatInInt(ArdupilotNative_nativeAttitudeGetSlewYaw());
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
int ArdupilotNative_nativeLoiterGetTargetYawRateGodGiven
  () {
    return hideFloatInInt(ArdupilotNative_nativeLoiterGetTargetYawRate());
  }

float ArdupilotNative_nativeLoiterGetTargetPitch
  () {
  ModeLoiter* loiter = dynamic_cast<ModeLoiter*>(copter.juavGetNativeCurrentFlightMode());
      return loiter->juavLoiterGetTargetPitch();
  }
int ArdupilotNative_nativeLoiterGetTargetPitchGodGiven
  () {
  return hideFloatInInt(ArdupilotNative_nativeLoiterGetTargetPitch());
  }

float ArdupilotNative_nativeLoiterGetTargetRoll
  () {
  ModeLoiter* loiter = dynamic_cast<ModeLoiter*>(copter.juavGetNativeCurrentFlightMode());
      return loiter->juavLoiterGetTargetRoll();
  }
int ArdupilotNative_nativeLoiterGetTargetRollGodGiven
  () {
  return hideFloatInInt(ArdupilotNative_nativeLoiterGetTargetRoll());
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
int ArdupilotNative_nativeGuidedGetWpNavRollGodGiven
  () {
    return hideFloatInInt(ArdupilotNative_nativeGuidedGetWpNavRoll());
  }


float ArdupilotNative_nativeGuidedGetWpNavPitch
  () {
      Mode* mode = copter.juavGetNativeCurrentFlightMode();
      return mode->juavGetWpNav()->get_pitch();
  }
int ArdupilotNative_nativeGuidedGetWpNavPitchGodGiven
  () {
      return hideFloatInInt(ArdupilotNative_nativeGuidedGetWpNavPitch());
  }

float ArdupilotNative_nativeGuidedGetTargetYawRate
  () {
    ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
      return guided->juavGuidedGetTargetYawRate();
  }
int ArdupilotNative_nativeGuidedGetTargetYawRateGodGiven
  () {
    return hideFloatInInt(ArdupilotNative_nativeGuidedGetTargetYawRate());
  }

float ArdupilotNative_nativeGuidedGetAutoYawRateCds
  () {
  ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
  return guided->juavGetAutoYaw().rate_cds();
  }
int ArdupilotNative_nativeGuidedGetAutoYawRateCdsGodGiven
  () {
  return hideFloatInInt(ArdupilotNative_nativeGuidedGetAutoYawRateCds());
  }

float ArdupilotNative_nativeGuidedGetAutoYawYaw
  () {
ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
  return guided->juavGetAutoYaw().yaw();
  }
int ArdupilotNative_nativeGuidedGetAutoYawYawGodGiven
  () {
return hideFloatInInt(ArdupilotNative_nativeGuidedGetAutoYawYaw());
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
int ArdupilotNative_nativeGuidedGetPosControlRollGodGiven
  () {
    return hideFloatInInt(ArdupilotNative_nativeGuidedGetPosControlRoll());
  }

float ArdupilotNative_nativeGuidedGetPosControlPitch
  () {
    Mode* mode = copter.juavGetNativeCurrentFlightMode();
          return mode->juavGetPosControl()->get_pitch();
  }
int ArdupilotNative_nativeGuidedGetPosControlPitchGodGiven
  () {
    return hideFloatInInt(ArdupilotNative_nativeGuidedGetPosControlPitch());
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
int ArdupilotNative_nativeGuidedGetAngleControlRunRollInGodGiven
  () {
    return hideFloatInInt(ArdupilotNative_nativeGuidedGetAngleControlRunRollIn());
  }

float ArdupilotNative_nativeGuidedGetAngleControlRunPitchIn
  () {
  ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
            return guided->juavGuidedGetAngleControlRunPitchIn();
  }
int ArdupilotNative_nativeGuidedGetAngleControlRunPitchInGodGiven
  () {
  return hideFloatInInt(ArdupilotNative_nativeGuidedGetAngleControlRunPitchIn());
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
int ArdupilotNative_nativeGuidedGetAngleControlRunYawRateInGodGiven
  () {
    return hideFloatInInt(ArdupilotNative_nativeGuidedGetAngleControlRunYawRateIn());
  }

float ArdupilotNative_nativeGuidedGetAngleControlRunYawIn
  () {
    ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
                        return guided->juavGuidedGetAngleControlRunYawIn();
  }
int ArdupilotNative_nativeGuidedGetAngleControlRunYawInGodGiven
  () {
    return hideFloatInInt(ArdupilotNative_nativeGuidedGetAngleControlRunYawIn());
  }

void ArdupilotNative_nativeGuidedAngleControlRunAfterAttitude
  () {
  ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
        guided->juavGuidedAngleControlRunAfterAttitude();
  }
//MODE GUIDED