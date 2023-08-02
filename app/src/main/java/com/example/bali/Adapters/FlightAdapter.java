package com.example.bali.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.bali.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bali.ChecklistFragment;
import com.example.bali.FlightInfo;

import java.util.ArrayList;
import java.util.List;

public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.ViewHolder> {

    List<FlightInfo> dataSet = new ArrayList<>();

    @NonNull
    @Override
    public FlightAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_flight, parent, false);

        ImageView imageViewCalendar = view.findViewById(R.id.imageview_flight_calendar);
        TextView textViewLabel = view.findViewById(R.id.textview_flight_label);

        TextView textViewFrom = view.findViewById(R.id.textview_flight_from);
        TextView textViewTo = view.findViewById(R.id.textview_flight_to);
        TextView textViewTime = view.findViewById(R.id.textview_flight_time);
        TextView textViewInfo = view.findViewById(R.id.textview_flight_info);

        TextView textViewFrom1 = view.findViewById(R.id.textview_flight_from1);
        TextView textViewTo1 = view.findViewById(R.id.textview_flight_to1);
        TextView textViewTime1 = view.findViewById(R.id.textview_flight_time1);
        TextView textViewInfo1 = view.findViewById(R.id.textview_flight_info1);

        return new ViewHolder(view, imageViewCalendar, textViewLabel, textViewFrom, textViewTo, textViewTime, textViewInfo, textViewFrom1, textViewTo1, textViewTime1, textViewInfo1);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightAdapter.ViewHolder holder, int position) {
        holder.textViewLabel.setText(dataSet.get(position).label);
        holder.imageViewCalendar.setImageDrawable(dataSet.get(position).calendar);
        holder.textViewFrom.setText(dataSet.get(position).from);
        holder.textViewTo.setText(dataSet.get(position).to);
        holder.textViewTime.setText(dataSet.get(position).time);
        holder.textViewInfo.setText(dataSet.get(position).flightInfo);
        holder.textViewFrom1.setText(dataSet.get(position).from1);
        holder.textViewTo1.setText(dataSet.get(position).to1);
        holder.textViewTime1.setText(dataSet.get(position).time1);
        holder.textViewInfo1.setText(dataSet.get(position).flightInfo1);
    }

    @Override
    public int getItemCount() {
        if(dataSet == null || dataSet.size() == 0)
            return 0;

        return dataSet.size();
    }

    public void updateAdapter(List<FlightInfo> newDataset) {
        dataSet = newDataset;

        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageViewCalendar;
        public TextView textViewLabel;
        public TextView textViewFrom;
        public TextView textViewTo;
        public TextView textViewTime;
        public TextView textViewInfo;
        public TextView textViewFrom1;
        public TextView textViewTo1;
        public TextView textViewTime1;
        public TextView textViewInfo1;

        public ViewHolder(View view, ImageView imageViewCalendar, TextView textViewLabel, TextView textViewFrom, TextView textViewTo,
                          TextView textViewTime, TextView textViewInfo, TextView textViewFrom1, TextView textViewTo1,
                          TextView textViewTime1, TextView textViewInfo1){
            super(view);

            this.imageViewCalendar = imageViewCalendar;
            this.textViewLabel = textViewLabel;
            this.textViewFrom = textViewFrom;
            this.textViewTo = textViewTo;
            this.textViewTime = textViewTime;
            this.textViewInfo = textViewInfo;
            this.textViewFrom1 = textViewFrom1;
            this.textViewTo1 = textViewTo1;
            this.textViewTime1 = textViewTime1;
            this.textViewInfo1 = textViewInfo1;
        }
    }
}
