package Banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by allanromanato on 5/27/15.
 */
public class CriaBanco extends SQLiteOpenHelper {
    public static final String NOME_BANCO = "bancoAPP.db";
    public static final String TABELA = "Carro";
    public static final String ID = "_id";
    public static final String NOME = "carro";
    public static final String CONSUMO = "consumo";
    public static final int VERSAO = 1;

    public CriaBanco(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = (String.format(" CREATE TABLE %s(%s integer primary key autoincrement, %s text, %s REAL)", TABELA, ID, NOME, CONSUMO));
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }

}