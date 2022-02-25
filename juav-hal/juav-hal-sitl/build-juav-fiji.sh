#!/usr/bin/env bash
sudo rm -rf juav-fiji
mkdir juav-fiji
cd juav-fiji

cp ../../../juav-native/juav-native-ardupilot/jni/lib/lib*Sitl*.so ./
cp ../../../native-util/jni/lib/*.so ./
$FIJI_HOME/bin/fivmc -j4 \
--sys-libs "-lpthread -ldl -lm -lJuavSitlJni -lNativeUtil" \
-o JuavFiji ../target/*-with-dependencies.jar \
--main ub.cse.juav.copter.HalSitl

echo "AS ROOT run:"
echo "> cd juav-fiji"
echo "> export ARDU_SRC=/path/to/ardupilot/checkout"
echo "> ./JuavFiji fiji"
