package com.dantefx.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.dantefx.starcom.AgregarTareaFragment;

public class DBTareas extends DBHelper {
    AgregarTareaFragment context;

    public DBTareas(@Nullable AgregarTareaFragment context) {
        super(context);
        this.context = context;
    }

    public long insertarTarea(String nombre, String descripcion, int estado , String prioridad, String fechaEntrega){
        long id = 0;
        try {
            DBHelper dbHelper = new DBHelper(context);
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
}