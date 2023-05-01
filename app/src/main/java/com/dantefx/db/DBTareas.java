package com.dantefx.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dantefx.starcom.AgregarTareaFragment;
import com.dantefx.starcom.VerTareasFragment;

public class DBTareas extends DBHelper {
    Context context;

    public DBTareas(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarTarea(String nombre, String descripcion, int estado , String prioridad, String fechaEntrega){
        long id = 0;
        try {
            DBHelper dbHelper = new DBHelper(context.getApplicationContext());
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("descripcion", descripcion);
            values.put("estado", estado);
            values.put("prioridad", prioridad);
            values.put("fechaEntrega", fechaEntrega);

             id = db.insert(TABLE_TAREA, null, values);
        }catch (Exception ex){
            ex.toString();
        }
        return id;
    }

    public Cursor obtenerTareas() {
        SQLiteDatabase db = getReadableDatabase();
        String[] columnas = {"id as _id","nombre", "descripcion", "estado", "prioridad", "fechaEntrega"};
        Cursor cursor = db.query(TABLE_TAREA, columnas, null, null, null, null, null);
        return cursor;
    }

}