package tp1.androidproject.lifequality;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.like.LikeButton;
import com.like.OnLikeListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;
import tp1.androidproject.lifequality.Model.City;
import tp1.androidproject.lifequality.Model.UrbanArea;

public class CityActivity extends AppCompatActivity {
    private WeakReference<City> cityRef;
    private City cityObj;
    private VolleyController controller;
    private TextView cityNameTv;
    private TextView populationTv;
    private TextView countryTv;
    private TextView adminDivTv;
    private TextView timezoneTv;
    private LikeButton likeButton;

    private boolean hasUrbanArea = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        BottomNavigationBar bar = BottomNavigationBar.getInstance(this);
        //City.deleteAll(City.class);
        CircleImageView cityImg = findViewById(R.id.city_img);
        cityNameTv = findViewById(R.id.city_fullname);
        populationTv = findViewById(R.id.mayor_ua_tv);
        countryTv = findViewById(R.id.fullname_ua_tv);
        adminDivTv = findViewById(R.id.continent_ua_tv);
        timezoneTv = findViewById(R.id.timezone_tv);
        likeButton = findViewById(R.id.heart_button);

        Intent intent = getIntent();
        cityObj = new City();
        cityObj.setLocationUrl(intent.getStringExtra("chosenCityUrl"));
        this.cityRef = new WeakReference<>(cityObj);

        Initialize();

        String imgUrl = intent.getStringExtra("chosenCityImg");
        if(imgUrl ==  null || imgUrl.equals(""))
            cityImg.setVisibility(View.GONE);
        else
            Glide.with(getApplicationContext()).load(intent.getStringExtra("chosenCityImg")).into(cityImg);

        NotificationService.scheduleJob(getApplicationContext());

        startRequest();
    }


    public void Initialize(){
        likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                City city = cityRef.get();
                city.save();
                Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                City city = cityRef.get();

                ArrayList<City> savedCities = City.getAllSavedCities();
                if(savedCities != null && savedCities.size() >0){
                    for(City c : savedCities){
                        if(c.equals(city)){
                            c.delete();
                            Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        controller = VolleyController.getInstance(getApplicationContext());
    }

    private void startRequest () {
        new Thread(){public void run() {
            final City city = cityRef.get();

            JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, city.getLocationUrl(), null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (city == null)
                                    return;

                                city.setPopulation(String.valueOf(response.getInt("population")));

                                city.setName(response.getString("name"));
                                response = response.getJSONObject("_links");

                                JSONObject values = response.getJSONObject("city:admin1_division");
                                city.setAdminDivision(values.getString("name"));
                                city.setAdminDivisionUrl(values.getString("href"));

                                values = response.getJSONObject("city:country");
                                city.setCountry(values.getString("name"));
                                city.setCountryUrl(values.getString("href"));

                                values = response.getJSONObject("city:timezone");
                                city.setTimezone(values.getString("name"));

                                if (response.has("city:urban_area")) {
                                    hasUrbanArea = true;
                                    JSONObject ua = response.getJSONObject("city:urban_area");
                                    if (ua != null)
                                        city.setUrbanArea(new UrbanArea(ua.getString("href")));
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }finally {
                                DisplayInfo(city);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "Sorry, there has been an issue...", Toast.LENGTH_LONG).show();
                        }
                    });
            controller.addToRequestQueue(req);
        }}.run();
    }

    private void DisplayInfo(City city){
        populationTv.setText(city.getPopulation());
        cityNameTv.setText(city.getName());
        countryTv.setText(city.getCountry());
        adminDivTv.setText(city.getAdminDivision());
        timezoneTv.setText(city.getTimezone());

        if (hasUrbanArea)
            findViewById(R.id.ua_act_button).setVisibility(View.VISIBLE);

        List<City> citiesResult = City.find(City.class, "location_url = ?", city.getLocationUrl());
        if (citiesResult !=null && citiesResult.size() > 0)
            likeButton.setLiked(true);
        else
            likeButton.setLiked(false);
    }

    public void LaunchUrbanActivity(View v) {
        Intent urbanAreaAct = new Intent(getApplicationContext(), UrbanAreaActivity.class);
        urbanAreaAct.putExtra("urbanAreaUrl", cityRef.get().getUrbanArea().getUrl());
        startActivity(urbanAreaAct);
    }
}
