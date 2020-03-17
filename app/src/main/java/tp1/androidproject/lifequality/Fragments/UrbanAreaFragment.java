package tp1.androidproject.lifequality.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import tp1.androidproject.lifequality.Model.Salary;
import tp1.androidproject.lifequality.Model.UrbanArea;
import tp1.androidproject.lifequality.R;
import tp1.androidproject.lifequality.VolleyController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class UrbanAreaFragment extends Fragment {
    private UrbanArea urbanArea;
    private VolleyController controller;

    private CitiesUaFragment citiesFrag;
    private ScoresUaFragment scoresFrag;
    private SalariesUaFragment salariesFrag;

    private TextView nameUa;
    private TextView fullnameUa;
    private TextView continentUa;
    private TextView mayorUa;

    private View view;

    public UrbanAreaFragment(String uaUrl) {
        this.urbanArea = new UrbanArea(uaUrl);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_urban_area, container, false);

        controller = VolleyController.getInstance(getContext());

        this.nameUa = view.findViewById(R.id.name_ua_tv);
        this.fullnameUa = view.findViewById(R.id.fullname_ua_tv);
        this.continentUa = view.findViewById(R.id.continent_ua_tv);
        this.mayorUa = view.findViewById(R.id.mayor_ua_tv);

        init();
        parallelizedSubCatRequests();

        view.findViewById(R.id.card_view_list_cities).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listCitiesClicked();
            }
        });

        view.findViewById(R.id.card_view_salaries).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listSalariesClicked();
            }
        });

        view.findViewById(R.id.card_view_scores).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listScoresClicked();
            }
        });

        return view;
    }

    public void init(){
        new Thread(){public void run() {
            JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, urbanArea.getUrl(), null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                urbanArea.setName(response.getString("name"));
                                urbanArea.setFullname(response.getString("full_name"));
                                urbanArea.setContinent(response.getString("continent"));
                                urbanArea.setMayor(response.getString("mayor"));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            } finally {
                                DisplayInfo();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext(), "Sorry, there has been an issue...", Toast.LENGTH_LONG).show();
                        }
                    });
            controller.addToRequestQueue(req);
        }}.run();


    }

    private void parallelizedSubCatRequests() {
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, urbanArea.getUrl()+"images/", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray images = response.getJSONArray("photos");
                            if(images != null && images.length()!= 0){
                                String image = images.getJSONObject(0).getJSONObject("image").getString("web");
                                urbanArea.setImageUrl(image);
                                Glide.with(getContext()).load(image).into((ImageView)view.findViewById(R.id.ua_image));
                            }else{
                                view.findViewById(R.id.ua_image).setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getContext(), "Sorry, there has been an issue with the image...", Toast.LENGTH_LONG).show();
                    }
                });
        controller.addToRequestQueue(req);

        req = new JsonObjectRequest(Request.Method.GET, urbanArea.getUrl()+"scores/", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray scores = response.getJSONArray("categories");
                            for (int i = 0; i < scores.length(); i++){
                                JSONObject obj = scores.getJSONObject(i);
                                urbanArea.addScore(obj.getString("name"),(obj.getDouble("score_out_of_10")/2));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }finally {
                            scoresFrag = new ScoresUaFragment(urbanArea.getScores());
                            getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container_scores,scoresFrag).hide(scoresFrag).commit();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Sorry, there has been an issue...", Toast.LENGTH_LONG).show();
                    }
        });
        controller.addToRequestQueue(req);

        req = new JsonObjectRequest(Request.Method.GET, urbanArea.getUrl()+"cities/", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray cities = response.getJSONObject("_links").getJSONArray("city:items");
                            for (int i = 0; i < cities.length(); i++){
                                JSONObject obj = cities.getJSONObject(i);
                                urbanArea.addCity(obj.getString("name"));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }finally {

                            citiesFrag = new CitiesUaFragment(urbanArea.getCities());
                            getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container_cities,citiesFrag).hide(citiesFrag).commit();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Sorry, there has been an issue...", Toast.LENGTH_LONG).show();
                    }
        });
        controller.addToRequestQueue(req);

        req = new JsonObjectRequest(Request.Method.GET, urbanArea.getUrl()+"salaries/", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray salaries = response.getJSONArray("salaries");
                            for (int i = 0; i < salaries.length(); i++){
                                JSONObject obj = salaries.getJSONObject(i);
                                JSONObject percentiles = obj.getJSONObject("salary_percentiles");
                                urbanArea.addSalary(new Salary(obj.getJSONObject("job").getString("title"),
                                        percentiles.getDouble("percentile_25"),
                                        percentiles.getDouble("percentile_50"),
                                        percentiles.getDouble("percentile_75")));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }finally {
                            salariesFrag = new SalariesUaFragment(urbanArea.getSalaries());
                            getActivity().getSupportFragmentManager().beginTransaction().add(R.id.container_salaries,salariesFrag).hide(salariesFrag).commit();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Sorry, there has been an issue...", Toast.LENGTH_LONG).show();
                    }
                });
        controller.addToRequestQueue(req);


    }

    private void DisplayInfo(){
        nameUa.setText(urbanArea.getName());
        fullnameUa.setText(urbanArea.getFullname());
        continentUa.setText(urbanArea.getContinent());
        if (urbanArea.getMayor() != null && !urbanArea.getMayor().equals("")){
            mayorUa.setText(urbanArea.getMayor());
        }else{
            mayorUa.setVisibility(View.GONE);
            view.findViewById(R.id.mayor_ua_label_tv).setVisibility(View.GONE);
        }
    }

    public void listCitiesClicked(){
        FragmentTransaction transactionCities = getActivity().getSupportFragmentManager().beginTransaction();
        if (!citiesFrag.isVisible()){
            transactionCities.show(citiesFrag);
        }else{
            transactionCities.hide(citiesFrag);
        }
        transactionCities.commit();
    }

    public void listScoresClicked(){
        FragmentTransaction transactionScores = getActivity().getSupportFragmentManager().beginTransaction();
        if (!scoresFrag.isVisible()){
            transactionScores.show(scoresFrag);
        }else{
            transactionScores.hide(scoresFrag);
        }
        transactionScores.commit();
    }

    public void listSalariesClicked(){
        FragmentTransaction transactionScores = getActivity().getSupportFragmentManager().beginTransaction();
        if (!salariesFrag.isVisible()){
            transactionScores.show(salariesFrag);
        }else{
            transactionScores.hide(salariesFrag);
        }
        transactionScores.commit();
    }


}
