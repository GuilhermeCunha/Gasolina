package com.example.savegas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Banco.BancoController;
import Banco.CriaBanco;

public class ConsultaBanco extends AppCompatActivity {
    CriaBanco banco = new CriaBanco(this);
    Button botao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_consulta_banco);
        botao = (Button) findViewById(R.id.BotaoBuscarCarroPorNome);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText;
                editText = (EditText) findViewById(R.id.EditConsultarCarroPorNome);
                String strEditText = editText.getText().toString();
                BancoController crud = new BancoController(getBaseContext());
                Log.d("STREDITTEXT", strEditText);
                boolean encontrado = crud.pesquisarCarro(strEditText);
                if(encontrado){
                    Toast.makeText(getApplicationContext(), "Carro encontrado", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Carro não encontrado", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
