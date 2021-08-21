package com.example.basededatosandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class AdminSQLiteOpenHelper extends SQLiteOpenHelper  {

    //cuando de error importar el constructor y la sobreescrira de metodos con alt + enter
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase baseDeDatos) {
        String tablaBaseDatos = "create table articulos(codigo int primary key, descripcion text, precio real)";
        baseDeDatos.execSQL(tablaBaseDatos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
