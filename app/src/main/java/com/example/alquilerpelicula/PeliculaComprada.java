package com.example.alquilerpelicula;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PeliculaComprada extends AppCompatActivity {
    TextView tvsinopsis02, tvtitulo02, tvgenero02;
    ImageView imgportada02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.peliculacomprada);


        tvtitulo02 = findViewById(R.id.tvtitulo02);
        tvsinopsis02 = findViewById(R.id.tvsinopsis02);
        tvgenero02 = findViewById(R.id.tvgenero02);
        imgportada02 = findViewById(R.id.imgportada02);
    }
}
