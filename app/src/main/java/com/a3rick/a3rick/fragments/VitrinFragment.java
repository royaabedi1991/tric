package com.a3rick.a3rick.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a3rick.a3rick.R;
import com.a3rick.a3rick.activities.BeautyCategoryActivity;
import com.a3rick.a3rick.activities.CoockCategoryActivity;
import com.a3rick.a3rick.activities.FunCategoryActivity;
import com.a3rick.a3rick.activities.HouseCategoryActivity;
import com.a3rick.a3rick.adapters.BannerAdapter;
import com.a3rick.a3rick.adapters.BeautyAdapter;
import com.a3rick.a3rick.adapters.CoockAdapter;
import com.a3rick.a3rick.adapters.FavoriteAdapter;
import com.a3rick.a3rick.adapters.FunAdapter;
import com.a3rick.a3rick.adapters.HoseAdapter;
import com.a3rick.a3rick.models.ApiModels.Teepeto.Results.Result_;
import com.a3rick.a3rick.models.RecyclerViewModels.Banner;
import com.a3rick.a3rick.models.RecyclerViewModels.Favorite;

import java.util.ArrayList;
import java.util.List;


public class VitrinFragment extends Fragment {
    TextView seeAllBeauty;
    TextView seeAllCoock;
    TextView seeAllHouse;
    TextView seeAllFun;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vitrin, container, false);


        seeAllBeauty = view.findViewById(R.id.seen_all_beauity);
        seeAllCoock = view.findViewById(R.id.seen_all_coock);
        seeAllHouse = view.findViewById(R.id.seen_all_house);
        seeAllFun = view.findViewById(R.id.seen_all_fun);

        seeAllBeauty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), BeautyCategoryActivity.class);
                startActivity(intent);

            }
        });
        seeAllCoock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CoockCategoryActivity.class);
                startActivity(intent);
            }
        });
        seeAllHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HouseCategoryActivity.class);
                startActivity(intent);
            }
        });
        seeAllFun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FunCategoryActivity.class);
                startActivity(intent);
            }
        });

// Set the adapter

//BannerRecycler
        RecyclerView bannerRecycler = (RecyclerView) view.findViewById(R.id.recyceler_banner);
        LinearLayoutManager bannerManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true);
        bannerRecycler.setLayoutManager(bannerManager);
        BannerAdapter bannerAdapter = new BannerAdapter(getContext(), getBanners());
        bannerRecycler.setAdapter(bannerAdapter);


//FavoriteRecycler
        RecyclerView favoriteRecyceler = (RecyclerView) view.findViewById(R.id.recyceler_Favorit);
        FavoriteAdapter favoriteAdapter = new FavoriteAdapter(getContext(), getFavorites());
        favoriteRecyceler.setAdapter(favoriteAdapter);
        LinearLayoutManager favoriteManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true);
        favoriteRecyceler.setLayoutManager(favoriteManager);


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

    private List<Result_> getResults() {
        List<Result_> result_s = new ArrayList<>();

        for (int i = 0; i < 22; i++) {

            Result_ current = new Result_();

            result_s.add(current);

        }
        return result_s;


    }


    private List<Banner> getBanners() {

        List<Banner> banners = new ArrayList<>();

        for (int i = 0; i < 5; i++) {

            Banner current = new Banner();
            current.setRecource(R.drawable.banner);
            current.setTitle("طرز تهیه سبزی پلو");
            banners.add(current);


        }
        return banners;
    }

    private List<Favorite> getFavorites() {
        List<Favorite> favorites = new ArrayList<>();
        int[] drawables = {
                R.drawable.pic_1,
                R.drawable.pic_2,
                R.drawable.pic_3,
                R.drawable.pic_1,
                R.drawable.pic_2,


        };

        for (int i = 0; i < 5; i++) {

            Favorite current = new Favorite();
            current.setRecource(drawables[i]);
            favorites.add(current);


        }
        return favorites;

    }



}
