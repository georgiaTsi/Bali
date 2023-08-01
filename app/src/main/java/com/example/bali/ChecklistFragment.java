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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ChecklistFragment extends Fragment {

    SQLiteDatabase myDatabase;

    Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checklist, container, false);

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
                    .setTitle("Add item")
                    .setView(input)

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                        addItem(input.getText().toString());
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .setNegativeButton(android.R.string.no, null)
                    .show();
        });
    }

    //region RecyclerView
    private void initRecyclerView(RecyclerView recyclerView) {
        ArrayList<ChecklistFragment.ItemClass> items = readFromDatabase();

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adapter = new Adapter(this, items);
        recyclerView.setAdapter(adapter);
    }

    private void updateRecyclerView() {
        ArrayList<ChecklistFragment.ItemClass> items = readFromDatabase();
        adapter.updateAdapter(items);
    }

    private ArrayList<ChecklistFragment.ItemClass> readFromDatabase() {

        ArrayList<ChecklistFragment.ItemClass> items = new ArrayList<>();

        try {
            myDatabase = this.getActivity().openOrCreateDatabase("SupermarketDatabase", Context.MODE_PRIVATE, null);

            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS SupermarketList(Item VARCHAR,IsChecked BOOLEAN);");

            Cursor resultSet = myDatabase.rawQuery("Select * from SupermarketList", null);
            resultSet.moveToFirst();

            // Loop through all Results
            do {
                String item = resultSet.getString(0);

                boolean isChecked = Boolean.parseBoolean(resultSet.getString(1));

                items.add(new ChecklistFragment.ItemClass(item, isChecked));
            } while (resultSet.moveToNext());
        } catch (Exception e) {
        }

        return items;
    }

    public void addItem(String itemName){
        myDatabase.execSQL("INSERT INTO SupermarketList (Item, IsChecked) VALUES('" + itemName + "', 'false');");

        updateRecyclerView();
    }

    public void deleteRow(String item) {
        myDatabase.execSQL("DELETE FROM SupermarketList WHERE Item = '" + item + "'");

        updateRecyclerView();
    }

    public void updateRow(String item, boolean isChecked) {
        String query = String.format("UPDATE SupermarketList SET IsChecked = '%b' WHERE Item = '" + item + "'", isChecked);
        myDatabase.execSQL(query);
    }
    //endregion

    public static class ItemClass {
        public String Item;
        public Boolean IsChecked;

        public ItemClass(String item, boolean isChecked) {
            Item = item;
            IsChecked = isChecked;
        }
    }
}
