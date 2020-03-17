package tp1.androidproject.lifequality.Utils;

import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import tp1.androidproject.lifequality.Fragments.FavoritesFragment;
import tp1.androidproject.lifequality.Fragments.SearchFragment;
import tp1.androidproject.lifequality.Fragments.SettingsFragment;
import tp1.androidproject.lifequality.R;

public class BottomNavigationBar {
    
    public static void initializeBottomNavBar(final AppCompatActivity act, final BottomNavigationView bar, final int idSelected){

        bar.setSelectedItemId(idSelected);

        bar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.search:
                        Toast.makeText(act.getApplicationContext(),"Search",Toast.LENGTH_SHORT).show();
                        SearchFragment frag = new SearchFragment();
                        act.getSupportFragmentManager().beginTransaction().replace(R.id.container,frag).commit();
                        return true;
                    case R.id.settings:
                        Toast.makeText(act.getApplicationContext(),"Settings",Toast.LENGTH_SHORT).show();
                        SettingsFragment settingFrag = new SettingsFragment();
                        act.getSupportFragmentManager().beginTransaction().replace(R.id.container,settingFrag).commit();
                        return true;
                    case R.id.favorite:
                        Toast.makeText(act.getApplicationContext(),"Favorites",Toast.LENGTH_SHORT).show();
                        FavoritesFragment favoritesFrag = new FavoritesFragment();
                        act.getSupportFragmentManager().beginTransaction().replace(R.id.container,favoritesFrag).commit();
                        return true;
                }
                return false;
            }
        });
    }
}
