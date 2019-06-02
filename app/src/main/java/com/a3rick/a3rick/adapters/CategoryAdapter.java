package com.a3rick.a3rick.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a3rick.a3rick.R;
import com.a3rick.a3rick.activities.ContentActivity;
import com.a3rick.a3rick.models.RecyclerViewModels.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.Holder> {

    private List<Category> categories;
    private LayoutInflater inflater;
    private Context context;

    public List<Category> getCategories() {
        return categories;
    }

    public CategoryAdapter(Context context, List<Category> categories) {
        this.categories = categories;

        inflater = LayoutInflater.from(context);
    }

    public CategoryAdapter(List<Category> categories, LayoutInflater inflater, Context context) {
        this.categories = categories;
        this.inflater = inflater;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Holder holder = new Holder(inflater.inflate(R.layout.row_category_frag, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.Holder holder, final int position) {
        holder.setData(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                Context context1 = v.getContext();
                Intent intent = new Intent(context1, ContentActivity.class);
                intent.putExtra("ITEMPOSITION", position);

                context1.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;
        private CardView cardView;


        public Holder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img_category);
            textView = itemView.findViewById(R.id.tv_category);
            cardView = itemView.findViewById(R.id.card_category);
        }

        public void setData(int position) {

            Category current = categories.get(position);
            imageView.setImageResource(current.getRecource());
            textView.setText(current.getTitle());
        }
    }
}
