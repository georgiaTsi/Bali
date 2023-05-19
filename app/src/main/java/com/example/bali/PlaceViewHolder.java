package com.example.bali;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class PlaceViewHolder extends RecyclerView.ViewHolder{
    public ImageView imageView;
    public TextView textView;

    public PlaceViewHolder(View view, ImageView imageView, TextView textView){
        super(view);

        this.imageView = imageView;
        this.textView = textView;
    }
}