package ub.cse.juav.copter.modes;

import ub.cse.juav.copter.AcAttitudeControl;
import ub.cse.juav.jni.ArdupilotNativeWrapper;

public class ModeGuided extends Mode{

//    Guided_TakeOff 0,
//    Guided_WP 1,
//    Guided_Velocity 2,
//    Guided_PosVel 3,
//    Guided_Angle 4,

    public ModeGuided(AcAttitudeControl acAttitudeControl) {
        super(acAttitudeControl);
    }

    @Override
    public void run() {

        int guided_mode = getGuidedMode();
        // call the correct auto controller
        switch (guided_mode) {

            case 0: //Guided_TakeOff:
                // run takeoff controller
                takeoffRun();
                break;

            case 1: //Guided_WP:
                // run position controller
                posControlRun();
                break;

            case 2: //Guided_Velocity:
                // run velocity controller
                velControlRun();
                break;

            case 3://Guided_PosVel:
                // run position-velocity controller
                posvelControlRun();
                break;

            case 4://Guided_Angle:
                // run angle controller
                angleControlRun();
                break;
        }
    }

    private void angleControlRun() {
        ArdupilotNativeWrapper.nativeGuidedAngleControlRunPriorToAttitude();
        float rollIn = ArdupilotNativeWrapper.nativeGuidedGetAngleControlRunRollIn();
        float pitchIn = ArdupilotNativeWrapper.nativeGuidedGetAngleControlRunPitchIn();
        if(ArdupilotNativeWrapper.nativeGuidedIsAngleStateUseYawRate()) {
            float yawRateIn = ArdupilotNativeWrapper.nativeGuidedGetAngleControlRunYawRateIn();
            getAcAttitudeControler().inputEulerAngleRollPitchEulerRateYaw(rollIn, pitchIn, yawRateIn);
        } else {
            float yawIn = ArdupilotNativeWrapper.nativeGuidedGetAngleControlRunYawIn();
            getAcAttitudeControler().inputEulerAngleRollPitchYaw(rollIn, pitchIn,
                    yawIn,true);
        }

        ArdupilotNativeWrapper.nativeGuidedAngleControlRunAfterAttitude();
    }

    private void posvelControlRun() {
        ArdupilotNativeWrapper.nativeGuidedPosVelControlRunPriorToAttitude();
        float posControlRoll = ArdupilotNativeWrapper.nativeGuidedGetPosControlRoll();
        float posControlPitch = ArdupilotNativeWrapper.nativeGuidedGetPosControlPitch();
        attitudeControlCommon(posControlRoll,posControlPitch);
    }

    private void velControlRun() {
        ArdupilotNativeWrapper.nativeGuidedVelControlRunPriorToAttitude();
        float posControlRoll = ArdupilotNativeWrapper.nativeGuidedGetPosControlRoll();
        float posControlPitch = ArdupilotNativeWrapper.nativeGuidedGetPosControlPitch();
        attitudeControlCommon(posControlRoll,posControlPitch);
    }

    private void attitudeControlCommon(float roll, float pitch) {
        int autoYawMode = ArdupilotNativeWrapper.nativeGuidedGetAutoYawMode();
        if (autoYawMode == 0/*AUTO_YAW_HOLD*/) {
            float targetYawRate = ArdupilotNativeWrapper.nativeGuidedGetTargetYawRate();
            // roll & pitch from waypoint controller, yaw rate from pilot
            getAcAttitudeControler().inputEulerAngleRollPitchEulerRateYaw(roll, pitch, targetYawRate);
        } else if (autoYawMode == 6 /*AUTO_YAW_RATE*/) {
            float autoYawRateCds = ArdupilotNativeWrapper.nativeGuidedGetAutoYawRateCds();
            // roll & pitch from velocity controller, yaw rate from mavlink command or mission item
            getAcAttitudeControler().inputEulerAngleRollPitchEulerRateYaw(roll, pitch, autoYawRateCds);
        } else {
            float autoYawYaw = ArdupilotNativeWrapper.nativeGuidedGetAutoYawYaw();
            getAcAttitudeControler().inputEulerAngleRollPitchYaw(roll, pitch, autoYawYaw, true);
        }
    }

    private void posControlRun() {
        ArdupilotNativeWrapper.nativeGuidedPosControlRunPriorToAttitude();
        float wpNavRoll = ArdupilotNativeWrapper.nativeGuidedGetWpNavRoll();
        float wpNavPitch = ArdupilotNativeWrapper.nativeGuidedGetWpNavPitch();
        attitudeControlCommon(wpNavRoll,wpNavPitch);
    }

    private void takeoffRun() {
        ArdupilotNativeWrapper.nativeGuidedTakeoffRun();
    }

    private int getGuidedMode() {
        return ArdupilotNativeWrapper.nativeGuidedGetMode();
    }
}
