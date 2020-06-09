#!/bin/bash
java -Xmx1G -Xms1G -cp /home/adamczer/code/juav2/juav-hal/juav-hal-sitl/target/juav-hal-sitl-*-jar-with-dependencies.jar -Djava.library.path=/home/adamczer/code/juav2/juav-native/juav-native-sitl/jni/lib/ ub.cse.juav.copter.HalSitl
