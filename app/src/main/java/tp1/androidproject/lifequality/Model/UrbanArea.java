package tp1.androidproject.lifequality.Model;

import com.orm.SugarRecord;

import java.util.ArrayList;

/**
 * Model of a UrbanArea Object
 * Persistent by extending SugarRecord
 */
public class UrbanArea extends SugarRecord<UrbanArea> {
    private String url;
    private String name;
    private String fullname;
    private String mayor;
    private ArrayList<String> cities;
    private String continent;
    private ArrayList<Salary> salaries;
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

    public ArrayList<Salary> getSalaries() {
        return salaries;
    }

    public void addSalary(Salary salary){
        salaries.add(salary);
    }
}
