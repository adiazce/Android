package pe.adiazc.busroute.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import pe.adiazc.busroute.entity.Route;

public class RouteBusResponse {
    @SerializedName("BusPositions")
    List<Route> BusPositions  ;

    public List<Route> getBusPositions() {
        return BusPositions;
    }

    public void setBusPositions(List<Route> busPositions) {
        BusPositions = busPositions;
    }
}
