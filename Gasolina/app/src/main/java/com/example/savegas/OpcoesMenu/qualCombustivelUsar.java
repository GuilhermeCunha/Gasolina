package com.example.savegas.OpcoesMenu;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.savegas.R;

public class qualCombustivelUsar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_qual_combustivel_usar);


        Button calcular;
        calcular = (Button) findViewById(R.id.calcQualCombUsar);
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Declarando EditText's
                EditText alcoollitro;
                EditText gaslitro;

                //Declarando String's
                String texto1;
                String texto2;

                //Declarando Double's
                double valor2;
                double valor1;

                //Trazendo os EditText's pras variáveis criadas
                alcoollitro = (EditText) findViewById(R.id.alcoollitro);
                gaslitro = (EditText) findViewById(R.id.gaslitro);

                //Pegando as String's digitadas nos EditText's
                texto1 = alcoollitro.getText().toString();
                texto2 = gaslitro.getText().toString();

                //Transformando essas String's em Double's
                valor1 = Double.parseDouble(texto1);
                valor2 = Double.parseDouble(texto2);

                //Gerando o resultado a ser exibido
                double result = (valor2*0.7);
                String Mensagem ;

                if(result<valor1){
                    Mensagem = "Gasolina";
                }else{
                    Mensagem = "Alcool";
                }


                //Criando POP UP do resultado
                AlertDialog.Builder builder = new AlertDialog.Builder(qualCombustivelUsar.this);
                builder.setTitle("Rentável");
                builder.setMessage("O combustível mais rentável: " + Mensagem);
                builder.create();
                builder.show();

            }
        });


    }
}
