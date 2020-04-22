package ub.cse.juav2.math;

import javax.vecmath.Matrix3f;
import javax.vecmath.Quat4f;

public class JuavQuaternion extends Quat4f {

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

    //Retrun rotation matrix for this Quaternion
    public Matrix3f rotationMatrix() {
        Matrix3f ret = new Matrix3f();
        ret.set(this);
        return ret;
    }


    public float length() {
        return (float) Math.sqrt(JuavMath.sq(w)+JuavMath.sq(x)+JuavMath.sq(y)+JuavMath.sq(z));
    }

}
