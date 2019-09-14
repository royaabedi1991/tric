package com.a3rick.a3rick.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a3rick.a3rick.R;
import com.a3rick.a3rick.activities.CategoryActivity;
import com.a3rick.a3rick.models.models.Trick.categories.GetAllCategoryResult;
import com.a3rick.a3rick.models.models.Trick.categories.Result;
import com.a3rick.a3rick.webService.Trick.FileApi;
import com.a3rick.a3rick.webService.Trick.RetrofitClient;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.Holder> {
    private LayoutInflater inflater;
    List<Result> results;


    public CategoryAdapter(Context context, List<Result> results) {
        this.results = results;
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public CategoryAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Holder holder = new Holder(inflater.inflate(R.layout.row_category_frag, parent, false));

        holder.getAllCategory();
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.Holder holder, final int position) {
        holder.setData(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = "";
                Result current = results.get(position);
                switch (position) {
                    case 0:
                        title = "آشپزی";
                        break;

                    case 1:
                        title = "خانه داری";
                        break;

                    case 2:
                        title = "زیبایی";
                        break;

                    case 3:
                        title = "سرگرمی";
                        break;
                }

                Intent intent2 = new Intent(v.getContext(), CategoryActivity.class);
                intent2.putExtra("POSOTION", position);
                intent2.putExtra("TITLE", title);
                v.getContext().startActivity(intent2);


//                switch (position){
//
//                    case 0:
//                        Intent intent0 = new Intent(v.getContext(), CoockCategoryActivity.class);
//                        v.getContext().startActivity(intent0);
//                        break;
//
//                    case 1:
//                        Intent intent1 = new Intent(v.getContext(), HouseCategoryActivity.class);
//                        v.getContext().startActivity(intent1);
//                        break;
//
//                    case 2:
//                        Intent intent2 = new Intent(v.getContext(), CategoryActivity.class);
//                        v.getContext().startActivity(intent2);
//                        break;
//
//                    case 3:
//                        Intent intent3 = new Intent(v.getContext(), FunCategoryActivity.class);
//                        v.getContext().startActivity(intent3);
//                        break;
//
//
//
//
//
//
//
//                }


            }
        });


    }

    @Override
    public int getItemCount() {
        return results.size();
    }


    public class Holder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;

        public Holder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgage_category);
            textView = itemView.findViewById(R.id.tv_categori);
        }


        private void getAllCategory() {
            FileApi fileApi = RetrofitClient.getRetroClient().create(FileApi.class);
            Call<GetAllCategoryResult> call = fileApi.getAllCategory(0);
            call.enqueue(new Callback<GetAllCategoryResult>() {
                @Override
                public void onResponse(Call<GetAllCategoryResult> call, Response<GetAllCategoryResult> response) {
                    GetAllCategoryResult apiResponse = new GetAllCategoryResult();
                    apiResponse = response.body();
                    if (results != null) {
                        results = apiResponse.getResult();
                        notifyDataSetChanged();
                    }

                }

                @Override
                public void onFailure(Call<GetAllCategoryResult> call, Throwable t) {
                    Log.e("Tag", "error");

                }
            });


        }

        public void setData(int position) {
            Result current = results.get(position);
            Picasso.with(itemView.getContext()).load(current.getBannerImageFileAddress()).centerCrop().fit().into(imageView);
            textView.setText(current.getName());


        }


    }


}
