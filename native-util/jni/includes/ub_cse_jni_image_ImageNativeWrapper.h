/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class ub_cse_jni_image_ImageNativeWrapper */

#ifndef _Included_ub_cse_jni_image_ImageNativeWrapper
#define _Included_ub_cse_jni_image_ImageNativeWrapper
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     ub_cse_jni_image_ImageNativeWrapper
 * Method:    loadImageFile
 * Signature: (Ljava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_ub_cse_jni_image_ImageNativeWrapper_loadImageFile
  (JNIEnv *, jobject, jstring);

/*
 * Class:     ub_cse_jni_image_ImageNativeWrapper
 * Method:    pullWidth
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_ub_cse_jni_image_ImageNativeWrapper_pullWidth
  (JNIEnv *, jobject);

/*
 * Class:     ub_cse_jni_image_ImageNativeWrapper
 * Method:    pullHeight
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_ub_cse_jni_image_ImageNativeWrapper_pullHeight
  (JNIEnv *, jobject);

/*
 * Class:     ub_cse_jni_image_ImageNativeWrapper
 * Method:    readPixelRed
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_ub_cse_jni_image_ImageNativeWrapper_readPixelRed
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     ub_cse_jni_image_ImageNativeWrapper
 * Method:    readPixelGreen
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_ub_cse_jni_image_ImageNativeWrapper_readPixelGreen
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     ub_cse_jni_image_ImageNativeWrapper
 * Method:    readPixelBlue
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_ub_cse_jni_image_ImageNativeWrapper_readPixelBlue
  (JNIEnv *, jobject, jint, jint);

#ifdef __cplusplus
}
#endif
#endif