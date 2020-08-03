package ub.cse.juav.copter;

import ub.cse.juav.jni.ArdupilotNativeWrapper;

public class ApScheduler {
    private Copter copter;

    public void setCopter(Copter copter) {
        this.copter = copter;
    }

    public void setHalUtilPersistentDataSchedulerTask(int i) {
        ArdupilotNativeWrapper.setHalUtilPersistentDataSchedulerTask(i);
    }

    public void loop()
    {
        nativeApSchedulerPriorToFastLoop();
        // Execute the fast loop
        // ---------------------
//        if (_fastloop_fn) {
            setHalUtilPersistentDataSchedulerTask(-2);
            copter.fast_loop();
            setHalUtilPersistentDataSchedulerTask(-1);
//        }

        nativeApSchedulerPostFastLoop();
    }

    private void nativeApSchedulerPostFastLoop() {
        ArdupilotNativeWrapper.nativeApSchedulerPostToFastLoop();
    }

    private void nativeApSchedulerPriorToFastLoop() {
        ArdupilotNativeWrapper.nativeApSchedulerPriorToFastLoop();
    }

    public float getLoopPeriodS() {
//        get_loop_period_s
        return ArdupilotNativeWrapper.nativeApSchedulerGetLoopPeriodS();
    }
}
