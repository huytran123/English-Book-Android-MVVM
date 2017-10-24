package com.i.englishbook.controll;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.i.englishbook.R;
import com.i.englishbook.common.CodeHelper;
import com.i.englishbook.model.Category;

import java.util.ArrayList;

/**
 * Created by huytran on 8/17/2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {
    ArrayList<Category> cates;
    public boolean IsListLayout = true;
    int oldPosition = -1;

    public CategoryAdapter(ArrayList<Category> cates) {
        this.cates = cates;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(IsListLayout ? R.layout.item_cate : R.layout.item_cate_grid, parent, false);
        return new CategoryViewHolder(view,IsListLayout);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        Category cate = cates.get(position);
        holder.bindingCate(cate, position);
        View view = holder.itemView;
        int animationId = R.anim.item_animation_fall_up;
        if(oldPosition!=-1 && position< oldPosition) {
            animationId = R.anim.item_animation_fall_down;
        }
        oldPosition = position;
        Animation animation = AnimationUtils.loadAnimation(view.getContext(), animationId);
        view.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return cates.size();
    }


}
