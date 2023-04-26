package com.dantefx.starcom;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.dantefx.starcom.databinding.FragmentVerTareasBinding;

public class VerTareasFragment extends Fragment {

    private FragmentVerTareasBinding binding;
    private com.dantefx.starcom.db.DBHelper dbHelper;
    private SQLiteDatabase db;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentVerTareasBinding.inflate(inflater, container, false);

        return binding.getRoot();

    }



    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TableLayout tableLayout = binding.tableLayout;
        SQLiteDatabase db = new com.dantefx.starcom.db.DBHelper(getContext()).getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT nombre, descripcion, estado,prioridad, fechaEntrega FROM " + com.dantefx.starcom.db.DBHelper.TABLE_TAREA, null);

        while (cursor.moveToNext()) {
            TableRow row = new TableRow(getContext());

            TextView nombreView = new TextView(getContext());
            nombreView.setText(cursor.getString(0));
            row.addView(nombreView);

            TextView descripcionView = new TextView(getContext());
            descripcionView.setText(cursor.getString(1));
            row.addView(descripcionView);

            TextView estadoView = new TextView(getContext());
            estadoView.setText(cursor.getInt(2) == 1 ? "Completado" : "Pendiente");
            row.addView(estadoView);

            TextView fechaView = new TextView(getContext());
            fechaView.setText(cursor.getString(3));
            row.addView(fechaView);

            TextView editarView = new TextView(getContext());
            editarView.setText("Editar");
            row.addView(editarView);
            TextView eliminarView = new TextView(getContext());
            eliminarView.setText("Eliminar");
            row.addView(eliminarView);

            tableLayout.addView(row);
        }

        cursor.close();
        db.close();

        binding.buttonAgregarTareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(VerTareasFragment.this)
                        .navigate(R.id.action_SecondFragment_to_agregarTareaFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}

