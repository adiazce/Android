package pe.adiazc.busroute;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.IndoorBuilding;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import pe.adiazc.busroute.adapter.RouteBusAdapter;
import pe.adiazc.busroute.entity.Route;
import pe.adiazc.busroute.viewmodel.RouteBusViewModel;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    RouteBusViewModel routeBusViewModel;

    public List<Route> listroutes;
    ViewModelProvider.Factory factory ;
    public LatLngBounds.Builder builderOfBounds = new LatLngBounds.Builder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        factory = new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                if(modelClass.isAssignableFrom(RouteBusViewModel.class)){
                    return  (T) new RouteBusViewModel(getApplication());
                }
                throw new IllegalArgumentException("Unknown ViewModel class");
            }
        };
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        routeBusViewModel = factory.create(RouteBusViewModel.class);

         mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
             @Override
             public void onMapLoaded() {
                 routeBusViewModel.getListRoute().observe(MapsActivity.this, new Observer<List<Route>>() {
                     @Override
                     public void onChanged(@Nullable List<Route> routes) {
                         Log.i("observ",routes.size() +"");
                         listroutes = routes;
                     }

                 });
                 Log.i("observ",listroutes.size() +"");
                 if(listroutes != null ) {
                     for(Route r : listroutes ){
                         double lat = r.getLat();
                         double log = r.getLon();
                         LatLng latLng = new LatLng(lat,log);
                         mMap.addMarker( new MarkerOptions().
                                 position(latLng).
                                 title("Vehiculo ID "+r.getVehicleID())
                                 .snippet("Orientacion : "+r.getDirectionText()+"Numero Bloque : "+r.getBlockNumber())

                         );
                         builderOfBounds.include(latLng);
                     }
                     mMap.animateCamera(CameraUpdateFactory.newLatLng(builderOfBounds.build().getCenter()  ));
                     mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builderOfBounds.build(),150));

                 }
             }

         });



    }
}
