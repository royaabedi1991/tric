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
import com.a3rick.a3rick.models.RecyclerViewModels.Fun;

import java.util.List;

public class FunAdapter extends RecyclerView.Adapter<FunAdapter.Holder> {
    private List<Fun> funs;
    private LayoutInflater inflater;
    private Context context;

    public FunAdapter(Context context, List<Fun> funs) {
        this.funs = funs;
        inflater = LayoutInflater.from(context);
    }

    public FunAdapter(List<Fun> funs, LayoutInflater inflater, Context context) {
        this.funs = funs;
        this.inflater = inflater;
        this.context = context;

    }

    @NonNull
    @Override
    public FunAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FunAdapter.Holder holder = new FunAdapter.Holder(inflater.inflate(R.layout.row_fun, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FunAdapter.Holder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return funs.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public Holder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.card_fun);
        }

        public void setData(int position) {
            Fun current = funs.get(position);
            imageView.setImageResource(current.getResource());
        }
    }
}
