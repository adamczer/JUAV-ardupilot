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

public class MavlinkFrameReader {
    private final TransactionalInputStream in;

    public MavlinkFrameReader(InputStream in) {
        this.in = new TransactionalInputStream(in, 280);
    }

    public boolean next() throws IOException {
        this.in.commit();

        int versionMarker;
        while((versionMarker = this.in.read()) != -1) {
            int payloadLength;
            if ((payloadLength = this.in.read()) == -1) {
                return false;
            }

            switch(versionMarker) {
                case 253:
                    int incompatibleFlags;
                    return (incompatibleFlags = this.in.read()) != -1 && this.in.advance(9 + payloadLength + (incompatibleFlags & 1) * 13);
                case 254:
                    return this.in.advance(6 + payloadLength);
                default:
                    this.drop();
            }
        }

        return false;
    }

    public byte[] frame() {
        return this.in.getBuffer();
    }

    public void drop() throws IOException {
        this.in.rollback();
        this.in.skip(1L);
        this.in.commit();
    }
}
