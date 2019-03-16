package pe.adiazc.busroute.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.print.PrinterId;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import pe.adiazc.busroute.entity.Route;
import pe.adiazc.busroute.repository.RouteBusRepository;

public class RouteBusViewModel extends AndroidViewModel {
    private RouteBusRepository repository;

    LiveData<List<Route>>  listRoute ;

    public RouteBusViewModel(@NonNull Application application) {
        super(application);

        repository = new RouteBusRepository(application);

        listRoute = repository.getListRoute();

    }

    public    LiveData<List<Route>>  getListRoute() {
        return listRoute;
    }
}
