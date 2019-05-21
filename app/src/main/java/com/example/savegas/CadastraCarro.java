package com.example.savegas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Banco.BancoController;
import Banco.CriaBanco;

public class CadastraCarro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CriaBanco banco = new CriaBanco(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_cadastra_carro);

        Button botao = (Button)findViewById(R.id.BotaoCadastrarCarro);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BancoController crud = new BancoController(getBaseContext());
                EditText nome = (EditText)findViewById(R.id.EditNomeCarro);
                EditText consumo = (EditText)findViewById((R.id.EditConsumoCarro));

                String strNome = nome.getText().toString();
                double dbConsumo = Double.parseDouble(consumo.getText().toString());
                String resultado;

                resultado = crud.insereDado(strNome, dbConsumo);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
            }
        });
    }
}
