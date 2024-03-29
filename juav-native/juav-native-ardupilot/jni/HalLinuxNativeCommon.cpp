#include <HalLinuxNativeCommon.h>

#include <AP_HAL/AP_HAL_Namespace.h>
#include <AP_HAL_Linux/HAL_Linux_Class.h>
#include <AP_Vehicle/AP_Vehicle.h>


const AP_HAL::HAL &hal = AP_HAL::get_HAL();
AP_Vehicle * ud = AP_Vehicle::get_singleton();
const HAL_Linux* halLinux = dynamic_cast<const HAL_Linux*>(&hal);

bool HalLinuxNative_nativeHalLinuxShouldExit
   () {
         return halLinux->juavHalLinuxShouldExit();
    }


void HalLinuxNative_nativeHalLinuxInitializationPriorToControlLoop
  () {
     char *args[] = {
              "/home/adamczer/code/ardupilot/build/sitl/bin/arducopter",
              "-A",
              "udp:127.0.0.1:6001",
              "-A",
              "tcp:127.0.0.1:5762",
              "-B",
              "/dev/ttyS0",
              "-C",
              "/dev/ttyUSB0",
              "-l",
              "/home/erle/APM/logs",
              "-t",
              "/home/erle/APM/terrain/"
             };
       //-A udp:127.0.0.1:6001 -B /dev/ttyS0 -C /dev/ttyUSB0 -l /home/erle/APM/logs -t /home/erle/APM/terrain/

       //TODO switch to erle config
       // ie HAL_LINUX, ERLEBRAIN2
       halLinux->juavHalLinuxInitializationPriorToControlLoop(13,args,ud);


   }

void HalLinuxNative_nativeHalLinuxAfterShouldExit
  () {
       halLinux->juavHalLinuxAfterShouldExit();
  }

