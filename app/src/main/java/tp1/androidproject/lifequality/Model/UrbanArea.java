package tp1.androidproject.lifequality.Model;

import com.orm.SugarRecord;

import java.util.ArrayList;

public class UrbanArea extends SugarRecord<UrbanArea> {
    // https://api.teleport.org/api/urban_areas/slug:paris/
    private String url;
    private String name;
    private String fullname;
    private ArrayList<String> adminDivisions;
    private String mayor;
    private ArrayList<String> cities;
    private String continent;
    private String country;
    private String detailsUrl;
    // faire une liste d'image ?? "ua:images" -> lien mobile gros plan
    private String salariesUrl;
    private ArrayList<Salary> salaries;
    private String imageUrl;
    private ArrayList<Score> scores;


    public UrbanArea(String url){
        this.url = url;
        this.scores = new ArrayList<>();
        this.cities = new ArrayList<>();
        this.salaries = new ArrayList<>();
    }


    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Score> getScores() {
        return scores;
    }

    public void addScore(String cat, Double rating){
        scores.add(new Score(cat,rating.floatValue()));
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public ArrayList<String> getAdminDivisions() {
        return adminDivisions;
    }

    public void setAdminDivisions(ArrayList<String> adminDivisions) {
        this.adminDivisions = adminDivisions;
    }

    public String getMayor() {
        return mayor;
    }

    public void setMayor(String mayor) {
        this.mayor = mayor;
    }

    public ArrayList<String> getCities() {
        return cities;
    }

    public void addCity(String city){
        cities.add(city);
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDetailsUrl() {
        return detailsUrl;
    }

    public void setDetailsUrl(String detailsUrl) {
        this.detailsUrl = detailsUrl;
    }

    public ArrayList<Salary> getSalaries() {
        return salaries;
    }

    public void addSalary(Salary salary){
        salaries.add(salary);
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
