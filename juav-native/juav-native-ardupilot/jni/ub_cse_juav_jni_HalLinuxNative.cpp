#include <ub_cse_juav_jni_HalLinuxNative.h>
#include <HalLinuxNativeCommon.h>
#include <AP_HAL/AP_HAL_Namespace.h>
#include <AP_HAL_Linux/HAL_Linux_Class.h>
#include <AP_Vehicle/AP_Vehicle.h>


JNIEXPORT jboolean JNICALL Java_ub_cse_juav_jni_HalLinuxNative_nativeHalLinuxShouldExit
   (JNIEnv * env, jclass thisClass) {
        return HalLinuxNative_nativeHalLinuxShouldExit();
    }


JNIEXPORT void JNICALL Java_ub_cse_juav_jni_HalLinuxNative_nativeHalLinuxInitializationPriorToControlLoop
  (JNIEnv * env, jclass thisClass) {
	HalLinuxNative_nativeHalLinuxInitializationPriorToControlLoop();
   }

JNIEXPORT void JNICALL Java_ub_cse_juav_jni_HalLinuxNative_nativeHalLinuxAfterShouldExit
  (JNIEnv * env, jclass thisClass) {
       HalLinuxNative_nativeHalLinuxAfterShouldExit();
  }

