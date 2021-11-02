all:
	$(shell rm -rf ./lib)
	$(shell mkdir lib)

sitl:
	g++ -m32 -g -fPIC -std=gnu++11 -I"./includes" -I"./includes" -I"${JAVA_HOME}/include" -I"${JAVA_HOME}/include/linux" ub_cse_jni_image_ImageNativeWrapper.cpp -lpng -ldl -Wl,--no-undefined -shared -fpermissive -o lib/libNativeUtil.so

erle:
	arm-linux-gnueabihf-g++ -g -fPIC -std=gnu++11 -I"./includes" -I"${JAVA_HOME}/include" -I"${JAVA_HOME}/include/linux" ub_cse_jni_image_ImageNativeWrapper.cpp ub_cse_jni_image_ImageCameraWrapper.cpp -lpng -lopencv_video -lopencv_core -lopencv_highgui -ldl -Wl,--no-undefined -shared -fpermissive -o lib/libNativeUtil.so
#	arm-linux-gnueabihf-g++ -g -fPIC -std=gnu++11 -I"./includes" -I"${JAVA_HOME}/include" -I"${JAVA_HOME}/include/linux" ub_cse_jni_image_ImageNativeWrapper.cpp -lpng -ldl -Wl,--no-undefined -shared -fpermissive -o lib/libNativeUtil.so

clean:
	-rm -rf lib/*.so
