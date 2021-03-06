package tp1.androidproject.lifequality;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import tp1.androidproject.lifequality.Fragments.CityFragment;
import tp1.androidproject.lifequality.Utils.BottomNavigationBar;

/**
 * Activity created when a city has been chosen (from search or favorites)
 * First inflate the city fragament
 * Can later one contain the urban area fragment or else
 * Initialize the Bottom navigation bar by calling the class with the same name
 */
public class CityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        BottomNavigationBar.initializeBottomNavBar(this, (BottomNavigationView)findViewById(R.id.navigation_bar),R.id.search);

        Intent intent = getIntent();
        CityFragment frag = new CityFragment(intent.getStringExtra("chosenCityUrl"),intent.getStringExtra("chosenCityImg"));
        getSupportFragmentManager().beginTransaction().replace(R.id.container, frag).commit();

    }
}
