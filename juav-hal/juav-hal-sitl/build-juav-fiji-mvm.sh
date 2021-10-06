#!/usr/bin/env bash
rm -rf juav-fiji-mvm
mkdir juav-fiji-mvm
cd juav-fiji-mvm

$FIJI_HOME/bin/fivmc \
--sys-libs "-lpthread -ldl -lm -lJuavSitlJni" \
-o JuavFiji ../target/juav-hal-sitl-0.1-SNAPSHOT-jar-with-dependencies.jar \
--32 \
--g-def-max-mem 128M \
--g-def-immortal-mem 0M \
--payload \
--rt-library=NONE \
--rt-verbosity-limit 100 \
--main ub.cse.juav.copter.HalSitl

${FIJI_HOME}/bin/fivmc \
--32 \
--g-def-max-mem 128M \
--g-def-immortal-mem 0M \
--payload \
--rt-library=NONE \
--rt-verbosity-limit 100 \
-o simple \
../../../juav-mvm/target/juav-mvm-0.1-SNAPSHOT-jar-with-dependencies.jar \
--main ub.cse.juav.mvm.payloads.SimplePayload

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
-o mvm-simple \
--sys-libs "-lpthread -ldl -lm -lJuavSitlJni" \
--32 \
--g-def-max-mem 256M \
--g-def-immortal-mem 0M \
--link-payload JuavFiji \
--link-payload simple \
--rt-library=NONE \
--rt-verbosity-limit 100 \
../../../juav-mvm/target/juav-mvm-0.1-SNAPSHOT-jar-with-dependencies.jar \
--main ub.cse.juav.mvm.vmconfig.VMConfig

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
