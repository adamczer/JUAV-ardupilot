package ub.cse.juav2.math;

import javax.vecmath.Vector3f;

public class JuavVector3f extends Vector3f {
    public JuavVector3f() {
        super();
    }
    public JuavVector3f(float x, float y, float z) {
        super(x,y,z);
    }

    // Multiply each component by scalar
    public void opStar(float f) {
        x=x*f;
        y=y*f;
        z=z*f;
    }
    // divide each component by scalar
    public void opSlash(float f) {
        x=x/f;
        y=y/f;
        z=z/f;
    }

    //cross product %
    public static Vector3f opPercent(Vector3f v1, Vector3f v2) {
        JuavVector3f ret = new JuavVector3f();
        ret.cross(v1,v2);
        return ret;
    }
    //dot product *
    public float opStar(Vector3f v) {
        return dot(v);
    }
}
