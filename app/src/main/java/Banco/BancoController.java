package Banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static Banco.CriaBanco.TABELA;

public class BancoController {

    private SQLiteDatabase db;
    private CriaBanco banco;

    public BancoController(Context context){
        banco = new CriaBanco(context);
    }

    public String insereDado(String nome, Double consumo){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBanco.NOME, nome);
        valores.put(CriaBanco.CONSUMO, consumo);

        resultado = db.insert(TABELA, null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";
    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {CriaBanco.ID, CriaBanco.NOME, CriaBanco.CONSUMO};
        db = banco.getReadableDatabase();
        cursor = db.query(TABELA, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
    public boolean pesquisarCarro(String nome){
        db = banco.getReadableDatabase();
        String sql = "SELECT * FROM " + TABELA + " WHERE NOME= "+ nome;
        Cursor c = db.rawQuery(sql,null);
        if(c.getCount()>0){
            return true;
        }

        c.close();
        db.close();
        return false;
    }
}