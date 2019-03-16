package pe.adiazc.busroute.request;

import android.arch.lifecycle.LiveData;

import java.util.List;

import pe.adiazc.busroute.entity.Route;
import pe.adiazc.busroute.response.RouteBusResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface RouteRequest {
    @Headers("api_key: 9ff6a10a756740e7a9fd950185520bf8")
    @GET("Bus.svc/json/jBusPositions/")
    Call<RouteBusResponse>   BusPositions( );
}
