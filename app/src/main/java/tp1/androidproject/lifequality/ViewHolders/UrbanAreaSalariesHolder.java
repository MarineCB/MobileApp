package tp1.androidproject.lifequality.ViewHolders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import tp1.androidproject.lifequality.Model.Salary;
import tp1.androidproject.lifequality.Model.Score;
import tp1.androidproject.lifequality.R;

public class UrbanAreaSalariesHolder extends GenericViewHolder<Salary> {
    private TextView jobTitle;
    private TextView percentile25;
    private TextView percentile50;
    private TextView percentile75;

    public UrbanAreaSalariesHolder(@NonNull View itemView) {
        super(itemView);
        this.jobTitle = itemView.findViewById(R.id.job_title);
        this.percentile25 = itemView.findViewById(R.id.percentile_25);
        this.percentile50 = itemView.findViewById(R.id.percentile_50);
        this.percentile75 = itemView.findViewById(R.id.percentile_75);
    }

    @Override
    public void display(int position, ArrayList<Salary> salaries, Context context) {
        final Salary item = salaries.get(position);
        this.jobTitle.setText(item.getName());
        this.percentile25.setText(item.getPercentile25().toString());
        this.percentile50.setText(item.getPercentile50().toString());
        this.percentile75.setText(item.getPercentile75().toString());

    }
}
