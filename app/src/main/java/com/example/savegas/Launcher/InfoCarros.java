package com.example.savegas.Launcher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.example.savegas.R;

public class InfoCarros extends AppCompatActivity {
    Button botao;
    EditText editModelo;
    EditText editConsumo;
    Switch sGasolina;
    Switch sAlcool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_info_carros);
        botao = (Button) findViewById(R.id.BotaoIrParaMenu);
        sGasolina = (Switch) findViewById(R.id.SwitchGasolina);
        sAlcool = (Switch) findViewById(R.id.SwitchAlcool);
        sGasolina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sGasolina.isChecked()){
                    sAlcool.setChecked(false);
                }
            }
        });
        sAlcool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sAlcool.isChecked()){
                    sGasolina.setChecked(false);
                }
            }
        });

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean gasolina;
                if(sAlcool.isChecked()){
                    gasolina = false;
                }else if(sGasolina.isChecked()){
                    gasolina = true;
                }else{
                    gasolina = true;
                }

                editModelo = (EditText) findViewById(R.id.EditModeloCarro);
                String strModelo = editModelo.getText().toString();

                editConsumo = (EditText) findViewById(R.id.EditConsumoCarro);
                Double dbConsumo = Double.parseDouble(editConsumo.getText().toString());

                Intent i = new Intent(InfoCarros.this, Menu.class);
                Bundle bundle = new Bundle();
                bundle.putString("modelo", strModelo);
                bundle.putDouble("consumo", dbConsumo);
                bundle.putBoolean("gasolina", gasolina);

                i.putExtras(bundle);
                startActivity(i);
            }
        });


    }
}
