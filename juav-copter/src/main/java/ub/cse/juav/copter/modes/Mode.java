package ub.cse.juav.copter.modes;

import ub.cse.juav.copter.AcAttitudeControl;

import java.util.HashMap;
import java.util.Map;

public abstract class Mode {
    private AcAttitudeControl acAttitudeControler;

    public Mode(AcAttitudeControl acAttitudeControl) {
        this.acAttitudeControler = acAttitudeControl;
    }

    public AcAttitudeControl getAcAttitudeControler() {
        return acAttitudeControler;
    }

    private static boolean isAutoYawModeAutoYawHold() {
        //TODO native auto_yaw.mode() == AUTO_YAW_HOLD
        throw new IllegalStateException("unimplemented");
    }

    public void landRunHorizontalControl() {

//        call native portion
        nativePortionOfLandRunHorizontalControl();

        //TODO call native upto the below section
        // populate nav_roll, nav_pitch, target_yaw_rate return them from native code
        float nav_roll = getNavRoll();
        float nav_pitch = getNavPitch();
        float target_yaw_rate = getYawRate();

        // call attitude controller
        attitudeControllerCommon(nav_roll,nav_pitch,target_yaw_rate);
    }

    public void attitudeControllerCommon(float nav_roll, float nav_pitch, float target_yaw_rate) {
        if (isAutoYawModeAutoYawHold()) {
            // roll & pitch from waypoint controller, yaw rate from pilot
            acAttitudeControler.inputEulerAngleRollPitchEulerRateYaw(nav_roll, nav_pitch, target_yaw_rate);
        } else {
            // roll, pitch from waypoint controller, yaw heading from auto_heading()
            acAttitudeControler.inputEulerAngleRollPitchYaw(nav_roll, nav_pitch, getAutoYawYaw(), true);
        }
    }

    public void run() {
        run(true);
    }

    public void run(boolean b) {
        throw new IllegalStateException("Wrong call, should be using underling implementation not this class.");
    }

    private void nativePortionOfLandRunHorizontalControl() {
        //TODO native
        throw new IllegalStateException("unimplemented");
    }

    protected float getNavPitch() {
        //todo native
        throw new IllegalStateException("unimplemented");
    }

    protected float getNavRoll() {
        //todo native
        throw new IllegalStateException("unimplemented");
    }

    protected float getYawRate() {
        //todo native
        throw new IllegalStateException("unimplemented");
    }

    private float getAutoYawYaw() {
        //todo native
        throw new IllegalStateException("unimplemented");
    }

    // RTL states
    protected enum RTLState {
        RTL_Starting(0),
        RTL_InitialClimb(1),
        RTL_ReturnHome(2),
        RTL_LoiterAtHome(3),
        RTL_FinalDescent(4),
        RTL_Land(5);
        private int value;
        private static Map map = new HashMap<>();

        private RTLState(int value) {
            this.value = value;
        }

        static {
            for (RTLState pageType : RTLState.values()) {
                map.put(pageType.value, pageType);
            }
        }

        public static RTLState valueOf(int pageType) {
            return (RTLState) map.get(pageType);
        }

        public int getValue() {
            return value;
        }
    };

}
