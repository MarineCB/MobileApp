package tp1.androidproject.lifequality;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView citiesList = findViewById(R.id.places_list_rv);

        init(citiesList);
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
