package com.i.englishbook.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.i.englishbook.R;
import com.i.englishbook.annotations.Column;
import com.i.englishbook.annotations.Table;
import com.i.englishbook.controller.main.MainNavigator;
import com.i.englishbook.controller.main.MainView;

/**
 * Created by huytran on 8/17/2017.
 */
@Entity(tableName =  "Categories")
public class Category {
    @ColumnInfo(name = "e")
    public String E;
    @ColumnInfo(name = "v")
    public String V;
    @ColumnInfo(name = "id")
    @PrimaryKey
    public int Id;

    public String getImgCateName() {
        return String.format("ic_topic_%s", Id);
    }


    public Category() {
    }

    public Category(int id, String e, String v, int imageResource) {
        Id = id;
        E = e;
        V = v;
    }

    public void onClick(View view) {
        ((MainNavigator) view.getContext()).cateOnClick(view);
    }
}
