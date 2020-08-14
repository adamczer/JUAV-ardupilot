package ub.cse.juav.copter.modes;

import ub.cse.juav.copter.AcAttitudeControl;
import ub.cse.juav.jni.ArdupilotNative;
import ub.cse.juav.copter.Copter;

public class ModeLoiter extends Mode {
//    Loiter mode = 5
    public ModeLoiter(AcAttitudeControl acAttitudeControl) {
        super(acAttitudeControl);
    }

    @Override
    public void run() {
        long time1 = System.nanoTime();
        loiterRunPriorToAttitudeControl();

        float target_roll = getLoiterNavTargetRoll();
        float target_pitch = getLoiterNavTargetPitch();
        float target_yaw_rate = getTargetYawRate();

        getAcAttitudeControler().inputEulerAngleRollPitchEulerRateYaw(target_roll,target_pitch,target_yaw_rate);

        loiterRunAfterAttitudeControl();
	long time2 = System.nanoTime();
        if (Copter.LOG_TIMING) {
            System.out.format("Loiter: %d, %d, ", time1, time2);
        }
    }

    private float getTargetYawRate() {
        return ArdupilotNative.nativeLoiterGetTargetYawRate();
    }

    private float getLoiterNavTargetPitch() {
        return ArdupilotNative.nativeLoiterGetTargetPitch();
    }

    private float getLoiterNavTargetRoll() {
        return ArdupilotNative.nativeLoiterGetTargetRoll();
    }

    private void loiterRunAfterAttitudeControl() {
        ArdupilotNative.nativeLoiterRunAfterAttitudeControl();
    }

    private void loiterRunPriorToAttitudeControl() {
        ArdupilotNative.nativeLoiterRunPriorToAttitudeControl();
    }
}
