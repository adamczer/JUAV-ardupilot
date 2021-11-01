#!/usr/bin/env bash
rm -rf juav-fiji
mkdir juav-fiji
cd juav-fiji

cp ../../../juav-native/juav-native-ardupilot/jni/lib/lib*Erle*.so ./
if arch | grep -q "arm"; then
 $FIJI_HOME/bin/fivmc -j4 \
--sys-libs "-lpthread -ldl -lm -lJuavErleCopterJni" \
-o JuavFiji ../target/*-with-dependencies.jar \
--g-def-max-mem 256M \
--main ub.cse.juav.copter.HalLinuxClass
else
 $FIJI_HOME/bin/fivmc --target arm -j8 \
--sys-libs "-lpthread -ldl -lm -lJuavErleCopterJni" \
-o JuavFiji ../target/*-with-dependencies.jar \
--g-def-max-mem 256M \
--main ub.cse.juav.copter.HalLinuxClass
fi
echo "AS ROOT run:" 
echo "> cd juav-fiji"
echo "> export ARDU_SRC=/path/to/ardupilot/checkout"
echo "> ./JuavFiji fiji"
