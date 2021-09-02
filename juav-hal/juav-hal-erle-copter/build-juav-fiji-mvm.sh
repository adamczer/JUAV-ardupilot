#!/usr/bin/env bash
rm -rf juav-fiji-mvm
mkdir juav-fiji-mvm
cd juav-fiji-mvm

cp ../../../juav-native/juav-native-ardupilot/jni/lib/lib*Erle*.so ./

#LD_LIBRARY_PATH=${PWD}
#sudo ldconfig
echo "JUAV payload"
$FIJI_HOME/bin/fivmc --target arm -j8 --32 \
--sys-libs "-lpthread -ldl -lm -lJuavErleCopterJni" \
-o JuavFiji ../target/juav-hal-erle-copter-0.1-SNAPSHOT-jar-with-dependencies.jar \
--g-def-max-mem 128M \
--g-def-immortal-mem 0M \
--payload \
--rt-library=NONE \
--main ub.cse.juav.copter.HalLinuxClass
echo "EXTRA payload"
${FIJI_HOME}/bin/fivmc --target arm -j8 --32 \
--g-def-max-mem 128M \
--g-def-immortal-mem 0M \
--payload \
--rt-library=NONE \
-o apps \
../../../juav-mvm/target/juav-mvm-0.1-SNAPSHOT-jar-with-dependencies.jar \
--main ub.cse.juav.mvm.payloads.SimplePayload
echo "MULTI VM CONFIG"
${FIJI_HOME}/bin/fivmc --target arm -j8 --32 \
-o mvm \
--sys-libs "-lpthread -ldl -lm -lJuavErleCopterJni" \
--g-def-max-mem 256M \
--g-def-immortal-mem 0M \
--link-payload JuavFiji \
--link-payload apps \
--rt-library=NONE \
../../../juav-mvm/target/juav-mvm-0.1-SNAPSHOT-jar-with-dependencies.jar \
--main ub.cse.juav.mvm.vmconfig.VMConfig
