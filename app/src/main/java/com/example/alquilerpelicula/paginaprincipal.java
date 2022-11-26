package com.example.alquilerpelicula;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class paginaprincipal extends AppCompatActivity {

    RecyclerView miRecyclerView;
    RecyclerView.LayoutManager miLayoutManager;
    AdaptadorPelicula miAdaptador;
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    List<Pelicula> resultado;
    private Menu menu2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paginaprincipal);
        miRecyclerView = findViewById(R.id.miRecycler);

        miLayoutManager = new LinearLayoutManager(this);
        miRecyclerView.setLayoutManager(miLayoutManager);

        resultado = new ArrayList<>();
        db =  FirebaseFirestore.getInstance();

        db.collection("Pelicula")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot documentos: task.getResult()){
                                String id = documentos.getId();
                                String titulo = documentos.getData().get("titulo").toString();
                                String genero = documentos.getData().get("genero").toString();
                                String sinopsis = documentos.getData().get("sinopsis").toString();
                                String portada = documentos.getData().get("portada").toString();
                                resultado.add(new Pelicula(id,portada,titulo,genero,sinopsis));
                            }
                            miAdaptador = new AdaptadorPelicula(resultado);
                            miRecyclerView.setAdapter(miAdaptador);
                        }else{
                            Log.e("APP", "error",task.getException());
                        }
                    }
                });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.searchmenu, menu);
        getMenuInflater().inflate(R.menu.menu_genero,menu);

        getMenuInflater().inflate(R.menu.menu_main,menu);

        MenuItem searchItem2 = menu.findItem(R.id.search2);
        SearchView searchView2 = (SearchView) searchItem2.getActionView();
        searchView2.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView2.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                miAdaptador.getFilter2().filter(newText);
                return false;
            }
        });

        ///////////////////
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                miAdaptador.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    public void Cerrarsesion(MenuItem item) {
        mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        startActivity( new Intent(this, interfaceinicio.class));
        finish();

    }

}