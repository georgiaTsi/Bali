package com.example.bali;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.info);
        bottomNavigationView.setItemIconTintList(null);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if(item.getTitle().equals("Info")){

            Fragment newFragment = new InfoFragment(InfoFragment.Menu.Info);

            transaction.replace(R.id.fragment_container_view, newFragment);
        }
        else if(item.getTitle().equals("Places")){

            Fragment newFragment = new InfoFragment(InfoFragment.Menu.Places);

            transaction.replace(R.id.fragment_container_view, newFragment);
        }
        else if(item.getTitle().equals("Notes")){

            Fragment newFragment = new NotesFragment();

            transaction.replace(R.id.fragment_container_view, newFragment);
        }

        transaction.addToBackStack(null);
        transaction.commit();

        return true;
    }
}