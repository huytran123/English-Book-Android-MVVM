package com.i.englishbook.model;

import android.graphics.Color;
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
    @Column(Name = "id")
    public int Id;
    @Column(Name = "cate_id")
    public int CateId;
    @Column(Name = "e")
    public String E;
    @Column(Name = "v")
    public String V;

    public boolean IsSelected;
    public boolean IsPlayed;
    public String SpeedText;
    public int ColorSpeedText = Color.WHITE;


    public void onClick(View view) {
        ((DetailNavigator) view.getContext()).sentenceClick(CodeHelper.getInt(view.getTag(), 0));
    }

    public void onClickSpeech(View view) {
        ((DetailNavigator) ((View) view.getParent()).getContext()).onClickSpeech(CodeHelper.getInt(view.getTag(), 0));
    }
}
