package com.example.alquilerpelicula;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ViendoPelicula extends AppCompatActivity {
    TextView tvviendo;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viendo_pelicula);
        String id =  getIntent().getExtras().getString("id");
        db =  FirebaseFirestore.getInstance();
        tvviendo = findViewById(R.id.tvviendo);

        db.collection("Pelicula").document(id)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()){
                            String Titulo;

                            Titulo = documentSnapshot.getString("titulo");
                            tvviendo.setText(Titulo);
                        }
                    }
                });
    }
}
