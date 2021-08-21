package com.example.basededatosandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


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

    //======================btn registrar===================================================================================
    //primero debi haber creado la clase AdminSQLiteOpenHelper
    public void registrar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        //Se abre la base de datos en modo lectura y escritura
        SQLiteDatabase baseDeDatos = admin.getWritableDatabase();
        String codigoString,descripcionString,precioString;
        codigoString = codigo.getText().toString();
        descripcionString = descripcion.getText().toString();
        precioString = precio.getText().toString();

        //empty significa vacio

        if(!codigoString.isEmpty() && !descripcionString.isEmpty() && !precioString.isEmpty()){
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
    //====================== Fin btn registrar===================================================================================
    //======================btn Consultar===================================================================================
    public void buscar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
        //Se abre la base de datos en modo lectura y escritura
        SQLiteDatabase baseDeDatos = admin.getWritableDatabase();
        String codigoStringo= codigo.getText().toString();
        //empty significa vacio

        if(!codigoStringo.isEmpty() ){
            String sqlSelect = "Select descripcion,precio from articulos where codigo = " + codigoStringo;
            Cursor fila = baseDeDatos.rawQuery(sqlSelect,null);
            //moveToFirst -> Revisa si la consulta contiene valores
            if(fila.moveToFirst()){
                descripcion.setText(fila.getString(0));
                precio.setText(fila.getString(1));
                baseDeDatos.close();
            }else{
                Toast.makeText(this,"No exite el articulo",Toast.LENGTH_SHORT).show();
                baseDeDatos.close();
            }
        }else{
            Toast.makeText(this,"Debes introducir el codigo del articulo",Toast.LENGTH_SHORT).show();
        }
    }

    //======================Fin btn Consultar===================================================================================
//====================== btn Eliminar===================================================================================
public void Eliminar(View view){
    AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
    //Se abre la base de datos en modo lectura y escritura
    SQLiteDatabase baseDeDatos = admin.getWritableDatabase();
    String codigoString= codigo.getText().toString();
    if(!codigoString.isEmpty()){
        int cantidad = baseDeDatos.delete("articulos","codigo=" + codigoString,null);
        baseDeDatos.close();
        codigo.setText("");
        descripcion.setText("");
        precio.setText("");
       if (cantidad ==1){
           Toast.makeText(this,"Articulo eliminado exitosamente",Toast.LENGTH_SHORT).show();
       }else{
           Toast.makeText(this,"El articulo no existe",Toast.LENGTH_SHORT).show();
       }
    }else{
        Toast.makeText(this,"Debes introducir el codigo del articulo",Toast.LENGTH_SHORT).show();
        baseDeDatos.close();
    }
}
//======================Fin btn Eliminar===================================================================================
//====================== btn Modificar===================================================================================
public void modificar (View view){
    AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"administracion",null,1);
    //Se abre la base de datos en modo lectura y escritura
    SQLiteDatabase baseDeDatos = admin.getWritableDatabase();

    String codigoString,descripcionString,precioString;
    codigoString = codigo.getText().toString();
    descripcionString = descripcion.getText().toString();
    precioString = precio.getText().toString();

    //empty significa vacio
    if(!codigoString.isEmpty() && !descripcionString.isEmpty() && !precioString.isEmpty()){
        ContentValues modificar = new ContentValues();
        modificar.put("codigo",codigoString);
        modificar.put("descripcion",descripcionString);
        modificar.put("precio",precioString);

        //Modificar los registrs en la base de datos
        int cantidad = baseDeDatos.update("articulos",modificar,"codigo=" + codigoString,null);
        baseDeDatos.close();
        if(cantidad == 1){
            Toast.makeText(this,"Articulo " + descripcionString + " modificado exitosamente",Toast.LENGTH_SHORT ).show();
        }else{
            Toast.makeText(this,"El articulo no existe",Toast.LENGTH_SHORT).show();
        }
        codigo.setText("");
        descripcion.setText("");
        precio.setText("");
        Toast.makeText(this,"Registro Exitoso del producto codigo: " + codigoString,Toast.LENGTH_SHORT ).show();
    }else{
        Toast.makeText(this,"Debes llenar todos los campos",Toast.LENGTH_SHORT).show();
    }

}
//======================Fin btn Modificar===================================================================================

}