package ub.cse.juav.copter;

import ub.cse.juav.math.JuavMath;
import ub.cse.juav.math.JuavMatrix3f;
import ub.cse.juav.math.JuavQuaternion;
import ub.cse.juav.math.JuavVector3f;

import javax.vecmath.Vector2f;
import javax.vecmath.Vector3f;

public class AcAttitudeControl {

    // thrust_heading_rotation_angles - calculates two ordered rotations to move the att_from_quat quaternion to the att_to_quat quaternion.
// The first rotation corrects the thrust vector and the second rotation corrects the heading vector.
    public void thrustHeadingRotationAngles(JuavQuaternion attToQuat, JuavQuaternion attFromQuat, JuavVector3f attDiffAngle, float thrustVecDot) {
        JuavMatrix3f att_to_rot_matrix = new JuavMatrix3f(); // rotation from the target body frame to the inertial frame.
        attToQuat.rotationMatrix(att_to_rot_matrix);

        JuavVector3f attToThrustVec = att_to_rot_matrix.opStar(new JuavVector3f(0,0,1));

        JuavMatrix3f attFromRotMatrix = new JuavMatrix3f(); // rotation from the current body frame to the inertial frame.
        attFromQuat.rotationMatrix(attFromRotMatrix);
        JuavVector3f attFromThrustVec = attFromRotMatrix.opStar(new JuavVector3f(0,0,1));

        // the dot product is used to calculate the current lean angle for use of external functions
        setThrustAngle((float) Math.acos(JuavMath.constrainFloat(new JuavVector3f(0,0,1).opStar(attFromThrustVec),-1.0f,1.0f)));

        // the cross product of the desired and target thrust vector defines the rotation vector
        JuavVector3f thrustVecCross = (JuavVector3f) attFromThrustVec.opPercent(attToThrustVec);

        // the dot product is used to calculate the angle between the target and desired thrust vectors
        thrustVecDot = (float) Math.acos(JuavMath.constrainFloat(attFromThrustVec.opStar(attToThrustVec), -1.0f, 1.0f));

        // Normalize the thrust rotation vector
        float thrustVectorLength = thrustVecCross.length();
        if (JuavMath.isZero(thrustVectorLength) || JuavMath.isZero(thrustVecDot)) {
            thrustVecCross = new JuavVector3f(0, 0, 1);
            thrustVecDot = 0.0f;
        } else {
            thrustVecCross.opSlash(thrustVectorLength);
        }
        JuavQuaternion thrustVecCorrectionQuat = new JuavQuaternion();
        thrustVecCorrectionQuat.fromAxisAngle(thrustVecCross, thrustVecDot);

        // Rotate thrust_vec_correction_quat to the att_from frame
        thrustVecCorrectionQuat = attFromQuat.inversed().opStar(thrustVecCorrectionQuat).opStar( attFromQuat);

        // calculate the remaining rotation required after thrust vector is rotated transformed to the att_from frame
        JuavQuaternion yawVecCorrectionQuat = thrustVecCorrectionQuat.inversed().opStar(attFromQuat.inversed()).opStar(attToQuat);

        // calculate the angle error in x and y.
        JuavVector3f rotation = new JuavVector3f();
        thrustVecCorrectionQuat.toAxisAngel(rotation);
        attDiffAngle.x = rotation.x;
        attDiffAngle.y = rotation.y;

        // calculate the angle error in z (x and y should be zero here).
        yawVecCorrectionQuat.toAxisAngel(rotation);
        attDiffAngle.z = rotation.z;

        // Todo: Limit roll an pitch error based on output saturation and maximum error.

        // Limit Yaw Error based on maximum acceleration - Update to include output saturation and maximum error.
        // Currently the limit is based on the maximum acceleration using the linear part of the SQRT controller.
        // This should be updated to be based on an angle limit, saturation, or unlimited based on user defined parameters.
        if (!JuavMath.isZero(getPAngleYawKp()) && Math.abs(attDiffAngle.z) > getAcAttitudeAccelYControlerMaxRadss() / getPAngleYawKp()) {
            attDiffAngle.z = JuavMath.constrainFloat(JuavMath.wrapPI(attDiffAngle.z), -getAcAttitudeAccelYControlerMaxRadss()/ getPAngleYawKp(), getAcAttitudeAccelYControlerMaxRadss() / getPAngleYawKp());
            yawVecCorrectionQuat.fromAxisAngle(new JuavVector3f(0.0f, 0.0f, attDiffAngle.z));
            attToQuat = attFromQuat.opStar(thrustVecCorrectionQuat).opStar(yawVecCorrectionQuat);
        }
    }


    // Proportional controller with piecewise sqrt sections to constrain second derivative
    float sqrtController(float error, float p, float secondOrdLim, float dt)
    {
        float correction_rate;
        if (JuavMath.isNegative(secondOrdLim) || JuavMath.isZero(secondOrdLim)) {
            // second order limit is zero or negative.
            correction_rate = error * p;
        } else if (JuavMath.isZero(p)) {
            // P term is zero but we have a second order limit.
            if (JuavMath.isPositive(error)) {
                correction_rate = JuavMath.safeSqrt(2.0f * secondOrdLim * (error));
            } else if (JuavMath.isNegative(error)) {
                correction_rate = -JuavMath.safeSqrt(2.0f * secondOrdLim* (-error));
            } else {
                correction_rate = 0.0f;
            }
        } else {
            // Both the P and second order limit have been defined.
            float linear_dist = secondOrdLim / JuavMath.sq(p);
            if (error > linear_dist) {
                correction_rate = JuavMath.safeSqrt(2.0f * secondOrdLim * (error - (linear_dist / 2.0f)));
            } else if (error < -linear_dist) {
                correction_rate = -JuavMath.safeSqrt(2.0f * secondOrdLim * (-error - (linear_dist / 2.0f)));
            } else {
                correction_rate = error * p;
            }
        }
        if (!JuavMath.isZero(dt)) {
            // this ensures we do not get small oscillations by over shooting the error correction in the last time step.
            return JuavMath.constrainFloat(correction_rate, -Math.abs(error) / dt, Math.abs(error) / dt);
        } else {
            return correction_rate;
        }
    }




    // Update rate_target_ang_vel using attitude_error_rot_vec_rad
    JuavVector3f updateAngVelTargetFromAttError(JuavVector3f attitudeErrorRotVecRad)
    {
        JuavVector3f rateTargetAngVel = new JuavVector3f();
        // Compute the roll angular velocity demand from the roll angle error
        if (getUseSqrtController()) {
            rateTargetAngVel.x = sqrtController(attitudeErrorRotVecRad.x, getPAngleRollKp(), JuavMath.constrainFloat(get_accel_roll_max_radss() / 2.0f, getAcAttitudeAccelRpControllerMinRadss(), getAcAttitudeAccelRpControllerMaxRadss()), getDt());
        } else {
            rateTargetAngVel.x = getPAngleRollKp() * attitudeErrorRotVecRad.x;
        }

        // Compute the pitch angular velocity demand from the pitch angle error
        if (getUseSqrtController()) {
            rateTargetAngVel.y = sqrtController(attitudeErrorRotVecRad.y, getPAnglePitchKp(), JuavMath.constrainFloat(get_accel_pitch_max_radss() / 2.0f, getAcAttitudeAccelRpControllerMinRadss(), getAcAttitudeAccelRpControllerMaxRadss()), getDt());
        } else {
            rateTargetAngVel.y = getPAnglePitchKp() * attitudeErrorRotVecRad.y;
        }

        // Compute the yaw angular velocity demand from the yaw angle error
        if (getUseSqrtController()) {
            rateTargetAngVel.z = sqrtController(attitudeErrorRotVecRad.z, getPAngleYawKp(), JuavMath.constrainFloat(get_accel_yaw_max_radss() / 2.0f, getAcAttitudeAccelYControlerMinRadss(), getAcAttitudeAccelYControlerMaxRadss()), getDt());
        } else {
            rateTargetAngVel.z = getPAngleYawKp() * attitudeErrorRotVecRad.z;
        }

        return rateTargetAngVel;
    }

    // limits angular velocity
    void angVelLimit(JuavVector3f eulerRad, float angVelRollMax, float angVelPitchMax, float angVelYawMax)
    {
        if (JuavMath.isZero(angVelRollMax) || JuavMath.isZero(angVelPitchMax)) {
            if (!JuavMath.isZero(angVelRollMax)) {
                eulerRad.x = JuavMath.constrainFloat(eulerRad.x, -angVelRollMax, angVelRollMax);
            }
            if (!JuavMath.isZero(angVelPitchMax)) {
                eulerRad.y = JuavMath.constrainFloat(eulerRad.y, -angVelPitchMax, angVelPitchMax);
            }
        } else {
            Vector2f thrust_vector_ang_vel = new Vector2f(eulerRad.x / angVelRollMax, eulerRad.y / angVelPitchMax);
            float thrust_vector_length = thrust_vector_ang_vel.length();
            if (thrust_vector_length > 1.0f) {
                eulerRad.x = thrust_vector_ang_vel.x * angVelRollMax / thrust_vector_length;
                eulerRad.y = thrust_vector_ang_vel.y * angVelPitchMax / thrust_vector_length;
            }
        }
        if (!JuavMath.isZero(angVelYawMax)) {
            eulerRad.z = JuavMath.constrainFloat(eulerRad.z, -angVelYawMax, angVelYawMax);
        }
    }

    // Calculates the body frame angular velocities to follow the target attitude
    public void attitudeControllerRunQuat()
    {
        JuavQuaternion thisAttitudeTargetQuat = getAttitudeTargetQuat();
        JuavVector3f thisRateTargetAngVel = getRateTargetAngVel();
        JuavVector3f thisAttitudeTargetAngVel = getAttitudeTargetAngVel();
        float thisThrustErrorAngle = getThrustErrorAngle();
        // Retrieve quaternion vehicle attitude
        JuavQuaternion attitudeVehicleQuat = getAhrsGetQuatBodyToNed();

        // Compute attitude error
        JuavVector3f attitudeErrorVector = new JuavVector3f();
        thrustHeadingRotationAngles(thisAttitudeTargetQuat, attitudeVehicleQuat, attitudeErrorVector, thisThrustErrorAngle);

        // Compute the angular velocity target from the attitude error
        JuavVector3f rateTargetAngVel = updateAngVelTargetFromAttError(attitudeErrorVector);

        // Add feedforward term that attempts to ensure that roll and pitch errors rotate with the body frame rather than the reference frame.
        // todo: this should probably be a matrix that couples yaw as well.
        rateTargetAngVel.x += JuavMath.constrainFloat(attitudeErrorVector.y, -JuavMath.M_PI / 4, JuavMath.M_PI / 4) * getAhrsGyro().z;
        rateTargetAngVel.y += -JuavMath.constrainFloat(attitudeErrorVector.x, -JuavMath.M_PI / 4, JuavMath.M_PI / 4) * getAhrsGyro().z;

        angVelLimit(thisRateTargetAngVel, (float) Math.toRadians(getAngVelRollMax()), (float)Math.toRadians(getAngVelPitchMax()), (float)Math.toRadians(getAngVelYawMax()));

        // Add the angular velocity feedforward, rotated into vehicle frame
        JuavQuaternion attitudeTargetAngVelQuat = new JuavQuaternion(0.0f, thisAttitudeTargetAngVel.x, thisAttitudeTargetAngVel.y, thisAttitudeTargetAngVel.z);
        JuavQuaternion toToFromQuat = attitudeVehicleQuat.inversed().opStar(getAttitudeTargetQuat());
        JuavQuaternion desiredAngVelQuat = toToFromQuat.inversed().opStar(attitudeTargetAngVelQuat).opStar(toToFromQuat);

        // Correct the thrust vector and smoothly add feedforward and yaw input
        setFeedforwardScalar(1.0f);
        if (thisThrustErrorAngle > getAcAttitudeThrustErrorAngle() * 2.0f) {
            thisRateTargetAngVel.z = getAhrsGyro().z;
        } else if (thisThrustErrorAngle > getAcAttitudeThrustErrorAngle()) {
            setFeedforwardScalar((1.0f - (thisThrustErrorAngle - getAcAttitudeThrustErrorAngle()) / getAcAttitudeThrustErrorAngle()));
            thisRateTargetAngVel.x += desiredAngVelQuat.x * getFeedforwardScalar();
            thisRateTargetAngVel.y += desiredAngVelQuat.y * getFeedforwardScalar();
            thisRateTargetAngVel.z += desiredAngVelQuat.z;
            thisRateTargetAngVel.z = getAhrsGyro().z * (1.0f - getFeedforwardScalar()) + thisRateTargetAngVel.z * getFeedforwardScalar();
        } else {
            thisRateTargetAngVel.x += desiredAngVelQuat.x;
            thisRateTargetAngVel.y += desiredAngVelQuat.y;
            thisRateTargetAngVel.z += desiredAngVelQuat.z;
        }

        if (getRateBfFfEnabled()) {
            // rotate target and normalize
            JuavQuaternion attitudeTargetUpdateQuat = new JuavQuaternion();
            attitudeTargetUpdateQuat.fromAxisAngle(new JuavVector3f(thisAttitudeTargetAngVel.x * getDt(), thisAttitudeTargetAngVel.y * getDt(), thisAttitudeTargetAngVel.z * getDt()));
            thisAttitudeTargetQuat = thisAttitudeTargetQuat.opStar(attitudeTargetUpdateQuat);
            thisAttitudeTargetQuat.normalize();
        }

        // ensure Quaternions stay normalized
        thisAttitudeTargetQuat.normalize();

        // Record error to handle EKF resets
        setAttitudeAngError(attitudeVehicleQuat.inversed().opStar(thisAttitudeTargetQuat));

        //sendBackResultsJuav
        setAttitudeTargetQuat(thisAttitudeTargetQuat);
        setRateTargetAngVel(thisRateTargetAngVel);
        setAttitudeTargetAngVel(thisAttitudeTargetAngVel);
        setThrustErrorAngle(thisThrustErrorAngle);
    }

    // Command an euler roll and pitch angle and an euler yaw rate with angular velocity feedforward and smoothing
    void inputEulerAngleRollPitchEulerRateYaw(float eulerRollAngleCd, float eulerPitchAngleCd, float eulerYawRateCds)
    {
        //these are computed on and need to be sent back to the native code
        JuavQuaternion thisAttitudeTargetQuat = getAttitudeTargetQuat();
        JuavVector3f thisAttitudeTargetEulerAngle = getAttitudeTargetEulerAngle();
        JuavVector3f thisAttitudeTargetEulerRate = getAttitudeTargetEulerRate();
        JuavVector3f thisAttitudeTargetAngVel = getAttitudeTargetAngVel();

        // Convert from centidegrees on public interface to radians
        float eulerRollAngle = (float) Math.toRadians(eulerRollAngleCd * 0.01f);
        float eulerPitchAngle = (float) Math.toRadians(eulerPitchAngleCd * 0.01f);
        float eulerYawRate = (float) Math.toRadians(eulerYawRateCds * 0.01f);

        // calculate the attitude target euler angles

        thisAttitudeTargetQuat.toEuler(thisAttitudeTargetEulerAngle);

        // Add roll trim to compensate tail rotor thrust in heli (will return zero on multirotors)
        eulerRollAngle += getRollTrimRad();

        if (getRateBfFfEnabled()) {
            // translate the roll pitch and yaw acceleration limits to the euler axis
            Vector3f eulerAccel = eulerAccelLimit(thisAttitudeTargetEulerAngle, new JuavVector3f(get_accel_roll_max_radss(), get_accel_pitch_max_radss(), get_accel_yaw_max_radss()));

            // When acceleration limiting and feedforward are enabled, the sqrt controller is used to compute an euler
            // angular velocity that will cause the euler angle to smoothly stop at the input angle with limited deceleration
            // and an exponential decay specified by smoothing_gain at the end.
            thisAttitudeTargetEulerRate.x = inputShapingAngle(JuavMath.wrapPI(eulerRollAngle - thisAttitudeTargetEulerAngle.x), getInputTc(), eulerAccel.x, thisAttitudeTargetEulerRate.x, getDt());
            thisAttitudeTargetEulerRate.y = inputShapingAngle(JuavMath.wrapPI(eulerPitchAngle - thisAttitudeTargetEulerAngle.y), getInputTc(), eulerAccel.y, thisAttitudeTargetEulerRate.y, getDt());

            // When yaw acceleration limiting is enabled, the yaw input shaper constrains angular acceleration about the yaw axis, slewing
            // the output rate towards the input rate.
            thisAttitudeTargetEulerRate.z = inputShapingAngVel(thisAttitudeTargetEulerRate.z, eulerYawRate, eulerAccel.z, getDt());

            // Convert euler angle derivative of desired attitude into a body-frame angular velocity vector for feedforward
            eulerRateToAngVel(thisAttitudeTargetEulerAngle, thisAttitudeTargetEulerRate, thisAttitudeTargetAngVel);
            // Limit the angular velocity
            angVelLimit(thisAttitudeTargetAngVel, (float) Math.toRadians(getAngVelRollMax()), (float) Math.toRadians(getAngVelPitchMax()), (float)Math.toRadians(getAngVelYawMax()));
            // Convert body-frame angular velocity into euler angle derivative of desired attitude
            ang_vel_to_euler_rate(thisAttitudeTargetEulerAngle, thisAttitudeTargetAngVel, thisAttitudeTargetEulerRate);
        } else {
            // When feedforward is not enabled, the target euler angle is input into the target and the feedforward rate is zeroed.
            thisAttitudeTargetEulerAngle.x = eulerRollAngle;
            thisAttitudeTargetEulerAngle.y = eulerPitchAngle;
            thisAttitudeTargetEulerAngle.z += eulerYawRate * getDt();
            // Compute quaternion target attitude
            thisAttitudeTargetQuat.fromEuler(thisAttitudeTargetEulerAngle);

            // Set rate feedforward requests to zero
            thisAttitudeTargetEulerRate = new JuavVector3f(0.0f, 0.0f, 0.0f);
            thisAttitudeTargetAngVel = new JuavVector3f(0.0f, 0.0f, 0.0f);
        }
        //these are computed on and need to be sent back to the native code
        setAttitudeTargetQuat(thisAttitudeTargetQuat);
        setAttitudeTargetEulerAngle(thisAttitudeTargetEulerAngle);
        setAttitudeTargetEulerRate(thisAttitudeTargetEulerRate);
        setAttitudeTargetAngVel(thisAttitudeTargetAngVel);
        // Call quaternion attitude controller
        attitudeControllerRunQuat();
    }



    // Convert an angular velocity vector to a 321-intrinsic euler angle derivative
// Returns false if the vehicle is pitched 90 degrees up or down
    boolean ang_vel_to_euler_rate(JuavVector3f eulerRad, JuavVector3f angVelRads, JuavVector3f eulerRateRads)
    {
        float sin_theta = (float) Math.sin(eulerRad.y);
        float cos_theta = (float) Math.cos(eulerRad.y);
        float sin_phi = (float) Math.sin(eulerRad.x);
        float cos_phi = (float) Math.cos(eulerRad.x);

        // When the vehicle pitches all the way up or all the way down, the euler angles become discontinuous. In this case, we just return false.
        if (JuavMath.isZero(cos_theta)) {
            return false;
        }

        eulerRateRads.x = angVelRads.x + sin_phi * (sin_theta / cos_theta) * angVelRads.y + cos_phi * (sin_theta / cos_theta) * angVelRads.z;
        eulerRateRads.y = cos_phi * angVelRads.y - sin_phi * angVelRads.z;
        eulerRateRads.z = (sin_phi / cos_theta) * angVelRads.y + (cos_phi / cos_theta) * angVelRads.z;
        return true;
    }

    // Convert a 321-intrinsic euler angle derivative to an angular velocity vector
    void eulerRateToAngVel(JuavVector3f eulerRad, JuavVector3f eulerRateRads, JuavVector3f angVelRads)
    {
        float sin_theta = (float) Math.sin(eulerRad.y);
        float cos_theta = (float) Math.cos(eulerRad.y);
        float sin_phi = (float) Math.sin(eulerRad.x);
        float cos_phi = (float) Math.cos(eulerRad.x);

        angVelRads.x = eulerRateRads.x - sin_theta * eulerRateRads.z;
        angVelRads.y = cos_phi * eulerRateRads.y + sin_phi * cos_theta * eulerRateRads.z;
        angVelRads.z = -sin_phi * eulerRateRads.y + cos_theta * cos_phi * eulerRateRads.z;
    }

    // calculates the velocity correction from an angle error. The angular velocity has acceleration and
// deceleration limits including basic jerk limiting using _input_tc
    float inputShapingAngle(float error_angle, float input_tc, float accel_max, float target_ang_vel, float dt)
    {
        // Calculate the velocity as error approaches zero with acceleration limited by accel_max_radss
        float desired_ang_vel = sqrtController(error_angle, 1.0f / Math.max(input_tc, 0.01f), accel_max, dt);

        // Acceleration is limited directly to smooth the beginning of the curve.
        return inputShapingAngVel(target_ang_vel, desired_ang_vel, accel_max, dt);
    }

    // limits the acceleration and deceleration of a velocity request
    float inputShapingAngVel(float target_ang_vel, float desired_ang_vel, float accel_max, float dt)
    {
        // Acceleration is limited directly to smooth the beginning of the curve.
        if (JuavMath.isPositive(accel_max)) {
            float delta_ang_vel = accel_max * dt;
            return JuavMath.constrainFloat(desired_ang_vel, target_ang_vel - delta_ang_vel, target_ang_vel + delta_ang_vel);
        } else {
            return desired_ang_vel;
        }
    }

    // translates body frame acceleration limits to the euler axis
    private JuavVector3f eulerAccelLimit(JuavVector3f eulerRad, JuavVector3f eulerAccel)
    {
        float sin_phi = JuavMath.constrainFloat((float) Math.abs(Math.sin(eulerRad.x)), 0.1f, 1.0f);
        float cos_phi = JuavMath.constrainFloat((float) Math.abs(Math.cos(eulerRad.x)), 0.1f, 1.0f);
        float sin_theta = JuavMath.constrainFloat((float) Math.abs(Math.sin(eulerRad.y)), 0.1f, 1.0f);

        JuavVector3f rotAccel = new JuavVector3f();
        if (JuavMath.isZero(eulerAccel.x) || JuavMath.isZero(eulerAccel.y) || JuavMath.isZero(eulerAccel.z) || JuavMath.isNegative(eulerAccel.x) || JuavMath.isNegative(eulerAccel.y) || JuavMath.isNegative(eulerAccel.z)) {
            rotAccel.x = eulerAccel.x;
            rotAccel.y = eulerAccel.y;
            rotAccel.z = eulerAccel.z;
        } else {
            rotAccel.x = eulerAccel.x;
            rotAccel.y = Math.min(eulerAccel.y / cos_phi, eulerAccel.z / sin_phi);
            rotAccel.z = Math.min(Math.min(eulerAccel.x / sin_theta, eulerAccel.y / sin_phi), eulerAccel.z / cos_phi);
        }
        return rotAccel;
    }

    // Command an euler roll, pitch and yaw angle with angular velocity feedforward and smoothing
    void inputEulerAngleRollPitchYaw(float eulerRollAngleCd, float eulerPitchAngleCd, float eulerYawAngleCd, boolean slewYaw)
    {
        //these are computed on and need to be sent back to the native code
        JuavQuaternion thisAttitudeTargetQuat = getAttitudeTargetQuat();
        JuavVector3f thisAttitudeTargetEulerAngle = getAttitudeTargetEulerAngle();
        JuavVector3f thisAttitudeTargetEulerRate = getAttitudeTargetEulerRate();
        JuavVector3f thisAttitudeTargetAngVel = getAttitudeTargetAngVel();

        // Convert from centidegrees on public interface to radians
        float eulerRollAngle = (float) Math.toRadians(eulerRollAngleCd * 0.01f);
        float eulerPitchAngle = (float) Math.toRadians(eulerPitchAngleCd * 0.01f);
        float eulerYawAngle = (float) Math.toRadians(eulerYawAngleCd * 0.01f);

        // calculate the attitude target euler angles
        thisAttitudeTargetQuat.toEuler(thisAttitudeTargetEulerAngle);

        // Add roll trim to compensate tail rotor thrust in heli (will return zero on multirotors)
        eulerRollAngle += getRollTrimRad();

        if (getRateBfFfEnabled()) {
            // translate the roll pitch and yaw acceleration limits to the euler axis
            Vector3f euler_accel = eulerAccelLimit(thisAttitudeTargetEulerAngle, new JuavVector3f(get_accel_roll_max_radss(), get_accel_pitch_max_radss(), get_accel_yaw_max_radss()));

            // When acceleration limiting and feedforward are enabled, the sqrt controller is used to compute an euler
            // angular velocity that will cause the euler angle to smoothly stop at the input angle with limited deceleration
            // and an exponential decay specified by _input_tc at the end.
            thisAttitudeTargetEulerRate.x = inputShapingAngle(JuavMath.wrapPI(eulerRollAngle - thisAttitudeTargetEulerAngle.x), getInputTc(), euler_accel.x, thisAttitudeTargetEulerRate.x, getDt());
            thisAttitudeTargetEulerRate.y = inputShapingAngle(JuavMath.wrapPI(eulerPitchAngle - thisAttitudeTargetEulerAngle.y), getInputTc(), euler_accel.y, thisAttitudeTargetEulerRate.y, getDt());
            thisAttitudeTargetEulerRate.z = inputShapingAngle(JuavMath.wrapPI(eulerYawAngle - thisAttitudeTargetEulerAngle.z), getInputTc(), euler_accel.z, thisAttitudeTargetEulerRate.z, getDt());
            if (slewYaw) {
                thisAttitudeTargetEulerRate.z = JuavMath.constrainFloat(thisAttitudeTargetEulerRate.z, -get_slew_yaw_rads(), get_slew_yaw_rads());
            }

            // Convert euler angle derivative of desired attitude into a body-frame angular velocity vector for feedforward
            eulerRateToAngVel(thisAttitudeTargetEulerAngle, thisAttitudeTargetEulerRate, thisAttitudeTargetAngVel);
            // Limit the angular velocity
            angVelLimit(thisAttitudeTargetAngVel, (float)Math.toRadians(getAngVelRollMax()), (float)Math.toRadians(getAngVelPitchMax()), (float)Math.toRadians(getAngVelYawMax()));
            // Convert body-frame angular velocity into euler angle derivative of desired attitude
            ang_vel_to_euler_rate(thisAttitudeTargetEulerAngle, thisAttitudeTargetAngVel, thisAttitudeTargetEulerRate);
        } else {
            // When feedforward is not enabled, the target euler angle is input into the target and the feedforward rate is zeroed.
            thisAttitudeTargetEulerAngle.x = eulerRollAngle;
            thisAttitudeTargetEulerAngle.y = eulerPitchAngle;
            if (slewYaw) {
                // Compute constrained angle error
                float angle_error = JuavMath.constrainFloat(JuavMath.wrapPI(eulerYawAngle - thisAttitudeTargetEulerAngle.z), -get_slew_yaw_rads() * getDt(), get_slew_yaw_rads() * getDt());
                // Update attitude target from constrained angle error
                thisAttitudeTargetEulerAngle.z = JuavMath.wrapPI(angle_error + thisAttitudeTargetEulerAngle.z);
            } else {
                thisAttitudeTargetEulerAngle.z = eulerYawAngle;
            }
            // Compute quaternion target attitude
            thisAttitudeTargetQuat.fromEuler(thisAttitudeTargetEulerAngle);

            // Set rate feedforward requests to zero
            thisAttitudeTargetEulerRate = new JuavVector3f(0.0f, 0.0f, 0.0f);
            thisAttitudeTargetAngVel = new JuavVector3f(0.0f, 0.0f, 0.0f);
        }

        //these are computed on and need to be sent back to the native code
        setAttitudeTargetQuat(thisAttitudeTargetQuat);
        setAttitudeTargetEulerAngle(thisAttitudeTargetEulerAngle);
        setAttitudeTargetEulerRate(thisAttitudeTargetEulerRate);
        setAttitudeTargetAngVel(thisAttitudeTargetAngVel);

        // Call quaternion attitude controller
        attitudeControllerRunQuat();
    }

    private void setThrustErrorAngle(float thisThrustErrorAngle) {
        //todo native
        throw new IllegalStateException("unimplemented");
    }

    private void setAttitudeTargetAngVel(JuavVector3f thisAttitudeTargetAngVel) {
        //todo native
        throw new IllegalStateException("unimplemented");
    }

    private void setAttitudeTargetEulerRate(JuavVector3f thisAttitudeTargetEulerRate) {
        //todo native
        throw new IllegalStateException("unimplemented");
    }

    private void setAttitudeTargetEulerAngle(JuavVector3f thisAttitudeTargetEulerAngle) {
        //todo native
        throw new IllegalStateException("unimplemented");
    }

    private float get_slew_yaw_rads() {
        return (float) Math.toRadians(getSlewYaw()*0.01f);
    }

    // Add roll trim to compensate tail rotor thrust in heli (will return zero on multirotors)
    private float getRollTrimRad() {
        return 0;
    }

    private float getSlewYaw() {
        //TODO native get _slew_yaw
        throw new IllegalStateException("unimplemented");
    }

    private float getInputTc () {
        //todo native return _input_tc
        throw new IllegalStateException("unimplemented");
    }

    private JuavVector3f getAttitudeTargetEulerRate() {
        //TODO native
        throw new IllegalStateException("unimplemented");
    }

    private JuavVector3f getAttitudeTargetEulerAngle() {
//        todo get _attitude_target_euler_angle
        throw new IllegalStateException("unimplemented");
    }

    private void setRateTargetAngVel(JuavVector3f thisRateTargetAngVel) {
        //todo set
        throw new IllegalStateException("unimplemented");
    }

    private void setAttitudeTargetQuat(JuavQuaternion thisAttitudeTargetQuat) {
        //todo set
        throw new IllegalStateException("unimplemented");
    }

    private void setAttitudeAngError(JuavQuaternion juavQuaternion) {
        //TODO _attitude_ang_error
        throw new IllegalStateException("unimplemented");

    }

    private boolean getRateBfFfEnabled() {
        //TODO _rate_bf_ff_enabled
        throw new IllegalStateException("unimplemented");
    }

    private JuavVector3f getAttitudeTargetAngVel() {
        //TODO native? _attitude_target_ang_vel
        throw new IllegalStateException("unimplemented");
    }

    private JuavQuaternion getAhrsGetQuatBodyToNed() {
        //TODO native?
        //todo native populate this quatrainian with
        //_ahrs.get_quat_body_to_ned(attitudeVehicleQuat); as seen in attitude_controller_run_quat
        throw new IllegalStateException("unimplemented");
    }

    private JuavQuaternion getAttitudeTargetQuat() {
        //TODO native?
        //todo populate from native the quatranian in _attitude_target_quat found in AC_AttitudeControl
        throw new IllegalStateException("unimplemented");
    }

    private float getThrustErrorAngle() {
        //TODO native?
//        todo return float found in  _thrust_error_angle of AC_AttitudeControl
        throw new IllegalStateException("unimplemented");
    }

    private JuavVector3f getAhrsGyro() {
        //TODO native?
        throw new IllegalStateException("unimplemented");
    }

    private JuavVector3f getRateTargetAngVel() {
        //TODO native?
        throw new IllegalStateException("unimplemented");
    }

    private float getAngVelRollMax() {
        //TODO native?
        throw new IllegalStateException("unimplemented");
    }

    private float getAngVelPitchMax() {
        //TODO native?
        throw new IllegalStateException("unimplemented");
    }

    private float getAngVelYawMax() {
        //TODO native?
        throw new IllegalStateException("unimplemented");
    }

    // get the roll acceleration limit in centidegrees/s/s or radians/s/s
    private float get_accel_roll_max() {
        throw new IllegalStateException("unimplemented");
//        return _accel_roll_max;
    }//todo native
    private float get_accel_roll_max_radss() { return (float) Math.toRadians(get_accel_roll_max() * 0.01f); }

    // get the pitch acceleration limit in centidegrees/s/s or radians/s/s
    private float get_accel_pitch_max() {
//        return _accel_pitch_max;
        throw new IllegalStateException("unimplemented");
    }//todo native
    private float get_accel_pitch_max_radss() { return (float) Math.toRadians(get_accel_pitch_max() * 0.01f); }

    // get the yaw acceleration limit in centidegrees/s/s or radians/s/s
    private float get_accel_yaw_max() {
//        return _accel_yaw_max;
        throw new IllegalStateException("unimplemented");
    } //todo native
    private float get_accel_yaw_max_radss() { return (float) Math.toRadians(get_accel_yaw_max() * 0.01f); }

    private void setFeedforwardScalar(float feedforwardScalar) {
        //_feedforward_scalar
        //TODO set value
        //TODO native?
        throw new IllegalStateException("unimplemented");
    }

    private float getFeedforwardScalar() {
        //_feedforward_scalar
        //TODO get value
        //TODO native?
        throw new IllegalStateException("unimplemented");
    }

    private static float getThrustAngle() {
        //todo native
        throw new IllegalStateException("unimplemented");
    }

    private float setThrustAngle(float newThrustAngle) {
        //todo native
        throw new IllegalStateException("unimplemented");
    }

    private boolean getUseSqrtController() {
        //todo native
        throw new IllegalStateException("unimplemented");
    }

    private float getDt() {
        //todo native
        throw new IllegalStateException("unimplemented");
    }

    private float getPAngleYawKp() {
        //todo native
//        _p_angle_yaw.kP()
        throw new IllegalStateException("unimplemented");
    }

    private float getPAngleRollKp() {
        //todo native
//        _p_angle_yaw.kP()
        throw new IllegalStateException("unimplemented");
    }

    private float getPAnglePitchKp() {
        //todo native
//        _p_angle_yaw.kP()
        throw new IllegalStateException("unimplemented");
    }

    private float getAcAttitudeAccelYControlerMaxRadss() {
        //todo native? = AC_ATTITUDE_ACCEL_Y_CONTROLLER_MAX_RADSS
        return (float) Math.toRadians(120.0f);
    }

    private float getAcAttitudeAccelYControlerMinRadss() {
        //todo native? = AC_ATTITUDE_ACCEL_Y_CONTROLLER_MAX_RADSS
        return (float) Math.toRadians(10.0f);
    }

    private float getAcAttitudeAccelRpControllerMinRadss() {
        //todo native? = AC_ATTITUDE_ACCEL_Rp_CONTROLLER_MIN_RADSS
        return (float) Math.toRadians(40.0f);
    }

    private float getAcAttitudeAccelRpControllerMaxRadss() {
        //todo native? = AC_ATTITUDE_ACCEL_Rp_CONTROLLER_MAX_RADSS
        return (float) Math.toRadians(720.0f);
    }

    private float getAcAttitudeThrustErrorAngle() {
        //todo native? = AC_ATTITUDE_THRUST_ERROR_ANGLE
        return (float) Math.toRadians(30.0f);
    }
}
