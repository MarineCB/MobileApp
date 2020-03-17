package tp1.androidproject.lifequality.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import tp1.androidproject.lifequality.R;
import tp1.androidproject.lifequality.RecyclerViewAdapter;

/**
 * Fragment of the Urban Area (UA) description : cities composing the UA
 * Inflated when the cities have been loaded
 * Visible when the card view, i.e. its container is clicked
 */

public class CitiesUaFragment extends Fragment {
    private ArrayList<String> cities;

    public CitiesUaFragment(ArrayList<String> cities) {
        this.cities = cities;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cities_ua, container, false);
        RecyclerView listCities = view.findViewById(R.id.cities_ua_list_rv);
        RecyclerViewAdapter<String> cityAdapter = new RecyclerViewAdapter<>(view.getContext(), R.layout.ua_cities_item, cities);
        listCities.setLayoutManager(new LinearLayoutManager(view.getContext()));
        listCities.setAdapter(cityAdapter);
        return view;
    }

}
