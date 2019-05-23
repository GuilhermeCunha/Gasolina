package com.example.savegas;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class valorAGastarPorTrajeto2 extends AppCompatActivity{
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_valor_agastar_por_trajeto2);
        Log.e("CRIADO", "DEPOIS DO CONTENT");
        Intent i = new Intent(valorAGastarPorTrajeto2.this, MapsActivity.class);
        startActivity(i);
        /*
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.frameMap, new MapsActivity(), "MapsFragment");
        transaction.add(R.id.frameEnderecos, new FrameEnderecos(), "EnderecosFragment");
        transaction.commitAllowingStateLoss();
        */
    }
}
