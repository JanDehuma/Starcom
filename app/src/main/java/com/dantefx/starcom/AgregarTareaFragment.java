package com.dantefx.starcom;

import android.app.DatePickerDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

import android.widget.TextView;

import com.dantefx.db.DBHandler;
import com.dantefx.db.DBTareas;
import com.google.android.material.textfield.TextInputLayout;


public class AgregarTareaFragment extends Fragment {

    private ImageButton pickDateBtn;
    private TextView selectedDateTV;
    private ImageButton guardar;
    private TextInputLayout campoNombre;
    private TextInputLayout campoDescripcion;
    private Spinner spinner;
    int estado = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_agregar_tarea, container, false);
        return inflater.inflate(R.layout.fragment_agregar_tarea, container, false);
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DBHandler dbHelper = new DBHandler(this.getContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        pickDateBtn = view.findViewById(R.id.idBtnPickDate);
        selectedDateTV = view.findViewById(R.id.idTVSelectedDate);
        guardar = view.findViewById(R.id.idBtnAgregar);
        campoNombre = view.findViewById(R.id.campoTareaLayout);
        campoDescripcion = view.findViewById(R.id.campoDescripcionLayout);
        spinner = view.findViewById(R.id.idSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.prioridades_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        pickDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting
                // the instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        AgregarTareaFragment.this.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                selectedDateTV.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        },
                        year, month, day);
                datePickerDialog.show();
            }
        });
        guardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                DBTareas bdTareas = new DBTareas(AgregarTareaFragment.this);
                long id = bdTareas.insertarTarea(campoNombre.getEditText().getText().toString(), campoDescripcion.getEditText().getText().toString(),
                        estado, spinner.getSelectedItem().toString(), selectedDateTV.getText().toString());

                if(id>0){
                    Toast.makeText(getContext(), "REGISTRO GUARDADO", Toast.LENGTH_SHORT).show();
                    limpiar();
                }else {
                    Toast.makeText(getContext(), "ERROR AL GUARDAR EL REGISTRO", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    private void limpiar() {
        campoNombre.getEditText().setText("");
        campoDescripcion.getEditText().setText("");
        spinner.setSelection(0);
        selectedDateTV.setText("");
    }


}