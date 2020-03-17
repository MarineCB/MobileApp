package tp1.androidproject.lifequality.Model;

import android.util.Log;
import android.util.Pair;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

public class City extends SugarRecord<City> {
    private String locationUrl;
    private String name;
    @Ignore
    private String adminDivisionUrl;
    private String adminDivision;
    private String country;
    @Ignore
    private String countryUrl;
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
        return adminDivision;
    }

    public String getAdminDivisionUrl() {
        return this.adminDivisionUrl;
    }

    public void setAdminDivision(String adminDivision) {
        this.adminDivision = adminDivision;
    }

    public void setAdminDivisionUrl(String url) {
        this.adminDivisionUrl = url;
    }

    public String getCountry() {
        return this.country;
    }

    public String getCountryUrl() {
        return this.countryUrl;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCountryUrl(String url) {
        this.countryUrl = url;
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
    /*    Iterator<City> iterator = City.findAll(City.class);
        ArrayList<City> temp = new ArrayList<>();
        while (iterator.hasNext()) {
            temp.add(iterator.next());
        }
        Log.i("Favorite", temp.toString());
        return temp;*/
        return new ArrayList<>(City.listAll(City.class));
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
