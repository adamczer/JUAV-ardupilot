package ub.cse.juav.copter;

import ub.cse.juav.jni.ArdupilotNative;

public class ApVehicle implements Callback{
    ApScheduler scheduler;

    public void setScheduler(ApScheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void setSchedulerGDt(float f) {
        ArdupilotNative.nativeSetAPVehicleSchedulerGDt(f);
    }


    public void loop() {
        scheduler.loop();
        setSchedulerGDt(scheduler.getLoopPeriodS());
    }
}
