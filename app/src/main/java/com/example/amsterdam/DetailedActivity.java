package com.example.amsterdam;

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
        Rijksmuseum,
        VanGogh,
        Stedelijk,
        CentralStation,
        MuseumRembrand,
        VondelPark,
        AdamLookout,
        Jordaan,
        MuseumQuarter,
        DamSquare,
        RoyalPalace,
        OudeKerk,
        Westerkerk,
        Kalvestraat
    };

    String location = "";

    boolean isEditMode = false;
    SharedPreferences sharedPref;

    Places place;

    TextView textView;
    TextView labelTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_detailed_maps);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGoogleMaps();
            }
        });

        initEditFab();

        labelTextView = findViewById(R.id.textview_info);
        textView = findViewById(R.id.textview_contentscrolling_text);

        place = (Places) getIntent().getExtras().get("place");

        String text = "";

        switch (place){

            case Rijksmuseum:
                toolBarLayout.setTitle(getResources().getString(R.string.rijksmuseum));
                toolBarLayout.setBackground(getDrawable(R.drawable.rijksmuseum));
                text = getResources().getString(R.string.rijksmuseumText);

                location = "52.36013906701168, 4.885200038169821";
                break;

            case VanGogh:
                toolBarLayout.setTitle(getResources().getString(R.string.vangogh));
                toolBarLayout.setBackground(getDrawable(R.drawable.vangogh));
                text = getResources().getString(R.string.vangoghText);

                location = "52.35857970757039, 4.8810970574423855";
                break;

            case Stedelijk:
                toolBarLayout.setTitle(getResources().getString(R.string.stedelijk));
                toolBarLayout.setBackground(getDrawable(R.drawable.stedelijk));
                text = getResources().getString(R.string.stedelijkText);

                location = "52.35814212708922, 4.87976612689848";
                break;

            case CentralStation:
                toolBarLayout.setTitle(getResources().getString(R.string.centralStation));
                toolBarLayout.setBackground(getDrawable(R.drawable.centraal_station));
                text = getResources().getString(R.string.centralStationText);

                location = "52.3792630069844, 4.900317496395518";
                break;

            case MuseumRembrand:
                toolBarLayout.setTitle(getResources().getString(R.string.museumRembrand));
                toolBarLayout.setBackground(getDrawable(R.drawable.museum_rembrand));
                text = getResources().getString(R.string.museumRembrandText);

                location = "52.36950654407767, 4.901234998243723";
                break;

            case VondelPark:
                toolBarLayout.setTitle(getResources().getString(R.string.vondelpark));
                toolBarLayout.setBackground(getDrawable(R.drawable.vondelpark));
                text = getResources().getString(R.string.vondelparkText);

                location = "52.358376263480885, 4.868565132992322";
                break;

            case AdamLookout:
                toolBarLayout.setTitle(getResources().getString(R.string.adamLookout));
                toolBarLayout.setBackground(getDrawable(R.drawable.adam_lookout));
                text = getResources().getString(R.string.adamLookoutText);

                location = "52.38402599593522, 4.902330769408753";
                break;

            case Jordaan:
                toolBarLayout.setTitle(getResources().getString(R.string.jordaan));
                toolBarLayout.setBackground(getDrawable(R.drawable.jordaan));
                text = getResources().getString(R.string.jordaanText);

                location = "52.37778879909396, 4.88116895216806";
                break;

            case MuseumQuarter:
                toolBarLayout.setTitle(getResources().getString(R.string.museumQuarter));
                toolBarLayout.setBackground(getDrawable(R.drawable.museum_quarter));
                text = getResources().getString(R.string.museumQuarterText);

                location = "52.35857970757039, 4.8810970574423855";
                break;

            case DamSquare:
                toolBarLayout.setTitle(getResources().getString(R.string.damSquare));
                toolBarLayout.setBackground(getDrawable(R.drawable.dam_square));
                text = getResources().getString(R.string.damSquareText);

                location = "52.37274837238484, 4.893008892698326";
                break;

            case RoyalPalace:
                toolBarLayout.setTitle(getResources().getString(R.string.royalPalace));
                toolBarLayout.setBackground(getDrawable(R.drawable.palace));
                text = getResources().getString(R.string.royalPalaceText);

                location = "52.37293457433286, 4.891375798243842";
                break;

            case OudeKerk:
                toolBarLayout.setTitle(getResources().getString(R.string.oudeKerk));
                toolBarLayout.setBackground(getDrawable(R.drawable.oude_kerk));
                text = getResources().getString(R.string.oudeKerkText);

                location = "52.38459118863662, 4.900387474427411";
                break;

            case Westerkerk:
                toolBarLayout.setTitle(getResources().getString(R.string.westerkerk));
                toolBarLayout.setBackground(getDrawable(R.drawable.westerkerk));
                text = getResources().getString(R.string.westerkerkText);

                location = "52.3746848277992, 4.8836379043433995";
                break;

            case Kalvestraat:
                toolBarLayout.setTitle(getResources().getString(R.string.kalverstraat));
                toolBarLayout.setBackground(getDrawable(R.drawable.kalverstraat));
                text = getResources().getString(R.string.kalverstraatText);

                location = "52.370872947612774, 4.891735207486195";
                break;
        }

        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String details = sharedPref.getString(place.toString(), text);
        textView.setText(details);
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

    private void initEditFab(){
        FloatingActionButton editFab = findViewById(R.id.fab_detailed_edit);
        editFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isEditMode){//Edit
                    editFab.setImageResource(R.drawable.save);

                    textView.setFocusable(true);
                    textView.setEnabled(true);
                    textView.setClickable(true);
                    textView.setFocusableInTouchMode(true);
                }
                else{//Save
                    editFab.setImageResource(R.drawable.pencil);

                    textView.setFocusable(false);
                    textView.setEnabled(false);
                    textView.setClickable(false);
                    textView.setFocusableInTouchMode(false);

                    textView.setTextColor(-16777216);

                    //Save
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString(place.toString(), textView.getText().toString());
                    editor.apply();
                }

                isEditMode = !isEditMode;
            }
        });
    }
}