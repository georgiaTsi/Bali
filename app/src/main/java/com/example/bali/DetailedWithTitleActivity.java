package com.example.bali;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
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
        Brunch,
        Food,
        Dessert,
        Drink,
        Shopping,
        Other,
        Neighborhood,
        Language,
        FlightsInfo,
        Hotels,
        Checklist
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

        List<PlaceItem> items = new ArrayList<>();

        GeneralPlaces place = (GeneralPlaces) getIntent().getExtras().get("place");

        String text = "";
        String titleToolbarText = "";

        switch (place) {
            case Museums:
                text = getResources().getString(R.string.museumsText);
                titleToolbarText = "Μουσεία";

//                items.add(new PlaceItem(this, R.string.rijksmuseum, R.drawable.rijksmuseum, null, DetailedActivity.Places.Rijksmuseum));
//                items.add(new PlaceItem(this, R.string.vangogh, R.drawable.vangogh, null, DetailedActivity.Places.VanGogh));
//                items.add(new PlaceItem(this, R.string.stedelijk, R.drawable.stedelijk, null, DetailedActivity.Places.Stedelijk));
//                items.add(new PlaceItem(this, R.string.museumRembrand, R.drawable.museum_rembrand, null, DetailedActivity.Places.MuseumRembrand));

                textForMaps = "museums";

                break;

            case Hotels:
                text = getResources().getString(R.string.hotelsText);
                titleToolbarText = getResources().getString(R.string.hotels);

                break;

            /*case Churches:
                text = getResources().getString(R.string.churchesText);
                titleToolbarText = "Εκκλησίες";

                textForMaps = "churches";

                items.add(new PlaceItem(this, R.string.oudeKerk, R.drawable.oude_kerk, null, DetailedActivity.Places.OudeKerk));
                items.add(new PlaceItem(this, R.string.westerkerk, R.drawable.westerkerk, null, DetailedActivity.Places.Westerkerk));

                break;

            case Parks:
                text = getResources().getString(R.string.parksText);
                titleToolbarText = "Πάρκα";

                textForMaps = "parks";

                items.add(new PlaceItem(this, R.string.vondelpark, R.drawable.vondelpark, null, DetailedActivity.Places.VondelPark));

                break;

            case Brunch:
                //text = getResources().getString(R.string.parksText);
                titleToolbarText = "Brunch";

                textForMaps = "brunch";

                items.add(new PlaceItem(this, R.string.pluk, R.drawable.pluk, null, DetailedActivity.Places.Pluk));
                items.add(new PlaceItem(this, R.string.omelegg, R.drawable.omelegg, null, DetailedActivity.Places.Omelegg));
                items.add(new PlaceItem(this, R.string.breakfastClub, R.drawable.breakfast_club, null, DetailedActivity.Places.BreakfastClub));
                items.add(new PlaceItem(this, R.string.bakersAndRoasters, R.drawable.bakers_roasters, null, DetailedActivity.Places.BakersRoasters));
                items.add(new PlaceItem(this, R.string.greenwoods, R.drawable.greenwoods, null, DetailedActivity.Places.Greenwoods));
                items.add(new PlaceItem(this, R.string.staringAtJacob, R.drawable.staring_at_jacob, null, DetailedActivity.Places.StaringAtJacob));
                items.add(new PlaceItem(this, R.string.pancakeBakery, R.drawable.pancake_bakery, null, DetailedActivity.Places.PancakeBakery));
                items.add(new PlaceItem(this, R.string.scandinavianEmbassy, R.drawable.scandinavian_embassy7, null, DetailedActivity.Places.ScandinavianEmbassy));
                items.add(new PlaceItem(this, R.string.littleCoins, R.drawable.little_coins, null, DetailedActivity.Places.LittleCoins));
                items.add(new PlaceItem(this, R.string.bakhuysAmsterdam, R.drawable.bakhuys_amsterdam, null, DetailedActivity.Places.BakhuysAmsterdam));

                break;

            case Food:
                //toolBarLayout.setTitle(getResources().getString(R.string.food));
                text = getResources().getString(R.string.foodText);

//                textForMaps = "museums";

                break;

            case Dessert:

                titleToolbarText = "Γλυκά";

                textForMaps = "dessert";

                items.add(new PlaceItem(this, R.string.vanStapele, R.drawable.van_stapele, null, DetailedActivity.Places.VanStapele));

                break;

            case Drink:

                text = getResources().getString(R.string.drinkText);
                titleToolbarText = "Ποτό";

                items.add(new PlaceItem(this, R.string.xtracoldIcebar, R.drawable.xtracold, null, DetailedActivity.Places.XtraCold));

                textForMaps = "drink";

                break;

            case Shopping:

                text = getResources().getString(R.string.shoppingText);
                titleToolbarText = "Ψώνια";

                items.add(new PlaceItem(this, R.string.kalverstraat, R.drawable.kalverstraat, null, DetailedActivity.Places.Kalvestraat));
                items.add(new PlaceItem(this, R.string.nineStreets, R.drawable.nine_streets, null, DetailedActivity.Places.NineStreets));
                items.add(new PlaceItem(this, R.string.albertCuyp, R.drawable.albert_cuyp, null, DetailedActivity.Places.AlbertCuyp));

                textForMaps = "markets";

                break;

            case Other:

                text = "";
                titleToolbarText = "Άλλα";

                items.add(new PlaceItem(this, R.string.centralStation, R.drawable.centraal_station, null, DetailedActivity.Places.CentralStation));
                items.add(new PlaceItem(this, R.string.adamLookout, R.drawable.adam_lookout, null, DetailedActivity.Places.AdamLookout));
                items.add(new PlaceItem(this, R.string.nemoRooftop, R.drawable.nemo, null, DetailedActivity.Places.Nemo));

                break;

            case Neighborhood:

                text = "";//getResources().getString(R.string.shoppingText);
                titleToolbarText = "Γειτονιές";

                items.add(new PlaceItem(this, R.string.jordaan, R.drawable.jordaan, null, DetailedActivity.Places.Jordaan));
                items.add(new PlaceItem(this, R.string.museumQuarter, R.drawable.museum_quarter, null, DetailedActivity.Places.MuseumQuarter));
                items.add(new PlaceItem(this, R.string.damSquare, R.drawable.dam_square, null, DetailedActivity.Places.DamSquare));

                //textForMaps = ;

                break;*/

            case Language:

                text = getResources().getString(R.string.languageText);
                titleToolbarText = getResources().getString(R.string.language);

                break;

            case FlightsInfo:

                text = getResources().getString(R.string.flights_infoText);
                titleToolbarText = getResources().getString(R.string.flights_info);

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