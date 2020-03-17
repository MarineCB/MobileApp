package tp1.androidproject.lifequality.ViewHolders;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Specify the behavior of all the view holder used
 * Extends RecyclerView.ViewHolder so the class inheriting have the same behavior
 */
public abstract class GenericViewHolder<E> extends RecyclerView.ViewHolder{

    GenericViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void display(int position,ArrayList<E> resultItems, final Context context);
}
