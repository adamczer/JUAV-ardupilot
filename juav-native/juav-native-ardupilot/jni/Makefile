# this build is working off a clean checkout of ardupilot
# @ commit 22c9081c8325ec8d980906fe7b1d23f29080a216
# ensure ARDU_SRC env variable set to ardupilot checkout
#ARDU_SRC=/home/adamczer/code/ardupilot
#JAVA_HOME=/home/erle/program_files/jdk1.8.0_191/
ARDU_SITL_INPUTS:=$(shell find ${ARDU_SRC}/build/sitl -type f -name "*.o" \
| sed 's/.*loslib.c.0.o//g' \
| sed 's/.*luac.c.0.o//g' \
| sed 's/.*AP_PiccoloCAN.*//g' \
| tr '\n' ' ')

ARDU_ERLE_COPTER_INPUTS:=$(shell find ${ARDU_SRC}/build/erlebrain2 -type f -name "*.o" \
| sed 's/.*loslib.c.0.o//g' \
| sed 's/.*luac.c.0.o//g' \
| sed 's/.*RCOutput_Disco.cpp.0.o//g' \
| sed 's/.*GPIO_Sysfs.cpp.0.o//g' \
| sed 's/.*ESCPackets.c.0.o//g' \
| tr '\n' ' ')

all: sitl

sitl: stage-files arducopter-sitl-lib juav-sitl-jni

erle: stage-files arducopter-erle-copter arducopter-erle-copter-lib juav-erle-copter-lib

stage-files:
	$(shell cp -r ardupilot/* $(ARDU_SRC))

arducopter-erle-copter:
	cd $(ARDU_SRC) && ./waf configure --board=erlebrain2 && ./waf copter

arducopter-erle-copter-lib: $(ARDU_ERLE_COPTER_INPUTS)
	/usr/bin/arm-linux-gnueabihf-g++ -std=gnu++11 -o lib/libArduCopterErleCopter.so -shared $(ARDU_ERLE_COPTER_INPUTS)

juav-erle-copter-lib:
	/usr/bin/arm-linux-gnueabihf-g++ -lm -Wl,--no-undefined -Wl,--gc-sections -pthread -L./lib -fPIC -std=gnu++11 -I"./includes" -I"./common-includes" -I"${JAVA_HOME}/include" -I"${JAVA_HOME}/include/linux" -I"$(ARDU_SRC)/libraries/" -I"$(ARDU_SRC)/libraries/AP_HAL_Linux/" -I"$(ARDU_SRC)/libraries/AP_HAL/" -I"${ARDU_SRC}/build/erlebrain2/libraries/GCS_MAVLink/" -I"$(ARDU_SRC)/libraries/AP_Common/missing/" -I"$(ARDU_SRC)/libraries/AP_HAL_Linux/" -I"$(ARDU_SRC)/ArduCopter/" ArdupilotNativeCommon.cpp ub_cse_juav_jni_ArdupilotNative.cpp HalLinuxNativeCommon.cpp ub_cse_juav_jni_HalLinuxNative.cpp -lArduCopterErleCopter -lpthread -ldl -shared -DCONFIG_HAL_BOARD=HAL_BOARD_LINUX -DCONFIG_HAL_BOARD_SUBTYPE=HAL_BOARD_SUBTYPE_LINUX_ERLEBRAIN2 -fpermissive -o lib/libJuavErleCopterJni.so

# sim_vehicle.py -v ArduCopter --console --map
# resotore original sources by checking out file
# this file needs to be installed to /usr/local/lib/libArduCopterSitl.so
arducopter-sitl-lib : $(ARDU_SITL_INPUTS)
	mkdir -p lib
	-$(shell sim_vehicle.py -v ArduCopter --console --map)
	g++ -std=gnu++11 -o lib/libArduCopterSitl.so -shared $(ARDU_SITL_INPUTS)
	


#BUILD native jni library replacing with reimplemented versions of files to allow access to JUAV
juav-sitl-jni:
	g++ -g -L./lib -fPIC -std=gnu++11 -I"./includes" -I"./common-includes"  -I"${JAVA_HOME}/include" -I"${JAVA_HOME}/include/linux" -I"$(ARDU_SRC)/libraries/" -I"$(ARDU_SRC)/libraries/AP_HAL_SITL/" -I"$(ARDU_SRC)/libraries/AP_HAL/" -I"${ARDU_SRC}/build/sitl/libraries/GCS_MAVLink/" -I"$(ARDU_SRC)/libraries/AP_Common/missing/" -I"$(ARDU_SRC)/libraries/AP_HAL_SITL/" -I"$(ARDU_SRC)/ArduCopter/" ub_cse_juav_jni_ArdupilotNative.cpp ub_cse_juav_jni_HalSitlNative.cpp ArdupilotNativeCommon.cpp HalSitlNativeCommon.cpp -lArduCopterSitl -lpthread -ldl -Wl,--no-undefined -shared -DCONFIG_HAL_BOARD=HAL_BOARD_SITL -DCONFIG_HAL_BOARD_SUBTYPE=HAL_BOARD_SUBTYPE_NONE -fpermissive -o lib/libJuavSitlJni.so

clean:
	-rm -rf lib/*.so
