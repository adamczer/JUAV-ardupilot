package ub.cse.juav.copter.modes;

import ub.cse.juav.copter.AcAttitudeControl;
import ub.cse.juav.jni.ArdupilotNative;
import ub.cse.juav.jni.ArdupilotNativeWrapper;
import com.fiji.fivm.r1.fivmRuntime;
import ub.cse.juav.jni.FijiJniSwitch;
import ub.cse.juav.copter.Copter;

public class ModeAuto extends Mode{

//    Auto_TakeOff 0
//    Auto_WP 1
//    Auto_Land 2
//    Auto_RTL 3
//    Auto_CircleMoveToEdge 4
//    Auto_Circle 5
//    Auto_Spline 6
//    Auto_NavGuided 7
//    Auto_Loiter 8
//    Auto_LoiterToAlt 9
//    Auto_NavPayloadPlace 10

    public ModeAuto(AcAttitudeControl acAttitudeControl) {
        super(acAttitudeControl);
    }

    @Override
    public void run() {
        // call the correct auto controller
        long time1 = System.nanoTime();
        switch (getCurrentAutoMode()) {

            case 0:
                takeoffRun();
                break;

            case 1:
            case 4:
                wpRun(); //has attitude control in java
                break;

            case 2:
                landRun();
                break;

            case 3:
                rtlRun();
                break;

            case 5:
                circleRun(); //has attitude control in java
                break;

            case 6:
                splineRun(); //has attitude control in java
                break;

            case 7:
                if(isNavGidedEnabled())
                    navGuidedRun();
                break;

            case 8:
                loiterRun(); //has attitude control in java
                break;

            case 9:
                loiterToAltRun();
                break;

            case 10:
                payloadPlaceRun();
                break;
        }
	long time2 = System.nanoTime();
        if (Copter.LOG_TIMING) {
            if (FijiJniSwitch.usingFiji) {
		fivmRuntime.logPrint("Auto: ");
		fivmRuntime.logPrint(Long.toString(time1));
		fivmRuntime.logPrint(", ");
		fivmRuntime.logPrint(Long.toString(time2));
		fivmRuntime.logPrint(", ");
            } else {
                System.out.format("Auto: %d, %d, ", time1, time2);
            }
        }
    }

    private void payloadPlaceRun() {
        ArdupilotNativeWrapper.nativeAutoModePayloadPlaceRun();
    }

    private void loiterToAltRun() {
        ArdupilotNativeWrapper.nativeAutoModeLoiterToAltRun();
    }

    private void loiterRun() {
        ArdupilotNativeWrapper.nativeAutoModeLoiterRunPriorToAttitude();

        getAcAttitudeControler().inputEulerAngleRollPitchEulerRateYaw(ArdupilotNativeWrapper.nativeGetWpNavRoll(),ArdupilotNativeWrapper.nativeGetWpNavPitch(), ArdupilotNative.nativeAutoModeGetTargetYawRate());

    }

    private void navGuidedRun() {
        ArdupilotNativeWrapper.nativeAutoModeNavGuidedRun();
    }

    private boolean isNavGidedEnabled() {
    return ArdupilotNativeWrapper.nativeAutoModeIsNavGuidedEnabled();
    }

    private void splineRun() {
        ArdupilotNativeWrapper.nativeAutoModeSplineRunPriorToAttitude();
        float roll = ArdupilotNativeWrapper.nativeGetWpNavRoll();
        float pitch = ArdupilotNativeWrapper.nativeGetWpNavRoll();

        int autoYawMode = ArdupilotNativeWrapper.nativeAutoModeGetAutoYawMode();
        if (autoYawMode == 0/*AUTO_YAW_HOLD*/) {
            float targetYaw = ArdupilotNativeWrapper.nativeAutoModeGetTargetYawRate();
            // roll & pitch from waypoint controller, yaw rate from pilot
            getAcAttitudeControler().inputEulerAngleRollPitchEulerRateYaw(roll, pitch, targetYaw);
        } else {
            float yaw = ArdupilotNativeWrapper.nativeAutoModeGetAutoYawYaw();
            getAcAttitudeControler().inputEulerAngleRollPitchYaw(roll, pitch, yaw, true);
        }
    }

    private void circleRun() {
        ArdupilotNativeWrapper.nativeAutoModeCircleRunPriorToAttitude();

        float roll = ArdupilotNativeWrapper.nativeGetCopterCircleNavRoll();
        float pitch = ArdupilotNativeWrapper.nativeGetCopterCircleNavPitch();

        int autoYawMode = ArdupilotNativeWrapper.nativeAutoModeGetAutoYawMode();
        if (autoYawMode == 0/*AUTO_YAW_HOLD*/) {
            float targetYaw = ArdupilotNativeWrapper.nativeGetCopterCircleNavYaw();
            // roll & pitch from waypoint controller, yaw rate from pilot
            getAcAttitudeControler().inputEulerAngleRollPitchYaw(roll, pitch, targetYaw,true);
        } else {
            float yaw = ArdupilotNativeWrapper.nativeAutoModeGetAutoYawYaw();
            getAcAttitudeControler().inputEulerAngleRollPitchYaw(roll, pitch, yaw, true);
        }
    }

    private void rtlRun() {
        ArdupilotNativeWrapper.nativeAutoModeRtlRun();
    }

    private void landRun() {
        ArdupilotNativeWrapper.nativeAutoModeLandRun();
    }

    private void wpRun() {
        ArdupilotNativeWrapper.modeAutoWpRunPriorToAttitudeControl();

        float roll = ArdupilotNativeWrapper.nativeGetWpNavRoll();
        float pitch = ArdupilotNativeWrapper.nativeGetWpNavPitch();

        int autoYawMode = ArdupilotNativeWrapper.nativeAutoModeGetAutoYawMode();
        if (autoYawMode == 0/*AUTO_YAW_HOLD*/) {
            float targetYawRate = ArdupilotNativeWrapper.nativeAutoModeGetTargetYawRate();
            // roll & pitch from waypoint controller, yaw rate from pilot
            getAcAttitudeControler().inputEulerAngleRollPitchEulerRateYaw(roll, pitch, targetYawRate);
        } else {
            float yaw = ArdupilotNativeWrapper.nativeAutoModeGetAutoYawYaw();
            getAcAttitudeControler().inputEulerAngleRollPitchYaw(roll, pitch, yaw, true);
        }
    }

    private void takeoffRun() {
        //native
        ArdupilotNativeWrapper.nativeAutoModeTakeoffRun();
    }

    private int getCurrentAutoMode() {
        return ArdupilotNativeWrapper.nativeGetModeAutoMode();
    }
}
