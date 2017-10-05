package com.i.englishbook.model;

import android.view.View;

import com.i.englishbook.annotations.Column;
import com.i.englishbook.annotations.Table;
import com.i.englishbook.controller.main.MainNavigator;
import com.i.englishbook.controller.main.MainView;

/**
 * Created by huytran on 8/17/2017.
 */
@Table(Name = "Categories")
public class Category {
    @Column(Name = "e")
    public String E;
    @Column(Name = "v")
    public String V;
    @Column(Name = "id")
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
