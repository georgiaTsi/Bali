package com.example.bali;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;

public class DetailedActivity extends AppCompatActivity {

    public enum Places {
        Tukies,
        ClearCafe,
        SimplySocial,
        Monkey,
        TanahLot,
        CekingRice,
        UlunDanu,
        TamanAyunTemple,
        TamanUjung,
        KerthaGosa
    };

    String location = "";

    SharedPreferences sharedPref;

    Places place;

    TextView textView;

    CollapsingToolbarLayout toolBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        FloatingActionButton fab = findViewById(R.id.fab_detailed_maps);
        fab.setOnClickListener(view -> openGoogleMaps());

        textView = findViewById(R.id.textview_text);

        place = (Places) getIntent().getExtras().get("place");

        toolBarLayout = findViewById(R.id.toolbar_layout);

        switch (place){

            case Tukies:

                addInfo(R.string.tukies, R.drawable.tukies, R.string.tukiesText, "-8.507248884296095, 115.2640412490699");

                break;

            case ClearCafe:

                addInfo(R.string.clearCafe, R.drawable.clear_cafe, R.string.clearCafeText, "-8.508990874527484, 115.26501990859427");

                break;

            case SimplySocial:
                addInfo(R.string.simplySocial, R.drawable.simply_social, R.string.simplySocialText, "-8.507547284461431, 115.26406675092223");

                break;

            case Monkey:
                addInfo(R.string.monkey, R.drawable.monkey, R.string.monkeyText, "-8.519094312613257, 115.26067569851755");

                break;

            case TanahLot:
                addInfo(R.string.tanahLot, R.drawable.tanah_lot, R.string.tanahLotText, "-8.6209943633093, 115.08678166705218");

                break;

            case CekingRice:
                addInfo(R.string.cekingRice, R.drawable.ceking_rice, R.string.cekingRiceText, "-8.431546700458268, 115.27930836433387");

                break;

            case UlunDanu:
                addInfo(R.string.ulunDanu, R.drawable.ulun_danu, R.string.ulunDanuText, "-8.274894007970275, 115.16685556138685");

                break;

            case TamanAyunTemple:
                addInfo(R.string.tamanAyunTemple, R.drawable.taman_ayun_temple, R.string.tamanAyunTempleText, "-8.541473143961893, 115.17256910898536");

                break;

            case TamanUjung:
                addInfo(R.string.tamanUjung, R.drawable.taman_ujung, R.string.tamanUjungText, "-8.462826381572405, 115.63070136145579");

                break;

            case KerthaGosa:
                addInfo(R.string.kerthaGosa, R.drawable.kertha_gosa, R.string.kerthaGosaText, "-8.53516448128748, 115.40337588199732");

                break;
        }

        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
    }

    private void addInfo(int title, int drawable, int text, String location) {
        toolBarLayout.setTitle(getResources().getString(title));
        toolBarLayout.setBackground(getDrawable(drawable));

        textView.setText(getResources().getString(text));

        this.location = location;
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