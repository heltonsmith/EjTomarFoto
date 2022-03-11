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

public class VerFotosGabriel extends AppCompatActivity {

    RecyclerView myRecyclerView2;
    AdaptadorDatos adaptador;
    ArrayList<Fotos> listaFotos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_fotos_gabriel);

        File ruta = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File fotos[] = ruta.listFiles();

        if(fotos != null) {
            for (int i = 0; i < fotos.length; i++) {
                if (fotos[i].getAbsolutePath().contains("gabriel")) {
                    Bitmap myBitmap = BitmapFactory.decodeFile(fotos[i].getAbsolutePath());
                    String des = fotos[i].getAbsolutePath().split("Pictures/")[1];
                    String nombre = fotos[i].getAbsolutePath().split("Pictures/")[1].split("@")[0];
                    String fecha = fotos[i].getAbsolutePath().split("Pictures/")[1].split("@")[1];

                    listaFotos.add(new Fotos(myBitmap, des, nombre, fecha));
                }
            }

            myRecyclerView2 = findViewById(R.id.myRecyclerView2);
            adaptador = new AdaptadorDatos(listaFotos, this);

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            myRecyclerView2.setLayoutManager(layoutManager);

            myRecyclerView2.setAdapter(adaptador);
        }
        else{
            Toast.makeText(this, "Aun no hay fotos", Toast.LENGTH_SHORT).show();
        }

    }
}