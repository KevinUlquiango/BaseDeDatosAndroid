package com.example.basededatosandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLData;

public class MainActivity extends AppCompatActivity {
    private EditText codigo,descripcion,precio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        codigo = findViewById(R.id.txt_codigo);
        descripcion = findViewById(R.id.txt_descripcion);
        precio = findViewById(R.id.txt_precio);
    }

    //btn registrar
    //primero debi haber creado la clase AdminSQLiteOpenHelper
    public void registrar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        SQLiteDatabase baseDeDatos = admin.getWritableDatabase();
        String codigoString,descripcionString,precioString;
        codigoString = codigo.getText().toString();
        descripcionString = descripcion.getText().toString();
        precioString = precio.getText().toString();

        //empty significa vacio

        if(!codigoString.isEmpty() && descripcionString.isEmpty() && precioString.isEmpty()){
            ContentValues registrar = new ContentValues();
            registrar.put("codigo",codigoString);
            registrar.put("descripcion",descripcionString);
            registrar.put("precio",precioString);

            //Inserat los registrs en la base de datos
            baseDeDatos.insert("articulos",null,registrar);
            baseDeDatos.close();
            codigo.setText("");
            descripcion.setText("");
            precio.setText("");
            Toast.makeText(this,"Registro Exitoso del producto codigo: " + codigoString,Toast.LENGTH_SHORT ).show();
        }else{
            Toast.makeText(this,"Debes llenar todos los campos",Toast.LENGTH_SHORT).show();
        }


    }

}