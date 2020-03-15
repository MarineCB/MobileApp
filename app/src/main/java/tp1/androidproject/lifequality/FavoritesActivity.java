package tp1.androidproject.lifequality;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import tp1.androidproject.lifequality.Constant.Constants;
import tp1.androidproject.lifequality.Model.City;
import tp1.androidproject.lifequality.Model.CityDisplay;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class FavoritesActivity extends AppCompatActivity {
    private ArrayList<CityDisplay> citiesFavorite = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        RecyclerView citiesList = findViewById(R.id.favorite_cities_list_rv);
     //   ArrayList<City> savedCities = City.getAllSavedCities();

        BottomNavigationBar.initializeBottomNavBar(getApplicationContext(), (BottomNavigationView)findViewById(R.id.navigation_bar),R.id.favorite);
        new LoadFavorites(new WeakReference<>(citiesList),
                new WeakReference<>(getApplicationContext()),
                        new WeakReference<>((TextView)findViewById(R.id.no_city_saved_tv))).execute();
    }
}


class LoadFavorites extends AsyncTask<String, CityDisplay, ArrayList<CityDisplay>> {
    private WeakReference<RecyclerView> listCitiesRef;
    private RecyclerView listCities;
    private RecyclerViewAdapter<CityDisplay> myAdapter;
    private WeakReference<Context> contextRef;
    private WeakReference<TextView> listEmptyRef;
    private ArrayList<City> savedCities;

    public LoadFavorites(WeakReference<RecyclerView> rvRef, WeakReference<Context> contextRef, WeakReference<TextView> listEmptyRef) {
        this.listCitiesRef = rvRef;
        this.contextRef = contextRef;
        this.listEmptyRef = listEmptyRef;
        this.savedCities = City.getAllSavedCities();
    }

    @Override
    protected void onPreExecute() {
        listCities = listCitiesRef.get();
        myAdapter = new RecyclerViewAdapter<>(contextRef.get(), R.layout.search_results_item);
        listCities.setLayoutManager(new LinearLayoutManager(contextRef.get()));
        listCities.setAdapter(myAdapter);
    }

    @Override
    protected void onProgressUpdate(CityDisplay... results) {
        myAdapter.add(results[0]);
        myAdapter.notifyItemInserted(myAdapter.getResultItems().size() - 1);
    }

    @Override
    protected ArrayList<CityDisplay> doInBackground(String... strings) {
        ArrayList<CityDisplay> listResults = myAdapter.getResultItems();
        Log.i("Favorite", savedCities.toString());
        for (City city : savedCities) {
            publishProgress(new CityDisplay(city.getName(), retrieveImgUrl(city.getName(),city.getCountry(), city.getAdminDivision()), city.getLocationUrl()));
        }
        return listResults;

    }

    private String retrieveImgUrl(String nameCity, String nameCountry, String adminDiv) {
        URL url;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL("https://www.flickr.com/services/feeds/photos_public.gne?tags=" + nameCity + ",%20" + nameCountry + ",%20" + adminDiv + "&format=json");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setUseCaches(false);
            urlConnection.connect();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            String s = readStream(in);
            s = s.substring(15, s.length() - 1);

            JSONObject json = new JSONObject(s);
            JSONArray array = json.getJSONArray("items");

            if (array != null && array.length() > 0)
                return array.getJSONObject(0).getJSONObject("media").getString("m");
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }

        return null;
    }


    private String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is), 1000);
        for (String line = r.readLine(); line != null; line = r.readLine()) {
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }

    @Override
    protected void onPostExecute(ArrayList<CityDisplay> results) {
        Context context = contextRef.get();
        TextView listEmpty = listEmptyRef.get();
        if (listCitiesRef != null) {
            if (results == null || results.size() == 0) {
                listEmpty.setVisibility(View.VISIBLE);
                listCities.setVisibility(View.INVISIBLE);
            } else {
                listEmpty.setVisibility(View.GONE);
                listCities.setVisibility(View.VISIBLE);


            }
        }
    }
}
