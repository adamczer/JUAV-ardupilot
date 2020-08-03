#!/bin/bash
# ensure arducopter lib is in ldconfig
#sudo ln -s /home/adamczer/code/juav2/juav-native/juav-native-ardupilot/jni/lib/libArduCopterSitl.so /usr/local/lib/
# ls -la /usr/local/lib/libArduCopterSitl.so
# sudo ldconfig
# ./run-juav-copter.sh
export JUAV_SRC=/home/adamczer/code/juav2
export ARDU_SRC=/home/adamczer/code/ardupilot

java -cp ${JUAV_SRC}/juav-hal/juav-hal-sitl/target/juav-hal-sitl-*-jar-with-dependencies.jar -Djava.library.path=${JUAV_SRC}/juav-native/juav-native-ardupilot/jni/lib/ ub.cse.juav.copter.HalSitl
