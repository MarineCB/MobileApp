package tp1.androidproject.lifequality.Model;

public class Score {
    private String categoryName;
    private Float rating;

    public Score(String categoryName, Float rating) {
        this.categoryName = categoryName;
        this.rating = rating;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Float getRating() {
        return rating;
    }

    @Override
    public String toString(){
        return categoryName+": "+rating.toString();
    }
}
