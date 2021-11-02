#!/bin/bash
mkdir -p jni/includes
cd target/classes
javah ub.cse.jni.image.ImageNativeWrapper
javah ub.cse.jni.image.ImageCameraWrapper
cp *.h ../../jni/includes/
