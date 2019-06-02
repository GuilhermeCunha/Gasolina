package com.example.savegas.OpcoesMenu;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.savegas.Mapa.Helpers.directionhelpers.FetchURL;
import com.example.savegas.R;
import com.example.savegas.TaskLoadedCallback;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

import static com.example.savegas.Mapa.Helpers.MapsDistanceMatrix.getDistanceInfo;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback ,
        GoogleApiClient.OnConnectionFailedListener, TaskLoadedCallback {
    private static final String TAG = "MapsActivity";
    private GoogleMap mMap;

    private static final float DEFAULT_ZOOM = 15f;



    //Distance
    private MarkerOptions place1, place2;
    private Polyline currentPolyline;

    //Elementos
    private EditText edOrigem;
    private EditText edDestino;
    private Button botaoCalcular;
    private Switch idaEVolta;



    //Informações do carro
    private String modelo = "";
    private boolean flex= false;
    private double consumoGasolina = 0.0;
    private double consumoAlcool = 0.0;




    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(this, "Map is Ready", Toast.LENGTH_SHORT).show();
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);


        LatLng bahia = new LatLng(-12.500932, -39.189517);
        mMap.addMarker(new MarkerOptions().position(bahia).title("Bahia"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bahia));
        init();
    }

    public void init(){
        idaEVolta = (Switch) findViewById(R.id.SwitchIdaEVolta2);
        //Permite selecionar pontos através do clique no mapa
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        final ArrayList markerPoints= new ArrayList();
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (markerPoints.size() > 1) {
                    markerPoints.clear();
                    mMap.clear();
                }

                // Adding new item to the ArrayList
                markerPoints.add(latLng);

                // Creating MarkerOptions
                MarkerOptions options = new MarkerOptions();

                // Setting the position of the marker
                options.position(latLng);

                if (markerPoints.size() == 1) {
                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                } else if (markerPoints.size() == 2) {
                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                }

                // Add new marker to the Google Map Android API V2
                mMap.addMarker(options);

                // Checks, whether start and end locations are captured
                if (markerPoints.size() >= 2) {
                    LatLng origin = (LatLng) markerPoints.get(0);
                    LatLng dest = (LatLng) markerPoints.get(1);
                    Log.e(TAG, "Origin: " + origin);
                    Log.e(TAG, "Destin: " + dest);
                    place1 = (new MarkerOptions().position(origin).title("Origem"));
                    place2 = (new MarkerOptions().position(dest).title("Destino"));


                    //Cria a rota entre os dois pontos
                    new FetchURL(MapsActivity.this).execute(getUrl(place1.getPosition(), place2.getPosition(), "driving"), "driving");
                    //Calcula a distância exata da rota
                    double kms = getDistanceInfo(origin, dest);


                    Location a = new Location("");
                    a.setLatitude(origin.latitude);
                    a.setLongitude(origin.longitude);
                    Location b = new Location("");
                    b.setLatitude(dest.latitude);
                    b.setLongitude(dest.longitude);
                    float distance = a.distanceTo(b)/1000;
                    if(idaEVolta.isChecked()){
                        distance *= 2;
                    }
                    Toast.makeText(getApplicationContext(), "Distancia: " + distance + "kms", Toast.LENGTH_LONG).show();

                    /*
                    //Cria reta da marcação da rota
                    mMap.addPolyline(new PolylineOptions().add(origin, new LatLng(origin.latitude,origin.longitude),new LatLng(dest.latitude,dest.longitude), dest))
                            .setWidth(10);
                    */

                }

            }
        });






        botaoCalcular = (Button) findViewById(R.id.BotaoCalcularGastoPorTrajeto);
        botaoCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"Botão acionado");
                edOrigem = (EditText) findViewById(R.id.EditEnderecoOrigem);
                edDestino = (EditText) findViewById(R.id.EditEnderecoDestino);
                String txtOrigem = edOrigem.getText().toString();
                String txtDestino = edDestino.getText().toString();
                Log.e(TAG , "Origem: "+ txtOrigem);
                Log.e(TAG, "Destino" + txtDestino);

                Geocoder geocoder = new Geocoder(MapsActivity.this);

                List<Address> list = new ArrayList<>();
                try{
                    list = geocoder.getFromLocationName(txtOrigem, 1);
                    Address adOrigem = list.get(0);
                    list = geocoder.getFromLocationName(txtDestino, 1);
                    Address adDestino = list.get(0);
                    Log.e(TAG, "Endereço da Origem: " + adOrigem.getAddressLine(0));
                    Log.e(TAG, "Endereço do Destino: " + adDestino.getAddressLine(0));
                    LatLng llOrigem = new LatLng(adOrigem.getLatitude(),adOrigem.getLongitude());
                    LatLng llDestino = new LatLng(adDestino.getLatitude(),adDestino.getLongitude());
                    Location a = new Location("");
                    a.setLatitude(llOrigem.latitude);
                    a.setLongitude(llOrigem.longitude);
                    Location b = new Location("");
                    b.setLatitude(llDestino.latitude);
                    b.setLongitude(llDestino.longitude);


                    mMap.addMarker(new MarkerOptions().position(llOrigem).title(adOrigem.getAddressLine(0)));
                    mMap.addMarker(new MarkerOptions().position(llDestino).title(adDestino.getAddressLine(0)));

                    float distance = a.distanceTo(b)/1000;
                    if(idaEVolta.isChecked()){
                        distance *= 2;
                    }
                    Toast.makeText(getApplicationContext(), "Distancia: " + distance + "kms", Toast.LENGTH_LONG).show();

                    place1 = (new MarkerOptions().position(llOrigem).title(adOrigem.getAddressLine(0)));
                    place2 = (new MarkerOptions().position(llDestino).title(adDestino.getAddressLine(0)));
                    moveCamera(llOrigem,DEFAULT_ZOOM, adOrigem.getAddressLine(0));

                    /*
                    //Cria a rota entre os dois pontos
                    new FetchURL(MapsActivity.this).execute(getUrl(place1.getPosition(), place2.getPosition(), "driving"), "driving");
                    //Pega distância exata da rota
                    double kms = getDistanceInfo(llOrigem, llDestino);
                    */
                    //Cria a marcação da rota
                    mMap.addPolyline(new PolylineOptions().add(llOrigem, new LatLng(llOrigem.latitude,llOrigem.longitude),new LatLng(llDestino.latitude,llDestino.longitude), llDestino))
                    .setWidth(10);


                } catch (Exception e) {
                    Log.e(TAG, "CATCH DO GEOCODER");
                }


            }
        });
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_maps);
        initMap();
    }

    private void initMap(){
        Log.d(TAG, "initMap: initializing map");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    private void moveCamera(LatLng latLng, float zoom, String title){
        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

        MarkerOptions options = new MarkerOptions().position(latLng).title(title);
        mMap.addMarker(options);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }






    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + "AIzaSyAdFxhY1adIeIwLc2iUKEbLtjlw6LJmgWc";
        return url;
    }

    @Override
    public void onTaskDone(Object... values) {
        if (currentPolyline != null)
            currentPolyline.remove();
        currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);
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
