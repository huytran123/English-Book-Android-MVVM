package com.i.englishbook.controller.detail;

import android.content.Context;
import android.databinding.ObservableField;
import android.graphics.Color;
import android.view.View;

import com.i.englishbook.R;
import com.i.englishbook.common.Common;
import com.i.englishbook.common.SqlHelper;
import com.i.englishbook.controller.base.BaseViewModel;
import com.i.englishbook.model.Category;
import com.i.englishbook.model.ModePlay;
import com.i.englishbook.model.Sentence;

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
            CurrentCategory.set(SqlHelper.<Category>exeSql(this.context, String.format("Select * from Categories where id = %s", cateId), Category.class).get(0));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Observable.<ArrayList<Sentence>>create(e -> {
            try {
                e.onNext(SqlHelper.exeSql(context, String.format("Select * from Sentences where cat_id = %s", cateId), Sentence.class));
            } catch (Exception ex) {
                e.onError(new Throwable(ex));
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(next -> getNavigator().getSentencesComplete(next),
                        error -> getNavigator().getSentencesError(error.getMessage()), () -> {
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

    public int compareTextSpeech(String originText, String textSpeech) {
        originText = originText.toUpperCase().replace("[\\W]", "");
        textSpeech = textSpeech.toUpperCase();
        String[] arrayOrigin = originText.split(" ");
        String[] arraySpeech = textSpeech.split(" ");
        int totalNumberComplete = 0;
        for (String t1 : arraySpeech) {
            for (String t2 : arrayOrigin) {
                if (t1.equals(t2)) {
                    ++totalNumberComplete;
                    break;
                }
            }
        }
        float percent = (float) totalNumberComplete / arrayOrigin.length;
        if (percent > 0.5) return context.getResources().getColor(R.color.warning_color);
        else if (percent > 0.8) return context.getResources().getColor(R.color.complete_color);
        return context.getResources().getColor(R.color.error_color);
    }

}
