package com.example.savegas.OpcoesMenu;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.savegas.R;



public class mediaDeConsumoKmInicialKmFinal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_media_de_consumo_km_inicial_km_final);

        Button calcular;
        calcular = (Button) findViewById(R.id.caclkminicialfinal);
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Declarando EditText's
                EditText KmInicial;
                EditText KmFinal;
                EditText Litros;

                //Declarando String's
                String texto1;
                String texto2;
                String texto3;

                //Declarando Double's
                double valor1;
                double valor2;
                double valor3;

                //Trazendo os EditText's pras variáveis criadas
                KmInicial = (EditText) findViewById(R.id.kmInicio);
                KmFinal   = (EditText) findViewById(R.id.kmfinal);
                Litros    = (EditText) findViewById(R.id.litrosabastecidos2);

                //Pegando as String's digitadas nos EditText's
                texto1 = KmInicial.getText().toString();
                texto2 = KmFinal.getText().toString();
                texto3 = Litros.getText().toString();

                //Transformando essas String's em Double's
                valor1 = Double.parseDouble(texto1);
                valor2 = Double.parseDouble(texto2);
                valor3 = Double.parseDouble(texto3);

                //Gerando o resultado a ser exibido

                String Mensagem = String.valueOf((valor2-valor1)/valor3);

                //Criando POP UP do resultado
                AlertDialog.Builder builder = new AlertDialog.Builder(mediaDeConsumoKmInicialKmFinal.this);
                builder.setTitle("Média");
                builder.setMessage("A média de consumo é: " + Mensagem);
                builder.create();
                builder.show();

            }

        });

    }
}
