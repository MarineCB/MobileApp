package tp1.androidproject.lifequality.Model;

import android.util.Pair;

import tp1.androidproject.lifequality.Model.UrbanArea;

public class Location {
    private String locationUrl;
    private String name;
    private Pair<String,String> adminDivision;
    private Pair<String,String> Country;
    private String population;
    private String timezone;
    private UrbanArea urbanArea;

    public Location(String url){
        this.locationUrl = url;
    }

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
}
