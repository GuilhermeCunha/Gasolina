package com.example.savegas;

import android.Manifest;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private static final String TAG = "MapsActivity";
    private GoogleMap mMap;

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 15f;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(this, "Map is Ready", Toast.LENGTH_SHORT).show();
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        //LatLng llorigem = new LatLng();
        //LatLng lldestino = new LatLng();
        MarkerOptions origem = new MarkerOptions();
        //origem.position(llorigem);
        MarkerOptions destino = new MarkerOptions();
        //origem.position(lldestino);

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        LatLng brasil = new LatLng(-12.500932, -39.189517);
        mMap.addMarker(new MarkerOptions().position(brasil).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(brasil));

        Button botaoCalcular;
        botaoCalcular = (Button) findViewById(R.id.BotaoCalcularGastoPorTrajeto);
        //botaoCalcular.setOnEditorActionListener();
        Log.e("CRIADO", "Passei pelo botao");
        botaoCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("CRIADO", "Entrei pelo botao");
                EditText origem;
                EditText destino;

                origem = (EditText) findViewById(R.id.EditEnderecoOrigem);
                destino = (EditText) findViewById(R.id.EditEnderecoDestino);

                String txtOrigem = origem.getText().toString();
                String txtDestino = destino.getText().toString();
                Log.e("CRIADO: " , txtOrigem);
                Log.e("CRIADO: " , txtDestino);

                Geocoder geocoder = new Geocoder(MapsActivity.this);
                //Address adOrigem, adDestino;

                List<Address> list = new ArrayList<>();
                try{
                    list = geocoder.getFromLocationName(txtOrigem, 1);
                    Address adOrigem = list.get(0);
                    list = geocoder.getFromLocationName(txtDestino, 1);
                    Address adDestino = list.get(0);
                    Log.e("ENDEREÇO", adOrigem.getAddressLine(0));
                    Log.e("ENDEREÇO", adDestino.getAddressLine(0));
                    LatLng llOrigem = new LatLng(adOrigem.getLatitude(),adOrigem.getLongitude());
                    LatLng llDestino = new LatLng(adDestino.getLatitude(),adDestino.getLongitude());

                    moveCamera(llOrigem,DEFAULT_ZOOM, adOrigem.getAddressLine(0));

                } catch (Exception e) {
                    Log.e(TAG, "CATCH DO GEOCODER");
                }


            }
        });

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        Log.e("CRIADO", "DEPOIS DO CONTENT");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_maps);
        /*
        setContentView(R.layout.layout_maps);
        //Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
                */
        //onMapReady(mMap);
        initMap();
    }
    private void initMap(){
        Log.d(TAG, "initMap: initializing map");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(MapsActivity.this);
    }

    private void moveCamera(LatLng latLng, float zoom, String title){
        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

        MarkerOptions options = new MarkerOptions().position(latLng).title(title);
        mMap.addMarker(options);
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


}
