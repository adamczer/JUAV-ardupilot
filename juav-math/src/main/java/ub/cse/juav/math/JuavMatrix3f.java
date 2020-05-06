package ub.cse.juav.math;

import javax.vecmath.Matrix3f;
import javax.vecmath.Vector3f;

public class JuavMatrix3f extends Matrix3f {

    //multiply matrix with vector and return result *
    public JuavVector3f opStar(Vector3f v) {
        JuavVector3f ret = new JuavVector3f();
        ret.x=v.x;
        ret.y=v.y;
        ret.z=v.z;
        transform(ret);
        return ret;
    }
}
