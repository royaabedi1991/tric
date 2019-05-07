package com.a3rick.a3rick.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.a3rick.a3rick.R;
import com.a3rick.a3rick.models.Banner;
import com.a3rick.a3rick.models.Beauty;
import com.a3rick.a3rick.models.Favorite;

import java.util.List;

public class BeautyAdapter extends RecyclerView.Adapter<BeautyAdapter.Holder> {

    private List<Beauty> beauties;
    private LayoutInflater inflater;
    private Context context;

    public BeautyAdapter(List<Beauty> beauties, LayoutInflater inflater, Context context) {
        this.beauties = beauties;
        this.inflater = inflater;
        this.context = context;
    }

    public BeautyAdapter(Context context, List<Beauty> beauties) {
        this.beauties = beauties;

        inflater = LayoutInflater .from(context);
    }


    @NonNull
    @Override
    public BeautyAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Holder holder = new Holder(inflater.inflate(R.layout.row_beauty, parent, false));

        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull BeautyAdapter.Holder holder, int position) {
        holder.setData(position);

    }

    @Override
    public int getItemCount() {
        return beauties.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public Holder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.card_beauty);
        }

        public void setData(int position) {
            Beauty current= beauties.get(position);
            imageView.setImageResource(current.getResource());
        }
    }
}
