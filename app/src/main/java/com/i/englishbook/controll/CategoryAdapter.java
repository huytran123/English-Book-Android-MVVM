package com.i.englishbook.controll;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.i.englishbook.R;
import com.i.englishbook.model.Category;

import java.util.ArrayList;

/**
 * Created by huytran on 8/17/2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {
    ArrayList<Category> cates;

    public CategoryAdapter(ArrayList<Category> cates) {
        this.cates = cates;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cate, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        Category cate = cates.get(position);
        holder.bindingCate(cate, position);
    }

    @Override
    public int getItemCount() {
        return cates.size();
    }
}
