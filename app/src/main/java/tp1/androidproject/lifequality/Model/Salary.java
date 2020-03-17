package tp1.androidproject.lifequality.Model;

import com.orm.SugarRecord;
import org.decimal4j.util.DoubleRounder;

/**
 * Model of a Salary Object
 * Persistent by extending SugarRecord
 */
public class Salary extends SugarRecord<Salary> {
    private String name;
    private Double percentile25;
    private Double percentile50;
    private Double percentile75;

    public Salary(String name, Double percentile25, Double percentile50, Double percentile75) {
        this.name = name;
        this.percentile25 = DoubleRounder.round(percentile25,2);
        this.percentile50 = DoubleRounder.round(percentile50,2);
        this.percentile75 = DoubleRounder.round(percentile75,2);
    }

    public String getName() {
        return name;
    }

    public Double getPercentile25() {
        return percentile25;
    }

    public Double getPercentile50() {
        return percentile50;
    }

    public Double getPercentile75() {
        return percentile75;
    }
}
