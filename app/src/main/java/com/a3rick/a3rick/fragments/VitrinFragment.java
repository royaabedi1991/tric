package com.a3rick.a3rick.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a3rick.a3rick.R;
import com.a3rick.a3rick.activities.CategoryActivity;
import com.a3rick.a3rick.adapters.BeautyAdapter;
import com.a3rick.a3rick.adapters.CoockAdapter;
import com.a3rick.a3rick.adapters.FunAdapter;
import com.a3rick.a3rick.adapters.HoseAdapter;
import com.a3rick.a3rick.models.models.Trick.content_with_categoriId.Result;

import java.util.ArrayList;
import java.util.List;


public class VitrinFragment extends Fragment {
    TextView seeAllBeauty;
    TextView seeAllCoock;
    TextView seeAllHouse;
    TextView seeAllFun;
    LinearLayout linearLayout;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.fragment_vitrin, container, false);

        linearLayout = view.findViewById(R.id.loai_lenear);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                linearLayout.setVisibility(View.GONE);


            }
        }, 2000);


        seeAllBeauty = view.findViewById(R.id.seen_all_beauity);
        seeAllCoock = view.findViewById(R.id.seen_all_coock);
        seeAllHouse = view.findViewById(R.id.seen_all_house);
        seeAllFun = view.findViewById(R.id.seen_all_fun);

        seeAllBeauty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), CategoryActivity.class);
                intent.putExtra("POSOTION", 2);
                intent.putExtra("TITLE", "زیبایی");
                startActivity(intent);
                seeAllBeauty.setClickable(false);

            }
        });
        seeAllCoock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CategoryActivity.class);
                intent.putExtra("POSOTION", 0);
                intent.putExtra("TITLE", "آشپزی");
                startActivity(intent);
                seeAllCoock.setClickable(false);
            }
        });
        seeAllHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CategoryActivity.class);
                intent.putExtra("POSOTION", 1);
                intent.putExtra("TITLE", "خانه داری");
                startActivity(intent);
                seeAllHouse.setClickable(false);

            }
        });
        seeAllFun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CategoryActivity.class);
                intent.putExtra("POSOTION", 3);
                intent.putExtra("TITLE", "سرگرمی");
                startActivity(intent);
                seeAllFun.setClickable(false);
            }
        });

//BeautyRecycler
        RecyclerView beautyRecycler = (RecyclerView) view.findViewById(R.id.recyceler_beauty_category);
        BeautyAdapter beautyAdapter = new BeautyAdapter(getContext(), getResults());
        beautyRecycler.setAdapter(beautyAdapter);
        LinearLayoutManager beautyManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true);
        beautyRecycler.setLayoutManager(beautyManager);


//HouseRecycler

        RecyclerView houseRecycler = (RecyclerView) view.findViewById(R.id.recyceler_khanedari_category);
        HoseAdapter houseAdapter = new HoseAdapter(getContext(), getResults());

        houseRecycler.setAdapter(houseAdapter);
        LinearLayoutManager houseManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true);
        houseRecycler.setLayoutManager(houseManager);


//CoockRecycler

        RecyclerView coockRecycler = (RecyclerView) view.findViewById(R.id.recyceler_coock_category);
        CoockAdapter coockAdapter = new CoockAdapter(getContext(), getResults());
        coockRecycler.setAdapter(coockAdapter);
        LinearLayoutManager coockManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true);
        coockRecycler.setLayoutManager(coockManager);


//FunRecycler
        RecyclerView funRecycler = (RecyclerView) view.findViewById(R.id.recyceler_fun_category);
        FunAdapter funAdapter = new FunAdapter(getContext(), getResults());
        funRecycler.setAdapter(funAdapter);
        LinearLayoutManager funManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true);
        funRecycler.setLayoutManager(funManager);

        return view;
    }

    private List<Result> getResults() {
        List<Result> result_s = new ArrayList<>();

        for (int i = 0; i < 22; i++) {

            Result current = new Result();

            result_s.add(current);

        }
        return result_s;


    }


    public void checkConnection() {

        if (isOnline()) {



            } else {


            }


    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        seeAllBeauty.setClickable(true);
        seeAllFun.setClickable(true);
        seeAllHouse.setClickable(true);
        seeAllCoock.setClickable(true);
    }
}
