package com.dantefx.starcom;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dantefx.db.DBTareas;
import com.dantefx.starcom.databinding.FragmentVerTareasBinding;

public class VerTareasFragment extends Fragment {

    private FragmentVerTareasBinding binding;
    private DBTareas dbTareas;
    private RecyclerView recyclerView;
    private TareasAdapter adaptador;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentVerTareasBinding.inflate(inflater, container, false);
        dbTareas = new DBTareas(getContext());
        Cursor cursor = dbTareas.obtenerTareas();


        recyclerView = binding.rvTareas;
        adaptador = new TareasAdapter(cursor);
        recyclerView.setAdapter(adaptador);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(VerTareasFragment.this)
                        .navigate(R.id.action_SecondFragment_to_agregarTareaFragment);
            }
        });
    }

    private void actualizarTabla() {
        Cursor nuevoCursor = dbTareas.obtenerTareas();
        adaptador.swapCursor(nuevoCursor);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}



