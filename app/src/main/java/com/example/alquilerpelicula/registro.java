package com.example.alquilerpelicula;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class registro extends AppCompatActivity {
    EditText etNombre, etApellidos, etCorreo, etContraseña, etVerificarContra;
    FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        etNombre = findViewById(R.id.etNombre);
        etApellidos = findViewById(R.id.etApellidos);
        etCorreo = findViewById(R.id.etCorreo);
        etContraseña = findViewById(R.id.etContraseña);
        etVerificarContra = findViewById(R.id.etVerificarContra);
    }
    public void ContinuatTarjeta(View view) {

        Intent intent = new Intent(this, registroTarjeta.class);

        String nombres = etNombre.getText().toString();
        String apellidos = etApellidos.getText().toString();
        String correo = etCorreo.getText().toString();
        String password = etContraseña.getText().toString();
        String verificarcontraseña = etVerificarContra.getText().toString();

        if(!nombres.isEmpty() && !apellidos.isEmpty() && !correo.isEmpty() && !password.isEmpty() && !verificarcontraseña.isEmpty()){
            if (password.length() >=6){
                if( password.equals(verificarcontraseña)) {
                    mAuth.createUserWithEmailAndPassword(correo, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                Map<String, Object> Cliente = new HashMap<>();
                                Cliente.put("nombres", nombres);
                                Cliente.put("apellidos", apellidos);
                                Cliente.put("correo", correo);
                                Cliente.put("password", password);

                                String id = mAuth.getCurrentUser().getUid();

                                db.collection("Cliente")
                                        .document(id).set(Cliente)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(getApplicationContext(), "Guardado Exitoso ", Toast.LENGTH_LONG).show();
                                                intent.putExtra("id", id);
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
                            } else {
                                Toast.makeText(registro.this, "Nose pudo registar este usuario", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "El password debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "Debe completar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}
