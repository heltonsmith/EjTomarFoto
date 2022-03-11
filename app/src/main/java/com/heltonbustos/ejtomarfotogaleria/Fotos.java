package com.heltonbustos.ejtomarfotogaleria;

import android.graphics.Bitmap;

public class Fotos {
    private Bitmap bitmap;
    private String des;
    private String nombre;
    private String fecha;

    public Fotos(Bitmap bitmap, String des, String nombre, String fecha) {
        this.bitmap = bitmap;
        this.des = des;
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
