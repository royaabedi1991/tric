package com.a3rick.a3rick.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.a3rick.a3rick.R;
import com.a3rick.a3rick.models.Favorite;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.Holder> {

    private List<Favorite> favorites;
    private LayoutInflater inflater;
    private Context context;


    public FavoriteAdapter(List<Favorite> favorites, LayoutInflater inflater, Context context) {
        this.favorites = favorites;
        this.inflater = inflater;
        this.context = context;
    }


    public FavoriteAdapter(Context context, List<Favorite> favorites) {
        this.favorites = favorites;

        inflater = LayoutInflater .from(context);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        FavoriteAdapter.Holder holder = new FavoriteAdapter.Holder(inflater.inflate(R.layout.row_favorite, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.Holder holder, int position) {
        holder.setData(position);

    }

    @Override
    public int getItemCount() {
        return favorites.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        //private CardView cardView2;

        public Holder(View itemView) {
            super(itemView);
            imageView= itemView.findViewById(R.id.card_favorite);
        }

        public void setData(int position) {
            Favorite current= favorites.get(position);
            imageView.setImageResource(current.getRecource());

        }
    }
}
