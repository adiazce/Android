package pe.adiazc.busroute;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import pe.adiazc.busroute.adapter.RouteBusAdapter;
import pe.adiazc.busroute.entity.Route;
import pe.adiazc.busroute.viewmodel.RouteBusViewModel;

public class MainActivity extends AppCompatActivity {
    Button  btMap ;
    RecyclerView rvRouteBus;
    RouteBusViewModel routeBusViewModel;
    RouteBusAdapter adapter;
    List<Route> listroutes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btMap = findViewById(R.id.btMap);
        rvRouteBus = findViewById(R.id.rvRouteBus);
        InicializarLista();

        ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                if(modelClass.isAssignableFrom(RouteBusViewModel.class)){
                    return (T) new RouteBusViewModel(getApplication());
                }

                throw new IllegalArgumentException("Unknown ViewModel class");
            }
        };
        routeBusViewModel = factory.create(RouteBusViewModel.class);
        routeBusViewModel.getListRoute().observe(this, new Observer<List<Route>>() {
            @Override
            public void onChanged(@Nullable List<Route> routes) {
                Log.d("OBSER",routes.size()+"");
                adapter.setLisRoute(routes);
            }
        });

        btMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MapsActivity.class);
                startActivity(intent);
            }
        });


    }

    public void InicializarLista(){
        adapter = new RouteBusAdapter(this);


        rvRouteBus.setAdapter(adapter);
        rvRouteBus.setLayoutManager(new LinearLayoutManager(this));

    }
}
