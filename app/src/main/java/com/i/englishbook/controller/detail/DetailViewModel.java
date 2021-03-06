package com.i.englishbook.controller.detail;

import android.content.Context;
import android.databinding.ObservableField;
import android.graphics.Color;
import android.view.View;

import com.i.englishbook.R;
import com.i.englishbook.common.Common;
import com.i.englishbook.common.SqlHelper;
import com.i.englishbook.controller.base.BaseViewModel;
import com.i.englishbook.model.AppDB;
import com.i.englishbook.model.Category;
import com.i.englishbook.model.ModePlay;
import com.i.englishbook.model.Sentence;
import com.i.englishbook.model.StatusRead;
import com.i.englishbook.model.dao.SentenceDAO;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by huytran on 9/26/2017.
 */

public class DetailViewModel extends BaseViewModel<DetailNavigator> {

    public ObservableField<Boolean> IsPlay = new ObservableField<>(false);
    public ObservableField<Integer> NumberLoop = new ObservableField<>(0);
    public ObservableField<ModePlay> CurrentModePlay = new ObservableField<>(ModePlay.NEXT);
    public ObservableField<Category> CurrentCategory = new ObservableField<>(new Category());

    public ModePlay getCurrentPlayMode() {
        return CurrentModePlay.get();
    }

    public DetailViewModel(Context c) {
        this.context = c;
    }

    public void getSentences(int cateId) {
        try {
            CurrentCategory.set(AppDB.getINSTANCE(context).categoryDAO().getCategory(cateId));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        AppDB.getINSTANCE(context).sentenceDAO().getSentenceByCate(cateId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(next -> getNavigator().getSentencesComplete(next),
                        error -> getNavigator().getSentencesError(error.getMessage()),
                        () -> {
                        });

    }

    public void playClick(View view) {
        IsPlay.set(!IsPlay.get());
        if (IsPlay.get())
            getNavigator().playSentence(0);
        else
            getNavigator().stopSentence();

    }

    public void loopClick(View view) {
        if (NumberLoop.get() <= Common.MAX_LOOP_PLAY) {
            NumberLoop.set(NumberLoop.get() + 1);
            CurrentModePlay.set(ModePlay.LOOP);
        } else {
            NumberLoop.set(0);
            CurrentModePlay.set(ModePlay.NEXT);
        }
    }

    public void nextClick(View view) {
        CurrentModePlay.set(ModePlay.NEXT);
    }

    public void randomClick(View view) {
        CurrentModePlay.set(ModePlay.RANDOM);

    }

    public String compareTextSpeech(String originText, String textSpeech) {
        originText = originText.toUpperCase().replace(".", "").replace("?", "").replace("!", "");
        textSpeech = textSpeech.toUpperCase();
        String[] arrayOrigin = originText.split(" ");
        String[] arraySpeech = textSpeech.split(" ");
        int totalNumberComplete = 0;
        if (arrayOrigin.length == arraySpeech.length) {
            for (String t1 : arraySpeech) {
                for (String t2 : arrayOrigin) {
                    if (t1.equals(t2)) {
                        ++totalNumberComplete;
                        break;
                    }
                }
            }
        }
        float percent = (float) totalNumberComplete / arrayOrigin.length;
        if (percent > 0.8) {
            return StatusRead.COMPLETE;
        } else if (percent > 0.5) {
            return StatusRead.WARNING;
        }
        return StatusRead.ERROR;
    }

}
