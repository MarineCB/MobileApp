package tp1.androidproject.lifequality.Model;

import com.orm.SugarRecord;

/**
 * Model of a Score Object
 * Persistent by extending SugarRecord
 */
public class Score extends SugarRecord<Score> {
    private String categoryName;
    private Float rating;

    Score(String categoryName, Float rating) {
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
