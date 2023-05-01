package com.dantefx.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "starcom.db";
    public static final String TABLE_USUARIO = "USUARIO";
    public static final String TABLE_TAREA = "TAREA";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_USUARIO + "(" +
                "nombre TEXT NOT NULL)");

        db.execSQL("CREATE TABLE " + TABLE_TAREA + "(" +
                "id INT PRIMARY KEY AUTOINCREMENT ," +
                "nombre TEXT NOT NULL," +
                "descripcion TEXT NOT NULL," +
                "estado BOOLEAN(1) NOT NULL," +
                "fechaEntrega TEXT NOT NULL," +
                "prioridad TEXT NOT NULL," +
                "usuario TEXT," +
                "FOREIGN KEY (usuario) REFERENCES TABLE_USUARIO(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_USUARIO );
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_TAREA );
        onCreate(sqLiteDatabase);
    }





    public Cursor obtenerTodasLasTareas() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columnas = {
                "id",
                "nombre",
                "descripcion",
                "estado",
                "fechaEntrega"
        };
        Cursor cursor = db.query(TABLE_TAREA, columnas, null, null, null, null, null);
        return cursor;
    }


}
