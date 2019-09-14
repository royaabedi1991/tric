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
import com.a3rick.a3rick.models.models.Trick.content_with_categoriId.GetContentResult;
import com.a3rick.a3rick.models.models.Trick.content_with_categoriId.Result;
import com.a3rick.a3rick.webService.Trick.FileApi;
import com.a3rick.a3rick.webService.Trick.RetrofitClient;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HoseAdapter extends RecyclerView.Adapter<HoseAdapter.Holder> {
    private LayoutInflater inflater;
    List<Result> result_s;


    public HoseAdapter(Context context, List<Result> result_s) {
        this.result_s = result_s;
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public HoseAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Holder holder = new Holder(inflater.inflate(R.layout.row_house, parent, false));

        holder.getContents();
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull HoseAdapter.Holder holder, final int position) {
        holder.setData(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Result current = result_s.get(position);
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
        return result_s.size();
    }


    public class Holder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;

        public Holder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.card_house);
            textView = itemView.findViewById(R.id.tvHouse);
        }


        private void getContents() {
            FileApi fileApi = RetrofitClient.getRetroClient().create(FileApi.class);
            Call<GetContentResult> call = fileApi.getContent(/*"{{Token}}",*/2, 1, 15, "LastItem");
            call.enqueue(new Callback<GetContentResult>() {
                @Override
                public void onResponse(Call<GetContentResult> call, Response<GetContentResult> response) {
                    GetContentResult apiResponse = new GetContentResult();
                    apiResponse = response.body();
                    if (result_s != null) {
                        result_s = apiResponse.getResult();
                        notifyDataSetChanged();
                    }

                }

                @Override
                public void onFailure(Call<GetContentResult> call, Throwable t) {
                    Log.e("Tag", "error");
//                    Toast.makeText(itemView.getContext(), "اتصال به اینترنت برقرار نیست", Toast.LENGTH_SHORT).show();


                }
            });


        }

        public void setData(int position) {
            Result current = result_s.get(position);
            Picasso.with(itemView.getContext()).load(current.getHeaderImageFileAddress()).centerCrop().fit().into(imageView);
            textView.setText(current.getSubject());


        }


    }


}
