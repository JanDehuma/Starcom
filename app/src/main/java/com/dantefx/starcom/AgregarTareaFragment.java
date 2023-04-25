package com.dantefx.starcom;

import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AgregarTareaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgregarTareaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AgregarTareaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AgregarTareaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AgregarTareaFragment newInstance(String param1, String param2) {


        AgregarTareaFragment fragment = new AgregarTareaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }


    Calendar calendar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_agregar_tarea, container, false);

        Button btnAgregar = view.findViewById(R.id.idBtnAgregar);
        Button btnSeleccionarFecha = view.findViewById(R.id.btnFecha);
        EditText editTextTitulo = view.findViewById(R.id.campoTarea);
        EditText editTextDescripcion = view.findViewById(R.id.campoDescripcion);
        Spinner spinnerPriori = view.findViewById(R.id.idSpinner);
        EditText ediTextFecha = view.findViewById(R.id.btnFecha);
        calendar = Calendar.getInstance();

        String[] prioridades = {
                "Bajo",
                "Medio",
                "Alto"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, prioridades);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPriori.setAdapter(adapter);

        btnSeleccionarFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                // Actualizar la fecha en el botón
                                calendar.set(year, monthOfYear, dayOfMonth);
                                String fechaSeleccionada = DateFormat.getDateInstance().format(calendar.getTime());
                                btnSeleccionarFecha.setText(fechaSeleccionada);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = editTextTitulo.getText().toString();
                if (titulo.isEmpty()) {
                    Toast.makeText(getContext(), "Debe ingresar un título para la tarea", Toast.LENGTH_SHORT).show();
                    return;
                }
                String descripcion = editTextDescripcion.getText().toString();
                String prioridad = spinnerPriori.getSelectedItem().toString();
                String fecha = ediTextFecha.getText().toString();

                // Aquí puede agregar la tarea a la base de datos o hacer cualquier otra cosa necesaria

                // Mostrar un mensaje de éxito
                Toast.makeText(getContext(), "La tarea se agregó correctamente", Toast.LENGTH_SHORT).show();

                // Limpiar los campos
                editTextTitulo.setText("");
                editTextDescripcion.setText("");
                spinnerPriori.setSelection(0);
                ediTextFecha.setText("");
            }
        });


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_agregar_tarea, container, false);
    }


}