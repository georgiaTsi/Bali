package com.example.amsterdam;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DetailedWithTitleActivity extends AppCompatActivity {

    public enum GeneralPlaces {
        Museums,
        Palace,
        Churches,
        Parks,
        Food,
        Drink,
        Shopping,
        Other,
        Neighborhood
    };

    String textForMaps = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detailed_with_title);

        TextView labelTextView = findViewById(R.id.textview_label);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        Toolbar toolbar = findViewById(R.id.toolbar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        PlaceAdapter adapter = new PlaceAdapter(this);
        recyclerView.setAdapter(adapter);

        List<MainActivity.Item> items = new ArrayList<>();

        GeneralPlaces place = (GeneralPlaces) getIntent().getExtras().get("place");

        String text = "";
        String titleToolbarText = "";

        switch (place) {
            case Museums:
                text = getResources().getString(R.string.museumsText);
                titleToolbarText = "Μουσεία";

                items.add(new MainActivity.Item(getResources().getString(R.string.rijksmuseum), ResourcesCompat.getDrawable(getResources(), R.drawable.rijksmuseum, null), null, DetailedActivity.Places.Rijksmuseum));
                items.add(new MainActivity.Item(getResources().getString(R.string.vangogh), ResourcesCompat.getDrawable(getResources(), R.drawable.vangogh, null), null, DetailedActivity.Places.VanGogh));
                items.add(new MainActivity.Item(getResources().getString(R.string.stedelijk), ResourcesCompat.getDrawable(getResources(), R.drawable.stedelijk , null), null, DetailedActivity.Places.Stedelijk));
                items.add(new MainActivity.Item(getResources().getString(R.string.museumRembrand), ResourcesCompat.getDrawable(getResources(), R.drawable.museum_rembrand , null), null, DetailedActivity.Places.MuseumRembrand));

                textForMaps = "museums";

                break;

            case Palace:
                text = getResources().getString(R.string.palaceText);
                titleToolbarText = "Βασιλεία";

                items.add(new MainActivity.Item(getResources().getString(R.string.royalPalace), ResourcesCompat.getDrawable(getResources(), R.drawable.palace , null), null, DetailedActivity.Places.RoyalPalace));

                //textForMaps = "museums";

                break;

            case Churches:
                text = getResources().getString(R.string.churchesText);
                titleToolbarText = "Εκκλησίες";

                textForMaps = "churches";

                items.add(new MainActivity.Item(getResources().getString(R.string.oudeKerk), ResourcesCompat.getDrawable(getResources(), R.drawable.oude_kerk , null), null, DetailedActivity.Places.OudeKerk));
                items.add(new MainActivity.Item(getResources().getString(R.string.westerkerk), ResourcesCompat.getDrawable(getResources(), R.drawable.westerkerk , null), null, DetailedActivity.Places.Westerkerk));

                break;

            case Parks:
                text = getResources().getString(R.string.parksText);
                titleToolbarText = "Πάρκα";

                textForMaps = "parks";

                items.add(new MainActivity.Item(getResources().getString(R.string.vondelpark), ResourcesCompat.getDrawable(getResources(), R.drawable.vondelpark , null), null, DetailedActivity.Places.VondelPark));

                break;

            case Food:
                //toolBarLayout.setTitle(getResources().getString(R.string.food));
                text = getResources().getString(R.string.foodText);

//                textForMaps = "museums";

                break;

            case Drink:

                text = getResources().getString(R.string.drinkText);
                titleToolbarText = "Ποτό";

//                textForMaps = "museums";

                break;

            case Shopping:

                text = getResources().getString(R.string.shoppingText);
                titleToolbarText = "Ψώνια";

                items.add(new MainActivity.Item(getResources().getString(R.string.kalverstraat), ResourcesCompat.getDrawable(getResources(), R.drawable.kalverstraat , null), null, DetailedActivity.Places.Kalvestraat));

//                textForMaps = "museums";

                break;

            case Other:

                text = "";//getResources().getString(R.string.shoppingText);
                titleToolbarText = "Άλλα";

                items.add(new MainActivity.Item(getResources().getString(R.string.centralStation), ResourcesCompat.getDrawable(getResources(), R.drawable.centraal_station , null), null, DetailedActivity.Places.CentralStation));
                items.add(new MainActivity.Item(getResources().getString(R.string.adamLookout), ResourcesCompat.getDrawable(getResources(), R.drawable.adam_lookout , null), null, DetailedActivity.Places.AdamLookout));

                //textForMaps = "museums";

                break;

            case Neighborhood:

                text = "";//getResources().getString(R.string.shoppingText);
                titleToolbarText = "Γειτονιές";

                items.add(new MainActivity.Item(getResources().getString(R.string.jordaan), ResourcesCompat.getDrawable(getResources(), R.drawable.jordaan , null), null, DetailedActivity.Places.Jordaan));
                items.add(new MainActivity.Item(getResources().getString(R.string.museumQuarter), ResourcesCompat.getDrawable(getResources(), R.drawable.museum_quarter , null), null, DetailedActivity.Places.MuseumQuarter));
                items.add(new MainActivity.Item(getResources().getString(R.string.damSquare), ResourcesCompat.getDrawable(getResources(), R.drawable.dam_square , null), null, DetailedActivity.Places.DamSquare));

                //textForMaps = "museums";

                break;
        }

        labelTextView.setText(text);
        toolbar.setTitle(titleToolbarText);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        adapter.updateAdapter(items);

        initMapFab();
    }

    private void initMapFab() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_detailed_maps);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGoogleMaps();
            }
        });
    }

    private void openGoogleMaps() {
        try{
            Uri gmmIntentUri = Uri.parse("geo:52.36864099594794, 4.8970006533552635?q="+textForMaps);

            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

            mapIntent.setPackage("com.google.android.apps.maps");

            startActivity(mapIntent);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}