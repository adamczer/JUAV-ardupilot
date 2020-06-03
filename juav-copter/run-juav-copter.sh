#!/bin/bash
java -Xmx1G -Xms1G -cp /home/adamczer/code/juav2/juav-copter/target/juav-copter-0.1-SNAPSHOT-jar-with-dependencies.jar -Djava.library.path=/home/adamczer/code/juav2/juav-native/juav-native-sitl/jni/lib/ ub.cse.juav.copter.HalSitl
