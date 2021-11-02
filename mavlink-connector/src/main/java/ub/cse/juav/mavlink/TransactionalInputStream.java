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

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

public class TransactionalInputStream extends PushbackInputStream {
    private final byte[] buffer;
    private int cbuffer;

    TransactionalInputStream(InputStream in, int bufferSize) {
        super(in, bufferSize);
        this.buffer = new byte[bufferSize];
        this.cbuffer = 0;
    }

    public int read() throws IOException {
        int c = super.read();
        if (c != -1) {
            this.buffer[this.cbuffer++] = (byte)c;
        }

        return c;
    }

    void rollback() throws IOException {
        super.unread(this.buffer, 0, this.cbuffer);
    }

    boolean advance(int bytes) throws IOException {
        for(int i = 0; i < bytes; ++i) {
            if (this.read() == -1) {
                return false;
            }
        }

        return true;
    }

    void commit() {
        this.cbuffer = 0;
    }

    public byte[] getBuffer() {
        byte[] bytes = new byte[this.cbuffer];
        System.arraycopy(this.buffer, 0, bytes, 0, this.cbuffer);
        return bytes;
    }

    public void unread(int b) throws IOException {
        this.disallowUnread();
    }

    public void unread(byte[] b) throws IOException {
        this.disallowUnread();
    }

    public void unread(byte[] b, int off, int len) throws IOException {
        this.disallowUnread();
    }

    private void disallowUnread() {
        throw new IllegalStateException("use either commit or rollback instead.");
    }
}
