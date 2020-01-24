package com.example.adiligencia01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static com.example.adiligencia01.CriaBanco.EMAIL;
import static com.example.adiligencia01.CriaBanco.SENHA;
import static com.example.adiligencia01.CriaBanco.TABELA;

public class BancoController {
    private SQLiteDatabase db;
    private CriaBanco banco;

    public BancoController(Context context){
        banco = new CriaBanco(context);
    }
    public String insereDado(String nomecadast, String emailcadast, String senhacadast) {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBanco.NOME, nomecadast);
        valores.put(CriaBanco.EMAIL, emailcadast);
        valores.put(CriaBanco.SENHA, senhacadast);
        resultado = db.insert(TABELA, null, valores);
        db.close();

        if (resultado == -1)
            return "Erro ao inserir registro";
    else
        return "Registro inserido com sucesso";

    }
    public Cursor fazerLogin (String emailcadast, String senhacadast){
        db = banco.getWritableDatabase();
        String sql = "SELECT * FROM " +TABELA+ " WHERE " +EMAIL+ " = ? AND " +SENHA+ " = ?";
        String[] selectionArgs = new String[] { emailcadast, senhacadast};
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        if (cursor !=null) {
            cursor.moveToFirst();
            return cursor;
        }else{
            return null;

        }



    }
}
