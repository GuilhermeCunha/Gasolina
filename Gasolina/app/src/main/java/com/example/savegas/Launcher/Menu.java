package com.example.savegas.Launcher;

import android.app.Dialog;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.savegas.OpcoesMenu.CadastroCarro;
import com.example.savegas.OpcoesMenu.MaisOpcoes;
import com.example.savegas.OpcoesMenu.MapsActivity;
import com.example.savegas.R;
import com.example.savegas.dbHelper.ConexaoSQLite;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import ModulosJava.ConnectivityInfo;
/*
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import Banco.BancoController;
import Banco.CriaBanco;
*/

public class Menu extends AppCompatActivity {
    private static final String TAG = "Menu";
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private String modelo = "";
    private boolean gasolina = true;
    private double consumo = 0.0;
    private Bundle extras;


    Button valorTrajeto;
    Button maisOpcoes;
    ImageButton configuracoes;
    Button cadastroCarro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConexaoSQLite conexaoSQLite = ConexaoSQLite.getInstancia(this);
        /*
        extras = getIntent().getExtras();
        if (extras != null) {
            modelo = extras.getString("modelo");
            gasolina = extras.getBoolean("gasolina");
            consumo = extras.getDouble("consumo");
            Log.e("Bundle", "Modelo: " + modelo);
            Log.e("Bundle", "gasolina: " + gasolina);
            Log.e("Bundle", "consumo: " + consumo);
        }
        */


        valorTrajeto = (Button) findViewById(R.id.vltrajeto);
        configuracoes = (ImageButton) findViewById(R.id.conf);
        maisOpcoes = (Button) findViewById(R.id.maisopcoes);
        cadastroCarro = (Button) findViewById(R.id.btcad);


        maisOpcoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this, MaisOpcoes.class);
                startActivity(i);

            }
        });

        cadastroCarro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this, CadastroCarro.class);
                startActivity(i);
            }
        });


        valorTrajeto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(isServicesOK()){
                    if(ConnectivityInfo.isConnected(Menu.this)){

                        Intent i = new Intent(Menu.this, MapsActivity.class);
                        //i.putExtras(extras);
                        startActivity(i);

                    }else{
                        Toast.makeText(getApplicationContext(), "Conecte-se a internet para poder realizar o calculo através do mapa", Toast.LENGTH_LONG).show();
                    }

                }

            }
        });



        configuracoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this, com.example.savegas.OpcoesMenu.configuracoes.class);
                startActivity(i);
            }
        });


    }

    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(Menu.this);

        if(available == ConnectionResult.SUCCESS){
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        } else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(Menu.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(Menu.this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
