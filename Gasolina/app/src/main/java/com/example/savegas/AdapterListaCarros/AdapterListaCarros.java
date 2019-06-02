package com.example.savegas.AdapterListaCarros;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.savegas.Model.Carro;
import com.example.savegas.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterListaCarros extends BaseAdapter {
    private Context context;

    public AdapterListaCarros(Context context, List<Carro> carroList, ArrayList<Double> valores ) {
        this.context = context;
        this.carroList = carroList;
        this.valores = valores;
    }
    public AdapterListaCarros(Context context, List<Carro> carroList) {
        this.context = context;
        this.carroList = carroList;
    }

    private List<Carro> carroList;
    private ArrayList<Double> valores;

    @Override
    public int getCount() {
        return this.carroList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.carroList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void removerCarro(int position){
        this.carroList.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(this.context, R.layout.layout_cadastro_carro, null);


        TextView modelo = (TextView) v.findViewById(R.id.TextModeloCarro);
        TextView flex = (TextView) v.findViewById(R.id.flex);
        TextView kml1 = (TextView) v.findViewById(R.id.TextKmPorLitro);
        TextView kml2 = (TextView) v.findViewById(R.id.Alcool);

        try{
            modelo.setText(this.carroList.get(position).getModelo());
            //tipoMontante.setText(this.montanteList.get(position).getTipo());
            flex.setText(this.carroList.get(position).getFlex());

            //if(this.valores != null)valorEmReais.setText(this.valores.get(position).toString());
            double Valor = this.carroList.get(position).getKml1();
            String strValor = String.valueOf(Valor);
            kml1.setText(strValor);
            double Valor1 = this.carroList.get(position).getKml2();
            String strValor1 = String.valueOf(Valor1);
            kml1.setText(strValor1);
        }catch (Exception e){
            Log.e("GETVIEW", "ERRO GETVIEW: " + e.getMessage());
        }

        //double cotacao = (double) pegarCotacao(this.montanteList.get(position).getTipo());
        //valorEmReais.setText("R$" + cotacao);
        return v;
    }


}

