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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DetailedWithTitleActivity extends AppCompatActivity {

    public enum GeneralPlaces {
        Museums,
        Language,
        FlightsInfo,
        Hotels,
        Checklist
    }

    String textForMaps = "";

    Boolean isMapsButtonHidden = false;
    boolean isEditMode = false;

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

                text = getResources().getString(R.string.flights_infoText);
                titleToolbarText = getResources().getString(R.string.flights_info);
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
        FloatingActionButton fabEdit = findViewById(R.id.fab_detailed_edit);

        if(fabMaps == null || fabEdit == null)
            return;

        fabMaps.setOnClickListener(view -> openGoogleMaps());
        fabEdit.setOnClickListener(view -> edit(fabEdit));

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

    private void edit(FloatingActionButton fabEdit) {
        if (!isEditMode) {//Edit
            fabEdit.setImageResource(R.drawable.save);

            labelTextView.setFocusable(true);
            labelTextView.setEnabled(true);
            labelTextView.setClickable(true);
            labelTextView.setFocusableInTouchMode(true);
        }
        else {//Save
            fabEdit.setImageResource(R.drawable.pencil);

            labelTextView.setFocusable(false);
            labelTextView.setEnabled(false);
            labelTextView.setClickable(false);
            labelTextView.setFocusableInTouchMode(false);

            labelTextView.setTextColor(-16777216);

            //Save
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(place.toString(), labelTextView.getText().toString());
            editor.apply();
        }

        isEditMode = !isEditMode;
    }
}