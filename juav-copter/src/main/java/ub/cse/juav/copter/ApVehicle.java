package ub.cse.juav.copter;

public class ApVehicle implements Callback{
    ApScheduler scheduler;

    public void setScheduler(ApScheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void setSchedulerGDt(float f) {
        //todo native
        // ap_vehicle G_Dt = f
        throw new IllegalStateException("unimplemented");
    }


    public void loop() {
        scheduler.loop();
        setSchedulerGDt(scheduler.getLoopPeriodS());
    }
}
