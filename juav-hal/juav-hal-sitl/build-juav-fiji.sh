#!/usr/bin/env bash
rm -rf juav-fiji
mkdir juav-fiji
cd juav-fiji
mkdir juav-jars
cd juav-jars
unzip ../../target/juav-hal-sitl-*-with-dependencies.jar
rm -rf META-INF
unzip $FIJI_HOME/lib/rtsj.jar
rm -rf META-INF
cd ..
mkdir build
find juav-jars -name \*.class -exec cp {} build/ \;
mkdir libs
cp ../../../juav-native/juav-native-ardupilot/jni/lib/lib*Sitl*.so ./libs/

$FIJI_HOME/bin/fivmc \
--link-dir libs \
--link-dynamic ArduCopterSitl \
--link-dynamic JuavSitlJni \
-o JuavFiji ./build/*.class \
--64 \
--main ub.cse.juav.copter.HalSitl

cp libs/* .
./JuavFiji fiji
cd ..
