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
   JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_surfaceTracingInvalidateForLogging
     (JNIEnv * env, jclass) {
        copter.juavSurfaceTracingInvalidateForLogging();
     }
   JNIEXPORT jint JNICALL Java_ub_cse_juav_jni_ArdupilotNative_getFlightModeNumber
     (JNIEnv * env, jclass thisClass) {
        const char * flightmodeName = copter.juavGetNativeCurrentFlightMode()->name();
            if (strcmp(flightmodeName,"STABILIZE") == 0)
                return 0;
            else if (strcmp(flightmodeName,"RTL") == 0)
                return 6;
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