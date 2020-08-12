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
(JNIEnv * env, jclass thisClass, jstring javaString) {
   const char *nativeString = env->GetStringUTFChars(javaString, 0);
   char nativeArg0[100];
   strcpy(nativeArg0,nativeString);
   strcat(nativeArg0,"/build/sitl/bin/arducopter");
   char nativeArg8[100];
   strcpy(nativeArg8,nativeString);
   strcat(nativeArg8,"/Tools/autotest/default_params/copter.parm");
   char *args[] = {
         nativeArg0,
         "-S",
         "-I0",
         "--model",
         "+",
         "--speedup",
         "1",
         "--defaults",
         nativeArg8
        };
     sitl->juavNativeInitizationPriorToControlLoop(9,args,ud);
     env->ReleaseStringUTFChars(javaString, nativeString);
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