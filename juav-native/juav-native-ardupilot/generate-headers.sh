#!/bin/bash
mkdir -p jni/includes
cd target/classes
javah ub.cse.juav.jni.ArdupilotNative
javah ub.cse.juav.jni.HalSitlNative
javah ub.cse.juav.jni.HalLinuxNative
cp *.h ../../jni/includes/
