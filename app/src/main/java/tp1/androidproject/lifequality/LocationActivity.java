package tp1.androidproject.lifequality;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;
import tp1.androidproject.lifequality.Model.Location;
import tp1.androidproject.lifequality.Model.UrbanArea;

public class LocationActivity extends AppCompatActivity {
    private Location location;
    private VolleyController controller;
    private TextView cityNameTv;
    private TextView populationTv;
    private TextView countryTv;
    private TextView adminDivTv;
    private TextView timezoneTv;

    private boolean hasUrbanArea = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        CircleImageView cityImg = findViewById(R.id.city_img);
        cityNameTv = findViewById(R.id.city_fullname);
        populationTv = findViewById(R.id.mayor_ua_tv);
        countryTv = findViewById(R.id.fullname_ua_tv);
        adminDivTv = findViewById(R.id.continent_ua_tv);
        timezoneTv = findViewById(R.id.timezone_tv);

        controller = VolleyController.getInstance(getApplicationContext());

        Intent intent = getIntent();
        location = new Location(intent.getStringExtra("chosenCityUrl"));

        String imgUrl = intent.getStringExtra("chosenCityImg");
        if(imgUrl ==  null || imgUrl.equals(""))
            cityImg.setVisibility(View.GONE);
        else
            Glide.with(getApplicationContext()).load(intent.getStringExtra("chosenCityImg")).into(cityImg);

        startRequest();
    }

    private void startRequest () {
        new Thread(){public void run() {

            JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, location.getLocationUrl(), null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                location.setPopulation(String.valueOf(response.getInt("population")));

                                location.setName(response.getString("name"));
                                response = response.getJSONObject("_links");

                                JSONObject values = response.getJSONObject("city:admin1_division");
                                location.setAdminDivision(values.getString("name"), values.getString("href"));

                                values = response.getJSONObject("city:country");
                                location.setCountry(values.getString("name"), values.getString("href"));

                                values = response.getJSONObject("city:timezone");
                                location.setTimezone(values.getString("name"));

                                if (response.has("city:urban_area")) {
                                    hasUrbanArea = true;
                                    JSONObject ua = response.getJSONObject("city:urban_area");
                                    if (ua != null)
                                        location.setUrbanArea(new UrbanArea(ua.getString("href")));
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }finally {
                                DisplayInfo();
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

    private void DisplayInfo(){
        populationTv.setText(location.getPopulation());
        cityNameTv.setText(location.getName());
        countryTv.setText(location.getCountry());
        adminDivTv.setText(location.getAdminDivision());
        timezoneTv.setText(location.getTimezone());
        if (hasUrbanArea)
            findViewById(R.id.ua_act_button).setVisibility(View.VISIBLE);
    }

    public void LaunchUrbanActivity(View v) {
        Intent urbanAreaAct = new Intent(getApplicationContext(), UrbanAreaActivity.class);
        urbanAreaAct.putExtra("urbanAreaUrl", location.getUrbanArea().getUrl());
        startActivity(urbanAreaAct);
    }
}
