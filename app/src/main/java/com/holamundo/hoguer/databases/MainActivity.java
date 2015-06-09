package com.holamundo.hoguer.databases;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends ActionBarActivity {
    EditText numeropelicula1,nombre1, categoria1, duracion1,idioma1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numeropelicula1=(EditText) findViewById(R.id.numero_pelicula);
        nombre1 = (EditText) findViewById(R.id.nombre);
        categoria1 = (EditText) findViewById(R.id.categoria);
        duracion1 = (EditText) findViewById(R.id.duracion);
        idioma1 = (EditText) findViewById(R.id.idioma);

    }

    public void agregar (View v){

        try {
            conexion admin = new conexion(this, "peliculas", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();

            String numero_pelicula = numeropelicula1.getText().toString();
            String nombre = nombre1.getText().toString();
            String categoria = categoria1.getText().toString();
            String duracion = duracion1.getText().toString();
            String idioma = idioma1.getText().toString();

            ContentValues registro = new ContentValues();
            registro.put("numero_pelicula",numero_pelicula);
            registro.put("nombre", nombre);
            registro.put("categoria", categoria);
            registro.put("duracion", duracion);
            registro.put("idioma", (idioma));


            bd.insert("peliculas", null, registro);
            bd.close();

            numeropelicula1.setText("");
            nombre1.setText("");
            categoria1.setText("");
            duracion1.setText("");
            idioma1.setText("");


            Toast.makeText(this, "se agrego un nueva pelicula", Toast.LENGTH_SHORT).show();
        }
        catch(Exception e) {
            Toast.makeText(this, "Error" + e, Toast.LENGTH_SHORT).show();
        }
        }

    public void limpiar (View v) {
        numeropelicula1.setText("");
        nombre1.setText("");
        categoria1.setText("");
        duracion1.setText("");
        idioma1.setText("");

    }
    public void buscar(View v) {

        try {
            conexion admin = new conexion(this, "peliculas", null, 1);
            SQLiteDatabase bd = admin.getReadableDatabase();
            String numero_pelicula = numeropelicula1.getText().toString();
            Cursor fila = bd.rawQuery("select nombre,categoria,duracion,idioma from peliculas where numero_pelicula=" + numeropelicula1, null);
            if (fila.moveToFirst()) {
                numeropelicula1.setText(fila.getString(0));
                nombre1.setText(fila.getString(1));
                categoria1.setText(fila.getString(2));
                duracion1.setText(fila.getString(3));
                idioma1.setText(fila.getString(4));
            } else {
                Toast.makeText(this, "No existe la pelicula en la base de datos", Toast.LENGTH_SHORT).show();
            }
            bd.close();
        }catch(Exception e){
            Toast.makeText(this, "Error" + e , Toast.LENGTH_SHORT).show();
        }
    }

    public void eliminar (View v) {

        try {
            conexion admin = new conexion(this, "peliculas", null, 1);
            SQLiteDatabase bd = admin.getReadableDatabase();
            String nombre = nombre1.getText().toString();
            int cant = bd.delete("peliculas", "nombre=" + nombre1, null);
            bd.close();
            numeropelicula1.setText("");
            nombre1.setText("");
            categoria1.setText("");
            duracion1.setText("");
            idioma1.setText("");

            if (cant == 1) {
                Toast.makeText(this, "La pelicula se ha Eliminado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No existe la pelicula en la  base", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e) {
            Toast.makeText(this,"error"+e, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
