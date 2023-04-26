package com.dantefx.starcom;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import com.dantefx.db.DBHandler;
import com.dantefx.db.DBHelper;
import com.dantefx.db.DBTareas;
import com.dantefx.starcom.databinding.FragmentTareasBinding;
public class TareasFragment extends Fragment {

    private FragmentTareasBinding binding;
    ListView tareas;
    ArrayList<String> tareasList = new ArrayList<String>();
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        DBHandler dbHelper = new DBHandler(this.getContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        binding = FragmentTareasBinding.inflate(inflater, container, false);
        tareas=binding.listTareasFirst;

        if (db != null) {
            Cursor resultSet = db.rawQuery("Select * from tareas",null);
            resultSet.moveToFirst();
            for (int i = 0; i < resultSet.getCount(); i++) {
                tareasList.add(resultSet.getString(1));
            }
        }

        ArrayAdapter adapter = new ArrayAdapter(this.getContext(), R.layout.row_first,tareasList);
        tareas.setAdapter(adapter);
        TextView dateView = binding.fecha;
        setDate(dateView);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonVerTareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(TareasFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
        binding.buttonAgregarTareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(TareasFragment.this)
                        .navigate(R.id.action_FirstFragment_to_agregarTareaFragment);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void setDate (TextView view){
        String str = String.format("%tc", new Date());
        view.setText(str.substring(0,10));
    }


}