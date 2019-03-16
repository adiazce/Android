package pe.adiazc.busroute.entity;

import com.google.gson.annotations.SerializedName;

public class Route {
    @SerializedName("VehicleID")
    private long VehicleID;
    @SerializedName("Lat")
    private double Lat;
    @SerializedName("Lon")
    private double Lon;


    @SerializedName("DirectionText")
    private String DirectionText;


    @SerializedName("BlockNumber")
    private String BlockNumber;

    public long getVehicleID() {
        return VehicleID;
    }

    public void setVehicleID(long vehicleID) {
        VehicleID = vehicleID;
    }

    public double getLat() {
        return Lat;
    }

    public void setLat(double lat) {
        Lat = lat;
    }

    public double getLon() {
        return Lon;
    }

    public void setLon(double lon) {
        Lon = lon;
    }


    public String getDirectionText() {
        return DirectionText;
    }

    public void setDirectionText(String directionText) {
        DirectionText = directionText;
    }


    public String getBlockNumber() {
        return BlockNumber;
    }

    public void setBlockNumber(String blockNumber) {
        BlockNumber = blockNumber;
    }
}
