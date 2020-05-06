package ub.cse.juav.copter;

public class ModeStabilize extends Mode{
    public ModeStabilize(AcAttitudeControl acAttitudeControl) {
        super(acAttitudeControl);
    }

    // stabilize_run - runs the main stabilize controller
// should be called at 100hz or more
    @Override
    public void run()
    {
        //TODO call native to this point again
        nativePortionOfModeStabilizeRun1();
        // populate target_roll, target_pitch, target_yaw_rate return them from native code
        float target_roll = getTargetRoll();
        float target_pitch = getTargetPitch();
        float target_yaw_rate = getTargetYawRate();

        // call attitude controller
        getAcAttitudeControler().inputEulerAngleRollPitchEulerRateYaw(target_roll, target_pitch, target_yaw_rate);

        // output pilot's throttle
        nativePortionOfModeStabilizeRun2();
    }

    private float getTargetYawRate() {
        //todo native
        throw new IllegalStateException("unimplemented");
    }

    private float getTargetPitch() {
        //todo native
        throw new IllegalStateException("unimplemented");
    }

    private float getTargetRoll() {
        //todo native
        throw new IllegalStateException("unimplemented");
    }

    private void nativePortionOfModeStabilizeRun1() {
        //todo native prior to mode_stabilize run
        throw new IllegalStateException("unimplemented");
    }

    private void nativePortionOfModeStabilizeRun2() {
//        attitude_control->set_throttle_out(get_pilot_desired_throttle(),
//        true, g.throttle_filt);
        //todo native prior to mode_stabilize run
        throw new IllegalStateException("unimplemented");
    }

}
