package com.example.bali;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class InfoFragment extends Fragment {
    List<PlaceItem> allItemsList = new ArrayList<>();

    RecyclerView recyclerView;
    ItemAdapter itemAdapter;

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

    private void populateList(){
        allItemsList.add(new PlaceItem(getContext(), R.string.flights_info, R.drawable.icon_flight, DetailedWithTitleActivity.GeneralPlaces.FlightsInfo, null));
        allItemsList.add(new PlaceItem(getContext(), R.string.hotels, R.drawable.icon_hotel, DetailedWithTitleActivity.GeneralPlaces.Hotels, null));
        allItemsList.add(new PlaceItem(getContext(), R.string.checklist, R.drawable.icon_checklist, DetailedWithTitleActivity.GeneralPlaces.Checklist, null));
        allItemsList.add(new PlaceItem(getContext(), R.string.language, R.drawable.icon_language, DetailedWithTitleActivity.GeneralPlaces.Language, null));

        itemAdapter.updateAdapter(allItemsList);
    }
}
