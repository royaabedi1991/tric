package com.a3rick.a3rick.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.a3rick.a3rick.R;
import com.a3rick.a3rick.adapters.CategoryAdapter;
import com.a3rick.a3rick.models.ApiModels.Teepeto.Results.Result_;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        RecyclerView categoryRecyceler = (RecyclerView) view.findViewById(R.id.category_recycler);
        LinearLayoutManager categoryManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        categoryRecyceler.setLayoutManager(categoryManager);
        CategoryAdapter categoryAdapter = new CategoryAdapter(getContext(), getResults());
        categoryRecyceler.setAdapter(categoryAdapter);


        return view;
    }

private List<Result_> getResults() {
    List<Result_> result_s = new ArrayList<>();

    for (int i = 0; i < 3; i++) {

        Result_ current = new Result_();

        result_s.add(current);



    }
    return result_s;


}
}
