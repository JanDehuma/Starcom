package com.dantefx.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBTareas extends SQLiteOpenHelper {
    private static final String TAREAS_TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS tareas(ID INTEGER PRIMARY KEY," +
            "nombre TEXT NOT NULL," +
            "descripcion TEXT NOT NULL," +
            "estado BOOLEAN(1) NOT NULL," +
            "fechaEntrega TEXT NOT NULL," +
            "prioridad TEXT NOT NULL," +
            "usuario TEXT)";
    private static final String DB_NAME = "tareasdb.sqlite";
    private static final int DB_VERSION = 1;
    public DBTareas(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TAREAS_TABLE_CREATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}