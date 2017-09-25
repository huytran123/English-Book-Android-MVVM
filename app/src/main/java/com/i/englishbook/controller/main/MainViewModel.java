package com.i.englishbook.controller.main;

import android.content.Context;

import com.i.englishbook.common.SqlHelper;
import com.i.englishbook.controller.base.BaseViewModel;
import com.i.englishbook.model.Category;

import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * Created by huytran on 8/17/2017.
 */

public class MainViewModel extends BaseViewModel {
    public MainViewModel(Context c) {
        context = c;
    }

    public Observable<ArrayList<Category>> getCategories() throws IllegalAccessException, InstantiationException {
        return Observable.create(e -> {
            e.onNext(SqlHelper.exeSql(context, "Select * from Categories", Category.class));

        });
    }


}
