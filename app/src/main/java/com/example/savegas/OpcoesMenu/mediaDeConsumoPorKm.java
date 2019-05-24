package com.example.savegas.OpcoesMenu;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.savegas.R;

public class mediaDeConsumoPorKm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_media_de_consumo_por_km);

        Button calcular;
        calcular = (Button) findViewById(R.id.calcularMediaConsumo1);
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Declarando EditText's
                EditText kmsRodados;
                EditText litrosAbastecidos;

                //Declarando String's
                String texto1;
                String texto2;

                //Declarando Double's
                double valor2;
                double valor1;

                //Trazendo os EditText's pras variáveis criadas
                kmsRodados = (EditText) findViewById(R.id.editKmsRodados);
                litrosAbastecidos = (EditText) findViewById(R.id.editLitrosAbastecidos);

                //Pegando as String's digitadas nos EditText's
                texto1 = kmsRodados.getText().toString();
                texto2 = litrosAbastecidos.getText().toString();

                //Transformando essas String's em Double's
                valor1 = Double.parseDouble(texto1);
                valor2 = Double.parseDouble(texto2);

                //Gerando o resultado a ser exibido
                String mensagem = String.valueOf((valor1/valor2));

                //Criando POP UP do resultado
                AlertDialog.Builder builder = new AlertDialog.Builder(mediaDeConsumoPorKm.this);
                builder.setTitle("Média");
                builder.setMessage("A média de Litros/Km é: " + mensagem);
                builder.create();
                builder.show();

            }
        });
    }
}
