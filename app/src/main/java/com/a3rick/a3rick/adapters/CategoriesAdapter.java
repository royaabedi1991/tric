package com.a3rick.a3rick.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.a3rick.a3rick.ChangeCategoryItem;
import com.a3rick.a3rick.R;
import com.a3rick.a3rick.RecyclerViewClicked;
import com.a3rick.a3rick.activities.ContentActivity;
import com.a3rick.a3rick.models.models.Trick.content_with_categoriId.Result;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.Holder> implements Serializable {
    private LayoutInflater inflater;
    List<Result> result_s;
    RecyclerViewClicked recyclerViewClicked;
    ChangeCategoryItem changeCategoryItem;

    public CategoriesAdapter(Context context, List<Result> result_s, RecyclerViewClicked recyclerViewClicked, ChangeCategoryItem changeCategoryItem) {
        this.result_s = result_s;
        inflater = LayoutInflater.from(context);
        this.recyclerViewClicked = recyclerViewClicked;
        this.changeCategoryItem = changeCategoryItem;
    }


    @NonNull
    @Override
    public CategoriesAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Holder holder = new Holder(inflater.inflate(R.layout.row_categorys, parent, false));

//        notifyDataSetChanged();
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull CategoriesAdapter.Holder holder, final int position) {
        holder.setData(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {


                Result current = result_s.get(position);
                Intent intent = new Intent(v.getContext(), ContentActivity.class);


                recyclerViewClicked.onIteMRecycler(v, String.valueOf(current.getCategoryId()), current, changeCategoryItem);

            }
        });


    }

    @Override
    public int getItemCount() {
        return result_s.size();
    }


    public class Holder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;
        TextView likeCount;
        TextView viewCount;


        public Holder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.tv_headerImage_category);
            textView = itemView.findViewById(R.id.tv_subject_category);
            likeCount = itemView.findViewById(R.id.like_count_categories);
            viewCount = itemView.findViewById(R.id.view_count_categories);


        }


//        private void getContents() {
//            FileApi fileApi = RetrofitClient.getRetroClient().create(FileApi.class);
//            Call<GetContentResult> call = fileApi.getContent(/*"{{Token}}",*/3, 1, 50, "LastItem");
//            call.enqueue(new Callback<GetContentResult>() {
//                @Override
//                public void onResponse(Call<GetContentResult> call, Response<GetContentResult> response) {
//                    GetContentResult apiResponse = new GetContentResult();
//                    apiResponse = response.body();
//                    if (result_s != null) {
//                        result_s = apiResponse.getResult();
//
//                        notifyDataSetChanged();
//
//                    }
//
//                }
//
//                @Override
//                public void onFailure(Call<GetContentResult> call, Throwable t) {
//                    Log.e("Tag", "error");
//
//                }
//            });
//
//
//        }


        private void setData(int position) {
            Result current = result_s.get(position);
            Picasso.with(itemView.getContext()).load(current.getHeaderImageFileAddress()).centerCrop().fit().into(imageView);
            textView.setText(current.getSubject());
            likeCount.setText(String.valueOf(current.getLikeCount()));
            viewCount.setText(String.valueOf(current.getViewCount())+1);

        }


    }

}
