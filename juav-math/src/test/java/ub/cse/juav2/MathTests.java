package ub.cse.juav2;

import org.junit.Test;
import ub.cse.juav.math.JuavQuaternion;
import ub.cse.juav.math.JuavVector3f;

import javax.vecmath.Vector3f;

public class MathTests {
    @Test
    public void testVectorLength() {
        Vector3f v = new Vector3f(1,3,5);
        float length = v.length();
        System.out.println(length);
        v.normalize();
        System.out.println(v.length());
    }

    @Test
    public void testQuatFromAxis() {
        JuavQuaternion quat = new JuavQuaternion();
        JuavVector3f v = new JuavVector3f(1,2,3);
        System.out.println(quat);
        quat.fromAxisAngle(v);
        System.out.println(quat);
    }
    @Test
    public void testQuatInverse() {
        JuavQuaternion quat = new JuavQuaternion();
        JuavVector3f v = new JuavVector3f(1,2,3);
        quat.fromAxisAngle(v);
        System.out.println(quat);
        quat.inverse();
        System.out.println(quat);
    }
    @Test
    public void testModulo() {
        float v = 3.9f - (3.9f % 0.3f);
        System.out.println(v);
    }
}
