package com.i.englishbook.controller.detail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.i.englishbook.R;
import com.i.englishbook.common.Keys;
import com.i.englishbook.controll.SentenceAdapter;
import com.i.englishbook.controller.base.BaseView;
import com.i.englishbook.databinding.ActivityDetailBinding;
import com.i.englishbook.model.Sentence;

import java.util.ArrayList;

/**
 * Created by huytran on 9/26/2017.
 */

public class DetailView extends BaseView implements DetailNavigator {

    ActivityDetailBinding binding;
    DetailViewModel detailViewModel;
    int cateId;

    SentenceAdapter sentenceAdapter;
    ArrayList<Sentence> sentences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        detailViewModel = new DetailViewModel(this);
        detailViewModel.setNavigator(this);
        cateId = getIntent().getIntExtra(Keys.CATEGORIY_ID, 0);

        sentences = new ArrayList<>();
        sentenceAdapter = new SentenceAdapter(sentences);
        binding.recycler.setAdapter(sentenceAdapter);
        detailViewModel.getSentences(cateId);
    }

    @Override
    public void getSentencesComplete(ArrayList<Sentence> sentences) {
        this.sentences.addAll(sentences);
        sentenceAdapter.notifyDataSetChanged();

    }

    @Override
    public void getSentencesError(String mess) {

    }
}
