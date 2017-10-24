package com.i.englishbook.model;

import android.content.Context;
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
    @Column(Name = "status_read")
    public String StatusRead;
    @Column(Name = "is_favorite")
    public boolean IsFavorite;
    @Column(Name = "my_e")
    public String MyEnglish;


    public boolean IsSelected;
    public boolean IsPlayed;


    public void onClick(View view) {
        ((DetailNavigator) view.getContext()).sentenceClick(CodeHelper.getInt(view.getTag(), 0));
    }

    public void onClickSpeech(View view) {
        ((DetailNavigator) ((View) view.getParent()).getContext()).onClickSpeech(CodeHelper.getInt(view.getTag(), 0));
    }

    public void onClickSpeechSloow(View view) {
        ((DetailNavigator) ((View) view.getParent()).getContext()).onClickSpeechSlow(CodeHelper.getInt(view.getTag(), 0));
    }

    public void update(Context c){

    }
}
