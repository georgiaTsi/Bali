package com.example.bali.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bali.GetWithMeFragment;
import com.example.bali.R;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bali.ChecklistFragment;
import com.example.bali.DetailedWithTitleActivity;
import com.example.bali.MainActivity;
import com.example.bali.PlaceItem;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    List<PlaceItem> dataSet = new ArrayList<PlaceItem>();

    MainActivity activity;

    public ItemAdapter(MainActivity activity){
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item, parent, false);

        ImageView imageView = view.findViewById(R.id.imageview_holder);
        TextView textView = view.findViewById(R.id.textview_holder);

        ImageView imageView1 = view.findViewById(R.id.imageview_holder1);
        TextView textView1 = view.findViewById(R.id.textview_holder1);

        return new ViewHolder(view, imageView, textView, imageView1, textView1);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        if(position % 2 != 0) {
            viewHolder.itemView.setVisibility(View.GONE);
            viewHolder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));

            return;
        }

        viewHolder.itemView.setVisibility(View.VISIBLE);
        viewHolder.itemView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        viewHolder.imageView.setImageDrawable(dataSet.get(position).image);
        viewHolder.textView.setText(dataSet.get(position).title);

        viewHolder.imageView.setOnClickListener(view -> {
            if(dataSet.get(position).generalPlace.equals(DetailedWithTitleActivity.GeneralPlaces.Checklist)) {
                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();

//                Fragment newFragment = new ChecklistFragment();
                Fragment newFragment = new GetWithMeFragment();
                transaction.replace(R.id.fragment_container_view, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
            else {
                Intent intent = new Intent(activity.getBaseContext(), DetailedWithTitleActivity.class);
                intent.putExtra("place", dataSet.get(position).generalPlace);
                activity.startActivity(intent);
            }
        });

        //next
        if(position + 1 >= dataSet.size()) {
            viewHolder.imageView1.setVisibility(View.INVISIBLE);
            viewHolder.textView1.setVisibility(View.INVISIBLE);

            return;
        }

        viewHolder.imageView1.setVisibility(View.VISIBLE);
        viewHolder.textView1.setVisibility(View.VISIBLE);

        viewHolder.imageView1.setImageDrawable(dataSet.get(position+1).image);
        viewHolder.textView1.setText(dataSet.get(position+1).title);

        viewHolder.imageView1.setOnClickListener(view -> {
            Intent intent = new Intent(activity.getBaseContext(), DetailedWithTitleActivity.class);
            intent.putExtra("place", dataSet.get(position+1).generalPlace);
            activity.startActivity(intent);
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

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView textView;
        public ImageView imageView1;
        public TextView textView1;

        public ViewHolder(View view, ImageView imageView, TextView textView, ImageView imageView1, TextView textView1){
            super(view);

            this.imageView = imageView;
            this.textView = textView;
            this.imageView1 = imageView1;
            this.textView1 = textView1;
        }
    }
}
