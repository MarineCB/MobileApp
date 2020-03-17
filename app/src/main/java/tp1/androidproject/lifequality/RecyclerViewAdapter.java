package tp1.androidproject.lifequality;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import tp1.androidproject.lifequality.ViewHolders.GenericViewHolder;
import tp1.androidproject.lifequality.ViewHolders.ResearchCityHolder;
import tp1.androidproject.lifequality.ViewHolders.UrbanAreaCitiesHolder;
import tp1.androidproject.lifequality.ViewHolders.UrbanAreaSalariesHolder;
import tp1.androidproject.lifequality.ViewHolders.UrbanAreaScoresHolder;

public class RecyclerViewAdapter<E> extends RecyclerView.Adapter<GenericViewHolder> {
    private ArrayList<E> resultItems;
    private Context context;
    private int resourceLayout;


    public RecyclerViewAdapter( Context context, int layout) {
        this.resultItems = new ArrayList<>();
        this.context = context;
        this.resourceLayout = layout;
    }

    public RecyclerViewAdapter( Context context, int layout, ArrayList<E> resultItems) {
        this.resultItems = resultItems;
        this.context = context;
        this.resourceLayout = layout;
    }

    public void add(E result){
        this.resultItems.add(result);
    }

    public ArrayList<E> getResultItems(){
        return this.resultItems;
    }

    @NonNull
    @Override
    public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GenericViewHolder holder = null;
        if(resourceLayout == R.layout.search_results_item){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_results_item,parent, false);
            holder = new ResearchCityHolder(view);
        }else if(resourceLayout == R.layout.ua_scores_item){
            View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.ua_scores_item,parent, false);
            holder = new UrbanAreaScoresHolder(view);
        }else if(resourceLayout == R.layout.ua_cities_item){
            View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.ua_cities_item,parent, false);
            holder = new UrbanAreaCitiesHolder(view);
        }else if(resourceLayout == R.layout.ua_salaries_item){
            View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.ua_salaries_item,parent, false);
            holder = new UrbanAreaSalariesHolder(view);
        }
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder holder, int position) {
        holder.display(position, resultItems, context);
    }

    @Override
    public int getItemCount() {
        return resultItems.size();
    }




}
