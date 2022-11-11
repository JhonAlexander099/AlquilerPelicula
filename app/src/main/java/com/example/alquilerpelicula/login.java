package com.example.alquilerpelicula;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {
    EditText etUsuario, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        etUsuario = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etpassword);

    }
}
