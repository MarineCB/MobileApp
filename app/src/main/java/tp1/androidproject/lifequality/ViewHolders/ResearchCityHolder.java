package tp1.androidproject.lifequality.ViewHolders;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import tp1.androidproject.lifequality.CityActivity;
import tp1.androidproject.lifequality.R;
import tp1.androidproject.lifequality.Model.CityDisplay;

import static androidx.core.content.ContextCompat.startActivity;

/**
 * View Holder of the list of cities resulting from a saerch/favorite
 */
public class ResearchCityHolder extends GenericViewHolder<CityDisplay> {
    private ImageView locationImage;
    private CardView cardView;
    private TextView locationFullname;
    private LinearLayout layout;
    private String cityUrl;

    /**
     * Retrieve graphical elements linked to this view (= one item of the list of cities)
     */
    public ResearchCityHolder(@NonNull View itemView) {
        super(itemView);
        locationImage = itemView.findViewById(R.id.image_location);
        locationFullname = itemView.findViewById(R.id.fullname_location);
        layout = itemView.findViewById(R.id.item_layout);
        cardView = itemView.findViewById(R.id.card_view);
    }

    /**
     * Override display to describe its specific display behavior
     */
    @Override
    public void display(int position, ArrayList<CityDisplay> resultItems, final Context context){
        final CityDisplay item = resultItems.get(position);
        this.locationFullname.setText(item.getFullName());
        this.cityUrl = item.getUrlCity();
        final String imageUrl = item.getImageUrl();
        if(imageUrl!= null && !imageUrl.equals(""))
            Glide.with(context).load(imageUrl).into(this.locationImage);

        this.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cityDetailsAct = new Intent(context, CityActivity.class);
                cityDetailsAct.putExtra("chosenCityUrl",item.getUrlCity());
                cityDetailsAct.putExtra("chosenCityImg",imageUrl);
                startActivity(context,cityDetailsAct,null);
            }
        });
    }

}