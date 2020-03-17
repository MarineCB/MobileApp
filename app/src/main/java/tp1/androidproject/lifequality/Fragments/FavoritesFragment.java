package tp1.androidproject.lifequality.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import tp1.androidproject.lifequality.LoadFavorites;
import tp1.androidproject.lifequality.R;

public class FavoritesFragment extends Fragment {

    public FavoritesFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        RecyclerView citiesList = view.findViewById(R.id.favorite_cities_list_rv);

        new LoadFavorites(new WeakReference<>(citiesList),
                new WeakReference<>(getContext()),
                new WeakReference<>((TextView)view.findViewById(R.id.no_city_saved_tv))).execute();

        return view;
    }

}
