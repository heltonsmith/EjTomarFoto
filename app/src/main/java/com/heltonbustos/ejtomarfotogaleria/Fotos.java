package com.heltonbustos.ejtomarfotogaleria;

import android.graphics.Bitmap;

public class Fotos {
    private Bitmap bitmap;
    private String des;

    public Fotos(Bitmap bitmap, String des) {
        this.bitmap = bitmap;
        this.des = des;
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
}
