package tp1.androidproject.lifequality;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


public class SearchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView citiesList = findViewById(R.id.places_list_rv);

        init(citiesList);


      //  requestPermissions();

    }

    private void requestPermissions() {
        if(Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(this, Manifest.permission_group.LOCATION) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission_group.LOCATION},100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                Intent location = new Intent(getApplicationContext(), NotificationService.class);
                startService(location);
            }
        }
    }

    private void init(final RecyclerView citiesList){
        SearchView searchArea = findViewById(R.id.location_search);
        searchArea.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                TextView wrongResearchTv = findViewById(R.id.result_info_tv);
                new ResearchLocation(new WeakReference<>(citiesList),query,
                        new WeakReference<>(getApplicationContext()), new WeakReference<>(wrongResearchTv)).execute();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });
    }
}
