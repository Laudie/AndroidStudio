package it.univaq.mobileprogramming.pharmacy.activity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import it.univaq.mobileprogramming.pharmacy.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if(mapFragment != null) mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;
        String NamePharmacy = getIntent().getStringExtra("NamePharmacy");
        String Address = getIntent().getStringExtra("Address");
        String Lat = getIntent().getStringExtra("Lat");
        String Lon = getIntent().getStringExtra("Lon");

            Lat = Lat.replace(",",".");
            Lon = Lon.replace(",",".");
           double latitud = Double.parseDouble(Lat);
           double longitud = Double.parseDouble(Lon);




        LatLng position = new LatLng(latitud, longitud);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 16f));
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(position);
        markerOptions.title(NamePharmacy);
        markerOptions.snippet(Address);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        Marker marker = googleMap.addMarker(markerOptions);
        marker.showInfoWindow();


    }
}
