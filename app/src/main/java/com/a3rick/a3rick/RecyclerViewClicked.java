package com.a3rick.a3rick;

import android.view.View;

import com.a3rick.a3rick.models.models.Trick.content_with_categoriId.Result;

public interface RecyclerViewClicked {
    void onIteMRecycler(View view, String id, Result current, ChangeCategoryItem changeCategoryItem);
}
