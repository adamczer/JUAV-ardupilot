#ifndef _Included_HalSitlNativeCommon
#define _Included_HalSitlNativeCommon

#ifdef __cplusplus
extern "C" {
#endif

/*
 * Class:     ub_cse_juav_jni_HalSitlNative
 * Method:    nativeInitizationPriorToControlLoop
 * Signature: (Ljava/lang/String;)V
 */
void HalSitlNative_nativeInitizationPriorToControlLoop
  (const char *);

/*
 * Class:     ub_cse_juav_jni_HalSitlNative
 * Method:    nativeHalSitlInnerLoopAfterCallBacks
 * Signature: ()V
 */
void HalSitlNative_nativeHalSitlInnerLoopAfterCallBacks
  ();

/*
 * Class:     ub_cse_juav_jni_HalSitlNative
 * Method:    getHalSitlSchedulerShouldReboot
 * Signature: ()Z
 */
bool HalSitlNative_getHalSitlSchedulerShouldReboot
  ();

/*
 * Class:     ub_cse_juav_jni_HalSitlNative
 * Method:    getHalSitlSchedulerShouldExit
 * Signature: ()Z
 */
bool HalSitlNative_getHalSitlSchedulerShouldExit
  ();

/*
 * Class:     ub_cse_juav_jni_HalSitlNative
 * Method:    sitlFillStackNan
 * Signature: ()V
 */
void HalSitlNative_sitlFillStackNan
  ();

#ifdef __cplusplus
}
#endif
#endif