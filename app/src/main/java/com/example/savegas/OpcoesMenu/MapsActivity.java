package com.example.savegas.OpcoesMenu;

import android.Manifest;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.savegas.Mapa.Helpers.MapsDistanceMatrix;
import com.example.savegas.Mapa.Helpers.PlaceAutocompleteAdapter;
import com.example.savegas.R;
import com.example.savegas.TaskLoadedCallback;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback ,
        GoogleApiClient.OnConnectionFailedListener, TaskLoadedCallback {
    private static final String TAG = "MapsActivity";
    private GoogleMap mMap;

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 15f;


    private PlaceAutocompleteAdapter mPlaceAutocompleteAdapter;
    private GoogleApiClient mGoogleApiClient;
    private static final LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(
            new LatLng(-40, -168), new LatLng(71, 136));
    private MapsDistanceMatrix matrix;

    //Distance
    private MarkerOptions place1, place2;
    private Polyline currentPolyline;

    //Elementos
    //private AutoCompleteTextView acOrigem;
    //private AutoCompleteTextView acDestino;
    private EditText edOrigem;
    private EditText edDestino;
    private Button BotaoCalcular;



    //Informações do carro
    private String modelo = "";
    private boolean gasolina = true;
    private double consumo = 0.0;



    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(this, "Map is Ready", Toast.LENGTH_SHORT).show();
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        final ArrayList markerPoints= new ArrayList();
        /*
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

                    // Getting URL to the Google Directions API
                    String url = getDirectionsUrl(origin, dest);

                    DownloadTask downloadTask = new DownloadTask();

                    // Start downloading json data from Google Directions API
                    downloadTask.execute(url);
                }

            }
        });*/

        //LatLng llorigem = new LatLng();
        //LatLng lldestino = new LatLng();
        //origem.position(llorigem);
        //origem.position(lldestino);

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);

        LatLng brasil = new LatLng(-12.500932, -39.189517);
        mMap.addMarker(new MarkerOptions().position(brasil).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(brasil));
        init();
    }

    public void init(){
        /*
        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();
        mPlaceAutocompleteAdapter = new PlaceAutocompleteAdapter(this, mGoogleApiClient, LAT_LNG_BOUNDS, null);
        acOrigem = (AutoCompleteTextView) findViewById(R.id.EditEnderecoOrigem);
        acOrigem.setAdapter(mPlaceAutocompleteAdapter);
        acDestino = (AutoCompleteTextView) findViewById(R.id.EditEnderecoDestino);
        acDestino.setAdapter(mPlaceAutocompleteAdapter);
        */


        Button botaoCalcular;
        botaoCalcular = (Button) findViewById(R.id.BotaoCalcularGastoPorTrajeto);
        //botaoCalcular.setOnEditorActionListener();
        Log.e(TAG, "Passei pelo botao");
        botaoCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edOrigem = (EditText) findViewById(R.id.EditEnderecoOrigem);
                edDestino = (EditText) findViewById(R.id.EditEnderecoDestino);
                Log.e("CRIADO", "Entrei pelo botao");


                String txtOrigem = edOrigem.getText().toString();
                String txtDestino = edDestino.getText().toString();
                Log.e(TAG , txtOrigem);
                Log.e(TAG, txtDestino);

                Geocoder geocoder = new Geocoder(MapsActivity.this);
                //Address adOrigem, adDestino;

                List<Address> list = new ArrayList<>();
                try{
                    list = geocoder.getFromLocationName(txtOrigem, 1);
                    Address adOrigem = list.get(0);
                    list = geocoder.getFromLocationName(txtDestino, 1);
                    Address adDestino = list.get(0);
                    Log.e(TAG, adOrigem.getAddressLine(0));
                    Log.e(TAG, adDestino.getAddressLine(0));
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
                    Toast.makeText(getApplicationContext(), "Distancia: " + distance + "kms", Toast.LENGTH_LONG).show();

                    place1 = (new MarkerOptions().position(llOrigem).title(adOrigem.getAddressLine(0)));
                    place2 = (new MarkerOptions().position(llDestino).title(adDestino.getAddressLine(0)));
                    moveCamera(llOrigem,DEFAULT_ZOOM, adOrigem.getAddressLine(0));
                    /*
                    //Getting both the coordinates
                    Log.e("DISTANCIA", "ANTES DO FETCH");
                    new FetchURL(MapsActivity.this).execute(getUrl(place1.getPosition(), place2.getPosition(), "driving"), "driving");
                    Log.e("DISTANCIA", "DEPOIS DO FETCH");

                    Log.e("DISTANCIA", "ANTES DE CHAMAR O MATRIX");
                    double kms = getDistanceInfo(llOrigem, llDestino);
                    Log.e("DISTANCIA", "DEPOIS DE CHAMAR O MATRIX");
                    */

                    /*
                    CRIA UMA LINHA RETA ENTRE OS DOIS PONTOS
                    mMap.addPolyline(new PolylineOptions().add(llOrigem, new LatLng(llOrigem.latitude,llOrigem.longitude),new LatLng(llDestino.latitude,llDestino.longitude), llDestino))
                    .setWidth(10);
                    */
                } catch (Exception e) {
                    Log.e(TAG, "CATCH DO GEOCODER");
                }


            }
        });


        MarkerOptions origem = new MarkerOptions();
        MarkerOptions destino = new MarkerOptions();
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

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Log.e("Bundle", "ENTREI NO IF");
            modelo = extras.getString("modelo");
            gasolina = extras.getBoolean("gasolina");
            consumo = extras.getDouble("consumo");
            Log.e("Bundle", "2 Modelo: " + modelo);
            Log.e("Bundle", "2 gasolina: " + gasolina);
            Log.e("Bundle", "2 consumo: " + consumo);
        }

        initMap();
    }
    private void initMap(){
        Log.d(TAG, "initMap: initializing map");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        /*
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.mapNearBy);
                */
        mapFragment.getMapAsync(this);
        //mapFragment.getMapAsync(MapsActivity.this);
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
