#!/bin/bash
mkdir -p jni/includes
cd target/classes
javah ub.cse.juav.jni.ArdupilotNative
cp *.h ../../jni/includes/
