package tp1.androidproject.lifequality.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import tp1.androidproject.lifequality.Model.Score;
import tp1.androidproject.lifequality.R;
import tp1.androidproject.lifequality.RecyclerViewAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Fragment of the Urban Area (UA) description : Scores of the UA
 * Inflated when the info have been loaded
 * Visible when the card view, i.e. its container is clicked
 * Charge the recycler that it contains
 */

public class ScoresUaFragment extends Fragment {
    private ArrayList<Score> scores;

    ScoresUaFragment(ArrayList<Score> scores) {
        this.scores = scores;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scores_ua, container, false);
        RecyclerView listScores = view.findViewById(R.id.scores_ua_list_rv);
        RecyclerViewAdapter<Score> scoreAdapter = new RecyclerViewAdapter<>(view.getContext(), R.layout.ua_scores_item, scores);
        listScores.setLayoutManager(new LinearLayoutManager(view.getContext()));
        listScores.setAdapter(scoreAdapter);
        return view;
    }

}
