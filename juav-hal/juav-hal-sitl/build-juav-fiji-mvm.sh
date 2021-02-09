#!/usr/bin/env bash
rm -rf juav-fiji-mvm
mkdir juav-fiji-mvm
cd juav-fiji-mvm

$FIJI_HOME/bin/fivmc \
--sys-libs "-lpthread -ldl -lm -lJuavSitlJni" \
-o JuavFiji ../target/juav-hal-sitl-0.1-SNAPSHOT-jar-with-dependencies.jar \
--32 \
--g-def-max-mem 128M \
--g-def-immortal-mem 64M \
--payload \
--rt-library=NONE \
--rt-verbosity-limit 100 \
--main ub.cse.juav.copter.HalSitl

${FIJI_HOME}/bin/fivmc \
--32 \
--g-def-max-mem 128M \
--g-def-immortal-mem 64M \
--payload \
--rt-library=NONE \
--rt-verbosity-limit 100 \
-o apps \
../../../juav-payloads/target/juav-payloads-0.1-SNAPSHOT-jar-with-dependencies.jar \
--main ub.cse.juav.payloads.simple.SimplePayload

${FIJI_HOME}/bin/fivmc \
-o mvm \
--sys-libs "-lpthread -ldl -lm -lJuavSitlJni" \
--32 \
--g-def-max-mem 256M \
--g-def-immortal-mem 128M \
--link-payload JuavFiji \
--link-payload apps \
--rt-library=NONE \
--rt-verbosity-limit 100 \
../../../juav-payloads/target/juav-payloads-0.1-SNAPSHOT-jar-with-dependencies.jar \
--main ub.cse.juav.payloads.vmconfig.VMConfig
