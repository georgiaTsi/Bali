package com.example.bali.Adapters;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bali.Adapter;
import com.example.bali.R;
import com.example.bali.GetWithMeFragment;

import java.util.List;

public class GetWithMeItemAdapter extends RecyclerView.Adapter<GetWithMeItemAdapter.ViewHolder> {

    GetWithMeFragment fragment;

    private List<GetWithMeFragment.GroupItem> dataSet;

    public GetWithMeItemAdapter(GetWithMeFragment fragment, List<GetWithMeFragment.GroupItem> dataSet){
        this.fragment = fragment;
        this.dataSet = dataSet;
    }

    @Override
    public GetWithMeItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_list, parent, false);

        return new GetWithMeItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GetWithMeItemAdapter.ViewHolder holder, int position) {
        holder.textview.setText(dataSet.get(position).Title);

        holder.checkBox.setChecked(dataSet.get(position).IsDeleted);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void updateAdapter(List<GetWithMeFragment.GroupItem> newDataset) {
        dataSet = newDataset;

        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textview;
        private CheckBox checkBox;
        private ImageButton delete;

        public ViewHolder(View view){
            super(view);

            textview = view.findViewById(R.id.textview_viewholder);
            checkBox = view.findViewById(R.id.checkbox_viewholder);
            delete = view.findViewById(R.id.imagebutton_viewholder_delete);

            textview.setOnClickListener(view1 -> checkBox.setChecked(!checkBox.isChecked()));

            checkBox.setOnCheckedChangeListener((compoundButton, isChecked) -> {
                if(isChecked){
                    textview.setPaintFlags(textview.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else{
                    textview.setPaintFlags(textview.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
                }

                fragment.updateRow(textview.getText().toString(), isChecked);
            });

            delete.setOnClickListener(view12 -> fragment.deleteRow(textview.getText().toString()));
        }
    }
}