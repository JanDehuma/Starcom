package com.dantefx.starcom;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.icu.text.Transliterator;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dantefx.db.DBTareas;

public class TareasAdapter extends RecyclerView.Adapter<TareasAdapter.ViewHolder> {
    private Cursor mCursor;

    public TareasAdapter(Cursor cursor) {
        mCursor = cursor;
    }
    private Context context;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNombre;
        public TextView tvDescripcion;
        public CheckBox tvEstado;
        public TextView tvPrioridad;
        public TextView tvFechaEntrega;
        public ImageButton buttonEditar;
        public ImageButton buttonEliminar;


        public ViewHolder(View itemView) {
            super(itemView);

            tvNombre = itemView.findViewById(R.id.texViewNombreTarea);
            tvDescripcion = itemView.findViewById(R.id.texViewDescripcionTarea);
            tvEstado = itemView.findViewById(R.id.checkboxTarea);
            tvPrioridad = itemView.findViewById(R.id.textViewPrioridad);
            tvFechaEntrega = itemView.findViewById(R.id.textViewFechaEntrega);
            buttonEliminar = itemView.findViewById(R.id.imageButtonEliminar);
            buttonEditar = itemView.findViewById(R.id.imageButtonEditar);

            tvEstado.setText("");
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_add, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull TareasAdapter.ViewHolder holder, int position) {
        mCursor.moveToPosition(position);
        String nombre = mCursor.getString(mCursor.getColumnIndexOrThrow("nombre"));
        String descripcion = mCursor.getString(mCursor.getColumnIndexOrThrow("descripcion"));
        int estado = mCursor.getInt(mCursor.getColumnIndexOrThrow("estado"));
        String prioridad = mCursor.getString(mCursor.getColumnIndexOrThrow("prioridad"));
        String fechaEntrega = mCursor.getString(mCursor.getColumnIndexOrThrow("fechaEntrega"));

        holder.tvNombre.setText(nombre);
        holder.tvDescripcion.setText(descripcion);

        holder.tvEstado.setChecked(false);
        holder.tvPrioridad.setText(prioridad);
        holder.tvFechaEntrega.setText(fechaEntrega);
        holder.buttonEliminar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DBTareas bdTareas = new DBTareas(context);
                bdTareas.borrarTarea(holder.getAdapterPosition());
                Toast.makeText(context, "REGISTRO BORRADO "+holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }


    public void swapCursor(Cursor newCursor) {
        if (mCursor != null) {
            mCursor.close();
        }
        mCursor = newCursor;
        if (mCursor != null) {
            notifyDataSetChanged();
        }
    }

}
