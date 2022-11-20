package com.example.alquilerpelicula;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class VerTrailer extends AppCompatActivity {
    VideoView vervideo;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_trailer);
        String id =  getIntent().getExtras().getString("id");
        db =  FirebaseFirestore.getInstance();

        db.collection("Pelicula").document(id)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()){
                            String trailer;

                            trailer = documentSnapshot.getString("trailer");
                            vervideo = (VideoView) findViewById(R.id.vervideo);
                            Uri uri = Uri.parse(trailer);
                            vervideo.setMediaController((new MediaController(VerTrailer.this)));
                            vervideo.setVideoURI(uri);
                            vervideo.requestFocus();
                            vervideo.start();
                        }
                    }
                });
    }
}
