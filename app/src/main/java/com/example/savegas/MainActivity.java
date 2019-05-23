package com.example.savegas;

import android.app.Dialog;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
/*
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import Banco.BancoController;
import Banco.CriaBanco;
*/

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;
    Button media1;
    Button media2;
    Button valorTrajeto;
    Button calcAbastecimento;
    Button gasolinaAlcool;
    Button configuracoes;
    Button cadastrarCarro;
    Button consultaBanco;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        media1 = (Button) findViewById(R.id.media1);
        media2 = (Button) findViewById(R.id.media2);
        valorTrajeto = (Button) findViewById(R.id.valortrajeto);
        calcAbastecimento = (Button) findViewById(R.id.calculoabastecimento);
        gasolinaAlcool = (Button) findViewById(R.id.gasolinaalcool);
        configuracoes = (Button) findViewById(R.id.configuracoes);
        cadastrarCarro = (Button) findViewById(R.id.cadastrarCarro);
        consultaBanco = (Button) findViewById(R.id.consultaBanco);


        media1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, mediaDeConsumoPorKm.class);
                startActivity(i);
            }
        });

        media2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, mediaDeConsumoKmInicialKmFinal.class);
                startActivity(i);
            }
        });

        valorTrajeto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(isServicesOK()){
                    //Intent i = new Intent(MainActivity.this, valorAGastarPorTrajeto.class);
                    Intent i = new Intent(MainActivity.this, MapsActivity.class);
                    startActivity(i);
                }

            }
        });

        calcAbastecimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, calculoAbastecimento.class);
                startActivity(i);
            }
        });

        gasolinaAlcool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, gasolinaOuAlcool.class);
                startActivity(i);
            }
        });

        configuracoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, com.example.savegas.configuracoes.class);
                startActivity(i);
            }
        });

        cadastrarCarro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, com.example.savegas.CadastraCarro.class);
                startActivity(i);
            }
        });
        consultaBanco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, com.example.savegas.ConsultaBanco.class);
                startActivity(i);
            }
        });

    }

    public boolean isServicesOK(){
        Log.e("CRIADO", "ENTREI NO ISSERVICES");
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if(available == ConnectionResult.SUCCESS){
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        } else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(MainActivity.this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
