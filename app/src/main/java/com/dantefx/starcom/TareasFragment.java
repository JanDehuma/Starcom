package com.dantefx.starcom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import android.widget.TextView;
import java.util.Date;
import com.dantefx.starcom.databinding.FragmentTareasBinding;
public class TareasFragment extends Fragment {

    private FragmentTareasBinding binding;
    ListView tareas;
    String num[] = {"Tarea 1","Tarea 2","Tarea 3","Tarea 4", "Tarea 5"};
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentTareasBinding.inflate(inflater, container, false);
        tareas=binding.listTareasFirst;

        ArrayAdapter adapter = new ArrayAdapter(this.getContext(), R.layout.row_first,num);
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