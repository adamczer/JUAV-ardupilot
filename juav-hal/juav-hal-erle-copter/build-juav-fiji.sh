#!/usr/bin/env bash
# this was used building on the physical frame

rm -rf juav-fiji
mkdir juav-fiji
cd juav-fiji
mkdir juav-jars
cd juav-jars
unzip ../../target/*-with-dependencies.jar
rm -rf META-INF
unzip $FIJI_HOME/lib/rtsj.jar
rm -rf META-INF
cd ..
mkdir build
find juav-jars -name \*.class -exec cp {} build/ \;
mkdir libs
cp ../../../juav-native/juav-native-ardupilot/jni/lib/lib*Erle*.so ./libs/

$FIJI_HOME/bin/fivmc \
--link-dir libs \
--link-dynamic ArduCopterErleCopter \
--link-dynamic JuavErleCopterJni \
-o JuavFiji ./build/*.class \
--main ub.cse.juav.copter.HalLinuxClass

cp libs/* .
./JuavFiji fiji
cd ..
