#include <ub_cse_juav_jni_HalLinuxNative.h>

#include <AP_HAL/AP_HAL_Namespace.h>
#include <AP_HAL_Linux/HAL_Linux_Class.h>
#include <AP_Vehicle/AP_Vehicle.h>


const AP_HAL::HAL &hal = AP_HAL::get_HAL();
AP_Vehicle * ud = AP_Vehicle::get_singleton();
const HAL_Linux* halLinux = dynamic_cast<const HAL_Linux*>(&hal);

JNIEXPORT jboolean JNICALL Java_ub_cse_juav_jni_HalLinuxNative_nativeHalLinuxShouldExit
   (JNIEnv * env, jclass thisClass) {
         halLinux.juavHalLinuxShouldExit();
    }


JNIEXPORT void JNICALL Java_ub_cse_juav_jni_HalLinuxNative_nativeHalLinuxInitializationPriorToControlLoop
 (JNIEnv * env, jclass thisClass, jint argCount, jobjectArray argArray) {
     char *args = new char [argCount];
       for (int i=0; i<argArray; i++) {
           jstring string = (jstring) (env->GetObjectArrayElement(argArray, i));
           const char *rawString = env->GetStringUTFChars(string, 0);
           args[i] = rawString;
           // Don't forget to call `ReleaseStringUTFChars` when you're done.
       }

       //TODO switch to erle config
       // ie HAL_LINUX, ERLEBRAIN2
       halLinux.juavHalLinuxInitializationPriorToControlLoop(argCount,args,ud);


   }

JNIEXPORT void JNICALL Java_ub_cse_juav_jni_HalLinuxNative_nativeHalLinuxAfterShouldExit
  (JNIEnv * env, jclass thisClass) {
       halLinux.juavHalLinuxAfterShouldExit();
  }

