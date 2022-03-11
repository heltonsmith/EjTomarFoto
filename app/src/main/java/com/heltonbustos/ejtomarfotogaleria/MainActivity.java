package com.heltonbustos.ejtomarfotogaleria;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button btnTomarGabriel, btnTomarJuan, btnVerFotosGabriel, btnVerFotosJuan;
    Button btnGuardar;
    ImageView imagen;
    String dueno = "";

    private static final int REQUEST_PERMISSION_CAMERA = 100; //detectar la respuesta del usuario si es OK
    private static final int TAKE_PICTURE = 101; //detecta si se tomo la foto con la camara del celular

    private static final int REQUEST_PERMISSION_WRITE_STORAGE = 200; //detectar la respuesta del usuario si es ok

    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTomarGabriel = findViewById(R.id.btnTomarGabriel);
        btnTomarJuan = findViewById(R.id.btnTomarJuan);
        btnGuardar = findViewById(R.id.btnGuardar);
        imagen = findViewById(R.id.imageView);

        btnVerFotosGabriel = findViewById(R.id.btnVerFotosGabriel);
        btnVerFotosJuan = findViewById(R.id.btnVerFotosJuan);

        //PERMISOS GABRIEL
        ////////////////////////////////
        btnTomarGabriel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dueno = "gabriel";
                permisosCamara();
            }
        });
        ////////////////////////////////
        //PERMISOS GABRIEL

        //PERMISOS JUAN
        ////////////////////////////////
        btnTomarJuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dueno = "juan";
                permisosCamara();
            }
        });
        ////////////////////////////////
        //PERMISOS JUAN

        //PERMISOS DE ALMACENAMIENTO GENERAL
        ////////////////////////////////
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permisosAlmacenamiento();
            }
        });
        ////////////////////////////////
        //PERMISOS DE ALMACENAMIENTO GENERAL


    }

    public void cargarFotosJuan(View view){
        Intent intent = new Intent(getApplicationContext(), VerFotosJuan.class);
        startActivity(intent);
    }

    public void cargarFotosGabriel(View view){
        Intent intent = new Intent(getApplicationContext(), VerFotosGabriel.class);
        startActivity(intent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == TAKE_PICTURE){
            if (resultCode == Activity.RESULT_OK && data != null){
                bitmap = (Bitmap) data.getExtras().get("data");
                imagen.setImageBitmap(bitmap);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION_CAMERA){
            if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                tomarFoto();
            } //puede ir un else indicando que no se aceptaron los permisos
        }
        else if(requestCode == REQUEST_PERMISSION_WRITE_STORAGE){
            if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                guardarFoto();
            } //puede ir un else indicando que no se aceptaron los permisos
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void permisosCamara(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){ //android marshmallow (Permiso en tiempo de ejecución)
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                tomarFoto();
            }
            else{ //api > 28 (Q)
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.CAMERA},
                        REQUEST_PERMISSION_CAMERA
                );
            }
        }
        else{ //permiso en tiempo de descarga
            tomarFoto();
        }
    }

    public void permisosAlmacenamiento(){
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P){ //Apis mas antiguas < 28
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    guardarFoto();
                }
                else{
                    //api < 28 (Q)
                    ActivityCompat.requestPermissions(
                            this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            REQUEST_PERMISSION_WRITE_STORAGE
                    );
                }
            }
            else{
                guardarFoto();
            }
        }
        else{
            guardarFoto();
        }
    }

    public void tomarFoto(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, TAKE_PICTURE);
        }
    }

    public void guardarFoto(){ //Android Q y posteriores
        OutputStream outputStream = null;
        File file = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){ //versiones recientes
            ContentResolver resolver = getContentResolver(); //para manejar los values
            ContentValues values = new ContentValues(); //metadatos de imagenes tipo, render, etc

            SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy hh.mm.ss");
            String tiempo = formatter.format(new Date());

            //agrego el dueño de la foto
            String filename = dueno + "@" + tiempo;

            values.put(MediaStore.Images.Media.DISPLAY_NAME, filename);
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
            values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/MyApp");
            values.put(MediaStore.Images.Media.IS_PENDING, 1); //1 la imagen se esta procesando

            Uri collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY); //construir una ruta en android
            Uri imageUri = resolver.insert(collection, values); //insertando en memoria la ruta anterior


            try {
                outputStream = resolver.openOutputStream(imageUri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


            values.clear();
            values.put(MediaStore.Images.Media.IS_PENDING, 0);
            resolver.update(imageUri, values, null, null);
        }
        else{ //Apis mas antiguas < 28
            String imageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();

            SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy hh.mm.ss");
            String tiempo = formatter.format(new Date());

            //agrego el dueño de la foto
            String filename = dueno + "@" + tiempo + ".jpg"; //nombre del archivo

            file = new File(imageDir, filename);

            try {
                outputStream = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (outputStream != null){
            try {
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (file != null){
            MediaScannerConnection.scanFile(this, new String[]{file.toString()}, null, null);
        }

    }


}