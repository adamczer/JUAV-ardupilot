#!/usr/bin/env bash
rm -rf juav-fiji
mkdir juav-fiji
cd juav-fiji

cp ../../../juav-native/juav-native-ardupilot/jni/lib/lib*Erle*.so ./
$FIJI_HOME/bin/fivmc -j4 \
--sys-libs "-lpthread -ldl -lm -lJuavErleCopterJni" \
-o JuavFiji ../target/*-with-dependencies.jar $JUAV_SRC/lib/rtsj.jar \
--main ub.cse.juav.copter.HalLinuxClass

echo "AS ROOT run:" 
echo "> cd juav-fiji"
echo "> export ARDU_SRC=/path/to/ardupilot/checkout"
echo "> ./JuavFiji fiji"
