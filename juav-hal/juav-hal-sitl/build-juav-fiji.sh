#!/usr/bin/env bash
rm -rf juav-fiji
mkdir juav-fiji
cd juav-fiji


$FIJI_HOME/bin/fivmc \
--sys-libs "-lpthread -ldl -lm -lJuavSitlJni" \
-o JuavFiji ../target/juav-hal-sitl-*-with-dependencies.jar $JUAV_SRC/lib/rtsj.jar \
--64 \
--main ub.cse.juav.copter.HalSitl

cp /usr/local/lib/*Sitl*.so .
echo "AS ROOT run:" 
echo "> cd juav-fiji"
echo "> export ARDU_SRC=/path/to/ardupilot/checkout"
echo "> ./JuavFiji fiji"
