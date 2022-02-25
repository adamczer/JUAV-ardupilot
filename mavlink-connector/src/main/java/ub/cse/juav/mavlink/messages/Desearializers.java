//        MIT License
//
//        Copyright (c) 2018 dronefleet
//
//        Permission is hereby granted, free of charge, to any person obtaining a copy
//        of this software and associated documentation files (the "Software"), to deal
//        in the Software without restriction, including without limitation the rights
//        to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//        copies of the Software, and to permit persons to whom the Software is
//        furnished to do so, subject to the following conditions:
//
//        The above copyright notice and this permission notice shall be included in all
//        copies or substantial portions of the Software.
//
//        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//        IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//        FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//        AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//        LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//        OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//        SOFTWARE.

package ub.cse.juav.mavlink.messages;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

public class Desearializers {
    public static byte[] slice(byte[] data, int offset, int length) {
        byte[] ret = new byte[length];
        int copyLength = Math.max(Math.min(length,data.length-offset),0);
        System.arraycopy(data, offset, ret, 0, copyLength);
        return ret;
    }

    public static long unsignedIntegerValue(byte[] data) {
        long value = 0;
        for (int i = 0; i < data.length; i++) {
            value = value | ((long) ((data[i] & 0xff)) << (i * 8));
        }
        return value;
    }

    public static long signedIntegerValue(byte[] data) {
        long value = unsignedIntegerValue(data);
        int signBitIndex = data.length * Byte.SIZE - 1;
        if ((value >> signBitIndex) == 1) {
            value |= (-1L << signBitIndex);
        }
        return value;
    }

    public static long integerValue(byte[] data, boolean signed) {
        if (signed) {
            return signedIntegerValue(data);
        } else {
            return unsignedIntegerValue(data);
        }
    }
    public static long longValue(byte[] data, boolean signed) {
        return integerValue(data,signed);
    }

    public static double doubleValue(byte[] data) {
        return ByteBuffer.wrap(data)
                .order(ByteOrder.LITTLE_ENDIAN)
                .getDouble();
    }

    public static float floatValue(byte[] data) {
        return ByteBuffer.wrap(data)
                .order(ByteOrder.LITTLE_ENDIAN)
                .getFloat();
    }

    public static String stringValue(byte[] data) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == 0) {
                return new String(data, 0, i, StandardCharsets.UTF_8);
            }
        }
        return new String(data, StandardCharsets.UTF_8);
    }
}