#!/bin/bash
# ensure arducopter lib is in ldconfig
#sudo ln -s /home/adamczer/code/juav2/juav-native/juav-native-ardupilot/jni/lib/libArduCopterSitl.so /usr/local/lib/
# ls -la /usr/local/lib/libArduCopterSitl.so
# sudo ldconfig
# ./run-juav-copter.sh

java -cp /home/adamczer/code/juav2/juav-hal/juav-hal-sitl/target/juav-hal-sitl-*-jar-with-dependencies.jar -Djava.library.path=/home/adamczer/code/juav2/juav-native/juav-native-ardupilot/jni/lib/ ub.cse.juav.copter.HalSitl
