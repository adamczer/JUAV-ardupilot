#!/bin/bash
# ensure arducopter lib is in ldconfig
#sudo ln -s /home/adamczer/code/juav2/juav-native/juav-native-ardupilot/jni/lib/libArduCopterSitl.so /usr/local/lib/
# ls -la /usr/local/lib/libArduCopterSitl.so
# sudo ldconfig
# ./run-juav-copter.sh
# jdk1.8.0_191
# jdk1.7.0_75
#/home/erle/program_files/jdk1.7.0_75/bin/java -cp /home/erle/newJuav/juav2/juav-hal/juav-hal-erle-copter/target/juav-hal-erle-copter-0.1-SNAPSHOT-jar-with-dependencies.jar -Djava.library.path=/home/erle/newJuav/juav2/juav-native/juav-native-ardupilot/jni/lib/ ub.cse.juav.copter.HalLinuxClass -A udp:127.0.0.1:6001 -B /dev/ttyS0 -C /dev/ttyUSB0 -l /home/erle/APM/logs -t /home/erle/APM/terrain/
/home/erle/program_files/jdk1.7.0_75/bin/java -Xmx256m -Xms256m -cp /home/erle/newJuav/juav2/juav-hal/juav-hal-erle-copter/target/juav-hal-erle-copter-0.1-SNAPSHOT-jar-with-dependencies.jar -Djava.library.path=/home/erle/newJuav/juav2/juav-native/juav-native-ardupilot/jni/lib/ ub.cse.juav.copter.HalLinuxClass
