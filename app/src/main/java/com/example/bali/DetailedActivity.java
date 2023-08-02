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
        TanahLot,
        CekingRice,
        UlunDanu
    };

    String location = "";

    SharedPreferences sharedPref;

    Places place;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        FloatingActionButton fab = findViewById(R.id.fab_detailed_maps);
        fab.setOnClickListener(view -> openGoogleMaps());

        textView = findViewById(R.id.textview_text);

        place = (Places) getIntent().getExtras().get("place");

        CollapsingToolbarLayout toolBarLayout = findViewById(R.id.toolbar_layout);

        switch (place){

            case Tukies:

                toolBarLayout.setTitle(getResources().getString(R.string.tukies));
                toolBarLayout.setBackground(getDrawable(R.drawable.tukies));

                textView.setText(getResources().getString(R.string.tukiesText));

                location = "-8.507248884296095, 115.2640412490699";

                break;

            case ClearCafe:

                toolBarLayout.setTitle(getResources().getString(R.string.clearCafe));
                toolBarLayout.setBackground(getDrawable(R.drawable.clear_cafe));

                textView.setText(getResources().getString(R.string.clearCafeText));

                location = "-8.508990874527484, 115.26501990859427";

                break;

            case SimplySocial:
                toolBarLayout.setTitle(getResources().getString(R.string.simplySocial));
                toolBarLayout.setBackground(getDrawable(R.drawable.simply_social));

                textView.setText(getResources().getString(R.string.simplySocialText));

                location = "-8.507547284461431, 115.26406675092223";

                break;

            case Monkey:
                toolBarLayout.setTitle(getResources().getString(R.string.monkey));
                toolBarLayout.setBackground(getDrawable(R.drawable.monkey));

                textView.setText(getResources().getString(R.string.monkeyText));

                location = "-8.519094312613257, 115.26067569851755";

                break;

            case TanahLot:
                toolBarLayout.setTitle(getResources().getString(R.string.tanahLot));
                toolBarLayout.setBackground(getDrawable(R.drawable.tanah_lot));

                textView.setText(getResources().getString(R.string.tanahLotText));

                location = "-8.6209943633093, 115.08678166705218";

                break;

            case CekingRice:
                toolBarLayout.setTitle(getResources().getString(R.string.cekingRice));
                toolBarLayout.setBackground(getDrawable(R.drawable.ceking_rice));

                textView.setText(getResources().getString(R.string.cekingRiceText));

                location = "-8.431546700458268, 115.27930836433387";

                break;

            case UlunDanu:
                toolBarLayout.setTitle(getResources().getString(R.string.ulunDanu));
                toolBarLayout.setBackground(getDrawable(R.drawable.ulun_danu));

                textView.setText(getResources().getString(R.string.ulunDanuText));

                location = "-8.274894007970275, 115.16685556138685";

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