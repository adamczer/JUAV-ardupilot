#!/bin/bash

#This script is launched automatically in Erle-Brain 2
#on every boot and loads the autopilot

# JUAV modified to launch jUAV instead, just uncomment
# the section corresponding to JUAV - JAVA or JUAV - FIJI
# and reboot so long as everything is build correctly then all should work
# assumptions ardupilot + juav has been build on the copter
# ARDU_SRC, JAVA_HOME, JUAV_SRC are set

RPIPROC=$(cat /proc/cpuinfo |grep "Hardware" | awk '{print $3}')
if [ "$RPIPROC" == "BCM2708" ]; then
        #echo "Raspberry Pi 1/0"
        APM_BIN_DIR="/home/erle/PXFmini"
        wifi="10.0.0.2:6000"
        ros=`sudo systemctl is-enabled ros.service`
        if [ "$ros" == "enabled" ]; then
                `sudo systemctl stop ros.service`
                `sudo systemctl disable ros.service`
                `sudo systemctl stop robot_blockly.service`
                `sudo systemctl disable robot_blockly.service`
                `sudo systemctl daemon-reload`
        fi
else
        APM_BIN_DIR="/home/erle"
        wifi="127.0.0.1:6001"
        #echo "Raspberry Pi 2"
	VERSION=`cat /etc/issue | grep 3`
	if [ -z $VERSION ]; then
		GPS="/dev/ttyAMA0"
	else
		GPS="/dev/ttyS0"
	fi
fi

FLAGS="-l /home/erle/APM/logs -t /home/erle/APM/terrain/"

date
sleep 10
echo "-A udp:$wifi -B $GPS -C /dev/ttyUSB0 $FLAGS" > /tmp/apm-args.txt
# ARDUPILOT
#while :; do
#        $APM_BIN_DIR/arducopter -A udp:$wifi -B $GPS -C /dev/ttyUSB0 $FLAGS
#done >> /home/erle/APM/info/copter.log 2>&1

#JUAV - JAVA
#while :; do
#${JAVA_HOME}/bin/java -Xmx256m -Xms256m -cp ${JUAV_SRC}/juav-hal/juav-hal-erle-copter/target/juav-hal-erle-copter-0.1-SNAPSHOT-jar-with-dependencies.jar -Djava.library.path=${JUAV_SRC}/juav-native/juav-native-ardupilot/jni/lib/ ub.cse.juav.copter.HalLinuxClass
#done >> /home/erle/APM/info/copter.log 2>&1


#JUAV - FIJI
#while :; do
#cd ${JUAV_SRC}/juav-hal/juav-hal-erle-copter/juav-fiji/ && ./JuavFiji fiji
#done >> /home/erle/APM/info/copter.log 2>&1
