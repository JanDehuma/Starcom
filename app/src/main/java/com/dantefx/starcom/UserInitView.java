package com.dantefx.starcom;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.dantefx.starcom.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

public class UserInitView extends AppCompatActivity {
    private EditText name;
    private ImageButton guardar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_init_view);
        name = findViewById(R.id.nameEdit);
        guardar = findViewById(R.id.buttonGuardarNombre);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserInitView.this, MainActivity.class);
                startActivity(intent);
                // Creating a shared pref object with a file name "MySharedPref" in private mode
                SharedPreferences sharedPreferences = getSharedPreferences("sleepyTaskSharedPreferences", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                // write all the data entered by the user in SharedPreference and apply
                myEdit.putString("name", name.getText().toString());
                myEdit.apply();
                Snackbar.make(view, "Bienvenido", Snackbar.LENGTH_LONG)
                        .setAction(name.getText(), null).show();
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        // Fetching the stored data from the SharedPreference
        SharedPreferences sh = getSharedPreferences("sleepyTaskSharedPreferences", MODE_PRIVATE);
        String s1 = sh.getString("name", "");

        // Setting the fetched data in the EditTexts
        //name.setText(s1);
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Creating a shared pref object with a file name "MySharedPref" in private mode
        SharedPreferences sharedPreferences = getSharedPreferences("sleepyTaskSharedPreferences", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        // write all the data entered by the user in SharedPreference and apply
        myEdit.putString("name", name.getText().toString());
        myEdit.apply();
    }

}