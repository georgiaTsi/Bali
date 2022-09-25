package com.example.amsterdam;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.res.ResourcesCompat;

public class PlaceItem {
    public String title;
    public Drawable image;
    public DetailedWithTitleActivity.GeneralPlaces generalPlace;
    public DetailedActivity.Places place;

    public PlaceItem(Context context, int titleStringId, int drawableId, DetailedWithTitleActivity.GeneralPlaces generalPlace, DetailedActivity.Places place) {
        this.title = context.getResources().getString(titleStringId);
        this.image = ResourcesCompat.getDrawable(context.getResources(), drawableId, null);
        this.generalPlace = generalPlace;
        this.place = place;
    }
}
