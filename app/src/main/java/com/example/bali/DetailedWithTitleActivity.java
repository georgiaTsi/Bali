package com.example.bali;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bali.Adapters.FlightAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DetailedWithTitleActivity extends AppCompatActivity {

    public enum GeneralPlaces {
        Museums,
        Language,
        FlightsInfo,
        Hotels,
        Checklist,
        Restaurant,
        Attraction
    }

    String textForMaps = "";

    Boolean isMapsButtonHidden = false;

    TextView labelTextView;

    GeneralPlaces place;

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detailed_with_title);

        labelTextView = findViewById(R.id.textview_label);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        Toolbar toolbar = findViewById(R.id.toolbar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        PlaceAdapter adapter = new PlaceAdapter(this);
        recyclerView.setAdapter(adapter);

        List<PlaceItem> items = new ArrayList<>();

        place = (GeneralPlaces) getIntent().getExtras().get("place");

        String text = "";
        String titleToolbarText = "";

        switch (place) {
            case Museums:
                //text = getResources().getString(R.string.museumsText);
                titleToolbarText = "Μουσεία";

//                items.add(new PlaceItem(this, R.string.rijksmuseum, R.drawable.rijksmuseum, null, DetailedActivity.Places.Rijksmuseum));
//                items.add(new PlaceItem(this, R.string.vangogh, R.drawable.vangogh, null, DetailedActivity.Places.VanGogh));
//                items.add(new PlaceItem(this, R.string.stedelijk, R.drawable.stedelijk, null, DetailedActivity.Places.Stedelijk));
//                items.add(new PlaceItem(this, R.string.museumRembrand, R.drawable.museum_rembrand, null, DetailedActivity.Places.MuseumRembrand));

                textForMaps = "museums";

                break;

            case Hotels:
                setContentView(R.layout.activity_hotels);

                buttonWithLink(findViewById(R.id.imagebutton_hotels_ubudmap), getResources().getString(R.string.hotel_ubud_map));
                buttonWithLink(findViewById(R.id.imagebutton_hotels_ubudlink), getResources().getString(R.string.hotel_ubud_link));

                buttonWithLink(findViewById(R.id.imagebutton_hotels_uluwatumap), getResources().getString(R.string.hotel_uluwatu_map));
                buttonWithLink(findViewById(R.id.imagebutton_hotels_uluwatulink), getResources().getString(R.string.hotel_uluwatu_link));

                buttonWithLink(findViewById(R.id.imagebutton_hotels_singaporemap), getResources().getString(R.string.hotel_singapore_map));
                buttonWithLink(findViewById(R.id.imagebutton_hotels_singaporelink), getResources().getString(R.string.hotel_singapore_link));

                openWhatsApp(findViewById(R.id.imagebutton_hotels_ubudwhatsapp), getResources().getString(R.string.hotel_ubud_phone));
                openWhatsApp(findViewById(R.id.imagebutton_hotels_uluwatuwhatsapp), getResources().getString(R.string.hotel_uluwatu_phone));

                findViewById(R.id.imagebutton_hotels_singaporewhatsapp).setOnClickListener(v -> {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:"+getResources().getString(R.string.hotel_singapore_phone)));
                    startActivity(callIntent);
                });

                isMapsButtonHidden = true;

                break;

            case Language:

                text = getResources().getString(R.string.languageText);
                titleToolbarText = getResources().getString(R.string.language);
                isMapsButtonHidden = true;

                break;

            case FlightsInfo:

                setContentView(R.layout.fragment_flights);

                FlightAdapter adapter1 = new FlightAdapter();

                RecyclerView recyclerView1 = findViewById(R.id.flights_recyclerview);
                recyclerView1.setLayoutManager(new LinearLayoutManager(this));
                recyclerView1.setAdapter(adapter1);

                List<FlightInfo> list = new ArrayList<>();

                FlightInfo flightInfo = new FlightInfo();
                flightInfo.label = "ΑΘήνα - Ντενπασάρ";
                flightInfo.calendar = ResourcesCompat.getDrawable(getResources(), R.drawable.calendar_9, null);
                flightInfo.from = "Από: Athens Greece";
                flightInfo.to = "Προς: Istanbul Airport Turkey";
                flightInfo.time = "21:45 - 23:15";
                flightInfo.flightInfo = "Πτήση: TK1846";
                flightInfo.from1 = "Από: Istanbul Airport Turkey";
                flightInfo.to1 = "Προς: Denpasar Bali Indonesia";
                flightInfo.time1 = "01:50 - 19:15";
                flightInfo.flightInfo1 = "Πτήση: TK66";

                list.add(flightInfo);
                adapter1.updateAdapter(list);

                isMapsButtonHidden = true;

                break;

            case Restaurant:
                titleToolbarText = "Φαγητό";

                items.add(new PlaceItem(this, R.string.tukies, R.drawable.tukies, null, DetailedActivity.Places.Tukies));
                items.add(new PlaceItem(this, R.string.clearCafe, R.drawable.clear_cafe, null, DetailedActivity.Places.ClearCafe));
                items.add(new PlaceItem(this, R.string.simplySocial, R.drawable.simply_social, null, DetailedActivity.Places.SimplySocial));

                isMapsButtonHidden = true;

                break;

            case Attraction:
                titleToolbarText = "Αξιοθέατα";

                items.add(new PlaceItem(this, R.string.monkey, R.drawable.monkey, null, DetailedActivity.Places.Monkey));
                items.add(new PlaceItem(this, R.string.cekingRice, R.drawable.ceking_rice, null, DetailedActivity.Places.CekingRice));
                items.add(new PlaceItem(this, R.string.tanahLot, R.drawable.tanah_lot, null, DetailedActivity.Places.TanahLot));
                items.add(new PlaceItem(this, R.string.ulunDanu, R.drawable.ulun_danu, null, DetailedActivity.Places.UlunDanu));

                isMapsButtonHidden = true;

                break;
        }

        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String details = sharedPref.getString(place.toString(), text);

        labelTextView.setText(details);
        toolbar.setTitle(titleToolbarText);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        adapter.updateAdapter(items);

        initFabs();
    }

    private void openWhatsApp(View ViewById, String String) {
        ViewById.setOnClickListener(v -> {
            String url = "https://api.whatsapp.com/send?phone=" + String;
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        });
    }

    private void buttonWithLink(ImageButton ViewById, String String) {

        ViewById.setOnClickListener(v -> {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(String)));
            }
            catch (ActivityNotFoundException e){
                Toast.makeText(DetailedWithTitleActivity.this, "No app found for handle this Url", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initFabs() {

        FloatingActionButton fabMaps = findViewById(R.id.fab_detailed_maps);

        if(fabMaps == null)
            return;

        fabMaps.setOnClickListener(view -> openGoogleMaps());

        if (isMapsButtonHidden)
            fabMaps.setVisibility(View.INVISIBLE);
    }

    private void openGoogleMaps() {
        try {
            Uri gmmIntentUri = Uri.parse("geo:52.36864099594794, 4.8970006533552635?q=" + textForMaps);

            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

            mapIntent.setPackage("com.google.android.apps.maps");

            startActivity(mapIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}