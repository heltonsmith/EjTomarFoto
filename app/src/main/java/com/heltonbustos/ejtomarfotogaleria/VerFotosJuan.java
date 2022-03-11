package com.heltonbustos.ejtomarfotogaleria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;

public class VerFotosJuan extends AppCompatActivity {

    RecyclerView myRecyclerView;
    AdaptadorDatos adaptador;
    ArrayList<Fotos> listaFotos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_fotos_juan);

        File ruta = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File fotos[] = ruta.listFiles();

        for (int i=0; i<fotos.length; i++){
            if (fotos[i].getAbsolutePath().contains("juan")){
                Bitmap myBitmap = BitmapFactory.decodeFile(fotos[i].getAbsolutePath());
                listaFotos.add(new Fotos(myBitmap, fotos[i].getAbsolutePath().split("Pictures/")[1]));
            }
        }

        myRecyclerView = findViewById(R.id.myRecyclerView);
        adaptador = new AdaptadorDatos(listaFotos, this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(layoutManager);

        myRecyclerView.setAdapter(adaptador);

    }
}