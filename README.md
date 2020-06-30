# JUAV-ardupilot
Java integration with Ardupilot

Getting started 

jUAV makes use of maven for building java code.

currently jUAV is based off ardupilot (https://github.com/ArduPilot/ardupilot.git) at 
commit 22c9081c8325ec8d980906fe7b1d23f29080a216 
which was the latest commit at the time of creation

set enviorment variable for root of ardupilot

> ARDU_SRC -> root of ardupilot checkout

> JUAV_SRC -> root of jUAV checkout

you will want to check out this commit and complete a full build including submodules 
required for simulation (https://ardupilot.org/dev/docs/building-setup-linux.html#building-setup-linux)

once this is all up and running you can navigate to the current checkout of jUAV.

WARNING/TODO: some hardcoaded paths may still exist. 
these types of problems will be clear and easy to fix. these have been refactored to enviornment variables

JUAV_ROOT=juav checkout location
build java code
JUAV_ROOT> mvn install
create the shared libraries for ardupilot and jUAV native calls.
JUAV_ROOT/juav-native/juav-native-ardupilot/jni> make sitl
> ln -s $JUAV_ROOT/juav-native/juav-native-ardupilot/jni/lib/libArduCopterSitl.so /usr/local/lib/

> sudo ldconfig 

JUAV_ROOT/juav-hal/juav-hal-sitl> ./run-juav-copter.sh

should now see the terminal waiting for the simulator to start, after fixing any path problems

should be in your path from building arducopter, 
juav loads source changes needed for extra access, 
can be reverted to get back to base ardupilot if desired.
> sim_vehicle.py -v ArduCopter --console --map
should now see vehicle and be able to issue commands via SITL


