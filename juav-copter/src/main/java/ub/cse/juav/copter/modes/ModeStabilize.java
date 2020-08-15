package ub.cse.juav.copter.modes;

import ub.cse.juav.copter.AcAttitudeControl;
import ub.cse.juav.jni.ArdupilotNativeWrapper;
import ub.cse.juav.copter.Copter;

public class ModeStabilize extends Mode{
    public ModeStabilize(AcAttitudeControl acAttitudeControl) {
        super(acAttitudeControl);
    }

    // stabilize_run - runs the main stabilize controller
// should be called at 100hz or more
    @Override
    public void run()
    {
        long time1 = System.nanoTime();
        nativePortionOfModeStabilizeRun1();
        // populate target_roll, target_pitch, target_yaw_rate return them from native code
        float target_roll = getTargetRoll();
        float target_pitch = getTargetPitch();
        float target_yaw_rate = getTargetYawRate();

        // call attitude controller
        getAcAttitudeControler().inputEulerAngleRollPitchEulerRateYaw(target_roll, target_pitch, target_yaw_rate);

        // output pilot's throttle
        nativePortionOfModeStabilizeRun2();
        long time2 = System.nanoTime();
	if (Copter.LOG_TIMING) {
            System.out.format("Stabalize: %d, %d, ", time1, time2);
        }
    }

    private float getTargetYawRate() {
        return ArdupilotNative.getStabilizationModeTargetYawRate();
    }

    private float getTargetPitch() {
        return ArdupilotNative.getStabilizationModeTargetPitch();
    }

    private float getTargetRoll() {
        return ArdupilotNative.getStabilizationModeTargetRoll();
    }

    private void nativePortionOfModeStabilizeRun1() {
        ArdupilotNative.nativeRunBeforeStabilizationCallAttitudeController();
    }

    private void nativePortionOfModeStabilizeRun2() {
        ArdupilotNative.nativeRunAfterStabilizationCallAttitudeController();
    }

}
