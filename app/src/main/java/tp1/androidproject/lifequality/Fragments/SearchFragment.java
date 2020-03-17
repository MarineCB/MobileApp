package tp1.androidproject.lifequality.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import tp1.androidproject.lifequality.LoadResearch;
import tp1.androidproject.lifequality.R;

/**
 * Fragment of the Urban Area (UA) description : cities composing the UA
 * Inflated when the cities have been loaded
 * Visible when the card view, i.e. its container is clicked
 * Charge the recycler that it contains
 */

public class SearchFragment extends Fragment {
    private View view;

    public SearchFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);
        RecyclerView citiesList = view.findViewById(R.id.places_list_rv);
        init(citiesList);
        return view;
    }

    /**
     * Initialize the SearchView et set the listener
     * Will start the search of the user's query through the LoadResearch class when the query is submitted
     */
    private void init(final RecyclerView citiesList){
        SearchView searchArea = view.findViewById(R.id.location_search);
        searchArea.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                TextView wrongResearchTv = view.findViewById(R.id.result_info_tv);
                new LoadResearch(new WeakReference<>(citiesList),query,
                        new WeakReference<>(view.getContext()), new WeakReference<>(wrongResearchTv)).execute();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });
    }

}
