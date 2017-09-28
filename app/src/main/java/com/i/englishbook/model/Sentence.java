package com.i.englishbook.model;

import android.view.View;

import com.i.englishbook.annotations.Column;
import com.i.englishbook.annotations.Table;
import com.i.englishbook.common.CodeHelper;
import com.i.englishbook.controller.detail.DetailNavigator;

/**
 * Created by huytran on 8/17/2017.
 */

@Table(Name = "Sentence")
public class Sentence {
    @Column(Name = "id", Type = DBType.INTEGER)
    public int Id;
    @Column(Name = "cate_id", Type = DBType.INTEGER)
    public int CateId;
    @Column(Name = "e")
    public String E;
    @Column(Name = "v")
    public String V;

    public boolean IsSelected;
    public boolean IsPlayed;

    public void onClick(View view) {
        ((DetailNavigator) view.getContext()).sentenceClick(CodeHelper.getInt(view.getTag(), 0));
    }
}
