package com.example.alquilerpelicula;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ComprarPelicula extends AppCompatActivity {
    TextView tvcostocompra,tvtitulocompra,tvnumerocompra,tvfechacompra,tvcvvcompra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comprarpelicula);

        String id = getIntent().getExtras().getString("id");


        tvcostocompra = findViewById(R.id.tvcostocompra);
        tvtitulocompra = findViewById(R.id.tvtitulocompra);
        tvnumerocompra = findViewById(R.id.tvnumerocompra);
        tvfechacompra = findViewById(R.id.tvfechacompra);
        tvcvvcompra = findViewById(R.id.tvcvvcompra);
    }
}


