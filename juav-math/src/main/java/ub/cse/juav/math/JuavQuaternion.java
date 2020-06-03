package ub.cse.juav.math;

import javax.vecmath.Matrix3f;
import javax.vecmath.Quat4f;

public class JuavQuaternion extends Quat4f {

    public JuavQuaternion() {
        super();
    }

    public JuavQuaternion(float v, float x, float y, float z) {
        w=v;
        this.x=x;
        this.y=y;
        this.z=z;
        //Ardupilot sets these values directly in usecases encountered super mucks with values causing floating point exception
//        super(v,x,y,z);
    }

    // return the rotation matrix equivalent for this quaternion
    public static JuavQuaternion fromRotationMatrix(Matrix3f matrix3f){
        JuavQuaternion ret = new JuavQuaternion();
        ret.set(matrix3f);
        return ret;
    }

    //Retrun rotation matrix for this Quaternion
//    Sets the provided matrix to the roataion matrix of this quaternion
    public void rotationMatrix(Matrix3f mat) {
        mat.set(this);
    }

    // create a quaternion from its axis-angle representation
    // only use with small angles.  I.e. length of v should less than 0.17 radians (i.e. 10 degrees)
    public void fromAxisAngle(JuavVector3f v) {
        float theta = v.length();
        if (JuavMath.isZero(theta)) {
            setX(1);
            setY(0);
            setZ(0);
            setW(0);
            return;
        }
        v.opSlash(theta);
        fromAxisAngle(v, theta);
    }

    // create a quaternion from its axis-angle representation
    // theta should less than 0.17 radians (i.e. 10 degrees)
    public void fromAxisAngle(JuavVector3f axis, float theta) {
        // axis must be a unit vector as there is no check for length
        if (JuavMath.isZero(theta)) {
            setW(1);
            setX(0);
            setY(0);
            setZ(0);
            return;
        }
        float st2 = (float) Math.sin(theta / 2.0f);

        setW((float) Math.cos(theta / 2.0f));
        setX(axis.x * st2);
        setY(axis.y * st2);
        setZ(axis.z * st2);
    }

    // convert this quaternion to a rotation vector where the direction of the vector represents
    // the axis of rotation and the length of the vector represents the angle of rotation
    public void toAxisAngel(JuavVector3f v) {
        float l = v.length();
        v.setX(x);
        v.setY(y);
        v.setZ(z);
        if (!JuavMath.isZero(l)) {
            v.opSlash(l);
            v.opStar(JuavMath.wrapPI((float) (2.0f * Math.atan2(l, w))));
        }
    }

    // Op for *
    public JuavQuaternion opStar(Quat4f v) {
        JuavQuaternion ret = new JuavQuaternion();
        ret.mul(this,v);
        return ret;
    }


    public float length() {
        return (float) Math.sqrt(JuavMath.sq(w)+JuavMath.sq(x)+JuavMath.sq(y)+JuavMath.sq(z));
    }

    public JuavQuaternion inversed() {
        JuavQuaternion ret = new JuavQuaternion();
        ret.inverse(this);
        return ret;
    }

    public void toEuler(JuavVector3f thisAttitudeTargetEulerAngle) {
        thisAttitudeTargetEulerAngle.x = getEulerRoll();
        thisAttitudeTargetEulerAngle.y = getEulerPitch();
        thisAttitudeTargetEulerAngle.z = getEulerYaw();
    }

    private float getEulerRoll() {
        return (float) Math.atan2(2.0f*(w*x + y*z), 1.0f - 2.0f*(x*x + y*y));
    }

    private float getEulerPitch() {
        return JuavMath.safeAsin(2.0f*(w*y - z*x));
    }

    private float getEulerYaw() {
        return (float) Math.atan2(2.0f*(w*z + x*y), 1.0f - 2.0f*(y*y + z*z));
    }
    // create a quaternion from Euler angles
    public void fromEuler(JuavVector3f thisAttitudeTargetEulerAngle) {
        float cr2 = (float) Math.cos(thisAttitudeTargetEulerAngle.x * 0.5f);
        float cp2 = (float) Math.cos(thisAttitudeTargetEulerAngle.y * 0.5f);
        float cy2 = (float) Math.cos(thisAttitudeTargetEulerAngle.z * 0.5f);
        float sr2 = (float) Math.sin(thisAttitudeTargetEulerAngle.x * 0.5f);
        float sp2 = (float) Math.sin(thisAttitudeTargetEulerAngle.y * 0.5f);
        float sy2 = (float) Math.sin(thisAttitudeTargetEulerAngle.z * 0.5f);

        w = cr2 * cp2 * cy2 + sr2 * sp2 * sy2;
        x = sr2 * cp2 * cy2 - cr2 * sp2 * sy2;
        y = cr2 * sp2 * cy2 + sr2 * cp2 * sy2;
        z = cr2 * cp2 * sy2 - sr2 * sp2 * cy2;
    }
}
