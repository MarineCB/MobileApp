package tp1.androidproject.lifequality.Model;

import android.util.Log;
import android.util.Pair;

import com.orm.SugarRecord;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

public class City extends SugarRecord<City> {
    private String locationUrl;
    private String name;
    private Pair<String,String> adminDivision;
    private Pair<String,String> Country;
    private String population;
    private String timezone;
    private UrbanArea urbanArea;

    public City(String url){
        this.locationUrl = url;
    }

    public City(){ }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdminDivision() {
        return adminDivision.first;
    }

    public void setAdminDivision(String adminDivision, String url) {
        this.adminDivision = new Pair<>(adminDivision, url);
    }

    public String getCountry() {
        return Country.first;
    }

    public void setCountry(String country, String url) {
        Country = new Pair<>(country, url);
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getLocationUrl() {
        return locationUrl;
    }

    public void setLocationUrl(String locationUrl) {
        this.locationUrl = locationUrl;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public UrbanArea getUrbanArea() {
        return urbanArea;
    }

    public void setUrbanArea(UrbanArea urbanArea) {
        this.urbanArea = urbanArea;
    }

    public static ArrayList<City> getAllSavedCities(){
        Iterator<City> iterator = City.findAll(City.class);
        ArrayList<City> temp = new ArrayList<>();
        while (iterator.hasNext()) {
            temp.add(iterator.next());
        }
        Log.i("Favorite", temp.toString());
        return temp;
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof City))
            return false;

        if(((City) obj).getLocationUrl().equals(this.getLocationUrl()))
            return true;
        else return false;
    }
}
