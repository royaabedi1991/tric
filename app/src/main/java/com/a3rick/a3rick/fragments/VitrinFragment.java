package com.a3rick.a3rick.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.a3rick.a3rick.R;
import com.a3rick.a3rick.adapters.BannerAdapter;
import com.a3rick.a3rick.adapters.BeautyAdapter;
import com.a3rick.a3rick.adapters.CoockAdapter;
import com.a3rick.a3rick.adapters.FavoriteAdapter;
import com.a3rick.a3rick.adapters.FunAdapter;
import com.a3rick.a3rick.adapters.HouseAdapter;
import com.a3rick.a3rick.models.RecyclerViewModels.Banner;
import com.a3rick.a3rick.models.RecyclerViewModels.Beauty;
import com.a3rick.a3rick.models.RecyclerViewModels.Coock;
import com.a3rick.a3rick.models.RecyclerViewModels.Favorite;
import com.a3rick.a3rick.models.RecyclerViewModels.Fun;
import com.a3rick.a3rick.models.RecyclerViewModels.House;

import java.util.ArrayList;
import java.util.List;


public class VitrinFragment extends Fragment {

    int selectedItemId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vitrin, container, false);



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
        BeautyAdapter beautyAdapter = new BeautyAdapter(getContext(), getBeauties());
        beautyRecycler.setAdapter(beautyAdapter);
        LinearLayoutManager beautyManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true);
        beautyRecycler.setLayoutManager(beautyManager);


//HouseRecycler

        RecyclerView houseRecycler = (RecyclerView) view.findViewById(R.id.recyceler_khanedari_category);
        HouseAdapter houseAdapter = new HouseAdapter(getContext(), getHouses());

        houseRecycler.setAdapter(houseAdapter);
        LinearLayoutManager houseManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true);
        houseRecycler.setLayoutManager(houseManager);


//CoockRecycler

        RecyclerView coockRecycler = (RecyclerView) view.findViewById(R.id.recyceler_coock_category);
        CoockAdapter coockAdapter = new CoockAdapter(getContext(), getCoockes());
        coockRecycler.setAdapter(coockAdapter);
        LinearLayoutManager coockManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true);
        coockRecycler.setLayoutManager(coockManager);


        //FunRecycler
        RecyclerView funRecycler = (RecyclerView) view.findViewById(R.id.recyceler_fun_category);
        FunAdapter funAdapter = new FunAdapter(getContext(), getFunes());
        funRecycler.setAdapter(funAdapter);
        LinearLayoutManager funManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true);
        funRecycler.setLayoutManager(funManager);

        return view;
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

    private List<Fun> getFunes() {

        List<Fun> funs = new ArrayList<>();
        int[] drawables = new int[]{

                R.drawable.pic_21,
                R.drawable.pic_22,
                R.drawable.pic_23,
                R.drawable.pic_24,
                R.drawable.pic_25,

        };
        for (int i = 0; i < 5; i++) {
            Fun current = new Fun();
            current.setResource(drawables[i]);
            funs.add(current);
        }
        return funs;
    }

    private List<Coock> getCoockes() {
        List<Coock> coocks = new ArrayList<>();
        int[] drawables = new int[]{
                R.drawable.pic_16,
                R.drawable.pic_17,
                R.drawable.pic_18,
                R.drawable.pic_19,
                R.drawable.pic_20,
        };
        for (int i = 0; i < 5; i++) {
            Coock current = new Coock();
            current.setResorce(drawables[i]);

            coocks.add(current);


        }
        return coocks;
    }

    private List<House> getHouses() {
        List<House> houses = new ArrayList<>();
        int[] drawables = new int[]{

                R.drawable.pic_10,
                R.drawable.pic_11,
                R.drawable.pic_12,
                R.drawable.pic_13,
                R.drawable.pic_14,


        };
        for (int i = 0; i < 5; i++) {

            House current = new House();
            current.setResource(drawables[i]);
            houses.add(current);

        }
        return houses;
    }


    private List<Beauty> getBeauties() {

        List<Beauty> beauties = new ArrayList<>();
        int[] drawables = new int[]{
                R.drawable.pic_5,
                R.drawable.pic_4,
                R.drawable.pic_6,
                R.drawable.pic_7,
                R.drawable.pic_8,
                R.drawable.pic_9,
                R.drawable.pic_5,


        };

        for (int i = 0; i < 7; i++) {

            Beauty current = new Beauty();
            current.setResource(drawables[i]);
            beauties.add(current);

        }
        return beauties;


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
