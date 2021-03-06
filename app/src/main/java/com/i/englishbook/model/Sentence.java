package com.i.englishbook.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.i.englishbook.R;
import com.i.englishbook.annotations.Column;
import com.i.englishbook.annotations.Table;
import com.i.englishbook.common.CodeHelper;
import com.i.englishbook.controller.detail.DetailNavigator;

/**
 * Created by huytran on 8/17/2017.
 */

@Entity(tableName = "Sentences")
public class Sentence {
    @ColumnInfo(name = "id")
    @PrimaryKey
    public int Id;
    @ColumnInfo(name = "cat_id")
    public int CateId;
    @ColumnInfo(name = "e")
    public String E;
    @ColumnInfo(name = "v")
    public String V;
    @ColumnInfo(name = "status_read")
    public String StatusRead;
    @ColumnInfo(name = "is_favorite")
    public boolean IsFavorite;
    @ColumnInfo(name = "my_e")
    public String MyEnglish;
    @Ignore
    public boolean IsSelected;
    @Ignore
    public boolean IsPlayed;
    @Ignore
    public boolean IsPlaying;
    @Ignore
    public boolean IsSpeech;


    public void onClick(View view) {
        ((DetailNavigator) view.getContext()).sentenceClick(CodeHelper.getInt(view.getTag(), 0));
    }

    public void onClickSpeech(View view) {
        ((DetailNavigator) ((View) view.getParent()).getContext()).onClickSpeech(CodeHelper.getInt(view.getTag(), 0));
    }

    public void updateStatus(Context c) {
        AppDB.getINSTANCE(c).sentenceDAO().update(this);
    }

    public Drawable getIconSpeech(Context c){
        if(IsPlaying)
            return ContextCompat.getDrawable(c, R.drawable.ic_sound_playing);
        else if(IsPlayed)
            return ContextCompat.getDrawable(c, R.drawable.ic_sound_played);
        return ContextCompat.getDrawable(c, R.drawable.ic_sound);
    }

}
