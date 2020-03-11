package tp1.androidproject.lifequality.ViewHolders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import tp1.androidproject.lifequality.R;

public class UrbanAreaCitiesHolder extends GenericViewHolder<String> {
    private TextView citiesTv;

    public UrbanAreaCitiesHolder(@NonNull View itemView) {
        super(itemView);
        this.citiesTv = itemView.findViewById(R.id.ua_cities);
    }

    @Override
    public void display(int position, ArrayList<String> cities, Context context) {
        final String item = cities.get(position);
        this.citiesTv.setText(item);
    }
}
