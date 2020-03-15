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
import tp1.androidproject.lifequality.Constant.Constants;
import tp1.androidproject.lifequality.Model.CityDisplay;

public class ResearchLocation extends AsyncTask<String, CityDisplay, ArrayList<CityDisplay>> {
    private String searchUrl;
    private WeakReference<RecyclerView> listCitiesRef;
    private WeakReference<Context> contextRef;
    private WeakReference<TextView> wrongResearchRef;
    private RecyclerView listCities;
    private RecyclerViewAdapter<CityDisplay> myAdapter;

    public ResearchLocation(WeakReference<RecyclerView> rvRef, String userInput, WeakReference<Context> contextRef,
                            WeakReference<TextView> wrongResearchRef){
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
    }

    @Override
    protected void onProgressUpdate(CityDisplay... results){
        myAdapter.add(results[0]);
        myAdapter.notifyItemInserted(myAdapter.getResultItems().size()-1);
    }

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
           // myAdapter.getResultItems();

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        } finally {
            if(urlConnection !=null)
                urlConnection.disconnect();
        }

        return null;
    }

    private String retrieveImgUrl(String fullNameCity) {
        URL url;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL("https://www.flickr.com/services/feeds/photos_public.gne?tags="+fullNameCity+"&format=json");
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



    private String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
        }
        is.close();
        return sb.toString();
    }

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