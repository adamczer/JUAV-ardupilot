package ub.cse.juav.copter.modes;

import ub.cse.juav.copter.AcAttitudeControl;
import ub.cse.juav.copter.Copter;
import ub.cse.juav.jni.ArdupilotNativeWrapper;
import com.fiji.fivm.r1.fivmRuntime;
import ub.cse.juav.jni.FijiJniSwitch;


public class ModeLoiter extends Mode {
//    Loiter mode = 5
    public ModeLoiter(AcAttitudeControl acAttitudeControl) {
        super(acAttitudeControl);
    }

    @Override
    public void run() {
        loiterRunPriorToAttitudeControl();

        float target_roll = getLoiterNavTargetRoll();
        float target_pitch = getLoiterNavTargetPitch();
        float target_yaw_rate = getTargetYawRate();

        getAcAttitudeControler().inputEulerAngleRollPitchEulerRateYaw(target_roll,target_pitch,target_yaw_rate);

        loiterRunAfterAttitudeControl();
    }

    private float getTargetYawRate() {
        return ArdupilotNativeWrapper.nativeLoiterGetTargetYawRate();
    }

    private float getLoiterNavTargetPitch() {
        return ArdupilotNativeWrapper.nativeLoiterGetTargetPitch();
    }

    private float getLoiterNavTargetRoll() {
        return ArdupilotNativeWrapper.nativeLoiterGetTargetRoll();
    }

    private void loiterRunAfterAttitudeControl() {
        ArdupilotNativeWrapper.nativeLoiterRunAfterAttitudeControl();
    }

    private void loiterRunPriorToAttitudeControl() {
        ArdupilotNativeWrapper.nativeLoiterRunPriorToAttitudeControl();
    }
}
