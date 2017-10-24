package com.i.englishbook.controller.detail;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.i.englishbook.R;
import com.i.englishbook.common.Keys;
import com.i.englishbook.controll.SentenceAdapter;
import com.i.englishbook.controller.base.BaseView;
import com.i.englishbook.databinding.ActivityDetailBinding;
import com.i.englishbook.model.ModePlay;
import com.i.englishbook.model.Sentence;
import com.i.englishbook.model.StatusRead;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

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
    String TAG = "DetailView";
    boolean isSpeech2Text = false;

    private static final int REQUEST_CODE_SPEED_TEXT = 10001;

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
        setSupportActionBar(binding.myToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
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
        Sentence s = sentences.get(currentSentence);
        s.IsSelected = false;
        sentenceAdapter.notifyItemChanged(currentSentence);

        if (isSpeech2Text) {
            isSpeech2Text = false;
            promptSpeechInput(s.E);
        }

        if (!viewModel.IsPlay.get()) return;
        ++currentSentence;
        if (currentSentence > sentences.size() - 1) {
            playCompleteCategory();
            return;
        }
        playSentence(currentSentence);
    }

    @Override
    public void playSentence(int index) {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            stopSentence();
        }
        if (index != currentSentence)
            currentSentence = index;
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
            sentences.get(currentSentence).IsSelected = false;
            sentenceAdapter.notifyItemChanged(currentSentence);
            currentSentence = 0;
        }
    }

    @Override
    public void sentenceClick(int index) {
        viewModel.IsPlay.set(false);
        stopSentence();
        playSentence(index);
        currentSentence = index;
    }

    @Override
    public void onClickSpeech(int index) {
        sentenceClick(index);
        isSpeech2Text = true;
    }

    @Override
    public void onClickSpeechSlow(int index) {
        float playbackSpeed = 0.95f;
        SoundPool soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
        AssetFileDescriptor afd = null;
        try {
            afd = getAssets().openFd(String.format("audios/%s.mp3", sentences.get(index).Id - 100));
            int soundId = soundPool.load(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength(), 1);
            AudioManager mgr = (AudioManager) getSystemService(AUDIO_SERVICE);
            final float volume = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

            soundPool.setOnLoadCompleteListener((soundPool1, sampleId, status) -> {
                soundPool.play(soundId, volume, volume, 1, 0, playbackSpeed);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_SPEED_TEXT && data != null) {

                ArrayList<String> result = data
                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                if (result != null && result.size() > 0) {
                    Sentence s = sentences.get(currentSentence);
                    s.MyEnglish = result.get(0).toUpperCase();
                    s.StatusRead = viewModel.compareTextSpeech(s.E, s.MyEnglish);
                    sentenceAdapter.notifyItemChanged(currentSentence);
                    s.update(this);
                } else {
                    Toast.makeText(this, "Not Found", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void playCompleteCategory() {
        if (viewModel.getCurrentPlayMode() == ModePlay.NEXT) {
            ++cateId;
        } else if (viewModel.getCurrentPlayMode() == ModePlay.RANDOM) {
            cateId = new Random().nextInt(59) + 1;
        } else {
            if (viewModel.NumberLoop.get() == 0)
                ++cateId;
            else
                viewModel.NumberLoop.set(viewModel.NumberLoop.get() - 1);
        }
        viewModel.getSentences(cateId);
        currentSentence = 0;
        stopSentence();
    }

    private void promptSpeechInput(String text) {
        if (!checkPermission())
            return;
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, text);
        try {
            startActivityForResult(intent, REQUEST_CODE_SPEED_TEXT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), "Not found",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
