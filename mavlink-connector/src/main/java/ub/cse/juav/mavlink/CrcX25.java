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


package ub.cse.juav.mavlink;

import java.nio.charset.StandardCharsets;

public class CrcX25 {
    private int crc = 65535;

    public CrcX25() {
    }

    public void accumulate(String str) {
        this.accumulate(str.getBytes(StandardCharsets.UTF_8));
    }

    public void accumulate(byte[] bytes) {
        this.accumulate(bytes, 0, bytes.length);
    }

    public void accumulate(byte[] bytes, int offset, int length) {
        for(int i = offset; i < length; ++i) {
            this.accumulate(bytes[i]);
        }

    }

    public void accumulate(int b) {
        b ^= this.crc & 255;
        b ^= b << 4 & 255;
        b &= 255;
        this.crc = this.crc >> 8 ^ b << 8 ^ b << 3 ^ b >> 4;
    }

    public int get() {
        return this.crc & '\uffff';
    }
}
