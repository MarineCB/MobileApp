package tp1.androidproject.lifequality;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import tp1.androidproject.lifequality.Fragments.SearchFragment;
import tp1.androidproject.lifequality.Utils.BottomNavigationBar;


public class SearchActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        BottomNavigationBar.initializeBottomNavBar(this, (BottomNavigationView)findViewById(R.id.navigation_bar),R.id.search);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new SearchFragment()).commit();
            }
            else if (extras.getBoolean("NotiClick")){
                ((BottomNavigationView)findViewById(R.id.navigation_bar)).setSelectedItemId(R.id.favorite);
            }

        }
    }

}
