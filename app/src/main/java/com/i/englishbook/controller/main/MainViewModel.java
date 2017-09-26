package com.i.englishbook.controller.main;

import android.content.Context;
import android.view.View;

import com.i.englishbook.common.SqlHelper;
import com.i.englishbook.controller.base.BaseViewModel;
import com.i.englishbook.model.Category;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by huytran on 8/17/2017.
 */

public class MainViewModel extends BaseViewModel<MainNavigator> {
    public MainViewModel(Context c) {
        context = c;
    }

    public void getCategories() {
        Observable.<ArrayList<Category>>create(e -> {
            try {
                e.onNext(SqlHelper.exeSql(context, "Select * from Categories", Category.class));
            } catch (Exception ex) {
                e.onError(new Throwable(ex));
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(next -> getNavigator().getCategoriesComplete(next),
                        error -> getNavigator().getCategoriesError(error.getMessage()), () -> {});

    }
}
