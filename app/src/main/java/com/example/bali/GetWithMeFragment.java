package com.example.bali;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bali.Adapters.GetWithMeAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class GetWithMeFragment extends Fragment {

    SQLiteDatabase myDatabase;

    GetWithMeAdapter adapter;

    ArrayList<Group> items;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_getwithme, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_main);

        initRecyclerView(recyclerView);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(view1 -> {
            final EditText input = new EditText(getContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            input.setLayoutParams(lp);

            new AlertDialog.Builder(getContext())
                    .setTitle("Add Group item")
                    .setView(input)

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                        addGroupLabel(input.getText().toString());
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .setNegativeButton(android.R.string.no, null)
                    .show();
        });
    }

    //region RecyclerView
    private void initRecyclerView(RecyclerView recyclerView) {
        ArrayList<Group> items = readFromDatabase();

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adapter = new GetWithMeAdapter(this, items);
        recyclerView.setAdapter(adapter);
    }

    private void updateRecyclerView() {
        ArrayList<Group> items = readFromDatabase();
        adapter.updateAdapter(items);
    }

    private ArrayList<Group> readFromDatabase() {

        items = new ArrayList<>();

        try {
            myDatabase = this.getActivity().openOrCreateDatabase("GetWithMeDatabase", Context.MODE_PRIVATE, null);

            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS GetWithMeList(Label VARCHAR, Title VARCHAR, IsChecked Boolean);");

            Cursor resultSet = myDatabase.rawQuery("Select * from GetWithMeList", null);
            resultSet.moveToFirst();

            HashSet<String> labels = new HashSet();
            List<GroupItem> groupItems = new ArrayList<>();

            // Loop through all Results
            do {
                String label = resultSet.getString(0);
                labels.add(label);

                String title = resultSet.getString(1);

                boolean isChecked = Boolean.parseBoolean(resultSet.getString(2));

                groupItems.add(new GroupItem(label, title, isChecked));
            } while (resultSet.moveToNext());

            for(String label: labels){
                List<GroupItem> list = groupItems.stream().filter(x -> x.Label.equals(label)).collect(Collectors.toList());
                items.add(new Group(label, list));
            }
        } catch (Exception e) {
        }

        return items;
    }

    private void addGroupLabel(String groupItemName) {
        items.add(new Group(groupItemName, new ArrayList<>()));

        adapter.updateAdapter(items);
    }

    public void addGroupItem(String groupLabel, String groupItemName) {
        myDatabase.execSQL("INSERT INTO GetWithMeList (Label, Title, IsChecked) VALUES('" + groupLabel + "', '" + groupItemName + "', 'false');");

        updateRecyclerView();
    }

    public void editTitle(String newTitle, String oldTitle){
        String query = String.format("UPDATE GetWithMeList SET Label = '" + newTitle + "' WHERE Label = '" + oldTitle + "'");
        myDatabase.execSQL(query);

        updateRecyclerView();
    }

    public void deleteRow(String item) {
        myDatabase.execSQL("DELETE FROM GetWithMeList WHERE Title = '" + item + "'");

        updateRecyclerView();
    }

    public void updateRow(String item, boolean isChecked) {
        String query = String.format("UPDATE GetWithMeList SET IsChecked = '%b' WHERE Title = '" + item + "'", isChecked);
        myDatabase.execSQL(query);
    }
    //endregion

    public static class Group {
        public String Label;
        public List<GroupItem> groupItems;

        public Group(String label, List<GroupItem> groupItems) {
            Label = label;
            this.groupItems = groupItems;
        }
    }

    public static class GroupItem{
        public String Label;
        public String Title;
        public Boolean IsDeleted;

        public GroupItem(String label, String title, Boolean isDeleted){
            this.Label = label;
            this.Title = title;
            this.IsDeleted = isDeleted;
        }
    }
}