package tp1.androidproject.lifequality.Fragments;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import tp1.androidproject.lifequality.Model.City;
import tp1.androidproject.lifequality.R;
import tp1.androidproject.lifequality.SearchActivity;
import tp1.androidproject.lifequality.Utils.Constants;

public class SettingsFragment extends Fragment {
    private View view;
    private Activity act;

    public SettingsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_settings, container, false);
        view.findViewById(R.id.delete_cities_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                City.deleteAll(City.class);
            }
        });
        return view;
    }
}
