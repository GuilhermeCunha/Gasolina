package com.example.savegas;


import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import java.math.BigDecimal;
import java.math.RoundingMode;

import ModulosJava.Distance;
import ModulosJava.ConnectivityInfo;

public class valorAGastarPorTrajeto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_valor_agastar_por_trajeto);


        Button mapa;
        mapa = (Button) findViewById(R.id.BotaoParaMapa);

        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean InternetConnectivity = ConnectivityInfo.isConnected(valorAGastarPorTrajeto.this);
                if(InternetConnectivity){
                    Intent i = new Intent(valorAGastarPorTrajeto.this, com.example.savegas.MapsActivity.class);
                    startActivity(i);
                }
            }
        });
        Button calcular;
        calcular = (Button) findViewById(R.id.BotaoValorTrajeto);

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean InternetConnectivity = ConnectivityInfo.isConnected(valorAGastarPorTrajeto.this);
                if(!InternetConnectivity){
                    AlertDialog.Builder builder = new AlertDialog.Builder(valorAGastarPorTrajeto.this);
                    builder.setTitle("Sem conexão com a internet");
                    builder.setMessage("Para a realização do cálculo é necessário estar conectado!\n");
                    builder.create();
                    builder.show();
                }else{
                    //Declarando EditText's
                    EditText origem;
                    EditText destino;
                    EditText consumo;
                    EditText valorCombustivel;

                    //CheckBox idaEVolta = (CheckBox) findViewById(R.id.CheckIdaEVolta);
                    Switch idaEVolta = (Switch) findViewById(R.id.SwitchIdaEVolta);
                    boolean boolIdaEVolta = idaEVolta.isChecked();




                    //Declarando String's
                    String strOrigem;
                    String strDestino;


                    origem = (EditText) findViewById(R.id.EditOrigem);
                    destino = (EditText) findViewById(R.id.EditDestino);
                    consumo = (EditText) findViewById(R.id.EditConsumo);
                    valorCombustivel = (EditText) findViewById(R.id.EditValorCombustivel);

                    strOrigem = origem.getText().toString();
                    strDestino = destino.getText().toString();

                    try{
                        double dbConsumo = Double.parseDouble(consumo.getText().toString());
                        double dbValorCombustivel = Double.parseDouble(valorCombustivel.getText().toString());
                        double distance = Distance.getDistance(strOrigem, strDestino);

                        //Log.e("DISTANCIA IDA", "Distancia: " + distance);


                        double valorTotal = ((distance/dbConsumo) * dbValorCombustivel);

                        if (boolIdaEVolta) {
                            distance = Distance.getDistance(strDestino, strOrigem);
                            valorTotal += ((distance/dbConsumo) * dbValorCombustivel);
                            //Log.e("DISTANCIA VOLTA", "Distancia: " + distance);
                        }

                        BigDecimal exactValue = new BigDecimal(valorTotal)
                                .setScale(2, RoundingMode.UP);
                        valorTotal = exactValue.doubleValue();
                        //Log.e("NGVL", "Passei pelo VALORTOTAL");
                        //Criando POP UP do resultado
                        AlertDialog.Builder builder = new AlertDialog.Builder(valorAGastarPorTrajeto.this);
                        builder.setTitle("O custo deste trajeto é de:");
                        builder.setMessage("R$" + valorTotal);
                        builder.create();
                        builder.show();

                    }catch (Exception e){
                        AlertDialog.Builder builder0 = new AlertDialog.Builder(valorAGastarPorTrajeto.this);
                        builder0.setTitle("Erro no preenchimento");
                        builder0.setMessage("Algum dado preenchido não está no formato correto");
                        builder0.create();
                        builder0.show();
                    }


                }

            }
        });
    }
}
