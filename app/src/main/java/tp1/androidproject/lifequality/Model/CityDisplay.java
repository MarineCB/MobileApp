package tp1.androidproject.lifequality.Model;

/**
 * Representation of the elements to display a city (ex in research or favorites)
 */
public class CityDisplay {
    private String fullName;
    private String imageUrl;
    private String urlCity;

    public CityDisplay(String fullName, String imageUrl, String urlCity) {
        this.fullName = fullName;
        this.imageUrl = imageUrl;
        this.urlCity = urlCity;
    }

    public String getFullName() {
        return fullName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getUrlCity() {
        return urlCity;
    }
}