package com.i.englishbook.controller.detail;

import android.content.res.AssetFileDescriptor;
import android.databinding.DataBindingUtil;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.i.englishbook.R;
import com.i.englishbook.common.Keys;
import com.i.englishbook.controll.SentenceAdapter;
import com.i.englishbook.controller.base.BaseView;
import com.i.englishbook.databinding.ActivityDetailBinding;
import com.i.englishbook.model.Sentence;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by huytran on 9/26/2017.
 */

public class DetailView extends BaseView implements DetailNavigator, MediaPlayer.OnCompletionListener {

    ActivityDetailBinding binding;
    DetailViewModel viewModel;
    int cateId;
    MediaPlayer mediaPlayer;

    SentenceAdapter sentenceAdapter;
    ArrayList<Sentence> sentences;
    int currentSentence = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        viewModel = new DetailViewModel(this);
        viewModel.setNavigator(this);
        cateId = getIntent().getIntExtra(Keys.CATEGORIY_ID, 0);
        binding.setVm(viewModel);
        sentences = new ArrayList<>();
        sentenceAdapter = new SentenceAdapter(sentences);
        binding.recycler.setAdapter(sentenceAdapter);
        viewModel.getSentences(cateId);
    }

    @Override
    public void getSentencesComplete(ArrayList<Sentence> sentences) {
        this.sentences.clear();
        this.sentences.addAll(sentences);
        sentenceAdapter.notifyDataSetChanged();
        if (viewModel.IsPlay.get())
            playSentence(currentSentence);

    }

    @Override
    public void getSentencesError(String mess) {

    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        sentences.get(currentSentence).IsSelected = false;
        sentenceAdapter.notifyItemChanged(currentSentence);
        if (!viewModel.IsPlay.get()) return;
        ++currentSentence;
        if (currentSentence > sentences.size() - 1) {
            viewModel.getSentences(++cateId);
            currentSentence = 0;
            stopSentence();
            return;
        }
        playSentence(currentSentence);
    }

    @Override
    public void playSentence(int index) {
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setOnCompletionListener(this);
        }
        binding.recycler.smoothScrollToPosition(index);
        AssetFileDescriptor afd = null;
        try {
            mediaPlayer.reset();
            afd = getAssets().openFd(String.format("audios/%s.mp3", sentences.get(index).Id - 100));
            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mediaPlayer.prepare();
            mediaPlayer.start();
            afd.close();
            sentences.get(index).IsPlayed = true;
            if (viewModel.IsPlay.get())
                sentences.get(index).IsSelected = true;
            sentenceAdapter.notifyItemChanged(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopSentence();
    }

    @Override
    public void stopSentence() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void sentenceClick(int index) {
        viewModel.IsPlay.set(false);
        stopSentence();
        playSentence(index);
        currentSentence = index;
    }
}
