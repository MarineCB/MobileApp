package tp1.androidproject.lifequality;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class BottomNavigationBar {
    private static BottomNavigationBar instance;
    private static BottomNavigationView bar;
    
    public static void initializeBottomNavBar(final Context context, final BottomNavigationView bar, final int idSelected){

        // Selection par defaut : premiere activité
        bar.setSelectedItemId(idSelected);

        bar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.search:
                        Log.i("context",context+"");
                        Toast.makeText(context.getApplicationContext(),"Search selected",Toast.LENGTH_SHORT).show();
                        Intent searchAct = new Intent(context.getApplicationContext(), SearchActivity.class);
                        context.getApplicationContext().startActivity(searchAct);
                        return true;
                    case R.id.settings:
                        Toast.makeText(context.getApplicationContext(),"Settings selected",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.favorite:
                        Toast.makeText(context.getApplicationContext(),"Favorites selected",Toast.LENGTH_SHORT).show();
                        if(R.id.favorite != bar.getSelectedItemId()) {
                            Intent favoriteAct = new Intent(context.getApplicationContext(), FavoritesActivity.class);
                            context.getApplicationContext().startActivity(favoriteAct);
                            return true;
                        }
                }
                return false;
            }
        });
    }
/*
    public static BottomNavigationBar getInstance(AppCompatActivity act){
        if (instance == null){
           return new BottomNavigationBar(act);
        }
        else
            return instance;
    }*/
}
