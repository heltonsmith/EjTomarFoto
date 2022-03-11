package com.heltonbustos.ejtomarfotogaleria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VerFotos extends AppCompatActivity {

    RecyclerView myRecyclerView;
    AdaptadorDatos adaptador;
    ArrayList<Fotos> listaFotos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_fotos);

        File ruta = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File fotos[] = ruta.listFiles();


        Bitmap myBitmap = BitmapFactory.decodeFile(fotos[0].getAbsolutePath());

        listaFotos.add(new Fotos(myBitmap, fotos[0].getAbsolutePath().split("Pictures/")[1]));
        listaFotos.add(new Fotos(myBitmap, fotos[1].getAbsolutePath().split("Pictures/")[1]));
        listaFotos.add(new Fotos(myBitmap, fotos[2].getAbsolutePath().split("Pictures/")[1]));
        listaFotos.add(new Fotos(myBitmap, fotos[3].getAbsolutePath().split("Pictures/")[1]));
        listaFotos.add(new Fotos(myBitmap, fotos[4].getAbsolutePath().split("Pictures/")[1]));

        myRecyclerView = findViewById(R.id.myRecyclerView);
        adaptador = new AdaptadorDatos(listaFotos, this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(layoutManager);

        myRecyclerView.setAdapter(adaptador);

    }
}