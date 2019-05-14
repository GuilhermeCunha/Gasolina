package com.example.savegas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button media1;
    Button media2;
    Button valorTrajeto;
    Button calcAbastecimento;
    Button gasolinaAlcool;
    Button configuracoes;

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
                Intent i = new Intent(MainActivity.this, valorAGastarPorTrajeto.class);
                startActivity(i);
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


    }
}
