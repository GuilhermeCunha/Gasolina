package com.example.savegas.OpcoesMenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.savegas.Launcher.Menu;
import com.example.savegas.OpcoesMenu.calculoAbastecimento;
import com.example.savegas.OpcoesMenu.qualCombustivelUsar;
import com.example.savegas.OpcoesMenu.mediaDeConsumoKmInicialKmFinal;
import com.example.savegas.OpcoesMenu.mediaDeConsumoPorKm;
import com.example.savegas.R;

public class MaisOpcoes extends AppCompatActivity {
    private boolean gasolina = true;
    private double consumo = 0.0;

    Button medconsumoinicialfinal;
    Button medconsumopercorrido;
    Button calcabastecimento;
    Button qualcomusar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mais_opcoes);

        medconsumoinicialfinal = (Button) findViewById(R.id.medconsumoinicialfinal);
        medconsumopercorrido = (Button) findViewById(R.id.medconsumopercorrido);
        calcabastecimento = (Button) findViewById(R.id.calcabastecimento);
        qualcomusar = (Button) findViewById(R.id.qualcomusar);

        medconsumoinicialfinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MaisOpcoes.this, mediaDeConsumoKmInicialKmFinal.class);
                startActivity(i);
            }
        });

        medconsumopercorrido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MaisOpcoes.this, mediaDeConsumoPorKm.class);
                startActivity(i);
            }
        });

        calcabastecimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MaisOpcoes.this, calculoAbastecimento.class);
                startActivity(i);
            }
        });

        qualcomusar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MaisOpcoes.this, qualCombustivelUsar.class);
                startActivity(i);
            }
        });

    }




}
