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
import com.a3rick.a3rick.activities.ContentActivity;
import com.a3rick.a3rick.models.models.Trick.favorites.GetFavoriteContentsResult;
import com.a3rick.a3rick.models.models.Trick.favorites.Result;
import com.a3rick.a3rick.webService.Trick.FileApi;
import com.a3rick.a3rick.webService.Trick.RetrofitClient;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyVideoAdapter extends RecyclerView.Adapter<MyVideoAdapter.Holder> {
    private LayoutInflater inflater;
    List<com.a3rick.a3rick.models.models.Trick.favorites.Result> results;


    public MyVideoAdapter(Context context, List<Result> results) {
        this.results = results;
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public MyVideoAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Holder holder = new Holder(inflater.inflate(R.layout.row_myvideo_fragment, parent, false));

        holder.getFavorite();
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyVideoAdapter.Holder holder, final int position) {
        holder.setData(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String title = "";
//                Result current = results.get(position);
//                switch (position) {
//                    case 0:
//                        title = "آشپزی";
//                        break;
//
//                    case 1:
//                        title = "خانه داری";
//                        break;
//
//                    case 2:
//                        title = "زیبایی";
//                        break;
//
//                    case 3:
//                        title = "سرگرمی";
//                        break;
//                }
//
//                Intent intent2 = new Intent(v.getContext(), CategoryActivity.class);
//                intent2.putExtra("POSOTION", position);
//                intent2.putExtra("TITLE", title);
//                v.getContext().startActivity(intent2);I
                Result current = results.get(position);
                Intent intent = new Intent(v.getContext(), ContentActivity.class);
                intent.putExtra("VIDEOADRESS", current.getVideoFileAddress());
                intent.putExtra("SUBJECT", current.getSubject());
                intent.putExtra("BODY", current.getBody());
                intent.putExtra("LIKECOUNT", current.getLikeCount());
                intent.putExtra("ImageHEADER", current.getHeaderImageFileAddress());
                intent.putExtra("ISLIKED", current.getIsLiked());
                intent.putExtra("ISBOOKMARKED", current.getIsBookmarked());
                intent.putExtra("TAGS", (Serializable) current.getAllTags());
                intent.putExtra("VIECOUNT", current.getViewCount());
                intent.putExtra("CONTENTID", current.getContentId());
                v.getContext().startActivity(intent);

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
            imageView = itemView.findViewById(R.id.tv_headerImage_myVideo);
            textView = itemView.findViewById(R.id.tv_subject_myVideo);
        }


        private void getFavorite() {
            FileApi fileApi = RetrofitClient.getRetroClient().create(FileApi.class);
            Call<GetFavoriteContentsResult> call = fileApi.GetFavorite(1, 10);
            call.enqueue(new Callback<GetFavoriteContentsResult>() {
                @Override
                public void onResponse(Call<GetFavoriteContentsResult> call, Response<GetFavoriteContentsResult> response) {
                    GetFavoriteContentsResult apiResponse = new GetFavoriteContentsResult();
                    apiResponse = response.body();
                    if (results != null) {
                        results = apiResponse.getResult();
                        notifyDataSetChanged();
                    }

                }

                @Override
                public void onFailure(Call<GetFavoriteContentsResult> call, Throwable t) {
                    Log.e("Tag", "error");

                }
            });


        }

        public void setData(int position) {
            Result current = results.get(position);
            Picasso.with(itemView.getContext()).load(current.getHeaderImageFileAddress()).centerCrop().fit().into(imageView);
            textView.setText(current.getSubject());


        }


    }


}
