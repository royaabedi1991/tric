package com.a3rick.a3rick.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.a3rick.a3rick.R;
import com.a3rick.a3rick.models.Favorite;
import com.a3rick.a3rick.models.House;

import java.util.List;

public class HouseAdapter extends RecyclerView.Adapter<HouseAdapter.Holder> {
    private List<House> houses;
    private LayoutInflater inflater;
    private Context context;

    public HouseAdapter(List<House> houses, LayoutInflater inflater, Context context) {
        this.houses = houses;
        this.inflater = inflater;
        this.context = context;
    }

    public HouseAdapter(Context context, List<House> houses) {
        this.houses = houses;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HouseAdapter.Holder holder = new HouseAdapter.Holder(inflater.inflate(R.layout.row_house, parent, false));

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
       holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return houses.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private ImageView imageView;
       // private TextView textView;
        public Holder(View itemView) {
            super(itemView);

            imageView= itemView.findViewById(R.id.card_house);


        }


        public void setData(int position) {

            House current= houses.get(position);
           imageView.setImageResource(current.getResource());
        }
    }
}
