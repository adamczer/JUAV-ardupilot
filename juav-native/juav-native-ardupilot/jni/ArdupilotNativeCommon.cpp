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

float retriveFloatFromInt(int ret) {
unsigned char const * p = reinterpret_cast<unsigned char const *>(&ret);
  float value;
  std::memcpy(&value, p, sizeof(float));
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

  int ArdupilotNative_nativeApSchedulerGetLoopPeriodS
    () {
       return hideFloatInInt(scheduler->get_loop_period_s());
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

int ArdupilotNative_getStabilizationModeTargetYawRate
  () {
  ModeStabilize* stabilize = dynamic_cast<ModeStabilize*>(copter.juavGetNativeCurrentFlightMode());
        return hideFloatInInt(stabilize->juavStabilizeGetTargetYawRate());
  }

int ArdupilotNative_getStabilizationModeTargetPitch
  () {
  ModeStabilize* stabilize = dynamic_cast<ModeStabilize*>(copter.juavGetNativeCurrentFlightMode());
          return hideFloatInInt(stabilize->juavStabilizeGetTargetPitch());
  }


int ArdupilotNative_getStabilizationModeTargetRoll
  () {
  ModeStabilize* stabilize = dynamic_cast<ModeStabilize*>(copter.juavGetNativeCurrentFlightMode());
          return hideFloatInInt(stabilize->juavStabilizeGetTargetRoll());
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

int ArdupilotNative_nativeGetAccelRollMax
  () {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
     return hideFloatInInt(attitudeController->get_accel_roll_max());
  }

int ArdupilotNative_nativeGetAccelPitchMax
  () {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    return hideFloatInInt(attitudeController->get_accel_pitch_max());
  }

int ArdupilotNative_nativeGetAccelYawMax
  () {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    return hideFloatInInt(attitudeController->get_accel_yaw_max());
  }

int ArdupilotNative_nativeGetAttitudeInputTc
  () {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    return hideFloatInInt(attitudeController->juavGetAttitudeInputTc());
  }

int ArdupilotNative_nativeAttitudeGetDt
  () {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    return hideFloatInInt(attitudeController->juavGetAttitudeDt());
  }

int ArdupilotNative_nativeGetAngVelRollMax
  () {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    return hideFloatInInt(attitudeController->juavGetAttitudeAngVelRollMax());
  }

int ArdupilotNative_nativeGetAngVelPitchMax
  () {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    return hideFloatInInt(attitudeController->juavGetAttitudeAngVelPitchMax());
  }

int ArdupilotNative_nativeGetAngVelYawMax
  () {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    return hideFloatInInt(attitudeController->juavGetAttitudeAngVelYawMax());
  }

void ArdupilotNative_nativeSetAttitudeTargetQuat
  (int q1, int q2, int q3, int q4) {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    attitudeController->juavSetAttitudeTargetQuat(retriveFloatFromInt(q1),retriveFloatFromInt(q2),retriveFloatFromInt(q3),retriveFloatFromInt(q4));
  }

void ArdupilotNative_nativeSetAttitudeTargetEulerAngle
  (int x, int y, int z) {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    attitudeController->juavSetAttitudeTargetEulerAngle(retriveFloatFromInt(x),retriveFloatFromInt(y),retriveFloatFromInt(z));
  }

void ArdupilotNative_nativeSetAttitudeTargetEulerRate
  (int x, int y, int z) {
      AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
      attitudeController->juavSetAttitudeTargetEulerRate(retriveFloatFromInt(x),retriveFloatFromInt(y),retriveFloatFromInt(z));
  }

void ArdupilotNative_nativeSetAttitudeTargetAngVel
  (int x, int y, int z) {
        AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
        attitudeController->juavSetAttitudeTargetAngVel(retriveFloatFromInt(x),retriveFloatFromInt(y),retriveFloatFromInt(z));
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

int ArdupilotNative_nativeGetAttitudeThrustErrorAngle
  () {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    return hideFloatInInt(attitudeController->juavGetAttitudeThrustErrorAngle());
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
  (int newThrustAngle) {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
        return attitudeController->juavSetAttitudeThrustAngle(retriveFloatFromInt(newThrustAngle));
  }

int ArdupilotNative_nativeAttitudeGetPAngleYawKp
  () {
   AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
   AC_P& p = attitudeController->get_angle_yaw_p();
   return hideFloatInInt(p.kP());
  }

int ArdupilotNative_nativeAttitudeGetPAngleRollKp
  () {
   AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
   AC_P& p = attitudeController->get_angle_roll_p();
   return hideFloatInInt(p.kP());
  }

int ArdupilotNative_nativeAttitudeGetPAnglePitchKp
  () {
   AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
   AC_P& p = attitudeController->get_angle_pitch_p();
   return hideFloatInInt(p.kP());
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
  (int newFeedForwardScalar) {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    attitudeController->juavAttitudeSetFeedForwardScalar(retriveFloatFromInt(newFeedForwardScalar));
  }

int ArdupilotNative_nativeGetAttitudeFeedForwardScalar
  () {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    return hideFloatInInt(attitudeController->juavAttitudeGetFeedForwardScalar());
  }

void ArdupilotNative_nativeSetAttitudeAngError
  (int w, int x, int y, int z) {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
    attitudeController->juavAttitudeSetAttitudeAngError(retriveFloatFromInt(w),retriveFloatFromInt(x),retriveFloatFromInt(y),retriveFloatFromInt(z));
  }

void ArdupilotNative_nativeAttitudeSetRateTargetAngVel
  (int x, int y, int z) {
      AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
      attitudeController->juavSetAttitudeRateTargetAngVel(retriveFloatFromInt(x),retriveFloatFromInt(y),retriveFloatFromInt(z));
  }

void ArdupilotNative_nativeAttitudeSetThrustErrorAngle
  (int newThrustErrorAngle) {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
          attitudeController->juavSetAttitudeThrustErrorAngle(retriveFloatFromInt(newThrustErrorAngle));
  }

  int ArdupilotNative_nativeAttitudeGetSlewYaw
    () {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
             return hideFloatInInt(attitudeController->juavAttitudeGetSlewYaw());
    }

  //AP VEHICLE
  void ArdupilotNative_nativeSetAPVehicleSchedulerGDt
    (int newGDt) {
        AP_Vehicle& vehicle = *AP_Vehicle::get_singleton();
        vehicle.juavSetAPVehicleSchedulerGDt(retriveFloatFromInt(newGDt));
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
  (int eulerRollAngleCd, int eulerPitchAngleCd, int eulerYawRateCds) {
    AC_AttitudeControl_t* attitudeController = copter.juavNativeGetAttitudeController();
        attitudeController->input_euler_angle_roll_pitch_euler_rate_yaw(retriveFloatFromInt(eulerRollAngleCd),retriveFloatFromInt(eulerPitchAngleCd),retriveFloatFromInt(eulerYawRateCds));
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

int ArdupilotNative_nativeLoiterGetTargetYawRate
  () {
    ModeLoiter* loiter = dynamic_cast<ModeLoiter*>(copter.juavGetNativeCurrentFlightMode());
    return hideFloatInInt(loiter->juavLoiterGetTargetYawRate());
  }

int ArdupilotNative_nativeLoiterGetTargetPitch
  () {
  ModeLoiter* loiter = dynamic_cast<ModeLoiter*>(copter.juavGetNativeCurrentFlightMode());
      return hideFloatInInt(loiter->juavLoiterGetTargetPitch());
  }

int ArdupilotNative_nativeLoiterGetTargetRoll
  () {
  ModeLoiter* loiter = dynamic_cast<ModeLoiter*>(copter.juavGetNativeCurrentFlightMode());
      return hideFloatInInt(loiter->juavLoiterGetTargetRoll());
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

int ArdupilotNative_nativeGuidedGetWpNavRoll
  () {
    Mode* mode = copter.juavGetNativeCurrentFlightMode();
    return hideFloatInInt(mode->juavGetWpNav()->get_roll());
  }


int ArdupilotNative_nativeGuidedGetWpNavPitch
  () {
      Mode* mode = copter.juavGetNativeCurrentFlightMode();
      return hideFloatInInt(mode->juavGetWpNav()->get_pitch());
  }

int ArdupilotNative_nativeGuidedGetTargetYawRate
  () {
    ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
      return hideFloatInInt(guided->juavGuidedGetTargetYawRate());
  }

int ArdupilotNative_nativeGuidedGetAutoYawRateCds
  () {
  ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
  return hideFloatInInt(guided->juavGetAutoYaw().rate_cds());
  }

int ArdupilotNative_nativeGuidedGetAutoYawYaw
  () {
ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
  return hideFloatInInt(guided->juavGetAutoYaw().yaw());
  }

void ArdupilotNative_nativeGuidedVelControlRunPriorToAttitude
  () {
    ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
      return guided->juavGuidedVelControlRunPriorToAttitude();
  }

int ArdupilotNative_nativeGuidedGetPosControlRoll
  () {
    Mode* mode = copter.juavGetNativeCurrentFlightMode();
          return hideFloatInInt(mode->juavGetPosControl()->get_roll());
  }

int ArdupilotNative_nativeGuidedGetPosControlPitch
  () {
    Mode* mode = copter.juavGetNativeCurrentFlightMode();
          return hideFloatInInt(mode->juavGetPosControl()->get_pitch());
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

int ArdupilotNative_nativeGuidedGetAngleControlRunRollIn
  () {
    ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
          return hideFloatInInt(guided->juavGuidedGetAngleControlRunRollIn());
  }

int ArdupilotNative_nativeGuidedGetAngleControlRunPitchIn
  () {
  ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
            return hideFloatInInt(guided->juavGuidedGetAngleControlRunPitchIn());
  }

bool ArdupilotNative_nativeGuidedIsAngleStateUseYawRate
  () {
    ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
                return guided->juavGuidedIsAngleStateUseYawRate();
  }

int ArdupilotNative_nativeGuidedGetAngleControlRunYawRateIn
  () {
    ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
                    return hideFloatInInt(guided->juavGuidedGetAngleControlRunYawRateIn());
  }

int ArdupilotNative_nativeGuidedGetAngleControlRunYawIn
  () {
    ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
                        return hideFloatInInt(guided->juavGuidedGetAngleControlRunYawIn());
  }

void ArdupilotNative_nativeGuidedAngleControlRunAfterAttitude
  () {
  ModeGuided* guided = dynamic_cast<ModeGuided*>(copter.juavGetNativeCurrentFlightMode());
        guided->juavGuidedAngleControlRunAfterAttitude();
  }
//MODE GUIDED

//MODE AUTO
int ArdupilotNative_nativeGetModeAutoMode
  () {
    ModeAuto* modeAuto = dynamic_cast<ModeAuto*>(copter.juavGetNativeCurrentFlightMode());
         return modeAuto->mode();
  }

  void ArdupilotNative_nativeAutoModeTakeoffRun
    () {
     ModeAuto* modeAuto = dynamic_cast<ModeAuto*>(copter.juavGetNativeCurrentFlightMode());
     modeAuto->juavAutoModeTakeOffRun();
    }

  void ArdupilotNative_nativeAutoModeLandRun
    () {
     ModeAuto* modeAuto = dynamic_cast<ModeAuto*>(copter.juavGetNativeCurrentFlightMode());
     modeAuto->juavAutoModeLandRun();
    }

  void ArdupilotNative_nativeAutoModeRtlRun
    () {
     ModeAuto* modeAuto = dynamic_cast<ModeAuto*>(copter.juavGetNativeCurrentFlightMode());
     modeAuto->juavAutoModeRtlRun();
    }

  void ArdupilotNative_nativeAutoModeNavGuidedRun
    () {
     ModeAuto* modeAuto = dynamic_cast<ModeAuto*>(copter.juavGetNativeCurrentFlightMode());
     modeAuto->juavAutoModeNavGuidedRun();
    }

  void ArdupilotNative_nativeAutoModeLoiterToAltRun
    () {
     ModeAuto* modeAuto = dynamic_cast<ModeAuto*>(copter.juavGetNativeCurrentFlightMode());
     modeAuto->juavAutoModeLoiterToAltRun();
    }


    //////////
int ArdupilotNative_nativeAutoModeGetTargetYawRate() {
    ModeAuto* modeAuto = dynamic_cast<ModeAuto*>(copter.juavGetNativeCurrentFlightMode());
    return hideFloatInInt(modeAuto->juavGetTargetYawRate());

}

int ArdupilotNative_nativeAutoModeGetAutoYawYaw() {
ModeAuto* modeAuto = dynamic_cast<ModeAuto*>(copter.juavGetNativeCurrentFlightMode());
  return hideFloatInInt(modeAuto->juavGetAutoYaw().yaw());
}

void ArdupilotNative_nativeAutoModeWpRunPriorToAttitudeControl
  () {
ModeAuto* modeAuto = dynamic_cast<ModeAuto*>(copter.juavGetNativeCurrentFlightMode());
  modeAuto->juav_wp_run_prior_to_attitude_control();
  }

int ArdupilotNative_nativeAutoModeGetAutoYawMode
  () {
  ModeAuto* modeAuto = dynamic_cast<ModeAuto*>(copter.juavGetNativeCurrentFlightMode());
    return modeAuto->juavGetAutoYaw().mode();
  }

void ArdupilotNative_nativeAutoModeCircleRunPriorToAttitude
  () {
   ModeAuto* modeAuto = dynamic_cast<ModeAuto*>(copter.juavGetNativeCurrentFlightMode());
       modeAuto->juav_circle_run_prior_to_attitude();
  }

int ArdupilotNative_nativeGetCopterCircleNavRoll
  () {
    return hideFloatInInt(copter.juavGetCircleNav()->get_roll());
  }

int ArdupilotNative_nativeGetCopterCircleNavPitch
  () {
return hideFloatInInt(copter.juavGetCircleNav()->get_pitch());
  }

int ArdupilotNative_nativeGetCopterCircleNavYaw
  () {
return hideFloatInInt(copter.juavGetCircleNav()->get_yaw());
  }

//

void ArdupilotNative_nativeAutoModeSplineRunPriorToAttitude() {
   ModeAuto* modeAuto = dynamic_cast<ModeAuto*>(copter.juavGetNativeCurrentFlightMode());
          modeAuto->juav_spline_run_prior_to_attitude();
  }

void ArdupilotNative_nativeAutoModeLoiterRunPriorToAttitude() {
   ModeAuto* modeAuto = dynamic_cast<ModeAuto*>(copter.juavGetNativeCurrentFlightMode());
          modeAuto->juav_loiter_run_prior_to_attitude();
}

void ArdupilotNative_nativeAutoModePayloadPlaceRun() {
ModeAuto* modeAuto = dynamic_cast<ModeAuto*>(copter.juavGetNativeCurrentFlightMode());
          modeAuto->juavAutoModeNavPayloadPlaceRun();
}

bool ArdupilotNative_nativeAutoModeIsNavGuidedEnabled() {
    ModeAuto* modeAuto = dynamic_cast<ModeAuto*>(copter.juavGetNativeCurrentFlightMode());
             return modeAuto->juav_is_nav_guided_enabled();
}

//MODE AUTO