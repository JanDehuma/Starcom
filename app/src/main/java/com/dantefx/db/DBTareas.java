package com.dantefx.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.NonNull;
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

    public long insertarTarea(@NonNull String nombre, @NonNull String descripcion, @NonNull int estado , @NonNull String prioridad, @NonNull String fechaEntrega){
        long id = 0;
        try {
            DBHelper dbHelper = new DBHelper(context.getApplicationContext());
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            if(nombre != null && descripcion !=  null && prioridad != null && fechaEntrega != null) {
                values.put("nombre", nombre);
                values.put("descripcion", descripcion);
                values.put("estado", estado);
                values.put("prioridad", prioridad);
                values.put("fechaEntrega", fechaEntrega);
                id = db.insert(TABLE_TAREA, null, values);
            }
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

    public Cursor obtenerNombreTarea(){
        SQLiteDatabase db = getReadableDatabase();
        String[] columnas = {"id as _id", "nombre"};
        Cursor cursor = db.query(TABLE_TAREA, columnas, null, null, null, null, null);
        return cursor;
    }

    public Cursor obtenerTarea(long id){
        SQLiteDatabase db = getReadableDatabase();
        String[] columnas = {"id as _id","nombre", "descripcion", "estado", "prioridad", "fechaEntrega"};
        Cursor cursor = db.query(TABLE_TAREA, columnas, null, null, null, null, null);
        return cursor;
    }
    public void borrarTarea(int id){
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = "id=?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        db.delete(TABLE_TAREA, whereClause, whereArgs);
    }

    public void borrarTodasLasTareas() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_TAREA, null, null);
    }


}