package com.a3rick.a3rick.holders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.a3rick.a3rick.R;

public class BannerHolder extends RecyclerView.ViewHolder {

    CardView cardBaner;
    TextView title;
    ImageView imageView;



    public BannerHolder(View itemView) {
        super(itemView);

        cardBaner = (CardView) itemView.findViewById(R.id.card_baner);
        title = (TextView) itemView.findViewById(R.id.title);
        imageView = (ImageView) itemView.findViewById(R.id.img_banner);
    }
}
