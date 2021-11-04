#!/usr/bin/env bash
sudo rm -rf juav-fiji-mvm
mkdir juav-fiji-mvm
cd juav-fiji-mvm

$FIJI_HOME/bin/fivmc \
--sys-libs "-lpthread -ldl -lm -lJuavSitlJni -lNativeUtil" \
-o JuavFiji ../target/juav-hal-sitl-0.1-SNAPSHOT-jar-with-dependencies.jar \
--32 \
--g-def-max-mem 128M \
--g-def-immortal-mem 0M \
--payload \
--rt-library=NONE \
--rt-verbosity-limit 100 \
--main ub.cse.juav.copter.HalSitl

# Greedy
if [ "$1" = "greedy" ]; then
  ${FIJI_HOME}/bin/fivmc \
  --32 \
  --g-def-max-mem 128M \
  --g-def-immortal-mem 0M \
  --payload \
  --rt-library=NONE \
  --rt-verbosity-limit 100 \
  -o greedy \
  ../../../juav-mvm/target/juav-mvm-0.1-SNAPSHOT-jar-with-dependencies.jar \
  --main ub.cse.juav.mvm.payloads.GreedyPayload

  ${FIJI_HOME}/bin/fivmc \
  -o mvm-greedy \
  --sys-libs "-lpthread -ldl -lm -lJuavSitlJni" \
  --32 \
  --g-def-max-mem 256M \
  --g-def-immortal-mem 0M \
  --link-payload JuavFiji \
  --link-payload greedy \
  --rt-library=NONE \
  --rt-verbosity-limit 100 \
  ../../../juav-mvm/target/juav-mvm-0.1-SNAPSHOT-jar-with-dependencies.jar \
  --main ub.cse.juav.mvm.vmconfig.VMConfig

elif [ "$1" = "astar" ]; then
  # astar
  echo ASTAR
  ${FIJI_HOME}/bin/fivmc \
  --32 \
  --g-def-max-mem 128M \
  --g-def-immortal-mem 0M \
  --payload \
  --rt-library=NONE \
  --rt-verbosity-limit 100 \
  -o astar \
  ../../../juav-mvm/target/juav-mvm-0.1-SNAPSHOT-jar-with-dependencies.jar \
  --main ub.cse.juav.mvm.payloads.AStarPayload

  ${FIJI_HOME}/bin/fivmc \
  -o mvm-astar \
  --sys-libs "-lpthread -ldl -lm -lJuavSitlJni" \
  --32 \
  --g-def-max-mem 256M \
  --g-def-immortal-mem 0M \
  --link-payload JuavFiji \
  --link-payload astar \
  --rt-library=NONE \
  --rt-verbosity-limit 100 \
  ../../../juav-mvm/target/juav-mvm-0.1-SNAPSHOT-jar-with-dependencies.jar \
  --main ub.cse.juav.mvm.vmconfig.VMConfig

elif [ "$1" = "color" ]; then
  # astar
  echo COLOR
  ${FIJI_HOME}/bin/fivmc \
  --sys-libs "-lNativeUtil" \
  --32 \
  --g-def-max-mem 128M \
  --g-def-immortal-mem 0M \
  --payload \
  --rt-library=NONE \
  --rt-verbosity-limit 100 \
  -o color \
  ../../../juav-mvm/target/juav-mvm-0.1-SNAPSHOT-jar-with-dependencies.jar \
  --main ub.cse.juav.mvm.payloads.LandOnColorThingPayload

  ${FIJI_HOME}/bin/fivmc \
  -o mvm-color \
  --sys-libs "-lpthread -ldl -lm -lJuavSitlJni -lNativeUtil" \
  --32 \
  --g-def-max-mem 256M \
  --g-def-immortal-mem 0M \
  --link-payload JuavFiji \
  --link-payload color \
  --rt-library=NONE \
  --rt-verbosity-limit 100 \
  ../../../juav-mvm/target/juav-mvm-0.1-SNAPSHOT-jar-with-dependencies.jar \
  --main ub.cse.juav.mvm.vmconfig.VMConfig


elif [ "$1" = "none" ]; then
  # none

  ${FIJI_HOME}/bin/fivmc \
  -o mvm-none \
  --sys-libs "-lpthread -ldl -lm -lJuavSitlJni" \
  --32 \
  --g-def-max-mem 256M \
  --g-def-immortal-mem 0M \
  --link-payload JuavFiji \
  --rt-library=NONE \
  --rt-verbosity-limit 100 \
  ../../../juav-mvm/target/juav-mvm-0.1-SNAPSHOT-jar-with-dependencies.jar \
  --main ub.cse.juav.mvm.vmconfig.VMConfig
else
   echo "try again"
fi