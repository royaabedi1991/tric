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
import com.a3rick.a3rick.models.RecyclerViewModels.Banner;

import java.util.List;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.Holder> {



    private List<Banner> banners;
    private LayoutInflater inflater;
    private Context context;


    public BannerAdapter(List<Banner> banners, LayoutInflater inflater, Context context) {
        this.banners = banners;
        this.inflater = inflater;
        this.context = context;


    }


    public BannerAdapter(Context context, List<Banner> banners) {
        this.banners = banners;

        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public BannerAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Holder holder = new Holder(inflater.inflate(R.layout.row_banner, parent, false));
        return holder;



    }


    @Override
    public void onBindViewHolder(@NonNull BannerAdapter.Holder holder, final int position) {
        holder.setData(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                Context context1 = v.getContext();
                Intent intent = new Intent(context1, ContentActivity.class);
                intent.putExtra("BaNNERITEMPOSITION", position);

                context1.startActivity(intent);

            }
        });



    }

    // TODO: 25/05/2019
    private void getContentWithTag(String Token, int CategoryId, int PageNumber, int RowCount, String QueryType, List<Integer> TagIds) {
//        FileApi fileApi = RetrofitClient.getRetroClient().create(FileApi.class);
//
//        Call<GetContentResult> call = fileApi.getContentWithTag(Token, CategoryId, PageNumber, RowCount, QueryType, TagIds);
//        call.enqueue(new Callback<GetContentResult>() {
//            @Override
//            public void onResponse(Call<GetContentResult> call, Response<GetContentResult> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<GetContentResult> call, Throwable t) {
//
//            }
//
//        });

    }

    @Override
    public int getItemCount() {
        return banners.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;
        private CardView cardView;
        //private CardView cardView2;

        public Holder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_banner);
            textView = itemView.findViewById(R.id.tvBaner);
            cardView = itemView.findViewById(R.id.card_baner);


        }


        public void setData(int position) {

            Banner current = banners.get(position);
            imageView.setImageResource(current.getRecource());


        }
    }
}
