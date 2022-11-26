package com.example.alquilerpelicula;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class PeliculaComprada extends AppCompatActivity {
    TextView tvsinopsis02,tvtitulo02, tvgenero02;
    ImageView imgportada02;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.peliculacomprada);

        tvtitulo02 = findViewById(R.id.tvtitulo02);
        tvsinopsis02 = findViewById(R.id.tvsinopsis02);
        tvgenero02 = findViewById(R.id.tvgenero02);
        imgportada02 = findViewById(R.id.imgportada02);


        String id =  getIntent().getExtras().getString("id");
        db =  FirebaseFirestore.getInstance();

        db.collection("Pelicula").document(id)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()){
                            String titulo,genero,sinopsis,portada = null;

                            titulo = documentSnapshot.getString("titulo");
                            genero = documentSnapshot.getString("genero");
                            sinopsis = documentSnapshot.getString("sinopsis");
                            portada = documentSnapshot.getString("portada");

                            tvtitulo02.setText(titulo);
                            tvsinopsis02.setText(sinopsis);
                            tvgenero02.setText(genero);
                            Glide.with(imgportada02.getContext()).load(portada).into(imgportada02);
                        }
                    }
                });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_regresar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.regresar:
                Intent intent = new Intent(this, interfaceinicio.class);
                startActivity(intent);
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void VerlaPelicula(View view) {
        Intent intent = new Intent(this, ViendoPelicula.class);
        String id =  getIntent().getExtras().getString("id");
        intent.putExtra("id",id);
        startActivity(intent);
    }

    public void VerelTrailer(View view) {
        Intent intent = new Intent(this, VerTrailer.class);
        String id =  getIntent().getExtras().getString("id");
        intent.putExtra("id",id);
        startActivity(intent);
    }
}
