package ub.cse.juav.copter;

public class ApScheduler {
    private Copter copter;

    public void setCopter(Copter copter) {
        this.copter = copter;
    }

    public void setHalUtilPersistentDataSchedulerTask(int i) {
//        hal.util->persistent_data.scheduler_task = i
        //todo native
        throw new IllegalStateException("unimplemented");
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
        //todo native
        throw new IllegalStateException("unimplemented");
    }

    private void nativeApSchedulerPriorToFastLoop() {
        //todo native
        throw new IllegalStateException("unimplemented");
    }

    public float getLoopPeriodS() {
//        get_loop_period_s
        //todo native
        throw new IllegalStateException("unimplemented");
    }
}
