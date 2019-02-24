package pe.ladrilloslark.maps;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LatLngBounds.Builder builderOfBounds = new LatLngBounds.Builder();
    PolylineOptions polylineOptions ;
    PolygonOptions  polygonOptions ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        polylineOptions = new PolylineOptions();
        polygonOptions = new PolygonOptions();
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

        // Add a marker in Sydney and move the camera
        for ( LatLng  d : this.Ubicaciones()
             ) {
            mMap.addMarker(new MarkerOptions().position(d).title("Lima").snippet("av Argentina"));
            builderOfBounds.include(d);

            polylineOptions.add(d);
            polygonOptions.add(d);

        }


      //  Polyline polyline = mMap.addPolyline(polylineOptions.width(2).color(Color.RED));



        Polygon polygon = mMap.addPolygon( polygonOptions.fillColor(Color.RED).strokeColor(Color.BLUE));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(builderOfBounds.build().getCenter()));



        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {


                mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builderOfBounds.build() , 150));


            }
        });




    }

    public ArrayList<LatLng> Ubicaciones(){
        ArrayList<LatLng>  ubi = new ArrayList<LatLng>();
        ubi.add(new LatLng( -11.991144,-77.0830969)) ;
        ubi.add(new LatLng( -12.0059187,-77.112979)) ;
        ubi.add(new LatLng( -11.9907672,-77.0909522)) ;
        ubi.add(new LatLng( -12.0463267,-77.0857366)) ;
        return ubi;

    }


}
