#include <HalSitlNativeCommon.h>

#include <AP_HAL/AP_HAL_Namespace.h>
#include <AP_HAL_SITL/HAL_SITL_Class.h>
#include <AP_Vehicle/AP_Vehicle.h>
#include <stdio.h>

#include <iostream>
using namespace std;


const AP_HAL::HAL &hal = AP_HAL::get_HAL();
AP_Vehicle * ud = AP_Vehicle::get_singleton();
const HAL_SITL* sitl = dynamic_cast<const HAL_SITL*>(&hal);

void HalSitlNative_nativeInitizationPriorToControlLoop
(const char* nativeString) {
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
     }

void HalSitlNative_nativeHalSitlInnerLoopAfterCallBacks
    () {
     sitl->juavNativeHalSitlAfterCallBacks();
    }

bool HalSitlNative_getHalSitlSchedulerShouldReboot
    () {
        return sitl->juavGetHalSitlSchedulerShouldReboot();
    }

bool HalSitlNative_getHalSitlSchedulerShouldExit
    () {
        return sitl->juavGetHalSitlSchedulerShouldExit();
    }

void HalSitlNative_sitlFillStackNan
    () {
        sitl->juavSitlFillStackNan();
    }