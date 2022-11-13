package com.example.alquilerpelicula;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class registroTarjeta extends AppCompatActivity {
    EditText etNrnTarjeta, etFechaVencimiento, etCvv;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrotarjeta);

        db = FirebaseFirestore.getInstance();

        etNrnTarjeta = findViewById(R.id.etNrnTarjeta);
        etFechaVencimiento = findViewById(R.id.etFechaVencimiento);
        etCvv = findViewById(R.id.etCvv);

    }

    public void Registrarse(View view) {
        Intent intent = new Intent(this, paginaprincipal.class);

        String nrnTarjeta = etNrnTarjeta.getText().toString();
        String fechavencimiento = etFechaVencimiento.getText().toString();
        String cvv = etCvv.getText().toString();
        String id = getIntent().getExtras().getString("id");

        if(!nrnTarjeta.isEmpty() && !fechavencimiento.isEmpty() && !cvv.isEmpty()){
            if (nrnTarjeta.length() == 16){
                if(cvv.length() == 3){

                    Map<String,Object > TarjetadeCredito = new HashMap<>();
                    TarjetadeCredito.put("numero", nrnTarjeta);
                    TarjetadeCredito.put("fechacaducacion", fechavencimiento);
                    TarjetadeCredito.put("cvc", cvv);
                    TarjetadeCredito.put("id_Cliente", id);

                    db.collection("TarjetadeCredito")
                            .add(TarjetadeCredito)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.e("FireApp","Error",e);
                                }
                            });
                }else{
                    Toast.makeText(this, "CVV no valido", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "El numero de tarjeta no es valido", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Debe completar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}
