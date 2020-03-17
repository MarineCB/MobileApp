package tp1.androidproject.lifequality;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

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

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import tp1.androidproject.lifequality.Model.City;
import tp1.androidproject.lifequality.Model.CityDisplay;
import tp1.androidproject.lifequality.Utils.Constants;

/**
 * Class extending AsynTask
 * Responsible for the loading of favorites information and retrieving of picture from flickr
 */
public class LoadFavorites extends AsyncTask<String, CityDisplay, ArrayList<CityDisplay>> {
    private WeakReference<RecyclerView> listCitiesRef;
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
        RecyclerView listCities = listCitiesRef.get();
        myAdapter = new RecyclerViewAdapter<>(contextRef.get(), R.layout.search_results_item);
        listCities.setLayoutManager(new LinearLayoutManager(contextRef.get()));
        listCities.setAdapter(myAdapter);
    }

    /**
     * Display each time a city is fully loaded
     */
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

    /**
     * Get the picture from Flickr with the name of the city (plus its country and administrative division)
     */
    private String retrieveImgUrl(String nameCity, String nameCountry, String adminDiv) {
        URL url;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL(Constants.FLICKR_URL + nameCity + ", " + adminDiv + ", " + nameCountry + "&format=json");
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

    /**
     * Transform the stream coming from the Url into a string
     */
    private String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is), 1000);
        for (String line = r.readLine(); line != null; line = r.readLine()) {
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }

    /**
     * If no city has been found, will make a informative message visible, invisible otherwise
     */
    @Override
    protected void onPostExecute(ArrayList<CityDisplay> results) {
        TextView listEmpty = listEmptyRef.get();

        if (listCitiesRef != null) {
            RecyclerView listCities = listCitiesRef.get();
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

