package com.dantefx.starcom;

import android.app.DatePickerDialog;
import android.content.Context;
import android.database.Cursor;
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
import com.dantefx.starcom.R;
import com.dantefx.starcom.TareasAdapter;
import com.google.android.material.textfield.TextInputLayout;


public class EditarTareaFragment extends Fragment {

    private ImageButton pickDateBtn;
    private TextView selectedDateTV;
    private ImageButton guardar;
    private TextInputLayout campoNombre;
    private TextInputLayout campoDescripcion;
    private Spinner spinner;
    int estado = 1;


    private int position1;
    private static final String ARG = "POS";

    private Bundle bundle = new Bundle();

    private TareasAdapter tareasAdapter;

    public EditarTareaFragment(){

    }

    public static EditarTareaFragment newInstance (Integer position1){
        EditarTareaFragment fragment = new EditarTareaFragment();
        Bundle args = new Bundle();

        args.putInt(ARG, position1);
        fragment.setArguments(args);
        return fragment;
    }
    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        if(getArguments() != null){
            position1 = getArguments().getInt(ARG);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editar_tarea, container, false);

        // Asignar el listener del botón guardar aquí.
        guardar = view.findViewById(R.id.idBtnActualizar);
        guardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String nombre = campoNombre.getEditText().getText().toString();
                String descripcion = campoDescripcion.getEditText().getText().toString();
                String prioridad = spinner.getSelectedItem().toString();
                String fechaEntrega = selectedDateTV.getText().toString();





                // Obtener el ID del registro que se va a actualizar

                DBTareas bdTareas = new DBTareas(getContext());
                System.out.println("AAAAAAAAAGGGGGGGGGGGGGGGGGGGGGGG" + bdTareas.actualizarTarea(position1,nombre,descripcion,
                        prioridad,fechaEntrega));

                boolean actualizacionExitosa = bdTareas.actualizarTarea(position1, nombre, descripcion, prioridad, fechaEntrega);

                try {
                    if (actualizacionExitosa) {
                        Toast.makeText(getContext(), "REGISTRO ACTUALIZADO", Toast.LENGTH_SHORT).show();
                        limpiar();

                        Cursor nuevoCursor = bdTareas.obtenerTareas();

                        // Actualizar el adaptador con el nuevo Cursor
                        tareasAdapter.swapCursor(nuevoCursor);
                    } else {
                        Toast.makeText(getContext(), "ERROR AL ACTUALIZAR EL REGISTRO", Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });





        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        //super.onViewCreated(view, savedInstanceState);
        DBHandler dbHelper = new DBHandler(this.getContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        pickDateBtn = view.findViewById(R.id.idBtnPickDate);
        selectedDateTV = view.findViewById(R.id.idTVSelectedDate);
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
                        com.dantefx.starcom.EditarTareaFragment.this.getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                selectedDateTV.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        },
                        year, month,day);

                datePickerDialog.show();
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