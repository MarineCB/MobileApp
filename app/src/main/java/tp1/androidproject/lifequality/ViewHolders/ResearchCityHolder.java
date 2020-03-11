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
import tp1.androidproject.lifequality.LocationActivity;
import tp1.androidproject.lifequality.R;
import tp1.androidproject.lifequality.Model.SearchResult;

import static androidx.core.content.ContextCompat.startActivity;


public class ResearchCityHolder extends GenericViewHolder<SearchResult> {
    private ImageView locationImage;
    //private CircleImageView locationImage;
    private CardView cardView;
    private TextView locationFullname;
    private LinearLayout layout;
    private String cityUrl;

    public ResearchCityHolder(@NonNull View itemView) {
        super(itemView);
        locationImage = itemView.findViewById(R.id.image_location);
        locationFullname = itemView.findViewById(R.id.fullname_location);
        layout = itemView.findViewById(R.id.item_layout);
        cardView = itemView.findViewById(R.id.card_view);
    }

    @Override
    public void display(int position, ArrayList<SearchResult> resultItems, final Context context){
        final SearchResult item = resultItems.get(position);
        this.locationFullname.setText(item.getFullName());
        this.cityUrl = item.getUrlCity();
        final String imageUrl = item.getImageUrl();
        if(imageUrl!= null && !imageUrl.equals(""))
            Glide.with(context).load(imageUrl).into(this.locationImage);

        this.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cityDetailsAct = new Intent(context, LocationActivity.class);
                cityDetailsAct.putExtra("chosenCityUrl",item.getUrlCity());
                cityDetailsAct.putExtra("chosenCityImg",imageUrl);
                startActivity(context,cityDetailsAct,null);
            }
        });
    }

}