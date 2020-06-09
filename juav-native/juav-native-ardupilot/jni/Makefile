# this build is working off a clean checkout of ardupilot
# @ commit 22c9081c8325ec8d980906fe7b1d23f29080a216
ARDU_SRC=/home/adamczer/code/ardupilot
#JAVA_HOME=/home/erle/program_files/jdk1.8.0_191/
ARDU_INPUTS:=$(shell find ${ARDU_SRC}/build/sitl -type f -name "*.o" \
| sed 's/.*loslib.c.0.o//g' \
| sed 's/.*luac.c.0.o//g' \
| tr '\n' ' ')
#out/libraries/AP_HAL_SITL/HAL_SITL_Class.cpp.0.o

all: arducopter-lib jni

# add exclusions for library files that are reimplemented
# ensure that you have previously built and ran sim-vehicle.py using
# inject updated juav code
# sim_vehicle.py -v ArduCopter --console --map
# resotore original sources by checking out file
# this file needs to be installed to /usr/local/lib/libArduCopterSitl.so
arducopter-lib : $(ARDU_INPUTS)
	$(shell cp -r ardupilot/* /home/adamczer/code/ardupilot/)
	-$(shell sim_vehicle.py -v ArduCopter --console --map)
	g++ -std=gnu++11 -o lib/libArduCopterSitl.so -shared $(ARDU_INPUTS)

#BUILD native jni library replacing with reimplemented versions of files to allow access to JUAV
jni:
	g++ -L./lib -fPIC -std=gnu++11 -I"./includes" -I"${JAVA_HOME}/include" -I"${JAVA_HOME}/include/linux" -I"$(ARDU_SRC)/libraries/" -I"$(ARDU_SRC)/libraries/AP_HAL_SITL/" -I"$(ARDU_SRC)/libraries/AP_HAL/" -I"${ARDU_SRC}/build/sitl/libraries/GCS_MAVLink/" -I"$(ARDU_SRC)/libraries/AP_Common/missing/" -I"$(ARDU_SRC)/libraries/AP_HAL_SITL/" -I"$(ARDU_SRC)/ArduCopter/" ub_cse_juav_jni_ArdupilotNative.cpp -lArduCopterSitl -lpthread -ldl -Wl,--no-undefined -shared -DCONFIG_HAL_BOARD=HAL_BOARD_SITL -DCONFIG_HAL_BOARD_SUBTYPE=HAL_BOARD_SUBTYPE_NONE -fpermissive -o lib/libJuavSitlJni.so

clean:
	-rm -rf lib/libJuavSitlJni.so lib/libArduCopterSitl.so
