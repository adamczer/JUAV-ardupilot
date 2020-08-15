#include <jni.h>
/* Header for class ub_cse_juav_jni_HalSitlNative */

#include <ub_cse_juav_jni_HalSitlNative.h>
#include <HalSitlNativeCommon.h>

JNIEXPORT void JNICALL Java_ub_cse_juav_jni_HalSitlNative_nativeInitizationPriorToControlLoop
(JNIEnv * env, jclass thisClass, jstring javaString) {
   const char *nativeString = env->GetStringUTFChars(javaString, 0);
   HalSitlNative_nativeInitizationPriorToControlLoop(nativeString);
   env->ReleaseStringUTFChars(javaString, nativeString);
     }

JNIEXPORT void JNICALL Java_ub_cse_juav_jni_HalSitlNative_nativeHalSitlInnerLoopAfterCallBacks
    (JNIEnv * env, jclass thisClass) {
     HalSitlNative_nativeHalSitlInnerLoopAfterCallBacks();
    }

JNIEXPORT jboolean JNICALL Java_ub_cse_juav_jni_HalSitlNative_getHalSitlSchedulerShouldReboot
    (JNIEnv * env, jclass thisClass) {
        return HalSitlNative_getHalSitlSchedulerShouldReboot();
    }

JNIEXPORT jboolean JNICALL Java_ub_cse_juav_jni_HalSitlNative_getHalSitlSchedulerShouldExit
    (JNIEnv * env, jclass thisClass) {
        return HalSitlNative_getHalSitlSchedulerShouldExit();
    }

JNIEXPORT void JNICALL Java_ub_cse_juav_jni_HalSitlNative_sitlFillStackNan
    (JNIEnv * env, jclass thisClass) {
        HalSitlNative_sitlFillStackNan();
    }