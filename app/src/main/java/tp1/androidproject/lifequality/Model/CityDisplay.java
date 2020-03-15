package tp1.androidproject.lifequality.Model;

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