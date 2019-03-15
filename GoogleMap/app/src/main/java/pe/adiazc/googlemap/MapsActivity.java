package pe.adiazc.googlemap;

import android.arch.lifecycle.LiveData;
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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import pe.adiazc.googlemap.entidad.Marcador;
import pe.adiazc.googlemap.repository.MarcadorRepository;
import pe.adiazc.googlemap.viewmodel.MarcadorViewModel;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback  {

    private GoogleMap mMap;

    private MarcadorViewModel marcadorViewModel;

   public LatLngBounds.Builder builderOfBounds = new LatLngBounds.Builder();
   public  List<Marcador> listaMarca;
   Marcador marcador ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        if(   this.getIntent().hasExtra("id") ){
            Bundle parm = this.getIntent().getExtras();
            marcador = new Marcador();
                int id = (int)  parm.get("id")  ;
            marcador.setId(  id )   ;
        }



        ViewModelProvider.Factory  factory = new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                if(modelClass.isAssignableFrom(MarcadorViewModel.class)){
                    return   (T) new MarcadorViewModel( getApplication());
                }

                throw new IllegalArgumentException("Unknown ViewModel class");
            }
        };

        marcadorViewModel = factory.create(MarcadorViewModel.class);
        marcadorViewModel.getAllmarcador().observe(this, new Observer<List<Marcador>>() {
            @Override
            public void onChanged(@Nullable List<Marcador> marcadors) {
                listaMarca = marcadors;
            }
        });






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




            mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                @Override
                public void onMapLoaded() {
                    if(marcador != null){
                        if(listaMarca != null ){
                            Marcador item = listaMarca.get( marcador.getId()-1 );

                            LatLng latLng = new LatLng( Double.valueOf(item.getLatitud()),Double.valueOf(item.getLongitud()));
                            mMap.addMarker(new MarkerOptions()
                                    .position(latLng)
                                    .title(item.getNombre())
                                    .snippet(item.getDescripcion()))
                                    .showInfoWindow();
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,13));
                        }

                    }else{
                    if(listaMarca != null ){

                        for (Marcador m : listaMarca){
                            LatLng latLng = new LatLng( Double.valueOf(m.getLatitud()),Double.valueOf(m.getLongitud())  );

                            mMap.addMarker(new MarkerOptions()
                                    .position(latLng)
                                    .title(m.getNombre())
                                    .snippet(m.getDescripcion()))
                                    .showInfoWindow();
                            builderOfBounds.include(latLng);
                        }

                        mMap.animateCamera(CameraUpdateFactory.newLatLng(builderOfBounds.build().getCenter()  ));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builderOfBounds.build() , 150));



                    }


                }
                }
            });




    }





}
