package com.example.bali.Adapters;

import android.app.AlertDialog;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bali.GetWithMeFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.bali.R;

public class GetWithMeAdapter extends RecyclerView.Adapter<GetWithMeAdapter.GroupHolder> {

    private final GetWithMeFragment getWithMeFragment;

    private List<GetWithMeFragment.Group> dataSet;

    public GetWithMeAdapter(GetWithMeFragment fragment, List<GetWithMeFragment.Group> dataSet){
        this.getWithMeFragment = fragment;
        this.dataSet = dataSet;
    }

    @Override
    public GroupHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_getwithmegroup, parent, false);

        return new GroupHolder(view);
    }

    @Override
    public void onBindViewHolder(GroupHolder holder, int position) {
        holder.textviewTitle.setText(dataSet.get(position).Label);

        holder.textViewCounter.setText(findDoneItems(dataSet.get(position).groupItems, holder.textviewTitle));

        holder.imageViewAdd.setOnClickListener(v -> {
            final EditText input = new EditText(getWithMeFragment.getContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            input.setLayoutParams(lp);

            new AlertDialog.Builder(getWithMeFragment.getContext())
                    .setTitle("Add Group item")
                    .setView(input)

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                        String label = dataSet.get(position).Label;
                        getWithMeFragment.addGroupItem(label, input.getText().toString());
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .setNegativeButton(android.R.string.no, null)
                    .show();
        });

        holder.imageViewEdit.setOnClickListener(v -> {
            final EditText input = new EditText(getWithMeFragment.getContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            input.setLayoutParams(lp);

            new AlertDialog.Builder(getWithMeFragment.getContext())
                    .setTitle("Edit title")
                    .setView(input)

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                        getWithMeFragment.editTitle(input.getText().toString(), dataSet.get(position).Label);
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .setNegativeButton(android.R.string.no, null)
                    .show();
        });

        holder.textviewTitle.setOnClickListener(v->{
            if(holder.recyclerView.getVisibility() == View.VISIBLE)
                holder.recyclerView.setVisibility(View.GONE);
            else
                holder.recyclerView.setVisibility(View.VISIBLE);
        });

        GetWithMeItemAdapter adapter = new GetWithMeItemAdapter(getWithMeFragment, dataSet.get(position).groupItems);

        holder.recyclerView.setLayoutManager(new LinearLayoutManager(getWithMeFragment.getContext()));
        holder.recyclerView.setAdapter(adapter);
    }

    private String findDoneItems(List<GetWithMeFragment.GroupItem> list, TextView textViewTitle){
        int done = 0;
        for(GetWithMeFragment.GroupItem item : list){
            if(item.IsDeleted)
                done++;
        }

        int all = list.size();

        if(all == done && all != 0)
            textViewTitle.setPaintFlags(textViewTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        else
            textViewTitle.setPaintFlags(textViewTitle.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);

        return "(" + done + "/" + all + ")";
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void updateAdapter(List<GetWithMeFragment.Group> newDataset) {
        dataSet = newDataset;

        notifyDataSetChanged();
    }

    public void updateAdapterWithoutNotify(List<GetWithMeFragment.Group> newDataset) {
        dataSet = newDataset;
    }

    public int getPositionByLabel(String label){
        for(int i = 0; i < dataSet.size(); i++){
            if(dataSet.get(i).Label.equals(label))
                return i;
        }

        return 0;
    }

    public static class GroupHolder extends RecyclerView.ViewHolder{
        public TextView textviewTitle;
        public TextView textViewCounter;
        public RecyclerView recyclerView;
        public ImageView imageViewAdd;
        public ImageView imageViewEdit;

        public GroupHolder(View view){
            super(view);

            textviewTitle = view.findViewById(R.id.textview_label);
            textViewCounter = view.findViewById(R.id.textview_counter);
            recyclerView = view.findViewById(R.id.recyclerview);
            imageViewAdd = view.findViewById(R.id.imageview_add);
            imageViewEdit = view.findViewById(R.id.imageview_edit);
        }
    }
}