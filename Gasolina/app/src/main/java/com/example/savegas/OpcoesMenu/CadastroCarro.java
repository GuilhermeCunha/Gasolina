package com.example.savegas.OpcoesMenu;


import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.savegas.Model.Carro;
import com.example.savegas.R;
import com.example.savegas.controller.CarroCTR;
import com.example.savegas.dbHelper.ConexaoSQLite;

public class CadastroCarro extends AppCompatActivity {
    private static String TAG = "CadastroCarro";

   private TextView KmlAlcool;
    //Declarando EditText's
    private EditText modelo;
    private EditText kml;
    private EditText editAlcool;

    //Declarando String's
    private String texto1;
    private String texto2;
    private String texto3;

    //Declarando Double's
    private double valor1,valor2;

    //Declarando CheckBox
    private CheckBox flex;
    Carro carroACadastrar = new Carro();
    Button calcular;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ConexaoSQLite conexaoSQLite = ConexaoSQLite.getInstancia(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cadastro_carro);
        //Trazendo os EditText's pras variáveis criadas
        modelo = (EditText) findViewById(R.id.modelocarro);
        kml = (EditText) findViewById(R.id.kmporlitro);
        editAlcool= findViewById(R.id.KmlAlcool);
        KmlAlcool= findViewById(R.id.Alcool);


        //Pegando as String's digitadas nos EditText's

        //texto2 = kml.getText().toString();
        //texto3 = editAlcool.getText().toString();

        //Transformando essas String's em Double's

        calcular = (Button) findViewById(R.id.btcadcarro);
        flex = findViewById(R.id.flex);
        flex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flex.isChecked()){
                    editAlcool.setVisibility(View.VISIBLE);
                    KmlAlcool.setVisibility(View.VISIBLE);
                }else{
                    editAlcool.setVisibility(View.INVISIBLE);
                    KmlAlcool.setVisibility(View.INVISIBLE);
                }
            }
        });





        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flex.isChecked()){
                    texto1 = modelo.getText().toString();
                    valor2 = Double.parseDouble(kml.getText().toString());
                    //valor2 = Double.parseDouble(kml.getText().toString());
                    //valor1 = Double.parseDouble(KmlAlcool.getText().toString());
                    valor1 = Double.parseDouble(editAlcool.getText().toString());

                    carroACadastrar.setModelo(texto1);
                    carroACadastrar.setFlex(1);
                    carroACadastrar.setKml1(valor2);
                    carroACadastrar.setKml2(valor1);
                    CarroCTR carroCTR = new CarroCTR (conexaoSQLite);
                    if(carroCTR.salvarCarroController(carroACadastrar)){
                        Log.e(TAG, "PRODUTO CADASTRADO NO BANCO");
                    }else{
                        Log.e(TAG, "PRODUTO NÃO CADASTRADO NO BANCO");
                    }
                    Log.e("CRIADO", "ENTREI NO IF");
                    Toast.makeText(getApplicationContext(),carroACadastrar.getModelo()+"\n" + carroACadastrar.getKml1() + "\n" + carroACadastrar.getKml2() + "\n", Toast.LENGTH_LONG).show();
                }else{
                    texto1 = modelo.getText().toString();
                    //valor2 = Double.parseDouble(kml.getText().toString());
                    //valor1 = Double.parseDouble(KmlAlcool.getText().toString());
                    valor2 = Double.parseDouble(kml.getText().toString());

                    carroACadastrar.setModelo(texto1);
                    carroACadastrar.setKml1(valor2);
                    carroACadastrar.setFlex(0);
                    carroACadastrar.setKml2(0);
                    CarroCTR carroCTR = new CarroCTR (conexaoSQLite);
                    if(carroCTR.salvarCarroController(carroACadastrar)){
                        Log.e(TAG, "PRODUTO CADASTRADO NO BANCO");
                    }else{
                        Log.e(TAG, "PRODUTO NÃO CADASTRADO NO BANCO");
                    }
                    Log.e("CRIADO", "ENTREI NO IF");
                    Log.e("CRIADO", "ENTREI NO ELSE");
                    Toast.makeText(getApplicationContext(),carroACadastrar.getModelo()+"\n" + carroACadastrar.getKml1() + "\n", Toast.LENGTH_LONG).show();

                }





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