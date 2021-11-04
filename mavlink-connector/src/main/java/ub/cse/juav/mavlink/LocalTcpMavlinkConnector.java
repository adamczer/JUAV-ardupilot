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

import ub.cse.juav.mavlink.messages.CommandLong;
import ub.cse.juav.mavlink.messages.CommandSetPositionTargetLocalNed;
import ub.cse.juav.mavlink.messages.GlobalPositionIntLight;
import ub.cse.juav.mavlink.messages.HeartbeatLight;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class LocalTcpMavlinkConnector {
    static final int systemId = 1;
    static final int componentId = 1;
    private static final Object lock = new Object();
    private GlobalPositionIntLight latestPosition = null;
    private long latestMode;
    private OutputStream os;
    private MavlinkPacketReader r;

    public LocalTcpMavlinkConnector(int port) throws IOException {
        Socket server = new Socket("127.0.0.1", port);
        InputStream is = server.getInputStream();
        this.os = server.getOutputStream();
        r = new MavlinkPacketReader(is);
    }

    public void updateState() {
        boolean needPosition = true;
        boolean needMode = true;
        try {
            int attempts = 0;
            while (needMode || needPosition) {
                // Mvm sometimes interrupts send?
                if(attempts++%10 == 0)
                    sendMessage(new CommandLong(systemId,componentId,512,0,
                        33,0,0,0,0,0,1));
                MavlinkPacket mp = r.next();
                if (mp.getMessageId() == 33) { //GlobalPositionInt
                    GlobalPositionIntLight pos = GlobalPositionIntLight.parse(mp.getPayload());
                    needPosition = false;
                    synchronized (lock) {
                        latestPosition = pos;
                    }
                } else if (mp.getMessageId() == 0) { //Heartbeat
                    if (mp.getVersionMarker()==254) {
                        long mode = HeartbeatLight.getFlightMode(mp.getPayload());
                        needMode = false;
                        synchronized (lock) {
                            latestMode = mode;
                        }
                    }
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static LocalTcpMavlinkConnector getConnection() {
        LocalTcpMavlinkConnector lmc = null;
        while (lmc == null) {
            try {
                lmc = new LocalTcpMavlinkConnector(5762);
            } catch (Exception e) {
                System.out.println("Mavlink unavailable, trying again in 5.");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return lmc;
    }

    public GlobalPositionIntLight getLatestPosition() {
        while (true) {
            synchronized (lock) {
                if (latestPosition == null)
                    continue;
                return latestPosition;
            }
        }
    }
    //4 -> guided
    //3 -> auto
    //0 -> stabilize
    public int getLatestMode() {
        while (true) {
            synchronized (lock) {
                return (int)latestMode;
            }
        }
    }

    public void guidedMode() {
        CommandLong ci = new CommandLong(systemId,componentId,176,0,3,4,
                0,0,0,0,0);
        sendMessage(ci);
    }

//    public void setWayPoint(float lat, float lon, float alt) {
//        CommandLong ci = new CommandLong(systemId,componentId,16,0,1,0,0,0,lat,lon,alt);
//        sendMessage(ci);
//    }

    public void autoMode() {
        CommandLong ci = new CommandLong(systemId,componentId,176,0,3,3,
                0,0,0,0,0);
        sendMessage(ci);
    }

    public void landMode(float lat, float lon) {
        CommandLong ci = new CommandLong(systemId,componentId,21,0,4,0,
                0,0,lat,lon,0);
        sendMessage(ci);
    }
    private int sequence;

    private void sendMessage(CommandLong c) {
        try {
            byte[] serializedPayload = c.getBytes();
            byte[] toSend = MavlinkPacket.createUnsignedMavlink2Packet(sequence++,systemId,componentId,76,
                    152,serializedPayload).getRawBytes();
            os.write(toSend);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveInGuidedMode(float x, float y, float z) {
        try {
            byte[] serializedPayload = CommandSetPositionTargetLocalNed.getMessage(systemId,componentId,x,y,z);
            byte[] toSend = MavlinkPacket.createUnsignedMavlink2Packet(sequence++,systemId,componentId,84,
                    143,serializedPayload).getRawBytes();
            os.write(toSend);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        LocalTcpMavlinkConnector lmc = new LocalTcpMavlinkConnector(5762);
//        Thread.sleep(30000);
        while(true) {
//            lmc.landMode(0,0);
//            lmc.updateState();
//            lmc.moveInGuidedMode(1f*.25f,1f*.75f,0f);
//            lmc.autoMode();
            lmc.guidedMode();
            Thread.sleep(5000);
//            lmc.setWayPoint(-35.36319f,149.1652f,5.0f);
//            Thread.sleep(50000);
        }
    }
}
