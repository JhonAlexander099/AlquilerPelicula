package com.example.alquilerpelicula;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class paginaprincipal extends AppCompatActivity {
    RecyclerView miRecyclerView;
    RecyclerView.LayoutManager miLayoutManager;
    List<Pelicula> resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paginaprincipal);
        miRecyclerView = findViewById(R.id.miRecycler);

        miLayoutManager = new LinearLayoutManager(this);
        miRecyclerView.setLayoutManager(miLayoutManager);

        resultado = new ArrayList<>();
    }
}