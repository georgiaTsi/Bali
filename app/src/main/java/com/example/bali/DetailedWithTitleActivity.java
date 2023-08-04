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

                FlightAdapter adapter1 = new FlightAdapter(this);

                RecyclerView recyclerView1 = findViewById(R.id.flights_recyclerview);
                recyclerView1.setLayoutManager(new LinearLayoutManager(this));
                recyclerView1.setAdapter(adapter1);

                List<FlightInfo> list = new ArrayList<>();

                FlightInfo flightInfoFirst = new FlightInfo();
                flightInfoFirst.label = "ΑΘήνα - Ντενπασάρ";
                flightInfoFirst.calendar = ResourcesCompat.getDrawable(getResources(), R.drawable.calendar_9, null);
                flightInfoFirst.from = "Από: Athens Greece";
                flightInfoFirst.to = "Προς: Istanbul Airport Turkey";
                flightInfoFirst.time = "21:45 - 23:15";
                flightInfoFirst.flightInfo = "Πτήση: TK1846";
                flightInfoFirst.from1 = "Από: Istanbul Airport Turkey";
                flightInfoFirst.to1 = "Προς: Denpasar Bali Indonesia";
                flightInfoFirst.time1 = "01:50 - 19:15";
                flightInfoFirst.flightInfo1 = "Πτήση: TK66";
                flightInfoFirst.moreInfo = "Διάρκεια ταξιδιού 16 ώρες και 30 λεπτά\n\nΑναμονή 2 ώρες και 35 λεπτά\n\nTurkish Airlines\nΑποσκευή 30 κιλών και χειραποσκευή 8 κιλών (55x40x23 cm)";
                list.add(flightInfoFirst);

                FlightInfo flightInfoSecond = new FlightInfo();
                flightInfoSecond.label = "Ντενπασάρ - Σιγκαπούρη";
                flightInfoSecond.calendar = ResourcesCompat.getDrawable(getResources(), R.drawable.calendar_21, null);
                flightInfoSecond.from = "Από: Ngurah Rai Airport I";
                flightInfoSecond.to = "Προς: Singapore Changi Airport T3";
                flightInfoSecond.time = "07:20 - 10:00";
                flightInfoSecond.flightInfo = "Πτήση: SQ949";
                flightInfoSecond.moreInfo = "Διάρκεια ταξιδιού 2 ώρες και 40 λεπτά\n\nSingapore Airlines\nΑποσκευή 25 κιλών(length+width+height μέχρι 158 cm) και χειραποσκευή 7 κιλών(length+width+height μέχρι 115 cm)";
                list.add(flightInfoSecond);

                FlightInfo flightInfoThird = new FlightInfo();
                flightInfoThird.label = "Σιγκαπούρη - Αθήνα";
                flightInfoThird.calendar = ResourcesCompat.getDrawable(getResources(), R.drawable.calendar_25, null);
                flightInfoThird.from = "Από: Singapore Changi Αεροσταθμός (Terminal) 1";
                flightInfoThird.to = "Προς: Istanbul Airport Turkey";
                flightInfoThird.time = "10:40 - 16:45";
                flightInfoThird.flightInfo = "Πτήση: TK209";
                flightInfoThird.from1 = "Από: Istanbul Airport Turkey";
                flightInfoThird.to1 = "Προς: Athens Greece";
                flightInfoThird.time1 = "18:50 - 20:15";
                flightInfoThird.flightInfo1 = "Πτήση: TK1845";
                flightInfoThird.moreInfo = "Διάρκεια ταξιδιού 14 ώρες και 35 λεπτά\n\nΑναμονή 2 ώρες και 5 λεπτά\n\nTurkish Airlines\nΑποσκευή 30 κιλών και χειραποσκευή 8 κιλών (55x40x23 cm)";
                list.add(flightInfoThird);

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
                items.add(new PlaceItem(this, R.string.tamanAyunTemple, R.drawable.taman_ayun_temple, null, DetailedActivity.Places.TamanAyunTemple));
                items.add(new PlaceItem(this, R.string.tamanUjung, R.drawable.taman_ujung, null, DetailedActivity.Places.TamanUjung));
                items.add(new PlaceItem(this, R.string.kerthaGosa, R.drawable.kertha_gosa, null, DetailedActivity.Places.KerthaGosa));

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