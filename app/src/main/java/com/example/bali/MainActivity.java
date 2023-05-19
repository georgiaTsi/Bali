package com.example.bali;

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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<com.example.bali.PlaceItem> allItemsList = new ArrayList<com.example.bali.PlaceItem>();

    RecyclerView recyclerView;
    com.example.bali.ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeRecyclerView();
    }

    private void initializeRecyclerView(){

        recyclerView = findViewById(R.id.recyclerview_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemAdapter = new com.example.bali.ItemAdapter(this);
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
        allItemsList.add(new PlaceItem(this, R.string.flights_info, R.drawable.rijksmuseum, DetailedWithTitleActivity.GeneralPlaces.FlightsInfo, null));
//        allItemsList.add(new PlaceItem(this, R.string.palace, R.drawable.palace, DetailedWithTitleActivity.GeneralPlaces.Palace, null));
//        allItemsList.add(new PlaceItem(this, R.string.churches, R.drawable.oude_kerk, DetailedWithTitleActivity.GeneralPlaces.Churches, null));
//        allItemsList.add(new PlaceItem(this, R.string.parks, R.drawable.vondelpark, DetailedWithTitleActivity.GeneralPlaces.Parks, null));
//        allItemsList.add(new PlaceItem(this, R.string.brunch, R.drawable.pluk, DetailedWithTitleActivity.GeneralPlaces.Brunch, null));
//        allItemsList.add(new PlaceItem(this, R.string.food, R.drawable.amsterdam, DetailedWithTitleActivity.GeneralPlaces.Food, null));
//        allItemsList.add(new PlaceItem(this, R.string.dessert, R.drawable.van_stapele, DetailedWithTitleActivity.GeneralPlaces.Dessert, null));
//        allItemsList.add(new PlaceItem(this, R.string.drink, R.drawable.xtracold, DetailedWithTitleActivity.GeneralPlaces.Drink, null));
//        allItemsList.add(new PlaceItem(this, R.string.other, R.drawable.adam_lookout, DetailedWithTitleActivity.GeneralPlaces.Other, null));
//        allItemsList.add(new PlaceItem(this, R.string.neighborhood, R.drawable.jordaan, DetailedWithTitleActivity.GeneralPlaces.Neighborhood, null));
//        allItemsList.add(new PlaceItem(this, R.string.shopping, R.drawable.kalverstraat, DetailedWithTitleActivity.GeneralPlaces.Shopping, null));
        allItemsList.add(new PlaceItem(this, R.string.language, R.drawable.language, DetailedWithTitleActivity.GeneralPlaces.Language, null));

        itemAdapter.updateAdapter(allItemsList);
    }
}