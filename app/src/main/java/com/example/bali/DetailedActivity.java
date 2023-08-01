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
        Kalvestraat,
        Pluk,
        Omelegg,
        BreakfastClub,
        BakersRoasters,
        Greenwoods,
        StaringAtJacob,
        PancakeBakery,
        ScandinavianEmbassy,
        LittleCoins,
        BakhuysAmsterdam,
        VanStapele,
        Nemo,
        NineStreets,
        AlbertCuyp,
        XtraCold,
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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab_detailed_maps);
        fab.setOnClickListener(view -> openGoogleMaps());

        initEditFab();

        labelTextView = findViewById(R.id.textview_info);
        textView = findViewById(R.id.textview_contentscrolling_text);

        place = (Places) getIntent().getExtras().get("place");

        String text = "";

        switch (place){

            /*case Rijksmuseum:
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

            case Pluk:
                toolBarLayout.setTitle(getResources().getString(R.string.pluk));
                toolBarLayout.setBackground(getDrawable(R.drawable.pluk));
                text = getResources().getString(R.string.plukText);

                location = "52.37040756456403, 4.883426779276959";
                break;

            case Omelegg:
                toolBarLayout.setTitle(getResources().getString(R.string.omelegg));
                toolBarLayout.setBackground(getDrawable(R.drawable.omelegg));
                text = getResources().getString(R.string.omeleggText);

                location = "52.351871594244095, 4.89152194251497";
                break;

            case BakersRoasters:
                toolBarLayout.setTitle(getResources().getString(R.string.bakersAndRoasters));
                toolBarLayout.setBackground(getDrawable(R.drawable.bakers_roasters));
                text = getResources().getString(R.string.bakersAndRoastersTest);

                location = "52.35747836439564, 4.889934115532199";
                break;

            case Greenwoods:
                toolBarLayout.setTitle(getResources().getString(R.string.greenwoods));
                toolBarLayout.setBackground(getDrawable(R.drawable.greenwoods));
                text = getResources().getString(R.string.greenwoodsText);

                location = "52.37792379105342, 4.891379218339655";
                break;

            case BreakfastClub:
                toolBarLayout.setTitle(getResources().getString(R.string.breakfastClub));
                toolBarLayout.setBackground(getDrawable(R.drawable.breakfast_club));
                text = getResources().getString(R.string.breakfastClubText);

                location = "52.35872221820171, 4.909746935629784";
                break;

            case StaringAtJacob:
                toolBarLayout.setTitle(getResources().getString(R.string.staringAtJacob));
                toolBarLayout.setBackground(getDrawable(R.drawable.staring_at_jacob));
                text = getResources().getString(R.string.staringAtJacobText);

                location = "52.36244756605894, 4.861836369375937";
                break;

            case PancakeBakery:
                toolBarLayout.setTitle(getResources().getString(R.string.pancakeBakery));
                toolBarLayout.setBackground(getDrawable(R.drawable.pancake_bakery));
                text = getResources().getString(R.string.pancakeBakeryText);

                location = "52.37773145381351, 4.886213527180256";
                break;

            case ScandinavianEmbassy:
                toolBarLayout.setTitle(getResources().getString(R.string.scandinavianEmbassy));
                toolBarLayout.setBackground(getDrawable(R.drawable.scandinavian_embassy7));
                text = getResources().getString(R.string.scandinavianEmpassyText);

                location = "52.35566093955727, 4.8952074384768";
                break;

            case LittleCoins:
                toolBarLayout.setTitle(getResources().getString(R.string.littleCoins));
                toolBarLayout.setBackground(getDrawable(R.drawable.little_coins));
                text = getResources().getString(R.string.littleCoinsText);

                location = "52.35909871751138, 4.89822396024936";
                break;

            case BakhuysAmsterdam:
                toolBarLayout.setTitle(getResources().getString(R.string.bakhuysAmsterdam));
                toolBarLayout.setBackground(getDrawable(R.drawable.bakhuys_amsterdam));
                text = getResources().getString(R.string.bakhuysAmsterdamText);

                location = "52.36125928997446, 4.906967888358872";
                break;

            case VanStapele:
                toolBarLayout.setTitle(getResources().getString(R.string.vanStapele));
                toolBarLayout.setBackground(getDrawable(R.drawable.van_stapele));
                text = getResources().getString(R.string.vanStapeleText);

                location = "52.36899556588311, 4.888519644195818";
                break;

            case Nemo:
                toolBarLayout.setTitle(getResources().getString(R.string.nemoRooftop));
                toolBarLayout.setBackground(getDrawable(R.drawable.nemo));
                text = getResources().getString(R.string.nemoRooftopText);

                location = "52.37390978001664, 4.912381413717868";
                break;

            case NineStreets:
                toolBarLayout.setTitle(getResources().getString(R.string.nineStreets));
                toolBarLayout.setBackground(getDrawable(R.drawable.nine_streets));
                text = getResources().getString(R.string.nineStreetsText);

                location = "52.36907019770731, 4.883229427209277";
                break;

            case AlbertCuyp:
                toolBarLayout.setTitle(getResources().getString(R.string.albertCuyp));
                toolBarLayout.setBackground(getDrawable(R.drawable.albert_cuyp));
                text = getResources().getString(R.string.albertCuypText);

                location = "52.356124543776325, 4.8953870982515095";
                break;

            case XtraCold:
                toolBarLayout.setTitle(getResources().getString(R.string.xtracoldIcebar));
                toolBarLayout.setBackground(getDrawable(R.drawable.xtracold));
                text = getResources().getString(R.string.xtracoldIcebarText);

                location = "52.36660895534379, 4.90007864243961";
                break;*/
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
        editFab.setOnClickListener(view -> {
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
        });
    }
}