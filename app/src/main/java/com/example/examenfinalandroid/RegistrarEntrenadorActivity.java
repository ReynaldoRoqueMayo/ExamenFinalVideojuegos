package com.example.examenfinalandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegistrarEntrenadorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_entrenador);


        Button btnRegistrar= findViewById(R.id.btnRegistrarEntrenador);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(RegistrarEntrenadorActivity.this, FormRegistroEntrenadorActivity.class);
                startActivity(intento);
            }
        });




    }
}