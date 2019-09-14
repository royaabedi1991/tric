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
import com.a3rick.a3rick.adapters.CategoryAdapter;
import com.a3rick.a3rick.models.models.Trick.categories.Result;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {
    LinearLayout linearLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        linearLayout = view.findViewById(R.id.loai_lenear);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                linearLayout.setVisibility(View.GONE);


            }
        }, 2000);


        RecyclerView categoryRecyceler = (RecyclerView) view.findViewById(R.id.category_recycler);
        LinearLayoutManager categoryManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        categoryRecyceler.setLayoutManager(categoryManager);
        CategoryAdapter categoryAdapter = new CategoryAdapter(getContext(), getAllResults());
        categoryRecyceler.setAdapter(categoryAdapter);


        return view;
    }

    private List<Result> getAllResults() {
        List<Result> results = new ArrayList<>();

        for (int i = 0; i < 3; i++) {

            Result current = new Result();

            results.add(current);


        }
        return results;


    }
}
