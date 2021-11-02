package ub.cse.juav.mavlink.messages;

public class GlobalPositionIntLight {
    private final double headingDegrees;
    private final double relAlt;
    private final double lon;
    private final double lat;

    private GlobalPositionIntLight(double lat, double lon, double relAlt, double headingDegrees) {
        this.lat = lat;
        this.lon = lon;
        this.relAlt = relAlt;
        this.headingDegrees = headingDegrees;
    }

    public static GlobalPositionIntLight parse(byte[] payload) {
//      lat
        byte[] data = Desearializers.slice(payload,4,4);
        double lat = (double)Desearializers.longValue(data,true)/10000000d;
//      lon
        data = Desearializers.slice(payload,8,4);
        double lon = (double)Desearializers.longValue(data,true)/10000000d;
//      Altrelitive
        data = Desearializers.slice(payload,16,4);
        double relAlt = (double)Desearializers.longValue(data,true) / 1000d;
//      heading
        data = Desearializers.slice(payload,26,3);
        double headingDegrees = (double)Desearializers.longValue(data,true)/100d;
        return new GlobalPositionIntLight(lat,lon,relAlt,headingDegrees);
    }

    public double getHeadingDegrees() {
        return headingDegrees;
    }

    public double getRelAlt() {
        return relAlt;
    }

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }

    @Override
    public String toString() {
        return "GlobalPositionInt{" +
                "headingDegrees=" + headingDegrees +
                ", relAlt=" + relAlt +
                ", lon=" + lon +
                ", lat=" + lat +
                '}';
    }
}
