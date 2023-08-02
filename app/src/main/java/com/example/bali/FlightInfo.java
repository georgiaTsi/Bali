package com.example.bali;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.res.ResourcesCompat;

public class FlightInfo {
    public String label;
    public Drawable calendar;
    public String from;
    public String to;
    public String time;
    public String flightInfo;
    public String from1;
    public String to1;
    public String time1;
    public String flightInfo1;

    public FlightInfo(){

    }

    public FlightInfo(Context context, String label, int drawableId, String from, String to, String time, String info,
                      String from1, String to1, String time1, String info1) {
        this.label = label;
        this.calendar = ResourcesCompat.getDrawable(context.getResources(), drawableId, null);
        this.from = from;
        this.to = to;
        this.time = time;
        this.flightInfo = info;
        this.from1 = from1;
        this.to1 = to1;
        this.time1 = time1;
        this.flightInfo1 = info1;
    }
}