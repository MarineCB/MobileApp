package tp1.androidproject.lifequality;

import android.content.Context;
import android.os.AsyncTask;
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
import tp1.androidproject.lifequality.Utils.Constants;
import tp1.androidproject.lifequality.Model.CityDisplay;

/**
 * Class extending AsynTask
 * Responsible for the loading of searched cities information and retrieving of pictures from flickr
 */
public class LoadResearch extends AsyncTask<String, CityDisplay, ArrayList<CityDisplay>> {
    private String searchUrl;
    private WeakReference<RecyclerView> listCitiesRef;
    private WeakReference<Context> contextRef;
    private WeakReference<TextView> wrongResearchRef;
    private RecyclerView listCities;
    private RecyclerViewAdapter<CityDisplay> myAdapter;

    public LoadResearch(WeakReference<RecyclerView> rvRef, String userInput, WeakReference<Context> contextRef, WeakReference<TextView> wrongResearchRef){
        this.listCitiesRef = rvRef;
        this.searchUrl = Constants.UrlCitySearch+userInput;
        this.contextRef = contextRef;
        this.wrongResearchRef = wrongResearchRef;
    }

    @Override
    protected  void onPreExecute(){
        listCities = listCitiesRef.get();
        myAdapter = new RecyclerViewAdapter<>(contextRef.get(), R.layout.search_results_item);
        listCities.setLayoutManager(new LinearLayoutManager(contextRef.get()));
        listCities.setAdapter(myAdapter);
        wrongResearchRef.get().setVisibility(View.GONE);
    }

    /**
     * Display each time a city is fully loaded
     */
    @Override
    protected void onProgressUpdate(CityDisplay... results){
        myAdapter.add(results[0]);
        myAdapter.notifyItemInserted(myAdapter.getResultItems().size()-1);
    }

    /**
     * Get the string result of the request
     * Convert it into JSon Object
     * Go through the JSON to retrieve the necessary information
     */
    @Override
    protected ArrayList<CityDisplay> doInBackground(String... strings) {
        URL url;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL(searchUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setUseCaches(false);
            urlConnection.connect();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            String rawJson = readStream(in);

            ArrayList<CityDisplay> listResults = myAdapter.getResultItems();
            JSONObject json = new JSONObject(rawJson);
            json = json.getJSONObject("_embedded");
            JSONArray array = json.getJSONArray("city:search-results");

            for(int i = 0; i<array.length();i++){
                JSONObject city_i = array.getJSONObject(i);
                String cityUrl = city_i.getJSONObject("_links").getJSONObject("city:item").getString( "href");
                String fullName = city_i.getString("matching_full_name");
                publishProgress(new CityDisplay(fullName, retrieveImgUrl(fullName),cityUrl));
            }

            return listResults;

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        } finally {
            if(urlConnection !=null)
                urlConnection.disconnect();
        }

        return null;
    }

    /**
     * Get the picture from Flickr with the name of the city (plus its country and administrative division)
     */
    private String retrieveImgUrl(String fullNameCity) {
        URL url;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL(Constants.FLICKR_URL+fullNameCity+"&format=json");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setUseCaches(false);
            urlConnection.connect();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            String s = readStream(in);
            s = s.substring(15,s.length()-1);

            JSONObject json = new JSONObject(s);
            JSONArray array = json.getJSONArray("items");

            if (array != null && array.length()>0)
                return array.getJSONObject(0).getJSONObject("media").getString("m");
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        } finally {
            if(urlConnection !=null)
                urlConnection.disconnect();
        }

        return null;
    }

    /**
     * Transform the stream coming from the Url into a string
     */
    private String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }

    /**
     * If no city has been found, will make a informative message visible, invisible otherwise
     */
    @Override
    protected void onPostExecute(ArrayList<CityDisplay> results){
        TextView wrongResearchTv = wrongResearchRef.get();
        if(listCitiesRef != null){
            if(results == null || results.size() == 0){
                wrongResearchTv.setVisibility(View.VISIBLE);
                listCities.setVisibility(View.INVISIBLE);
            }else {
                wrongResearchTv.setVisibility(View.GONE);
                listCities.setVisibility(View.VISIBLE);
            }
        }
    }
}