#define CONFIG_HAL_BOARD HAL_BOARD_SITL
#define CONFIG_HAL_BOARD_SUBTYPE HAL_BOARD_SUBTYPE_NONE

#include <jni.h>
#include <ub_cse_juav_jni_ArdupilotNative.h>

#include <AP_HAL/AP_HAL.h>
#include <AP_HAL_SITL/HAL_SITL_Class.h>

#include <iostream>    // C++ standard IO header

using namespace std;

const AP_HAL::HAL &hal = AP_HAL::get_HAL();

JNIEXPORT void JNICALL Java_ub_cse_juav_jni_ArdupilotNative_nativeInitizationPriorToControlLoop
  (JNIEnv * env, jclass thisClass) {


  }
