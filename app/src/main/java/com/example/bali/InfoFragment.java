package com.example.bali;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bali.Adapters.ItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class InfoFragment extends Fragment {
    public enum Menu{
        Info,
        Places
    }

    Menu menu;

    List<PlaceItem> allItemsList = new ArrayList<>();

    RecyclerView recyclerView;
    ItemAdapter itemAdapter;

    public InfoFragment(){
        this.menu = Menu.Info;
    }

    public InfoFragment(Menu menu){
        this.menu = menu;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        recyclerView = view.findViewById(R.id.recyclerview_info);

        initializeRecyclerView();

        return view;
    }

    private void initializeRecyclerView(){

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        itemAdapter = new ItemAdapter((MainActivity) getActivity());
        recyclerView.setAdapter(itemAdapter);

        populateList();
    }

    private void populateList() {
        allItemsList.clear();

        if (menu.equals(Menu.Info)) {
            allItemsList.add(new PlaceItem(getContext(), R.string.flights_info, R.drawable.icon_flight, DetailedWithTitleActivity.GeneralPlaces.FlightsInfo, null));
            allItemsList.add(new PlaceItem(getContext(), R.string.hotels, R.drawable.icon_hotel, DetailedWithTitleActivity.GeneralPlaces.Hotels, null));
            allItemsList.add(new PlaceItem(getContext(), R.string.checklist, R.drawable.icon_checklist, DetailedWithTitleActivity.GeneralPlaces.Checklist, null));
            allItemsList.add(new PlaceItem(getContext(), R.string.language, R.drawable.icon_language, DetailedWithTitleActivity.GeneralPlaces.Language, null));
            allItemsList.add(new PlaceItem(getContext(), R.string.money, R.drawable.money, DetailedWithTitleActivity.GeneralPlaces.Money, null));
        } else {
            allItemsList.add(new PlaceItem(getContext(), R.string.attraction, R.drawable.forest, DetailedWithTitleActivity.GeneralPlaces.Attraction, null));
            allItemsList.add(new PlaceItem(getContext(), R.string.food, R.drawable.restaurant, DetailedWithTitleActivity.GeneralPlaces.Restaurant, null));
        }

        itemAdapter.updateAdapter(allItemsList);
    }
}
