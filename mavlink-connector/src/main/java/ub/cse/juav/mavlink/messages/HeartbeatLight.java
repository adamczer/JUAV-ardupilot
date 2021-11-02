package ub.cse.juav.mavlink.messages;

public class HeartbeatLight {
    public static long getFlightMode(byte[] ba){
        return Desearializers.longValue(Desearializers.slice(ba,0,1),true);
    }
}
