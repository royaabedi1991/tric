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
import com.a3rick.a3rick.models.RecyclerViewModels.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        RecyclerView categoryRecyceler = (RecyclerView) view.findViewById(R.id.category_recycler);
        LinearLayoutManager categoryManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true);
        categoryRecyceler.setLayoutManager(categoryManager);
        CategoryAdapter categoryAdapter = new CategoryAdapter(getContext(), getCategory());
        categoryRecyceler.setAdapter(categoryAdapter);

//        categoryRecyceler.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Fragment fragment = new Fragment();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.home_container, fragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//
//
//
//            }
//        });
        return view;
    }

    private List<Category> getCategory() {
        List<Category> categories = new ArrayList<>();

        int[] drawables = new int[]{

                R.drawable.pic_10,
                R.drawable.pic_24,
                R.drawable.pic_23,
                R.drawable.pic_25,


        };

        String[] titles = new String[]{
                "سرگرمی",
                "آشپزی",
                "زیبایی",
                "خانه داری",


        };


        for (int i = 0; i < 4; i++) {
            Category current = new Category();
            current.setResource(drawables[i]);
            current.setTitle(titles[i]);
            categories.add(current);
        }
        return categories;
    }

}
