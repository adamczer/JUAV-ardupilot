#ifndef _Included_HalLinuxNativeCommon
#define _Included_HalLinuxNativeCommon

#ifdef __cplusplus
extern "C" {
#endif
bool HalLinuxNative_nativeHalLinuxShouldExit
   ();

void HalLinuxNative_nativeHalLinuxInitializationPriorToControlLoop
  ();

void HalLinuxNative_nativeHalLinuxAfterShouldExit
  ();

#ifdef __cplusplus
}
#endif
#endif