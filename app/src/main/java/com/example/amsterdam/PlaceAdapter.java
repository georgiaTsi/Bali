package com.example.amsterdam;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceViewHolder> {

    List<PlaceItem> dataSet = new ArrayList<PlaceItem>();

    Activity activity;

    public PlaceAdapter(Activity activity){
        this.activity = activity;
    }

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_place, parent, false);

        ImageView imageView = view.findViewById(R.id.imageview_image);
        TextView textView = view.findViewById(R.id.textview_title);

        return new PlaceViewHolder(view, imageView, textView);
    }

    @Override
    public void onBindViewHolder(PlaceViewHolder viewHolder, int position) {

        viewHolder.imageView.setImageDrawable(dataSet.get(position).image);
        viewHolder.textView.setText(dataSet.get(position).title);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity.getBaseContext(), DetailedActivity.class);
                intent.putExtra("place", dataSet.get(position).place);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(dataSet == null || dataSet.size() == 0)
            return 0;

        return dataSet.size();
    }

    public void updateAdapter(List<PlaceItem> newList){
        dataSet.clear();
        dataSet.addAll(newList);

        notifyDataSetChanged();
    }
}
