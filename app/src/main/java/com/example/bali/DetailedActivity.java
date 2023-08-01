package com.example.bali;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class DetailedActivity extends AppCompatActivity {

    public enum Places {
        Tukies,
        ClearCafe,
        SimplySocial,
        Monkey,
        TanahLot
    };

    String location = "";

    SharedPreferences sharedPref;

    Places place;

    TextView textView;
    TextView labelTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab_detailed_maps);
        fab.setOnClickListener(view -> openGoogleMaps());

        labelTextView = findViewById(R.id.textview_info);
        textView = findViewById(R.id.textview_text);

        place = (Places) getIntent().getExtras().get("place");

        switch (place){

            case Tukies:
                labelTextView.setText(getResources().getString(R.string.tukies));
                textView.setText(getResources().getString(R.string.tukiesText));

                location = "-8.507248884296095, 115.2640412490699";

                break;

            case ClearCafe:
                labelTextView.setText(getResources().getString(R.string.clearCafe));
                textView.setText(getResources().getString(R.string.clearCafeText));

                location = "-8.508990874527484, 115.26501990859427";

                break;

            case SimplySocial:
                labelTextView.setText(getResources().getString(R.string.simplySocial));
                textView.setText(getResources().getString(R.string.simplySocialText));

                location = "-8.507547284461431, 115.26406675092223";

                break;

            case Monkey:
                labelTextView.setText(getResources().getString(R.string.monkey));
                textView.setText(getResources().getString(R.string.monkeyText));

                location = "-8.519094312613257, 115.26067569851755";

                break;

            case TanahLot:
                labelTextView.setText(getResources().getString(R.string.tanahLot));
                textView.setText(getResources().getString(R.string.tanahLotText));

                location = "-8.6209943633093, 115.08678166705218";

                break;
        }

        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
    }

    private void openGoogleMaps() {
        try{
            Uri gmmIntentUri = Uri.parse("geo:"+location);

            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

            mapIntent.setPackage("com.google.android.apps.maps");

            startActivity(mapIntent);
        }
        catch(Exception e){}
    }
}