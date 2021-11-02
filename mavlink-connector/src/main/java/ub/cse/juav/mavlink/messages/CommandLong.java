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

public class CommandLong {
    private int targetSystem;
    private int targetComponent;
    private int command;
    private int confirmation;
    private float param1;
    private float param2;
    private float param3;
    private float param4;
    private float param5;
    private float param6;
    private float param7;

    public CommandLong(int targetSystem, int targetComponent, int command,
                        int confirmation, float param1, float param2, float param3, float param4, float param5,
                        float param6, float param7) {
        this.targetSystem = targetSystem;
        this.targetComponent = targetComponent;
        this.command = command;
        this.confirmation = confirmation;
        this.param1 = param1;
        this.param2 = param2;
        this.param3 = param3;
        this.param4 = param4;
        this.param5 = param5;
        this.param6 = param6;
        this.param7 = param7;
    }

    public byte[] getBytes() {
        byte[] ret = new byte[33];
        write(param1,ret,0,4);
        write(param2,ret,4,8);
        write(param3,ret,8,12);
        write(param4,ret,12,16);
        write(param5,ret,16,20);
        write(param6,ret,20,24);
        write(param7,ret,24,28);
        write(command,ret,28,30);
        return ret;
    }

    private void write(float value, byte[] buffer, int offset, int length) {
        if (length - offset != 4) {
            throw new IllegalArgumentException("length != 4");
        } else {
            ByteBuffer.wrap(buffer).order(ByteOrder.LITTLE_ENDIAN).putFloat(offset, value);
        }
    }

    private void write(long value, byte[] buffer, int offset, int length) {
        int i = offset;

        for(int shift = 0; i < length; ++shift) {
            buffer[i] = (byte)((int)(value >> shift * 8 & 255L));
            ++i;
        }
    }

    private void write(int value, byte[] buffer, int offset, int length) {
        this.write((long)value, buffer, offset, length);
    }
}
