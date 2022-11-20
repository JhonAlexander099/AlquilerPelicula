package com.example.alquilerpelicula;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class interfaceinicio extends AppCompatActivity {
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interfaceinicio);
        mAuth = FirebaseAuth.getInstance();
    }

    public void Registro(View view) {

        Intent intent = new Intent(this, registro.class);
        startActivity(intent);
    }

    public void login(View view) {
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser() !=null){
            startActivity(new Intent(this, paginaprincipal.class));
            finish();
        }
    }
}
