package tp1.androidproject.lifequality.ViewHolders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import tp1.androidproject.lifequality.R;

/**
 * View Holder of the list of cities composing an urban area
 */
public class UrbanAreaCitiesHolder extends GenericViewHolder<String> {
    private TextView citiesTv;

    /**
     * Retrieve graphical element linked to this view (= one item of the list of cities)
     */
    public UrbanAreaCitiesHolder(@NonNull View itemView) {
        super(itemView);
        this.citiesTv = itemView.findViewById(R.id.ua_cities);
    }

    /**
     * Override display to describe its specific display behavior
     */
    @Override
    public void display(int position, ArrayList<String> cities, Context context) {
        final String item = cities.get(position);
        this.citiesTv.setText(item);
    }
}
