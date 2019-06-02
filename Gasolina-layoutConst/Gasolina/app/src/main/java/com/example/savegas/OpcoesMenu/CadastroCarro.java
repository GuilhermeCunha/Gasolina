package com.example.savegas.OpcoesMenu;


import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.savegas.R;

public class CadastroCarro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cadastro_carro);

        Button calcular;
        calcular = (Button) findViewById(R.id.btcadcarro);
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Declarando EditText's
                EditText modelo;
                EditText kml;

                //Declarando String's
                String texto1;
                String texto2;

                //Declarando Double's
                double valor1;

                //Trazendo os EditText's pras variáveis criadas
                modelo = (EditText) findViewById(R.id.modelocarro);
                kml = (EditText) findViewById(R.id.kmporlitro);

                //Pegando as String's digitadas nos EditText's
                texto1 = modelo.getText().toString();
                texto2 = kml.getText().toString();

                //Transformando essas String's em Double's
                valor1 = Double.parseDouble(texto2);

                //VALORES PARA JOGAR NO BANCO, VARIAVEIS: ******valor1 = kml que faz o caarro & texto1 = modelo******

                //Criando POP UP do resultado
                AlertDialog.Builder builder = new AlertDialog.Builder(CadastroCarro.this);
                builder.setTitle("Cadastro ");
                builder.setMessage("Cadastro efetuado! " );
                builder.create();
                builder.show();

            }
        });
    }
}