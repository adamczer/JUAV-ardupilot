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
        long time1 = System.nanoTime();
        loiterRunPriorToAttitudeControl();

        float target_roll = getLoiterNavTargetRoll();
        float target_pitch = getLoiterNavTargetPitch();
        float target_yaw_rate = getTargetYawRate();

        getAcAttitudeControler().inputEulerAngleRollPitchEulerRateYaw(target_roll,target_pitch,target_yaw_rate);

        loiterRunAfterAttitudeControl();
	long time2 = System.nanoTime();
        if (Copter.LOG_TIMING) {
            if (FijiJniSwitch.usingFiji) {
		fivmRuntime.logPrint("Loiter: ");
		fivmRuntime.logPrint(Long.toString(time1));
		fivmRuntime.logPrint(", ");
		fivmRuntime.logPrint(Long.toString(time2));
		fivmRuntime.logPrint(", ");
            } else {
                System.out.format("Loiter: %d, %d, ", time1, time2);
            }
        }
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
