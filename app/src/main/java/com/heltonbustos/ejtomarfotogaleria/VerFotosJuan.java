package com.heltonbustos.ejtomarfotogaleria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

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

        if(fotos != null) {
            for (int i = 0; i < fotos.length; i++) {
                if (fotos[i].getAbsolutePath().contains("juan")) {
                    Bitmap myBitmap = BitmapFactory.decodeFile(fotos[i].getAbsolutePath());
                    String des = fotos[i].getAbsolutePath().split("Pictures/")[1];
                    String nombre = fotos[i].getAbsolutePath().split("Pictures/")[1].split("@")[0];
                    String fecha = fotos[i].getAbsolutePath().split("Pictures/")[1].split("@")[1];

                    listaFotos.add(new Fotos(myBitmap, des, nombre, fecha));
                }
            }

            myRecyclerView = findViewById(R.id.myRecyclerView);
            adaptador = new AdaptadorDatos(listaFotos, this);

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            myRecyclerView.setLayoutManager(layoutManager);

            myRecyclerView.setAdapter(adaptador);
        }
        else{
            Toast.makeText(this, "Aun no hay fotos", Toast.LENGTH_SHORT).show();
        }



    }
}