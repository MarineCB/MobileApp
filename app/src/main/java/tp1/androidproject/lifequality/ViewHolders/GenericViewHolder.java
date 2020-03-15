package tp1.androidproject.lifequality.ViewHolders;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class GenericViewHolder<E> extends RecyclerView.ViewHolder{

    public GenericViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void display(int position,ArrayList<E> resultItems, final Context context);
}
