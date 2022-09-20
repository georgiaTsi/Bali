package com.example.amsterdam;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DetailedWithTitleActivity extends AppCompatActivity {

    public enum GeneralPlaces {
        Museums,
        Palace,
        Churches,
        Parks,
        Food,
        Drink,
        Shopping,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detailed_with_title);

        TextView labelTextView = findViewById(R.id.textview_label);
        TextView titleTextView;// = findViewById(R.id.textview_toolbar_title);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        PlaceAdapter adapter = new PlaceAdapter(this);
        recyclerView.setAdapter(adapter);

        List<MainActivity.Item> items = new ArrayList<>();

        GeneralPlaces place = (GeneralPlaces) getIntent().getExtras().get("place");

        String text = "";
        String barText = "";

        switch (place) {
            case Museums:
                //toolBarLayout.setTitle(getResources().getString(R.string.museums));
                text = getResources().getString(R.string.museumsText);
                barText = "Μουσεία";

                items.add(new MainActivity.Item(getResources().getString(R.string.rijksmuseum), ResourcesCompat.getDrawable(getResources(), R.drawable.rijksmuseum, null), null, DetailedActivity.Places.Rijksmuseum));
                items.add(new MainActivity.Item(getResources().getString(R.string.vangogh), ResourcesCompat.getDrawable(getResources(), R.drawable.vangogh, null), null, DetailedActivity.Places.VanGogh));
                items.add(new MainActivity.Item(getResources().getString(R.string.stedelijk), ResourcesCompat.getDrawable(getResources(), R.drawable.stedelijk , null), null, DetailedActivity.Places.Stedelijk));

                break;

            case Palace:
                //toolBarLayout.setTitle(getResources().getString(R.string.palace));
                text = getResources().getString(R.string.palaceText);
                break;

            case Churches:
                //toolBarLayout.setTitle(getResources().getString(R.string.churches));
                text = getResources().getString(R.string.churchesText);
                break;

            case Parks:
                //toolBarLayout.setTitle(getResources().getString(R.string.parks));
                text = getResources().getString(R.string.parksText);
                break;

            case Food:
                //toolBarLayout.setTitle(getResources().getString(R.string.food));
                text = getResources().getString(R.string.foodText);
                break;

            case Drink:
                //toolBarLayout.setTitle(getResources().getString(R.string.drink));

                text = getResources().getString(R.string.drinkText);
                break;

            case Shopping:
                //toolBarLayout.setTitle(getResources().getString(R.string.shopping));

                text = getResources().getString(R.string.shoppingText);

                break;
        }

        labelTextView.setText(text);
        getSupportActionBar().setTitle(barText);

        adapter.updateAdapter(items);
    }
}