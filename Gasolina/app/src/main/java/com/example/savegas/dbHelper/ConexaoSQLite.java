package com.example.savegas.dbHelper;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexaoSQLite extends SQLiteOpenHelper {
    private static ConexaoSQLite INSTANCIA_CONEXAO;
    private static  final int VERSAO_DB =1;
    private static final String NOME_DB = "bl_carros_app";

    public ConexaoSQLite(Context context) {
        super(context, NOME_DB, null, VERSAO_DB);
    }
    public static ConexaoSQLite getInstancia(Context context){
        if(INSTANCIA_CONEXAO == null){
            INSTANCIA_CONEXAO = new ConexaoSQLite(context);
        }
        return INSTANCIA_CONEXAO;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlTabelaProduto= "CREATE TABLE IF NOT EXISTS Carro" +
                "(Id   INTEGER      PRIMARY KEY AUTOINCREMENT " +
        "NOT NULL,"+
        "Nome VARCHAR (20),"+
                "Flex VARCHAR (10),"+
                "KmL  DOUBLE,"+
                "Kml2 DOUBLE" +
                ")";
                db.execSQL(sqlTabelaProduto);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



}
