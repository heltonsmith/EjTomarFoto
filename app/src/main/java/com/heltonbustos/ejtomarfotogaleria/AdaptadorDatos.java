package com.heltonbustos.ejtomarfotogaleria;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorDatos extends RecyclerView.Adapter<AdaptadorDatos.ViewHolderDator>{

    ArrayList<Fotos> listDatos;
    Context context;

    public AdaptadorDatos(ArrayList<Fotos> listDatos, Context context) {
        this.listDatos = listDatos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderDator onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_recycler, null, false);
        return new ViewHolderDator(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDator holder, int position) {
        Bitmap bit = listDatos.get(position).getBitmap();
        String texto = listDatos.get(position).getDes();
        holder.imagen.setImageBitmap(bit);
        holder.des.setText(texto);
    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public class ViewHolderDator extends RecyclerView.ViewHolder{
        ImageView imagen;
        TextView des;

        public ViewHolderDator(@NonNull View itemView) {
            super(itemView);
            des = itemView.findViewById(R.id.myDes);
            imagen = itemView.findViewById(R.id.myImagen);
        }
    }
}
