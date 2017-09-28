package com.i.englishbook.controll;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.i.englishbook.databinding.ItemCateBinding;
import com.i.englishbook.databinding.ItemSentenceBinding;
import com.i.englishbook.model.Category;
import com.i.englishbook.model.Sentence;

/**
 * Created by huytran on 8/17/2017.
 */

public class SentenceViewHolder extends RecyclerView.ViewHolder {
    ItemSentenceBinding binding;

    public SentenceViewHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    public void bindingSentence(Sentence sentence, int position) {
        binding.setSentence(sentence);
        binding.setPosition(position);
    }
}
