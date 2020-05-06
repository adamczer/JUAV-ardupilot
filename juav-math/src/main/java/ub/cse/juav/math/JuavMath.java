package ub.cse.juav.math;

public class JuavMath {
    private static final float FLOAT_EPSILON = 1.1920928955078125e-7F;
    public static final float M_PI = 3.141592653589793f;
    private static final float M_2PI = M_PI * 2;
    private static final float M_PI_2 = M_PI / 2;

    /*
    * @brief: Check whether a float is zero
    */
    public static boolean isZero(float f) {
        return Math.abs(f) < FLOAT_EPSILON;
    }

    public static float wrapPI(float radian) {
        float res = wrap2PI(radian);
        if (res > M_PI) {
            res -= M_2PI;
        }
        return res;
    }

    private static float wrap2PI(float radian)
    {
        float res = fmodf(radian, M_2PI);
        if (res < 0) {
            res += M_2PI;
        }
        return res;
    }

    public static float fmodf(float val1, float val2) {
        return val1 - (val1 % val2);
    }

    public static float sq(float f) {
        return (float) Math.pow(f,2);
    }

    public static float constrainFloat(float amt,float low, float high) {
        return constrainValue(amt,low,high);
    }

    public static float constrainValue(float amt, float low, float high)
    {
        if (Float.isNaN(amt))
            return (low + high) / 2;
        else if (amt < low)
            return low;
        else if (amt > high)
            return high;
        else
            return amt;
    }

    public static float safeSqrt(float f) {
        double ret = Math.sqrt(f);
        if(Double.isNaN(ret))
            return 0;
        else
            return (float) ret;
    }

    public static boolean isNegative(float f) {
        return f < (-1*FLOAT_EPSILON);
    }

    public static boolean isPositive(float f) {
        return f < FLOAT_EPSILON;
    }

    public static float safeAsin(float f) {
        if (Float.isNaN(f)) {
            return 0.0f;
        }
        if (f >= 1.0f) {
            return M_PI_2;
        }
        if (f <= -1.0f) {
            return -M_PI_2;
        }
        return (float) Math.asin(f);
    }
}
