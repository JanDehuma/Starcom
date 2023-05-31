package com.dantefx.starcom;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TareasProgressAdapter extends RecyclerView.Adapter<TareasProgressAdapter.ViewHolder> {

    private Cursor cursor;
    private Context context;

    public TareasProgressAdapter(Cursor cursor) {
        this.cursor = cursor;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNombre;
        public ProgressBar progreso;
        public Spinner etapa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.rowFirstText);
            progreso = itemView.findViewById(R.id.activeProgress);
            etapa = itemView.findViewById(R.id.spinnerEtapa);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_first, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (cursor != null && cursor.moveToPosition(position)) {
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));

            holder.tvNombre.setText(nombre);

            String fechaInicio = cursor.getString(cursor.getColumnIndexOrThrow("fechaInicio"));
            String fechaFin = cursor.getString(cursor.getColumnIndexOrThrow("fechaFin"));
            long tiempoTranscurrido = calcularTiempoTranscurridoEnDias(fechaInicio, fechaFin);

            String mensaje = "Felicidades la tarea '" + nombre + "' tomó " + tiempoTranscurrido + " días.";
            Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();

        }
    }

    private long calcularTiempoTranscurridoEnDias(String fechaInicio, String fechaFin) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            Date fechaInicioObj = dateFormat.parse(fechaInicio);
            Date fechaFinObj = dateFormat.parse(fechaFin);
            long diferencia = fechaFinObj.getTime() - fechaInicioObj.getTime();
            long diasTranscurridos = TimeUnit.MILLISECONDS.toDays(diferencia);
            return diasTranscurridos;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public int getItemCount() {
        return cursor != null ? cursor.getCount() : 0;
    }
}
