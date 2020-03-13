package tp1.androidproject.lifequality;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class BottomNavigationBar {
    private static BottomNavigationBar instance;
    private static BottomNavigationView bar;
    
    private BottomNavigationBar(final AppCompatActivity act){
        bar = act.findViewById(R.id.navigation_bar);

        // Selection par defaut : premiere activité
        bar.setSelectedItemId(R.id.search);

        bar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.search:
                        Toast.makeText(act.getApplicationContext(),"Search selected",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.settings:
                        Toast.makeText(act.getApplicationContext(),"Settings selected",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.favorite:
                        Toast.makeText(act.getApplicationContext(),"Favorites selected",Toast.LENGTH_SHORT).show();
                        Intent favoriteAct = new Intent(act.getApplicationContext(), FavoritesActivity.class);
                        act.getApplicationContext().startActivity(favoriteAct);
                        return true;
                }
                return false;
            }
        });
    }

    public static BottomNavigationBar getInstance(AppCompatActivity act){
        if (instance == null){
           return new BottomNavigationBar(act);
        }
        else
            return instance;
    }
}
