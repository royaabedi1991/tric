package com.a3rick.a3rick;

import android.util.Log;

import com.a3rick.a3rick.adapters.CategoriesAdapter;
import com.a3rick.a3rick.models.models.Trick.content_with_categoriId.Result;

import java.util.List;

public class tt implements ChangeCategoryItem {
    int s;
    CategoriesAdapter categoryAdapter;
    List<Result> result_s;



    public tt(int i/*,CategoriesAdapter categoryAdapter, List<Result> result_s*/) {
        this.s = s;
      /*  this.categoryAdapter = categoryAdapter;
        this.result_s = result_s;*/
    }

    @Override
    public void changeDate(int countView, int likeCount, int Id) {

        result_s.get(Id).setLikeCount(likeCount);
        result_s.get(Id).setViewCount(countView);
//        categoryAdapter.notifyDataSetChanged();
        Log.e("D", "Dd");
    }
}
