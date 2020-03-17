package tp1.androidproject.lifequality.Model;

import com.orm.SugarRecord;
import java.util.ArrayList;

/**
 * Model of a City
 * Saved in the database by extending sugarORM
 */
public class City extends SugarRecord<City> {
    private String locationUrl;
    private String name;
    private String adminDivision;
    private String country;
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

    public void setAdminDivision(String adminDivision) {
        this.adminDivision = adminDivision;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
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
        return new ArrayList<>(City.listAll(City.class));
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof City))
            return false;

        return ((City) obj).getLocationUrl().equals(this.getLocationUrl());
    }
}
