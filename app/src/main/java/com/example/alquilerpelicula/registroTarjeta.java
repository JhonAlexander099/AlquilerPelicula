package com.example.alquilerpelicula;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class registroTarjeta extends AppCompatActivity {
    EditText etNrnTarjeta, etFechaVencimiento, etCvv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrotarjeta);

        etNrnTarjeta = findViewById(R.id.etNrnTarjeta);
        etFechaVencimiento = findViewById(R.id.etFechaVencimiento);
        etCvv = findViewById(R.id.etCvv);

    }
}
