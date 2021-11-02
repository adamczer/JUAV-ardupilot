#include <jni.h>
#include <ub_cse_jni_image_ImageCameraWrapper.h>
#include <iostream>
#include "opencv2/highgui/highgui.hpp"
#include "opencv2/imgproc/imgproc.hpp"
#include "opencv2/core/core.hpp"
using namespace std;
using namespace cv;
VideoCapture videoSource;
Mat frame;

JNIEXPORT jboolean JNICALL Java_ub_cse_jni_image_ImageCameraWrapper_initializeCamera
  (JNIEnv * env, jobject thisClass) {
    if(!videoSource.open(0)) {
            cout << "didnt open camera" << endl;
            return false;
        }
    return true;
  }

JNIEXPORT jint JNICALL Java_ub_cse_jni_image_ImageCameraWrapper_pullWidth
  (JNIEnv * env, jobject thisClass) {
    return frame.cols;
  }

JNIEXPORT jint JNICALL Java_ub_cse_jni_image_ImageCameraWrapper_pullHeight
  (JNIEnv * env, jobject thisClass) {
    return frame.rows;
  }

JNIEXPORT jboolean JNICALL Java_ub_cse_jni_image_ImageCameraWrapper_takePicture
  (JNIEnv * env, jobject thisClass) {
    videoSource >> frame;
    if(frame.empty()) {
        cout << "frame is empty" << endl;
        return false;
    }
    return true;
  }

JNIEXPORT jint JNICALL Java_ub_cse_jni_image_ImageCameraWrapper_readPixelRed
  (JNIEnv * env, jobject thisClass, jint c, jint r) {
    return frame.at<cv::Vec3b>(c,r)[2];
  }

JNIEXPORT jint JNICALL Java_ub_cse_jni_image_ImageCameraWrapper_readPixelGreen
  (JNIEnv *, jobject, jint c, jint r){
    return frame.at<cv::Vec3b>(c,r)[1];
  }

JNIEXPORT jint JNICALL Java_ub_cse_jni_image_ImageCameraWrapper_readPixelBlue
  (JNIEnv *, jobject, jint c, jint r) {
    return frame.at<cv::Vec3b>(c,r)[1];
  }