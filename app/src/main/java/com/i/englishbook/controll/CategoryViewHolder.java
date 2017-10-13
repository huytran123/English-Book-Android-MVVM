package com.i.englishbook.controll;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.i.englishbook.databinding.ItemCateBinding;
import com.i.englishbook.databinding.ItemCateGridBinding;
import com.i.englishbook.model.Category;

/**
 * Created by huytran on 8/17/2017.
 */

public class CategoryViewHolder extends RecyclerView.ViewHolder {
    ItemCateBinding binding;
    ItemCateGridBinding bindingGrid;
    boolean isListLayout;

    public CategoryViewHolder(View itemView, boolean isList) {
        super(itemView);


        isListLayout = isList;
        if (isListLayout) {
            binding = DataBindingUtil.bind(itemView);
        } else {
            bindingGrid = DataBindingUtil.bind(itemView);
        }
    }

    public void bindingCate(Category category, int position) {
        if (isListLayout) {
            binding.setCate(category);
            binding.setPosition(position);
        } else {
            bindingGrid.setCate(category);
            bindingGrid.setPosition(position);
        }

    }
}
