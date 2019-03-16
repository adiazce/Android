package pe.adiazc.busroute.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.List;

import pe.adiazc.busroute.entity.Route;
import pe.adiazc.busroute.request.RouteRequest;
import pe.adiazc.busroute.response.RouteBusResponse;
import pe.adiazc.busroute.servicio.SevicioManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RouteBusRepository {

    Retrofit retrofit ;
    MutableLiveData<List<Route>> listRoute;
    RouteRequest request;
    public RouteBusRepository(Application application) {

        retrofit = (new SevicioManager()).make();
        request = retrofit.create(RouteRequest.class);
        listRoute = new MutableLiveData<List<Route>>();

        request.BusPositions().enqueue(new Callback<RouteBusResponse>() {
            @Override
            public void onResponse(Call<RouteBusResponse> call, Response<RouteBusResponse> response) {
                Log.i("ingo",response.isSuccessful()+"");
                if(response.isSuccessful()) {
                    Log.i("response",response.body().getBusPositions().size()+"");
                    RouteBusResponse routeBusResponse = response.body();
                    listRoute.setValue(routeBusResponse.getBusPositions());
                }
            }

            @Override
            public void onFailure(Call<RouteBusResponse> call, Throwable t) {
                Log.i("ERROR",t.getMessage());
            }
        });

    }



    public LiveData< List<Route> > getListRoute() {
        return listRoute;
    }
}
