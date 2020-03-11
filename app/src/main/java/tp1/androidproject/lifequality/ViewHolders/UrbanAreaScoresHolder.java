package tp1.androidproject.lifequality.ViewHolders;

import android.content.Context;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import tp1.androidproject.lifequality.Model.Score;
import tp1.androidproject.lifequality.R;

public class UrbanAreaScoresHolder extends GenericViewHolder<Score> {
    private TextView catNametv;
    private RatingBar ratingBar;

    public UrbanAreaScoresHolder(@NonNull View itemView) {
        super(itemView);
        this.catNametv = itemView.findViewById(R.id.job_title);
        this.ratingBar = itemView.findViewById(R.id.rating_bar);
    }

    @Override
    public void display(int position, ArrayList<Score> scores, Context context) {
        final Score item = scores.get(position);
        this.catNametv.setText(item.getCategoryName());
        this.ratingBar.setRating(item.getRating());
    }
}
