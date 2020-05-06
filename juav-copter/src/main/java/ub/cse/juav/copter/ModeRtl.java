package ub.cse.juav.copter;

import static ub.cse.juav.copter.Mode.RTLState.*;

public class ModeRtl extends Mode{
    public ModeRtl(AcAttitudeControl acAttitudeControl) {
        super(acAttitudeControl);
    }

    // rtl_climb_return_run - implements the initial climb, return home and descent portions of RTL which all rely on the wp controller
//      called by rtl_run at 100hz or more
    public void climbReturnRun()
    {
        //TODO call native to this point again
        nativePortionOfClimbReturnRun();
       // populate nav_roll, nav_pitch, target_yaw_rate return them from native code
        // call attitude controller
        float nav_roll = getNavRoll();
        float nav_pitch = getNavPitch();
        float target_yaw_rate = getYawRate();

        attitudeControllerCommon(nav_roll,nav_pitch,target_yaw_rate);

        // check if we've completed this stage of RTL
        setStateComplete(getWpNavReachedWpDestination());
    }

    // rtl_climb_return_descent_run - implements the initial climb, return home and descent portions of RTL which all rely on the wp controller
//      called by rtl_run at 100hz or more
    public void loiterAtHomeRun()
    {
        //TODO call native to this point again
        nativePortionOfLoiterAtHome1();
        // populate nav_roll, nav_pitch, target_yaw_rate return them from native code
        float nav_roll = getNavRoll();
        float nav_pitch = getNavPitch();
        float target_yaw_rate = getYawRate();

        // call attitude controller
        attitudeControllerCommon(nav_roll,nav_pitch,target_yaw_rate);

        nativePortionOfLoiterAtHome2();
    }

    // rtl_run - runs the return-to-launch controller
// should be called at 100hz or more
    public void run(boolean disarmOnLand)
    {
        if (!isMotorsArmed()) {
            return;
        }

        // check if we need to move to next state
        if (isStateComplete()) {
            switch (getState()) {
                case RTL_Starting:
                    build_path();
                    climb_start();
                    break;
                case RTL_InitialClimb:
                    return_start();
                    break;
                case RTL_ReturnHome:
                    loiterathome_start();
                    break;
                case RTL_LoiterAtHome:
                    if (isRtlPathLand() || isCopterFailsafeRadio()) {
                        land_start();
                    }else{
                        descent_start();
                    }
                    break;
                case RTL_FinalDescent:
                    // do nothing
                    break;
                case RTL_Land:
                    // do nothing - rtl_land_run will take care of disarming motors
                    break;
            }
        }

        // call the correct run function
        switch (getState()) {

            case RTL_Starting:
                // should not be reached:
                setState(RTL_InitialClimb);
//                FALLTHROUGH;

            case RTL_InitialClimb:
                climbReturnRun();
                break;

            case RTL_ReturnHome:
                climbReturnRun();
                break;

            case RTL_LoiterAtHome:
                loiterAtHomeRun();
                break;

            case RTL_FinalDescent:
                descent_run();
                break;

            case RTL_Land:
                land_run(disarmOnLand);
                break;
        }
    }

    private void descent_start() {
        //todo native
        throw new IllegalStateException("unimplemented");
    }

    private void land_run(boolean disarmOnLand) {
        //todo native
        throw new IllegalStateException("unimplemented");
    }

    private void land_start() {
        //todo native
        throw new IllegalStateException("unimplemented");
    }

    private void loiterathome_start() {
        //todo native
        throw new IllegalStateException("unimplemented");
    }

    private void return_start() {
        //todo native
        throw new IllegalStateException("unimplemented");
    }

    private void descent_run() {
        //todo native
        throw new IllegalStateException("unimplemented");
    }

    private void climb_start() {
        //todo native
        throw new IllegalStateException("unimplemented");
    }

    private void build_path() {
        //todo native
        throw new IllegalStateException("unimplemented");
    }

    private boolean isMotorsArmed() {
        //todo native return if motors are armed.
        //        motors->armed()
        throw new IllegalStateException("unimplemented");
    }

    private boolean isStateComplete() {
        //todo native return _state_complete
        throw new IllegalStateException("unimplemented");
    }

    private RTLState getState() {
        //todo native return the correct value of enum
        throw new IllegalStateException("unimplemented");
//        int val = the native getter
//        return RTLState.valueOf(2);
    }

    private void setState(RTLState state) {
        //todo native set the state variable to be the correct int
        int valToSet = state.getValue();
        throw new IllegalStateException("unimplemented");
    }





    private void nativePortionOfLoiterAtHome2() {
        // check if we've completed this stage of RTL
        //todo portion after running attitude controller
        throw new IllegalStateException("not implemented");
    }

    private void nativePortionOfLoiterAtHome1() {
        //todo portion before running attitude controller
        throw new IllegalStateException("not implemented");
    }

    private void nativePortionOfClimbReturnRun() {
        //TODO native
        throw new IllegalStateException("unimplemented");
    }

    private void setStateComplete(boolean val) {
//         _state_complete = wp_nav->reached_wp_destination();
        //todo native set the _state_complete = val
        throw new IllegalStateException("not implemented");
    }

    private boolean getWpNavReachedWpDestination() {
//        TODO native return  wp_nav->reached_wp_destination()
        throw new IllegalStateException("unimplemented");
    }

    private boolean isRtlPathLand() {
//      todo native return  rtl_path.land
        throw new IllegalStateException("unimplemented");
    }

    private boolean isCopterFailsafeRadio() {
//        todo native return copter.failsafe.radio
        throw new IllegalStateException("unimplemented");
    }
}
