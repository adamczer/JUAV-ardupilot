#!/bin/bash

#This script is launched automatically in Erle-Brain 2
#on every boot and loads the autopilot

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

cd /home/erle
export ARDU_SRC=/home/erle/newJuav/ardupilot
#
oldLogs='timing_logs_'$(date '+%Y_%m_%d_%H_%M_%S')
mkdir $oldLogs
mv *.log ${oldLogs}/
mv /home/erle/newJuav/juav2/juav-hal/juav-hal-erle-copter/juav-fiji/*.log ${oldLogs}/

date
sleep 10
echo "-A udp:$wifi -B $GPS -C /dev/ttyUSB0 $FLAGS" > /tmp/apm-args.txt

# ARDUPILOT
#while :; do
#        /home/erle/arducopter -A udp:$wifi -B $GPS -C /dev/ttyUSB0 $FLAGS
#done >> /home/erle/APM/info/copter.log 2>&1


# JUAV ARDUPILOT
#while :; do
#        /home/erle/newJuav/ardupilot/build/erlebrain2/bin/arducopter -A udp:$wifi -B $GPS -C /dev/ttyUSB0 $FLAGS
#done >> /home/erle/APM/info/copter.log 2>&1

#JUAV - JAVA 
#while :; do
#/home/erle/program_files/jdk1.7.0_75/bin/java -Xmx256m -Xms256m -cp /home/erle/newJuav/juav2/juav-hal/juav-hal-erle-copter/target/juav-hal-erle-copter-0.1-SNAPSHOT-jar-with-dependencies.jar:/home/erle/newJuav/juav2/lib/rtsj.jar -Djava.library.path=/home/erle/newJuav/juav2/juav-native/juav-native-ardupilot/jni/lib/ ub.cse.juav.copter.HalLinuxClass java 
#done >> /home/erle/APM/info/copter.log 2>&1

#JUAV - FIJI 
#while :; do
#cd /home/erle/newJuav/juav2/juav-hal/juav-hal-erle-copter/juav-fiji/ && ./JuavFiji fiji 
#done >> /home/erle/APM/info/copter.log 2>&1
