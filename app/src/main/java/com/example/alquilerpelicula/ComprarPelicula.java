package com.example.alquilerpelicula;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class ComprarPelicula extends AppCompatActivity{
    TextView tvcostocompra,tvtitulocompra,tvnumerocompra,tvfechacompra,tvcvccompra;
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comprarpelicula);

        String id =  getIntent().getExtras().getString("id");
        db =  FirebaseFirestore.getInstance();


        tvcostocompra = findViewById(R.id.tvcostocompra);
        tvtitulocompra = findViewById(R.id.tvtitulocompra);
        tvnumerocompra = findViewById(R.id.tvnumerocompra);
        tvfechacompra = findViewById(R.id.tvfechacompra);
        tvcvccompra = findViewById(R.id.tvcvvcompra);

        mAuth = FirebaseAuth.getInstance();
        String idcliente = mAuth.getCurrentUser().getUid();

        db.collection("Pelicula").document(id)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()){
                            String titulo,precio = null;

                            titulo = documentSnapshot.getString("titulo");
                            precio = documentSnapshot.getString("precio");

                            tvtitulocompra.setText(titulo);
                            tvcostocompra.setText(precio);
                        }
                    }
                });

        db.collection("TarjetadeCredito").whereEqualTo("id_Cliente",idcliente)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot documentos:task.getResult()){
                            String numero = documentos.getData().get("numero").toString();
                            String fechacaducacion = documentos.getData().get("fechacaducacion").toString();
                            String cvc  = documentos.getData().get("cvc").toString();

                            tvnumerocompra.setText(numero);
                            tvfechacompra.setText(fechacaducacion);
                            tvcvccompra.setText(cvc);
                        }
                    }
                });

    }

    public void ComparPelicula(View view) {

        String idclienteg = getIntent().getExtras().getString("id");
        String idpeliculag = mAuth.getCurrentUser().getUid();

        Map<String, Object> Alquilar = new HashMap<>();
        Alquilar.put("id_cliente", idclienteg);
        Alquilar.put("id_pelicula", idpeliculag);


        db.collection("Alquilar")
                .add(Alquilar)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), "Pelicula Comprada ", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), PeliculaComprada.class);
                        String id =  getIntent().getExtras().getString("id");
                        intent.putExtra("id",id);
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("FireApp", "Error", e);
                    }
                });
    }
}


