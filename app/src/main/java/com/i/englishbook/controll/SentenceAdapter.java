package com.i.englishbook.controll;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.i.englishbook.R;
import com.i.englishbook.model.Category;
import com.i.englishbook.model.Sentence;

import java.util.ArrayList;

/**
 * Created by huytran on 8/17/2017.
 */

public class SentenceAdapter extends RecyclerView.Adapter<SentenceViewHolder> {
    ArrayList<Sentence> sentences;

    public SentenceAdapter(ArrayList<Sentence> sentences) {
        this.sentences = sentences;
    }

    @Override
    public SentenceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sentence, parent, false);
        return new SentenceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SentenceViewHolder holder, int position) {
        Sentence sentence = sentences.get(position);
        holder.bindingSentence(sentence,position);
    } 

    @Override
    public int getItemCount() {
        return sentences.size();
    }
}
