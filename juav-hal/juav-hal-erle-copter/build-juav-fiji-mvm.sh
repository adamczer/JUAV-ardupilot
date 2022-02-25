#!/usr/bin/env bash
rm -rf juav-fiji-mvm
mkdir juav-fiji-mvm
cd juav-fiji-mvm

cp ../../../juav-native/juav-native-ardupilot/jni/lib/lib*Erle*.so ./
cp ../../../native-util/jni/lib/libNativeUtil.so ./
#LD_LIBRARY_PATH=${PWD}
#sudo ldconfig
echo "JUAV payload"
$FIJI_HOME/bin/fivmc --target arm -j8 --32 \
--sys-libs "-lpthread -ldl -lm -lJuavErleCopterJni -lNativeUtil" \
-o JuavFiji ../target/juav-hal-erle-copter-0.1-SNAPSHOT-jar-with-dependencies.jar \
--g-def-max-mem 128M \
--g-def-immortal-mem 0M \
--payload \
--rt-library=NONE \
--main ub.cse.juav.copter.HalLinuxClass


#echo "EXTRA payload"
#${FIJI_HOME}/bin/fivmc --target arm -j8 --32 \
#--g-def-max-mem 128M \
#--g-def-immortal-mem 0M \
#--payload \
#--rt-library=NONE \
#-o apps \
#../../../juav-mvm/target/juav-mvm-0.1-SNAPSHOT-jar-with-dependencies.jar \
#--main ub.cse.juav.mvm.payloads.SimplePayload

#if [ "$1" = "greedy" ]; then
  ${FIJI_HOME}/bin/fivmc --target arm -j8 --32 \
  --g-def-max-mem 128M \
  --g-def-immortal-mem 0M \
  --payload \
  --rt-library=NONE \
  --rt-verbosity-limit 100 \
  -o greedy \
  ../../../juav-mvm/target/juav-mvm-0.1-SNAPSHOT-jar-with-dependencies.jar \
  --main ub.cse.juav.mvm.payloads.GreedyPayload

${FIJI_HOME}/bin/fivmc --target arm -j8 --32 \
-o mvm-greedy \
--sys-libs "-lpthread -ldl -lm -lJuavErleCopterJni -lNativeUtil" \
--g-def-max-mem 256M \
--g-def-immortal-mem 0M \
--link-payload JuavFiji \
--link-payload greedy \
--rt-library=NONE \
../../../juav-mvm/target/juav-mvm-0.1-SNAPSHOT-jar-with-dependencies.jar \
--main ub.cse.juav.mvm.vmconfig.VMConfig

#elif [ "$1" = "astar" ]; then
    # astar
    echo ASTAR
    ${FIJI_HOME}/bin/fivmc --target arm -j8 --32 \
    --g-def-max-mem 128M \
    --g-def-immortal-mem 0M \
    --payload \
    --rt-library=NONE \
    --rt-verbosity-limit 100 \
    -o astar \
    ../../../juav-mvm/target/juav-mvm-0.1-SNAPSHOT-jar-with-dependencies.jar \
    --main ub.cse.juav.mvm.payloads.AStarPayload

    ${FIJI_HOME}/bin/fivmc --target arm -j8 --32 \
    -o mvm-astar \
    --sys-libs "-lpthread -ldl -lm -lJuavErleCopterJni -lNativeUtil" \
    --g-def-max-mem 256M \
    --g-def-immortal-mem 0M \
    --link-payload JuavFiji \
    --link-payload astar \
    --rt-library=NONE \
    --rt-verbosity-limit 100 \
    ../../../juav-mvm/target/juav-mvm-0.1-SNAPSHOT-jar-with-dependencies.jar \
    --main ub.cse.juav.mvm.vmconfig.VMConfig

#  elif [ "$1" = "white" ]; then
      # astar
      echo COLOR
      ${FIJI_HOME}/bin/fivmc --target arm -j8 --32 \
      --sys-libs "-lNativeUtil" \
      --g-def-max-mem 128M \
      --g-def-immortal-mem 0M \
      --payload \
      --rt-library=NONE \
      --rt-verbosity-limit 100 \
      -o color \
      ../../../juav-mvm/target/juav-mvm-0.1-SNAPSHOT-jar-with-dependencies.jar \
      --main ub.cse.juav.mvm.payloads.LandOnColorThingCameraPayload

      ${FIJI_HOME}/bin/fivmc --target arm -j8 --32 \
      -o mvm-color \
      --sys-libs "-lpthread -ldl -lm -lJuavErleCopterJni -lNativeUtil" \
      --g-def-max-mem 256M \
      --g-def-immortal-mem 0M \
      --link-payload JuavFiji \
      --link-payload color \
      --rt-library=NONE \
      --rt-verbosity-limit 100 \
      ../../../juav-mvm/target/juav-mvm-0.1-SNAPSHOT-jar-with-dependencies.jar \
      --main ub.cse.juav.mvm.vmconfig.VMConfig

#elif [ "$1" = "none" ]; then
#  # none

  ${FIJI_HOME}/bin/fivmc  --target arm -j8 --32 \
  -o mvm-none \
  --sys-libs "-lpthread -ldl -lm -lJuavErleCopterJni -lNativeUtil" \
  --g-def-max-mem 256M \
  --g-def-immortal-mem 0M \
  --link-payload JuavFiji \
  --rt-library=NONE \
  --rt-verbosity-limit 100 \
  ../../../juav-mvm/target/juav-mvm-0.1-SNAPSHOT-jar-with-dependencies.jar \
  --main ub.cse.juav.mvm.vmconfig.VMConfig
#else
#   echo "try again"
#fi