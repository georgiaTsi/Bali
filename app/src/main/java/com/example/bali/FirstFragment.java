package com.example.bali;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

public class FirstFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_main);

//        initRecyclerView(recyclerView);

        return view;
    }

    //region RecyclerView
//    private void initRecyclerView(RecyclerView recyclerView) {
//        ArrayList<ItemClass> items = readFromDatabase();
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
//        adapter = new Adapter(this, items);
//        recyclerView.setAdapter(adapter);
//    }
//
//    private void updateRecyclerView() {
//        ArrayList<ItemClass> items = readFromDatabase();
//        adapter.updateAdapter(items);
//    }
//
//    private ArrayList<ItemClass> readFromDatabase() {
//
//        ArrayList<ItemClass> items = new ArrayList<>();
//
//        try {
//            myDatabase = this.getActivity().openOrCreateDatabase("SupermarketDatabase", Context.MODE_PRIVATE, null);
//
//            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS SupermarketList(Item VARCHAR,IsChecked BOOLEAN);");
//
//            Cursor resultSet = myDatabase.rawQuery("Select * from SupermarketList", null);
//            resultSet.moveToFirst();
//
//            // Loop through all Results
//            do {
//                String item = resultSet.getString(0);
//
//                boolean isChecked = Boolean.parseBoolean(resultSet.getString(1));
//
//                items.add(new ItemClass(item, isChecked));
//            } while (resultSet.moveToNext());
//        } catch (Exception e) {
//        }
//
//        return items;
//    }
//
//    public void addItem(String itemName) {
//        myDatabase.execSQL("INSERT INTO SupermarketList (Item, IsChecked) VALUES('" + itemName + "', 'false');");
//
//        updateRecyclerView();
//    }
//
//    public void deleteRow(String item) {
//        myDatabase.execSQL("DELETE FROM SupermarketList WHERE Item = '" + item + "'");
//
//        updateRecyclerView();
//    }
//
//    public void updateRow(String item, boolean isChecked) {
//        String query = String.format("UPDATE SupermarketList SET IsChecked = '%b' WHERE Item = '" + item + "'", isChecked);
//        myDatabase.execSQL(query);
//    }
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