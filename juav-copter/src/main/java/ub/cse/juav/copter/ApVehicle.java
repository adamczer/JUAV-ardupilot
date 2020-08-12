package ub.cse.juav.copter;

import ub.cse.juav.jni.ArdupilotNativeWrapper;

public class ApVehicle implements Callback{
    ApScheduler scheduler;

    public void setScheduler(ApScheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void setSchedulerGDt(float f) {
        ArdupilotNativeWrapper.nativeSetAPVehicleSchedulerGDt(f);
    }


    public void loop() {
        scheduler.loop();
        setSchedulerGDt(scheduler.getLoopPeriodS());
    }
}
