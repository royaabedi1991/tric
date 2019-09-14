package com.a3rick.a3rick.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.a3rick.a3rick.R;
import com.a3rick.a3rick.adapters.MyVideoAdapter;
import com.a3rick.a3rick.models.models.Trick.favorites.Result;

import java.util.ArrayList;
import java.util.List;

public class MyVideoFragment extends Fragment {
    LinearLayout linearLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_video, container, false);

        linearLayout = view.findViewById(R.id.loai_lenear);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                linearLayout.setVisibility(View.GONE);


            }
        }, 2000);


        RecyclerView myVideoRecyceler = (RecyclerView) view.findViewById(R.id.my_video_recycler);
        LinearLayoutManager myVideoManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        myVideoRecyceler.setLayoutManager(myVideoManager);
        MyVideoAdapter myVideoAdapter = new MyVideoAdapter(getContext(),getFavorite());
        myVideoRecyceler.setAdapter(myVideoAdapter);


        return view;
    }

    private List<Result> getFavorite() {
        List<Result> results = new ArrayList<>();

        for (int i = 0; i < 3; i++) {

            Result current = new Result();

            results.add(current);



        }
        return results;


    }
}
