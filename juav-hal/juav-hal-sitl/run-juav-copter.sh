#!/bin/bash
taskset -c 0 /home/juav/jdk1.7.0_80/bin/java -Xmx256m -Xms256m -cp ${JUAV_SRC}/juav-hal/juav-hal-sitl/target/juav-hal-sitl-*-jar-with-dependencies.jar -Djava.library.path=${JUAV_SRC}/juav-native/juav-native-ardupilot/jni/lib/ ub.cse.juav.copter.HalSitl java $1
#sudo -E /home/juav/jdk1.7.0_80/bin/java -cp ${JUAV_SRC}/juav-hal/juav-hal-sitl/target/juav-hal-sitl-*-jar-with-dependencies.jar -Djava.library.path=${JUAV_SRC}/juav-native/juav-native-ardupilot/jni/lib/ ub.cse.juav.copter.HalSitl java $1


#taskset -c 0-3 /home/juav/jdk1.7.0_80/bin/java -cp ${JUAV_SRC}/juav-hal/juav-hal-sitl/target/juav-hal-sitl-*-jar-with-dependencies.jar -Djava.library.path=${JUAV_SRC}/juav-native/juav-native-ardupilot/jni/lib/ ub.cse.juav.copter.HalSitl java $1
