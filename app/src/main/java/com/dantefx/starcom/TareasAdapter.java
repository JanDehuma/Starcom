package com.dantefx.starcom;

import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dantefx.db.DBTareas;

public class TareasAdapter extends RecyclerView.Adapter<TareasAdapter.ViewHolder> {
    private Cursor mCursor;

    public TareasAdapter(Cursor cursor) {
        mCursor = cursor;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNombre;
        public TextView tvDescripcion;
        public CheckBox tvEstado;
        public TextView tvPrioridad;
        public TextView tvFechaEntrega;
        public ImageView buttonEditar;
        public ImageView buttonEliminar;

        public ViewHolder(View itemView) {
            super(itemView);

            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvEstado = itemView.findViewById(R.id.tvEstado);
            tvPrioridad = itemView.findViewById(R.id.tvPrioridad);
            tvFechaEntrega = itemView.findViewById(R.id.tvFechaEntrega);
            buttonEliminar = itemView.findViewById(R.id.ivEliminar);
            buttonEditar = itemView.findViewById(R.id.ivEditar);

            tvEstado.setText("");
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_ver_tareas, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull TareasAdapter.ViewHolder holder, int position) {
        mCursor.moveToPosition(position);

        ImageView ivEliminar = holder.itemView.findViewById(R.id.ivEliminar);
        ImageView ivEditar = holder.itemView.findViewById(R.id.ivEditar);

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

        ivEliminar.setImageResource(android.R.drawable.ic_menu_delete);
        ivEditar.setImageResource(R.drawable.drawing48);



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
