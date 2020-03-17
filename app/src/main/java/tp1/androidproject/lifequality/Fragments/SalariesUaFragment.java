package tp1.androidproject.lifequality.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import tp1.androidproject.lifequality.Model.Salary;
import tp1.androidproject.lifequality.R;
import tp1.androidproject.lifequality.RecyclerViewAdapter;


public class SalariesUaFragment extends Fragment {
    private ArrayList<Salary> salaries;

    public SalariesUaFragment(ArrayList<Salary> salaries) {
        this.salaries = salaries;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_salaries_ua, container, false);
        RecyclerView listSalaries = view.findViewById(R.id.salaries_ua_list_rv);
        RecyclerViewAdapter<Salary> salariesAdapter = new RecyclerViewAdapter<>(view.getContext(), R.layout.ua_salaries_item, salaries);
        listSalaries.setLayoutManager(new LinearLayoutManager(view.getContext()));
        listSalaries.setAdapter(salariesAdapter);
        return view;
    }

}
