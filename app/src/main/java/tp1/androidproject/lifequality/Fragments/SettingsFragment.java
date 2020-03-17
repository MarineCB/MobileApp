package tp1.androidproject.lifequality.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import tp1.androidproject.lifequality.Model.City;
import tp1.androidproject.lifequality.R;

/**
 * Fragment of Settings
 * Inflated when the settings icon has been clicked on the navigation bar
 */

public class SettingsFragment extends Fragment {

    public SettingsFragment() {
    }

    /**
     * Set the listener of the button to delete all saved cities when clicked
     * Inform the user of the result
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        view.findViewById(R.id.delete_cities_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                City.deleteAll(City.class);
                Toast.makeText(getContext(),"Cities Deleted",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
