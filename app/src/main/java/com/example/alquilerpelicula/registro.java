package com.example.alquilerpelicula;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class registro extends AppCompatActivity {
    EditText etNombre, etApellidos, etCorreo, etContraseña, etVerificarContra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        etNombre = findViewById(R.id.etNombre);
        etApellidos = findViewById(R.id.etApellidos);
        etCorreo = findViewById(R.id.etCorreo);
        etContraseña = findViewById(R.id.etContraseña);
        etVerificarContra = findViewById(R.id.etVerificarContra);
    }
}
