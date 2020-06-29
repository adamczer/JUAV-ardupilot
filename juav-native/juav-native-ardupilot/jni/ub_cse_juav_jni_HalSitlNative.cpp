#include <jni.h>
/* Header for class ub_cse_juav_jni_HalSitlNative */

#include <ub_cse_juav_jni_HalSitlNative.h>

#include <AP_HAL/AP_HAL_Namespace.h>
#include <AP_HAL_SITL/HAL_SITL_Class.h>
#include <AP_Vehicle/AP_Vehicle.h>


const AP_HAL::HAL &hal = AP_HAL::get_HAL();
AP_Vehicle * ud = AP_Vehicle::get_singleton();
const HAL_SITL* sitl = dynamic_cast<const HAL_SITL*>(&hal);

JNIEXPORT void JNICALL Java_ub_cse_juav_jni_HalSitlNative_nativeInitizationPriorToControlLoop
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

JNIEXPORT void JNICALL Java_ub_cse_juav_jni_HalSitlNative_nativeHalSitlInnerLoopAfterCallBacks
    (JNIEnv * env, jclass thisClass) {
     sitl->juavNativeHalSitlAfterCallBacks();
    }

JNIEXPORT jboolean JNICALL Java_ub_cse_juav_jni_HalSitlNative_getHalSitlSchedulerShouldReboot
    (JNIEnv * env, jclass thisClass) {
        return sitl->juavGetHalSitlSchedulerShouldReboot();
    }

JNIEXPORT jboolean JNICALL Java_ub_cse_juav_jni_HalSitlNative_getHalSitlSchedulerShouldExit
    (JNIEnv * env, jclass thisClass) {
        return sitl->juavGetHalSitlSchedulerShouldExit();
    }

JNIEXPORT void JNICALL Java_ub_cse_juav_jni_HalSitlNative_sitlFillStackNan
    (JNIEnv * env, jclass thisClass) {
        sitl->juavSitlFillStackNan();
    }