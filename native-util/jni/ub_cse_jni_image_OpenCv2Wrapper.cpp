#include <jni.h>
#include <ub_cse_jni_image_OpenCv2Wrapper.h>
#include <iostream>
#include "opencv2/highgui/highgui.hpp"
#include "opencv2/imgproc/imgproc.hpp"
#include "opencv2/core/core.hpp"
using namespace std;
using namespace cv;
VideoCapture videoSource;
Mat frame;
int centroidX;
int centroidY;
int binerizeThresholdLow;
int binerizeThresholdHigh;

JNIEXPORT void JNICALL Java_ub_cse_jni_image_OpenCv2Wrapper_setBinerizeThreshold
  (JNIEnv * env, jobject thisObj, jint l, jint h) {
    binerizeThresholdLow = l;
    binerizeThresholdHigh = h;
  }

JNIEXPORT jboolean JNICALL Java_ub_cse_jni_image_OpenCv2Wrapper_initializeCamera
  (JNIEnv * env, jobject thisObj) {
    if(!videoSource.open(0)) {
        cout << "didnt open camera" << endl;
        return false;
    }
    return true;
  }

JNIEXPORT jint JNICALL Java_ub_cse_jni_image_OpenCv2Wrapper_pullWidth
  (JNIEnv * env, jobject thisObj) {
    return frame.cols;
  }

JNIEXPORT jint JNICALL Java_ub_cse_jni_image_OpenCv2Wrapper_pullHeight
  (JNIEnv * env, jobject thisObj) {
    return frame.rows;
  }

void doCommonImageStuff() {
    //smoothen image
    GaussianBlur(frame, frame, Size(15, 15), 0);
    //convert to gray for thresholding
    cvtColor(frame, frame, COLOR_BGR2GRAY);
    //threshold it to be white/black
    threshold(frame, frame, binerizeThresholdLow,binerizeThresholdHigh,THRESH_BINARY);
    //determine moments to get centroid... not sure if needed
    int overThreshold = 0;
    for(int r = 0; r < frame.rows; r++) {
      for(int c = 0; c < frame.cols -1; c++){
        if((int)frame.at<uchar>(cv::Point2i(c,r))>binerizeThresholdLow) {
          overThreshold++;
        }
      }
    }
    if(overThreshold>100) {
        Moments m = moments(frame,true);
        Point p(m.m10/m.m00, m.m01/m.m00);
        centroidX = p.x;
        centroidY = p.y;
    } else {
        centroidY = -1;
        centroidX = -1;
    }
}

JNIEXPORT jboolean JNICALL Java_ub_cse_jni_image_OpenCv2Wrapper_takePicture
  (JNIEnv * env, jobject thisObj) {
    videoSource >> frame;
    if(frame.empty()) {
      cout << "frame is empty" << endl;
      return false;
    }
    doCommonImageStuff();
    return true;
  }

JNIEXPORT jboolean JNICALL Java_ub_cse_jni_image_OpenCv2Wrapper_loadImageFile
  (JNIEnv * env, jobject thisObj, jstring filePath) {
    const char *nativeString = env->GetStringUTFChars(filePath, 0);
    frame = imread(nativeString);
    doCommonImageStuff();
    env->ReleaseStringUTFChars(filePath, nativeString);
    return true;
  }

JNIEXPORT jint JNICALL Java_ub_cse_jni_image_OpenCv2Wrapper_getBinerizeValue
  (JNIEnv * env, jobject thisObj, jint x, jint y) {
        if((int)frame.at<uchar>(cv::Point2i(x,y))>binerizeThresholdLow)
          return binerizeThresholdHigh;
        else
          return 0;
  }

JNIEXPORT jint JNICALL Java_ub_cse_jni_image_OpenCv2Wrapper_getCentroidX
  (JNIEnv *, jobject) {
    return centroidX;
  }

JNIEXPORT jint JNICALL Java_ub_cse_jni_image_OpenCv2Wrapper_getCentroidY
  (JNIEnv *, jobject) {
    return centroidY;
  }
