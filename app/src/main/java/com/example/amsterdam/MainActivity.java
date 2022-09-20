package com.example.amsterdam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Item> allItemsList = new ArrayList<Item>();

    RecyclerView recyclerView;
    ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeRecyclerView();
    }

    private void initializeRecyclerView(){

        recyclerView = findViewById(R.id.recyclerview_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemAdapter = new ItemAdapter(this);
        recyclerView.setAdapter(itemAdapter);

        populateList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_detailed, menu);
        menu.getItem(0).getIcon().mutate().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.action_notes){
            Intent intent = new Intent(getBaseContext(), NotesActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void populateList(){
        //All
        allItemsList.add(new Item(getResources().getString(R.string.museums), ResourcesCompat.getDrawable(getResources(), R.drawable.rijksmuseum, null), DetailedWithTitleActivity.GeneralPlaces.Museums, null));
        allItemsList.add(new Item(getResources().getString(R.string.palace), ResourcesCompat.getDrawable(getResources(), R.drawable.amsterdam, null), DetailedWithTitleActivity.GeneralPlaces.Palace, null));
        allItemsList.add(new Item(getResources().getString(R.string.churches), ResourcesCompat.getDrawable(getResources(), R.drawable.amsterdam, null), DetailedWithTitleActivity.GeneralPlaces.Churches, null));
        allItemsList.add(new Item(getResources().getString(R.string.parks), ResourcesCompat.getDrawable(getResources(), R.drawable.amsterdam, null), DetailedWithTitleActivity.GeneralPlaces.Parks, null));
        allItemsList.add(new Item(getResources().getString(R.string.food), ResourcesCompat.getDrawable(getResources(), R.drawable.amsterdam, null), DetailedWithTitleActivity.GeneralPlaces.Food, null));
        allItemsList.add(new Item(getResources().getString(R.string.drink), ResourcesCompat.getDrawable(getResources(), R.drawable.amsterdam, null), DetailedWithTitleActivity.GeneralPlaces.Drink, null));

        itemAdapter.updateAdapter(allItemsList);
    }

    public static class Item {
        public String title;
        public Drawable image;
        public DetailedWithTitleActivity.GeneralPlaces generalPlace;
        public DetailedActivity.Places place;

        public Item(String title, Drawable image, DetailedWithTitleActivity.GeneralPlaces generalPlace, DetailedActivity.Places place){
            this.title = title;
            this.image = image;
            this.generalPlace = generalPlace;
            this.place = place;
        }
    }
}