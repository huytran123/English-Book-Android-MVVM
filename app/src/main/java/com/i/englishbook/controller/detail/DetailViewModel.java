package com.i.englishbook.controller.detail;

import android.content.Context;

import com.i.englishbook.common.SqlHelper;
import com.i.englishbook.controller.base.BaseViewModel;
import com.i.englishbook.controller.main.MainNavigator;
import com.i.englishbook.model.Category;
import com.i.englishbook.model.Sentence;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by huytran on 9/26/2017.
 */

public class DetailViewModel extends BaseViewModel<DetailNavigator> {

    public DetailViewModel(Context c){
        this.context = c;
    }

    public void getSentences(int cateId) {
        Observable.<ArrayList<Sentence>>create(e -> {
            try {
                e.onNext(SqlHelper.exeSql(context, String.format("Select * from Sentences where cat_id = %s", cateId), Sentence.class));
            } catch (Exception ex) {
                e.onError(new Throwable(ex));
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(next -> getNavigator().getSentencesComplete(next),
                        error -> getNavigator().getSentencesError(error.getMessage()), () -> {});

    }
}
