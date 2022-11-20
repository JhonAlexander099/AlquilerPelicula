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

public class mostrarpelicula extends AppCompatActivity {
    TextView tvsinopsis01,tvprecio01,tvtitulo01, tvgenero01;
    ImageView imgportada01;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrarpelicula);

        tvtitulo01 = findViewById(R.id.tvtitulo02);
        tvsinopsis01 = findViewById(R.id.tvsinopsis02);
        tvprecio01 = findViewById(R.id.tvprecio01);
        tvgenero01 = findViewById(R.id.tvgenero02);
        imgportada01 = findViewById(R.id.imgportada02);


        String id =  getIntent().getExtras().getString("id");
        db =  FirebaseFirestore.getInstance();

        db.collection("Pelicula").document(id)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()){
                            String titulo,genero,sinopsis,precio,portada = null;

                            titulo = documentSnapshot.getString("titulo");
                            genero = documentSnapshot.getString("genero");
                            sinopsis = documentSnapshot.getString("sinopsis");
                            precio = documentSnapshot.getString("precio");
                            portada = documentSnapshot.getString("portada");


                            tvtitulo01.setText(titulo);
                            tvsinopsis01.setText(sinopsis);
                            tvprecio01.setText(precio);
                            tvgenero01.setText(genero);
                            Glide.with(imgportada01.getContext()).load(portada).into(imgportada01);
                        }
                    }
                });

/*
        db.collection("Pelicula").document("id")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String id = documentSnapshot.getId();
                        //String titulo = documentSnapshot.getString("titulo");
                        Toast.makeText(getApplicationContext(), "Titulo "+id, Toast.LENGTH_SHORT).show();
                    }
                });*/
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_regresar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.regresar:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void IraComprar(View view) {
        Intent intent = new Intent(this, ComprarPelicula.class);
        String id =  getIntent().getExtras().getString("id");
        intent.putExtra("id",id);
        String idcliente =  getIntent().getExtras().getString("idcliente");
        intent.putExtra("idcliente",idcliente);
        startActivity(intent);
        finish();
    }

    public void VerTrailer(View view) {
        Intent intent = new Intent(this, VerTrailer.class);
        String id =  getIntent().getExtras().getString("id");
        intent.putExtra("id",id);
        startActivity(intent);
    }
}
