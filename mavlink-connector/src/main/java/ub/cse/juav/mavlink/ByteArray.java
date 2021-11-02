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

public class ByteArray {
    private final byte[] bytes;

    ByteArray(byte[] bytes) {
        this.bytes = bytes;
    }

    public int getInt8(int offset) {
        return (int)this.getLong(offset, 1);
    }

    public void putInt8(int value, int offset) {
        this.putLong((long)value, offset, 1);
    }

    public int getInt16(int offset) {
        return (int)this.getLong(offset, 2);
    }

    public void putInt16(int value, int offset) {
        this.putLong((long)value, offset, 2);
    }

    public int getInt24(int offset) {
        return (int)this.getLong(offset, 3);
    }

    public void putInt24(int value, int offset) {
        this.putLong((long)value, offset, 3);
    }

    public long getInt48(int offset) {
        return this.getLong(offset, 6);
    }

    public void putInt48(long value, int offset) {
        this.putLong(value, offset, 6);
    }

    public byte[] slice(int offset, int size) {
        byte[] value = new byte[size];
        System.arraycopy(this.bytes, offset, value, 0, size);
        return value;
    }

    public long getLong(int offset, int size) {
        long value = 0L;

        for(int i = 0; i < size; ++i) {
            value |= ((long)this.bytes[offset + i] & 255L) << i * 8;
        }

        return value;
    }

    public void putLong(long value, int offset, int size) {
        for(int i = 0; i < size; ++i) {
            this.bytes[offset + i] = (byte)((int)(value >> i * 8 & 255L));
        }

    }

    public byte[] array() {
        return this.bytes;
    }

    public int length() {
        return this.bytes.length;
    }
}
