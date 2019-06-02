package com.example.savegas.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.savegas.Model.Carro;
import com.example.savegas.dbHelper.ConexaoSQLite;

import java.util.ArrayList;
import java.util.List;

public class CarroDAO {
    private static String TAG = "CarroDAO";
    private final ConexaoSQLite conexaoSQLite;

    public boolean salvarCarro(Carro c){
        SQLiteDatabase db = conexaoSQLite.getWritableDatabase();

        try{
            ContentValues values = new ContentValues();
            if(c.getFlex()==1){

                values.put("Flex", "True");
                values.put("KmL2", c.getKml2());
                values.put("Nome", c.getModelo() );
                values.put("KmL", c.getKml1());

                db.insert("Carro",null,values);

            }else if(c.getFlex()==0){
                values.put("Flex", "False");
                values.put("KmL2", c.getKml2());
                values.put("Nome", c.getModelo() );

                values.put("KmL", c.getKml1());

                db.insert("Carro",null,values);

            }

            return true;


        }catch (Exception e){
            e.printStackTrace();

        }finally {
            if(db!=null){
                db.close();
            }
        }

        return false;
    }


    public List<Carro> listarCarros(){
        List<Carro> carroList = new ArrayList<>();
        SQLiteDatabase db = null;
        //SQLiteDatabase db = conexaoSQLite.getWritableDatabase();
        Cursor cursor;
        String sql = "SELECT * FROM Carro;";
        try{
            db = conexaoSQLite.getReadableDatabase();
            cursor = db.rawQuery(sql, null);
            if(cursor.moveToFirst()){
                do{
                    Carro carro = new Carro();
                    carro.setId(cursor.getInt(0));
                    carro.setModelo(cursor.getString(1));
                    carro.setFlex(cursor.getInt(2));
                    carro.setKml1(cursor.getDouble(3));
                    carro.setKml2(cursor.getDouble(4));
                    carroList.add(carro);
                }while (cursor.moveToNext());
            }
        }catch (Exception e){
            Log.e(TAG, "ERRO: " + e.getMessage());
        }finally {
            if(db!= null)db.close();
        }

        return carroList;

    }







    public CarroDAO(ConexaoSQLite conexaoSQLite) {
        this.conexaoSQLite = conexaoSQLite;
    }
}
