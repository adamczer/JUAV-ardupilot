package ub.cse.juav.copter.modes;

import ub.cse.juav.copter.AcAttitudeControl;
import ub.cse.juav.jni.ArdupilotNative;

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
        ArdupilotNative.nativeGuidedAngleControlRunPriorToAttitude();
        float rollIn = ArdupilotNative.nativeGuidedGetAngleControlRunRollIn();
        float pitchIn = ArdupilotNative.nativeGuidedGetAngleControlRunPitchIn();
        if(ArdupilotNative.nativeGuidedIsAngleStateUseYawRate()) {
            float yawRateIn = ArdupilotNative.nativeGuidedGetAngleControlRunYawRateIn();
            getAcAttitudeControler().inputEulerAngleRollPitchEulerRateYaw(rollIn, pitchIn, yawRateIn);
        } else {
            float yawIn = ArdupilotNative.nativeGuidedGetAngleControlRunYawIn();
            getAcAttitudeControler().inputEulerAngleRollPitchYaw(rollIn, pitchIn,
                    yawIn,true);
        }

        ArdupilotNative.nativeGuidedAngleControlRunAfterAttitude();
    }

    private void posvelControlRun() {
        ArdupilotNative.nativeGuidedPosVelControlRunPriorToAttitude();
        float posControlRoll = ArdupilotNative.nativeGuidedGetPosControlRoll();
        float posControlPitch = ArdupilotNative.nativeGuidedGetPosControlPitch();
        attitudeControlCommon(posControlRoll,posControlPitch);
    }

    private void velControlRun() {
        ArdupilotNative.nativeGuidedVelControlRunPriorToAttitude();
        float posControlRoll = ArdupilotNative.nativeGuidedGetPosControlRoll();
        float posControlPitch = ArdupilotNative.nativeGuidedGetPosControlPitch();
        attitudeControlCommon(posControlRoll,posControlPitch);
    }

    private void attitudeControlCommon(float roll, float pitch) {
        int autoYawMode = ArdupilotNative.nativeGuidedGetAutoYawMode();
        if (autoYawMode == 0/*AUTO_YAW_HOLD*/) {
            float targetYawRate = ArdupilotNative.nativeGuidedGetTargetYawRate();
            // roll & pitch from waypoint controller, yaw rate from pilot
            getAcAttitudeControler().inputEulerAngleRollPitchEulerRateYaw(roll, pitch, targetYawRate);
        } else if (autoYawMode == 6 /*AUTO_YAW_RATE*/) {
            float autoYawRateCds = ArdupilotNative.nativeGuidedGetAutoYawRateCds();
            // roll & pitch from velocity controller, yaw rate from mavlink command or mission item
            getAcAttitudeControler().inputEulerAngleRollPitchEulerRateYaw(roll, pitch, autoYawRateCds);
        } else {
            float autoYawYaw = ArdupilotNative.nativeGuidedGetAutoYawYaw();
            getAcAttitudeControler().inputEulerAngleRollPitchYaw(roll, pitch, autoYawYaw, true);
        }
    }

    private void posControlRun() {
        ArdupilotNative.nativeGuidedPosControlRunPriorToAttitude();
        float wpNavRoll = ArdupilotNative.nativeGuidedGetWpNavRoll();
        float wpNavPitch = ArdupilotNative.nativeGuidedGetWpNavPitch();
        attitudeControlCommon(wpNavRoll,wpNavPitch);
    }

    private void takeoffRun() {
        ArdupilotNative.nativeGuidedTakeoffRun();
    }

    private int getGuidedMode() {
        return ArdupilotNative.nativeGuidedGetMode();
    }
}
