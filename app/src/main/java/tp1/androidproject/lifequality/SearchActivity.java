package tp1.androidproject.lifequality;

import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import tp1.androidproject.lifequality.Fragments.SearchFragment;
import tp1.androidproject.lifequality.Utils.BottomNavigationBar;

/**
 * First activity to be launched
 * Will launch the Favorites fragment if the extra contains the right boolean => Means that the activity is started from the notification
 * Launch the search fragment otherwise
 * Initialize the Bottom navigation bar by calling the class with the same name
 */
public class SearchActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        BottomNavigationBar.initializeBottomNavBar(this, (BottomNavigationView)findViewById(R.id.navigation_bar),R.id.search);
        Bundle extras = getIntent().getExtras();
            if (extras !=null && extras.getBoolean("NotiClick"))
                ((BottomNavigationView)findViewById(R.id.navigation_bar)).setSelectedItemId(R.id.favorite);
            else
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new SearchFragment()).commit();
    }

}
