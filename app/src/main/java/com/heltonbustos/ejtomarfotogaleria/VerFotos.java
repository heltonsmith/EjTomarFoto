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


        Bitmap myBitmap1 = BitmapFactory.decodeFile(fotos[0].getAbsolutePath());
        Bitmap myBitmap2 = BitmapFactory.decodeFile(fotos[1].getAbsolutePath());
        Bitmap myBitmap3 = BitmapFactory.decodeFile(fotos[2].getAbsolutePath());
        Bitmap myBitmap4 = BitmapFactory.decodeFile(fotos[3].getAbsolutePath());
        Bitmap myBitmap5 = BitmapFactory.decodeFile(fotos[4].getAbsolutePath());

        listaFotos.add(new Fotos(myBitmap1, fotos[0].getAbsolutePath().split("Pictures/")[1]));
        listaFotos.add(new Fotos(myBitmap2, fotos[1].getAbsolutePath().split("Pictures/")[1]));
        listaFotos.add(new Fotos(myBitmap3, fotos[2].getAbsolutePath().split("Pictures/")[1]));
        listaFotos.add(new Fotos(myBitmap4, fotos[3].getAbsolutePath().split("Pictures/")[1]));
        listaFotos.add(new Fotos(myBitmap5, fotos[4].getAbsolutePath().split("Pictures/")[1]));

        myRecyclerView = findViewById(R.id.myRecyclerView);
        adaptador = new AdaptadorDatos(listaFotos, this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(layoutManager);

        myRecyclerView.setAdapter(adaptador);

    }
}