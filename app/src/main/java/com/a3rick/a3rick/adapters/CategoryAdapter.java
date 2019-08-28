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
import com.a3rick.a3rick.activities.BeautyCategoryActivity;
import com.a3rick.a3rick.activities.CoockCategoryActivity;
import com.a3rick.a3rick.activities.FunCategoryActivity;
import com.a3rick.a3rick.activities.HouseCategoryActivity;
import com.a3rick.a3rick.models.ApiModels.Trick.Results.GetContentResult;
import com.a3rick.a3rick.models.ApiModels.Trick.Results.Result_;
import com.a3rick.a3rick.webService.Trick.FileApi;
import com.a3rick.a3rick.webService.Trick.RetrofitClient;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.Holder> {
    private LayoutInflater inflater;
    List<Result_> result_s;


    public CategoryAdapter(Context context, List<Result_> result_s) {
        this.result_s = result_s;
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public CategoryAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Holder holder = new Holder(inflater.inflate(R.layout.row_category_frag, parent, false));

        holder.getContents();
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.Holder holder, final int position) {
        holder.setData(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Result_ current = result_s.get(position);

                switch (position){

                    case 0:
                        Intent intent0 = new Intent(v.getContext(), BeautyCategoryActivity.class);
                        v.getContext().startActivity(intent0);
                        break;

                    case 1:
                        Intent intent1 = new Intent(v.getContext(), HouseCategoryActivity.class);
                        v.getContext().startActivity(intent1);
                        break;

                    case 2:
                        Intent intent2 = new Intent(v.getContext(), CoockCategoryActivity.class);
                        v.getContext().startActivity(intent2);
                        break;

                    case 3:
                        Intent intent3 = new Intent(v.getContext(), FunCategoryActivity.class);
                        v.getContext().startActivity(intent3);
                        break;







                }





//                intent.putExtra("VIDEOADRESS", current.getVideoFileAddress());
//                intent.putExtra("SUBJECT", current.getSubject());
//                intent.putExtra("BODY", current.getBody());
//                intent.putExtra("HEADERIMAGE", current.getHeaderImageFileAddress());


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
            imageView = itemView.findViewById(R.id.imgage_category);
            textView = itemView.findViewById(R.id.tv_categori);
        }


        private void getContents() {
            FileApi fileApi = RetrofitClient.getRetroClient().create(FileApi.class);
            Call<GetContentResult> call = fileApi.getContent(/*"{{Token}}",*/1, 1, 4, "LastItem");
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

                }
            });


        }

        public void setData(int position) {
            Result_ current = result_s.get(position);
            Picasso.with(itemView.getContext()).load(current.getHeaderImageFileAddress()).centerCrop().fit().into(imageView);
            textView.setText(current.getSubject());


        }


    }


}
