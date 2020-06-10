# this build is working off a clean checkout of ardupilot
# @ commit 22c9081c8325ec8d980906fe7b1d23f29080a216
ARDU_SRC=/home/adamczer/code/ardupilot
#JAVA_HOME=/home/erle/program_files/jdk1.8.0_191/
ARDU_SITL_INPUTS:=$(shell find ${ARDU_SRC}/build/sitl -type f -name "*.o" \
| sed 's/.*loslib.c.0.o//g' \
| sed 's/.*luac.c.0.o//g' \
| tr '\n' ' ')

ARDU_ERLE_COPTER_INPUTS:=$(shell find ${ARDU_SRC}/build/erlebrain2 -type f -name "*.o" \
| sed 's/.*loslib.c.0.o//g' \
| sed 's/.*luac.c.0.o//g' \
| tr '\n' ' ')

all: sitl

sitl: stage-files arducopter-sitl-lib juav-sitl-jni

erle: stage-files arducopter-erle-copter arducopter-erle-copter-lib juav-erle-copter-lib

stage-files:
	$(shell cp -r ardupilot/* $(ARDU_SRC))

arducopter-erle-copter:
	cd $(ARDU_SRC) && ./waf configure && ./waf --board=erlebrain2 copter

arducopter-erle-copter-lib: $(ARDU_ERLE_COPTER_INPUTS)
	/usr/bin/arm-linux-gnueabihf-g++ -std=gnu++11 -o lib/libArduCopterErleCopter.so -shared $(ARDU_ERLE_COPTER_INPUTS)

juav-erle-copter-lib:
	g++ -L./lib -fPIC -std=gnu++11 -I"./includes" -I"${JAVA_HOME}/include" -I"${JAVA_HOME}/include/linux" -I"$(ARDU_SRC)/libraries/" -I"$(ARDU_SRC)/libraries/AP_HAL_SITL/" -I"$(ARDU_SRC)/libraries/AP_HAL/" -I"${ARDU_SRC}/build/erlebrain2/libraries/GCS_MAVLink/" -I"$(ARDU_SRC)/libraries/AP_Common/missing/" -I"$(ARDU_SRC)/libraries/AP_HAL_SITL/" -I"$(ARDU_SRC)/ArduCopter/" ub_cse_juav_jni_ArdupilotNative.cpp -lArduCopterSitl -lpthread -ldl -Wl,--no-undefined -shared -DCONFIG_HAL_BOARD=HAL_BOARD_LINUX -DCONFIG_HAL_BOARD_SUBTYPE=HAL_BOARD_SUBTYPE_LINUX_ERLEBRAIN2 -fpermissive -o lib/libJuavErleCopterJni.so

# sim_vehicle.py -v ArduCopter --console --map
# resotore original sources by checking out file
# this file needs to be installed to /usr/local/lib/libArduCopterSitl.so
arducopter-sitl-lib : $(ARDU_SITL_INPUTS)
	-$(shell sim_vehicle.py -v ArduCopter --console --map)
	g++ -std=gnu++11 -o lib/libArduCopterSitl.so -shared $(ARDU_SITL_INPUTS)
	


#BUILD native jni library replacing with reimplemented versions of files to allow access to JUAV
juav-sitl-jni:
	g++ -L./lib -fPIC -std=gnu++11 -I"./includes" -I"${JAVA_HOME}/include" -I"${JAVA_HOME}/include/linux" -I"$(ARDU_SRC)/libraries/" -I"$(ARDU_SRC)/libraries/AP_HAL_SITL/" -I"$(ARDU_SRC)/libraries/AP_HAL/" -I"${ARDU_SRC}/build/sitl/libraries/GCS_MAVLink/" -I"$(ARDU_SRC)/libraries/AP_Common/missing/" -I"$(ARDU_SRC)/libraries/AP_HAL_SITL/" -I"$(ARDU_SRC)/ArduCopter/" ub_cse_juav_jni_ArdupilotNative.cpp -lArduCopterSitl -lpthread -ldl -Wl,--no-undefined -shared -DCONFIG_HAL_BOARD=HAL_BOARD_SITL -DCONFIG_HAL_BOARD_SUBTYPE=HAL_BOARD_SUBTYPE_NONE -fpermissive -o lib/libJuavSitlJni.so

clean:
	-rm -rf lib/*.so
