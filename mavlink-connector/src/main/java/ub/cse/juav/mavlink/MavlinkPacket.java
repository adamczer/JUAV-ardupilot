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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MavlinkPacket {
        static final int MAGIC_V1 = 254;
        static final int MAGIC_V2 = 253;
        private static final int INCOMPAT_FLAG_SIGNED = 1;
        private final int versionMarker;
        private final int incompatibleFlags;
        private final int compatibleFlags;
        private final int sequence;
        private final int systemId;
        private final int componentId;
        private final int messageId;
        private final byte[] payload;
        private final int checksum;
        private final byte[] signature;
        private final byte[] rawBytes;

        public static MavlinkPacket createSignedMavlink2Packet(int sequence, int systemId, int componentId, int messageId, int crcExtra, byte[] payload, int linkId, long timestamp, byte[] secretKey) {
            byte[] rawBytes = new byte[25 + payload.length];
            ByteArray bytes = new ByteArray(rawBytes);
            bytes.putInt8(253, 0);
            bytes.putInt8(payload.length, 1);
            bytes.putInt8(1, 2);
            bytes.putInt8(0, 3);
            bytes.putInt8(sequence, 4);
            bytes.putInt8(systemId, 5);
            bytes.putInt8(componentId, 6);
            bytes.putInt24(messageId, 7);
            System.arraycopy(payload, 0, rawBytes, 10, payload.length);
            int crc = generateCrc(rawBytes, crcExtra);
            bytes.putInt16(crc, 10 + payload.length);
            byte[] signature = generateSignature(rawBytes, linkId, timestamp, secretKey);
            System.arraycopy(signature, 0, rawBytes, rawBytes.length - signature.length, signature.length);
            return new MavlinkPacket(253, 1, 0, sequence, systemId, componentId, messageId, payload, crc, signature, rawBytes);
        }

        public static MavlinkPacket createUnsignedMavlink2Packet(int sequence, int systemId, int componentId, int messageId, int crcExtra, byte[] payload) {
            byte[] rawBytes = new byte[12 + payload.length];
            ByteArray bytes = new ByteArray(rawBytes);
            bytes.putInt8(253, 0);
            bytes.putInt8(payload.length, 1);
            bytes.putInt8(0, 2);
            bytes.putInt8(0, 3);
            bytes.putInt8(sequence, 4);
            bytes.putInt8(systemId, 5);
            bytes.putInt8(componentId, 6);
            bytes.putInt24(messageId, 7);
            System.arraycopy(payload, 0, rawBytes, 10, payload.length);
            int crc = generateCrc(rawBytes, crcExtra);
            bytes.putInt16(crc, 10 + payload.length);
            return new MavlinkPacket(253, 0, 0, sequence, systemId, componentId, messageId, payload, crc, new byte[0], rawBytes);
        }

        public static MavlinkPacket createMavlink1Packet(int sequence, int systemId, int componentId, int messageId, int crcExtra, byte[] payload) {
            byte[] rawBytes = new byte[8 + payload.length];
            ByteArray bytes = new ByteArray(rawBytes);
            bytes.putInt8(254, 0);
            bytes.putInt8(payload.length, 1);
            bytes.putInt8(sequence, 2);
            bytes.putInt8(systemId, 3);
            bytes.putInt8(componentId, 4);
            bytes.putInt8(messageId, 5);
            System.arraycopy(payload, 0, rawBytes, 6, payload.length);
            int crc = generateCrc(rawBytes, crcExtra);
            bytes.putInt16(crc, 6 + payload.length);
            return new MavlinkPacket(254, -1, -1, sequence, systemId, componentId, messageId, payload, crc, new byte[0], rawBytes);
        }

        public static MavlinkPacket fromV1Bytes(byte[] rawBytes) {
            ByteArray bytes = new ByteArray(rawBytes);
            int versionMarker = bytes.getInt8(0);
            int payloadLength = bytes.getInt8(1);
            int sequence = bytes.getInt8(2);
            int systemId = bytes.getInt8(3);
            int componentId = bytes.getInt8(4);
            int messageId = bytes.getInt8(5);
            byte[] payload = bytes.slice(6, payloadLength);
            int checksum = bytes.getInt16(6 + payloadLength);
            return new MavlinkPacket(versionMarker, -1, -1, sequence, systemId, componentId, messageId, payload, checksum, new byte[0], rawBytes);
        }

        public static MavlinkPacket fromV2Bytes(byte[] rawBytes) {
            ByteArray bytes = new ByteArray(rawBytes);
            int versionMarker = bytes.getInt8(0);
            int payloadLength = bytes.getInt8(1);
            int incompatibleFlags = bytes.getInt8(2);
            int compatibleFlags = bytes.getInt8(3);
            int sequence = bytes.getInt8(4);
            int systemId = bytes.getInt8(5);
            int componentId = bytes.getInt8(6);
            int messageId = bytes.getInt24(7);
            byte[] payload = bytes.slice(10, payloadLength);
            int checksum = bytes.getInt16(10 + payloadLength);
            byte[] signature;
            if ((incompatibleFlags & 1) != 0) {
                signature = bytes.slice(12 + payloadLength, 13);
            } else {
                signature = new byte[0];
            }

            return new MavlinkPacket(versionMarker, incompatibleFlags, compatibleFlags, sequence, systemId, componentId, messageId, payload, checksum, signature, rawBytes);
        }

        public static int generateCrc(byte[] packetBytes, int crcExtra) {
            if (packetBytes.length < 3) {
                return -1;
            } else {
                int payloadLength = packetBytes[1] & 255;
                byte packetLengthWithoutCrc;
                switch(packetBytes[0] & 255) {
                    case 253:
                        packetLengthWithoutCrc = 10;
                        break;
                    case 254:
                        packetLengthWithoutCrc = 6;
                        break;
                    default:
                        throw new IllegalStateException("not a mavlink packet");
                }

                packetLengthWithoutCrc += payloadLength;
                CrcX25 crc = new CrcX25();
                crc.accumulate(packetBytes, 1, packetLengthWithoutCrc);
                crc.accumulate(crcExtra);
                return crc.get();
            }
        }

        public static byte[] generateSignature(byte[] packetBytes, int linkId, long timestamp, byte[] secretKey) {
            if (packetBytes.length >= 3 && (packetBytes[0] & 253) == 253 && (packetBytes[2] & 1) != 0) {
                int payloadLength = packetBytes[1] & 255;
                int packetLengthWithCrc = 12 + payloadLength;
                if (packetBytes.length < packetLengthWithCrc) {
                    throw new IllegalArgumentException("specified packet seems to be incomplete");
                } else {
                    byte[] signature = new byte[13];
                    ByteArray bytes = new ByteArray(signature);
                    bytes.putInt8(linkId, 0);
                    bytes.putInt48(timestamp, 1);

                    try {
                        MessageDigest digest = MessageDigest.getInstance("SHA-256");
                        digest.update(secretKey);
                        digest.update(packetBytes, 0, packetLengthWithCrc);
                        digest.update(signature, 0, 7);
                        byte[] hash = digest.digest();
                        System.arraycopy(hash, 0, signature, 7, 6);
                        return signature;
                    } catch (NoSuchAlgorithmException var11) {
                        throw new IllegalStateException("JVM does not have an implementation of SHA-256 available.");
                    }
                }
            } else {
                return new byte[0];
            }
        }

        private MavlinkPacket(int versionMarker, int incompatibleFlags, int compatibleFlags, int sequence, int systemId, int componentId, int messageId, byte[] payload, int checksum, byte[] signature, byte[] rawBytes) {
            this.versionMarker = versionMarker;
            this.incompatibleFlags = incompatibleFlags;
            this.compatibleFlags = compatibleFlags;
            this.sequence = sequence;
            this.systemId = systemId;
            this.componentId = componentId;
            this.messageId = messageId;
            this.payload = payload;
            this.checksum = checksum;
            this.signature = signature;
            this.rawBytes = rawBytes;
        }

        public int getVersionMarker() {
            return this.versionMarker;
        }

        public int getIncompatibleFlags() {
            return this.incompatibleFlags;
        }

        public int getCompatibleFlags() {
            return this.compatibleFlags;
        }

        public int getSequence() {
            return this.sequence;
        }

        public int getSystemId() {
            return this.systemId;
        }

        public int getComponentId() {
            return this.componentId;
        }

        public int getMessageId() {
            return this.messageId;
        }

        public byte[] getPayload() {
            return this.payload;
        }

        public int getChecksum() {
            return this.checksum;
        }

        public byte[] getSignature() {
            return this.signature;
        }

        public byte[] getRawBytes() {
            return this.rawBytes;
        }

        public boolean isMavlink2() {
            return this.versionMarker == 253;
        }

        public boolean validateCrc(int crcExtra) {
            return generateCrc(this.rawBytes, crcExtra) == this.checksum;
        }

        public boolean isSigned() {
            return (this.incompatibleFlags & 1) != 0;
        }

        public boolean validateSignature(byte[] secretKey) {
            return this.isSigned() && Arrays.equals(this.signature, generateSignature(this.rawBytes, this.getSignatureLinkId(), this.getSignatureTimestamp(), secretKey));
        }

        public int getSignatureLinkId() {
            return this.isSigned() ? this.signature[0] & 255 : -1;
        }

        public long getSignatureTimestamp() {
            ByteArray bytes = new ByteArray(this.signature);
            return this.isSigned() ? bytes.getInt48(1) : -1L;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            } else if (o != null && this.getClass() == o.getClass()) {
                MavlinkPacket that = (MavlinkPacket)o;
                if (this.versionMarker != that.versionMarker) {
                    return false;
                } else if (this.incompatibleFlags != that.incompatibleFlags) {
                    return false;
                } else if (this.compatibleFlags != that.compatibleFlags) {
                    return false;
                } else if (this.sequence != that.sequence) {
                    return false;
                } else if (this.systemId != that.systemId) {
                    return false;
                } else if (this.componentId != that.componentId) {
                    return false;
                } else if (this.messageId != that.messageId) {
                    return false;
                } else if (this.checksum != that.checksum) {
                    return false;
                } else if (!Arrays.equals(this.payload, that.payload)) {
                    return false;
                } else {
                    return !Arrays.equals(this.signature, that.signature) ? false : Arrays.equals(this.rawBytes, that.rawBytes);
                }
            } else {
                return false;
            }
        }

        public int hashCode() {
            int result = this.versionMarker;
            result = 31 * result + this.incompatibleFlags;
            result = 31 * result + this.compatibleFlags;
            result = 31 * result + this.sequence;
            result = 31 * result + this.systemId;
            result = 31 * result + this.componentId;
            result = 31 * result + this.messageId;
            result = 31 * result + Arrays.hashCode(this.payload);
            result = 31 * result + this.checksum;
            result = 31 * result + Arrays.hashCode(this.signature);
            result = 31 * result + Arrays.hashCode(this.rawBytes);
            return result;
        }

        public String toString() {
            return "MavlinkPacket{versionMarker=" + this.versionMarker + ", incompatibleFlags=" + this.incompatibleFlags + ", compatibleFlags=" + this.compatibleFlags + ", sequence=" + this.sequence + ", systemId=" + this.systemId + ", componentId=" + this.componentId + ", messageId=" + this.messageId + ", payload=" + Arrays.toString(this.payload) + ", checksum=" + this.checksum + ", signature=" + Arrays.toString(this.signature) + '}';
        }
}
