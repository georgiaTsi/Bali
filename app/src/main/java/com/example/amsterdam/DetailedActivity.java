package com.example.amsterdam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import com.example.amsterdam.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailedActivity extends AppCompatActivity {

    public enum Places {
        Rijksmuseum,
        VanGogh,
        Stedelijk
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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
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

                //location = "51.51010481778367,-0.13452973304280164";
                break;

            case VanGogh:
                toolBarLayout.setTitle(getResources().getString(R.string.vangogh));
                toolBarLayout.setBackground(getDrawable(R.drawable.vangogh));
                text = getResources().getString(R.string.vangoghText);

                //location = "51.51010481778367,-0.13452973304280164";
                break;

            case Stedelijk:
                toolBarLayout.setTitle(getResources().getString(R.string.stedelijk));
                toolBarLayout.setBackground(getDrawable(R.drawable.stedelijk));
                text = getResources().getString(R.string.stedelijkText);

                //location = "51.51010481778367,-0.13452973304280164";
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