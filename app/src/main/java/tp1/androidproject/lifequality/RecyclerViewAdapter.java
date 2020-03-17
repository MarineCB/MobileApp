package tp1.androidproject.lifequality;

import android.content.Context;
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

/**
 * RecyclerViewAdapter extended class
 * Designed to be generic and be used for any inheriting class of GenericViewHolder (itself inheriting ViewHolder)
 * For now, the E is Score, Salary, String... depending on the recyler view we use
 */
public class RecyclerViewAdapter<E> extends RecyclerView.Adapter<GenericViewHolder> {
    private ArrayList<E> resultItems;
    private Context context;
    private int resourceLayout;


    RecyclerViewAdapter(Context context, int layout) {
        this.resultItems = new ArrayList<>();
        this.context = context;
        this.resourceLayout = layout;
    }

    public RecyclerViewAdapter( Context context, int layout, ArrayList<E> resultItems) {
        this.resultItems = resultItems;
        this.context = context;
        this.resourceLayout = layout;
    }

    /**
     *Add an item to the list that the recyler view contains
     */
    void add(E result){
        this.resultItems.add(result);
    }

    ArrayList<E> getResultItems(){
        return this.resultItems;
    }

    /**
     *Create the right wiew holder depending of the type of data the recycler view contains (ie on the type of item)
     * If each case, this method will inflate a class inheriting GenericViewHolder
     * The holder can not be null since all types of recyler view item currently used are represented by a if condition
     */
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

    /**
     * return the size of item in the list
     */
    @Override
    public int getItemCount() {
        return resultItems.size();
    }




}
