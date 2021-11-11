#!/bin/bash
mkdir -p jni/includes
cd target/classes
javah ub.cse.jni.image.OpenCv2Wrapper
cp *.h ../../jni/includes/
