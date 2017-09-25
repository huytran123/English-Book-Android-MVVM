package com.i.englishbook.controll;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.i.englishbook.databinding.ItemCateBinding;
import com.i.englishbook.model.Category;

/**
 * Created by huytran on 8/17/2017.
 */

public class CategoryViewHolder extends RecyclerView.ViewHolder {
    ItemCateBinding binding;

    public CategoryViewHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    public void bindingCate(Category category) {
        binding.setCate(category);
    }
}
