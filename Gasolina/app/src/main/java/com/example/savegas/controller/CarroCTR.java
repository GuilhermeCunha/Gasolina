package com.example.savegas.controller;

import com.example.savegas.DAO.CarroDAO;
import com.example.savegas.Model.Carro;
import com.example.savegas.dbHelper.ConexaoSQLite;

import java.util.List;

public class CarroCTR {
    private static String TAG = "CarroController";
    //private final CarroDAO montanteDAO;
    private final CarroDAO carroDAO;

    public CarroCTR(ConexaoSQLite conexaoSQLite) {
        this.carroDAO = new CarroDAO(conexaoSQLite);
    }

    public boolean salvarCarroController(Carro carro){
        return this.carroDAO.salvarCarro(carro);
    }
    public List<Carro> listarCarrosController(){
        return this.carroDAO.listarCarros();
    }
}
