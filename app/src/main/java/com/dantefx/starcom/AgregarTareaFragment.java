package com.dantefx.starcom;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;
import android.widget.TextView;

import com.dantefx.db.DBHandler;
import com.dantefx.db.DBHelper;
import com.dantefx.db.DBTareas;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class AgregarTareaFragment extends Fragment {

    private ImageButton pickDateBtn;
    private TextView selectedDateTV;
    private ImageButton guardar;
    private TextInputLayout campoNombre;
    private TextInputLayout campoDescripcion;
    private Spinner spinner;

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

                if (db != null) {
                    Snackbar.make(view, "Guardando", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    dbHelper.agregarTarea(String.valueOf(campoNombre.getEditText().getText()),String.valueOf(campoDescripcion.getEditText().getText()),
                            false,String.valueOf(selectedDateTV.getText()),spinner.getSelectedItem().toString());
                    // Hacer las operaciones que queramos sobre la base de datos
               /*     ContentValues cv = new ContentValues();
                    cv.put("nombre", String.valueOf(campoNombre.getEditText().getText()));
                    cv.put("descripcion", String.valueOf(campoDescripcion.getEditText().getText()));
                    cv.put("estado", 0);
                    cv.put("fechaEntrega", String.valueOf(selectedDateTV.getText()));
                    cv.put("prioridad", spinner.getSelectedItem().toString());
                    cv.put("usuario", "Dante");
                    db.insert("tareas", null, cv);*/
                }
            }
        });

    }
}