package com.example.bali.Adapters;

import android.app.AlertDialog;
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
import com.example.bali.R;

public class GetWithMeAdapter extends RecyclerView.Adapter<GetWithMeAdapter.GroupHolder> {

    private GetWithMeFragment getWithMeFragment;

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

        GetWithMeItemAdapter adapter = new GetWithMeItemAdapter(getWithMeFragment, dataSet.get(position).groupItems);

        holder.recyclerView.setLayoutManager(new LinearLayoutManager(getWithMeFragment.getContext()));
        holder.recyclerView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void updateAdapter(List<GetWithMeFragment.Group> newDataset) {
        dataSet = newDataset;

        notifyDataSetChanged();
    }

    public static class GroupHolder extends RecyclerView.ViewHolder{
        public TextView textviewTitle;
        public RecyclerView recyclerView;
        public ImageView imageViewAdd;
//        private CheckBox checkBox;
//        private ImageButton delete;

        public GroupHolder(View view){
            super(view);

            textviewTitle = view.findViewById(R.id.textview_label);
            recyclerView = view.findViewById(R.id.recyclerview);
            imageViewAdd = view.findViewById(R.id.imageview_add);

//            checkBox = view.findViewById(R.id.checkbox_viewholder);
//            delete = view.findViewById(R.id.imagebutton_viewholder_delete);

//            textviewTitle.setOnClickListener(view1 -> checkBox.setChecked(!checkBox.isChecked()));

//            checkBox.setOnCheckedChangeListener((compoundButton, isChecked) -> {
//                if(isChecked){
//                    textview.setPaintFlags(textview.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//                }
//                else{
//                    textview.setPaintFlags(textview.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
//                }
//
//                getWithMeFragment.updateRow(textview.getText().toString(), isChecked);
//            });
//
//            delete.setOnClickListener(view12 -> getWithMeFragment.deleteRow(textview.getText().toString()));
        }
    }
}