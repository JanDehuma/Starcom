package com.dantefx.starcom;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.dantefx.db.DBHandler;
import com.dantefx.starcom.databinding.FragmentVerTareasBinding;

import java.util.ArrayList;

public class VerTareasFragment extends Fragment {

    private FragmentVerTareasBinding binding;
    ListView tareas;
    ArrayList<String> tareasList = new ArrayList<String>();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        DBHandler dbHelper = new DBHandler(this.getContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        binding = FragmentVerTareasBinding.inflate(inflater, container, false);
        tareas=binding.listTareasEdit;
        if (db != null) {
            Cursor resultSet = db.rawQuery("Select * from tareas",null);
            resultSet.moveToFirst();
            for (int i = 0; i < resultSet.getCount(); i++) {
                tareasList.add(resultSet.getString(1));
            }
        }

        ArrayAdapter adapter = new ArrayAdapter(this.getContext(), R.layout.row_first,tareasList);
        tareas.setAdapter(adapter);

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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