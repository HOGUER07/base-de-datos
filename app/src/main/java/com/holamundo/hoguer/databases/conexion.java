package com.holamundo.hoguer.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



/**
 * Created by hoguer on 12/05/2015.
 */
public class conexion extends SQLiteOpenHelper{


    public conexion (Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table peliculas (numero_pelicula integer,nombre text, categoria text , duracion text , idioma text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("drop table if exists peliculas");
        db.execSQL("create table peliculas (numero_pelicula integer,nombre text, categoria text , duracion text , idioma text)");
    }
}
