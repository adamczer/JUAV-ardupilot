#!/usr/bin/env bash
rm -rf juav-fiji
mkdir juav-fiji
cd juav-fiji

cp ../../../juav-native/juav-native-ardupilot/jni/lib/lib*Sitl*.so ./
$FIJI_HOME/bin/fivmc -j4 --32 \
--sys-libs "-lpthread -ldl -lm -lJuavSitlJni" \
-o JuavFiji ../target/*-with-dependencies.jar $JUAV_SRC/lib/rtsj.jar \
--main ub.cse.juav.copter.HalSitl

echo "AS ROOT run:"
echo "> cd juav-fiji"
echo "> export ARDU_SRC=/path/to/ardupilot/checkout"
echo "> ./JuavFiji fiji"
