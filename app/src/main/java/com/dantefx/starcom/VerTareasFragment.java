package com.dantefx.starcom;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.dantefx.db.DBTareas;
import com.dantefx.starcom.databinding.FragmentVerTareasBinding;

public class VerTareasFragment extends Fragment {

    private FragmentVerTareasBinding binding;
    private DBTareas dbTareas;
    private SimpleCursorAdapter adaptador;

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

        dbTareas = new DBTareas(getContext());
        Cursor cursor = dbTareas.obtenerTareas();

        String[] desde = {"nombre", "descripcion", "estado", "prioridad", "fechaEntrega"};
        int[] a = {R.id.tvNombre, R.id.tvDescripcion, R.id.tvEstado, R.id.tvPrioridad, R.id.tvFechaEntrega};
        adaptador = new SimpleCursorAdapter(requireContext(), R.layout.fragment_ver_tareas, cursor, desde, a, 0);
        binding.lvTareas.setAdapter(adaptador);

        /*binding.buttonAgregarTareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(VerTareasFragment.this)
                        .navigate(R.id.action_SecondFragment_to_agregarTareaFragment);
            }
        });*/
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
