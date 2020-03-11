package tp1.androidproject.lifequality;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import tp1.androidproject.lifequality.Model.Score;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class ScoresUaFragment extends Fragment {
    private ArrayList<Score> scores;

    public ScoresUaFragment(ArrayList<Score> scores) {
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
