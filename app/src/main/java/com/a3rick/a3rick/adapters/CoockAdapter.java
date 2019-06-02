package com.a3rick.a3rick.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.a3rick.a3rick.R;
import com.a3rick.a3rick.models.RecyclerViewModels.Coock;

import java.util.List;

public class CoockAdapter extends RecyclerView.Adapter<CoockAdapter.Holder> {
    private List<Coock> coocks;
    private LayoutInflater inflater;
    private Context context;

    public CoockAdapter(Context context, List<Coock> coocks) {
        this.coocks = coocks;
        inflater = LayoutInflater.from(context);
    }

    public CoockAdapter(List<Coock> coocks, LayoutInflater inflater, Context context) {
        this.coocks = coocks;
        this.inflater = inflater;
        this.context = context;
    }

    @NonNull
    @Override
    public CoockAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CoockAdapter.Holder holder = new CoockAdapter.Holder(inflater.inflate(R.layout.row_coock, parent, false));


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CoockAdapter.Holder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return coocks.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        //  private TextView textView;

        public Holder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.card_coock);
        }

        public void setData(int position) {
            Coock current = coocks.get(position);
            imageView.setImageResource(current.getResorce());
        }
    }
}
