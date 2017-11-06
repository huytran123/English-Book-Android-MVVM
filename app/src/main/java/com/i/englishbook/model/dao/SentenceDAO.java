package com.i.englishbook.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.icu.text.Replaceable;

import com.i.englishbook.model.Sentence;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by huytran on 10/25/2017.
 */

@Dao
public interface SentenceDAO {
    @Query("SELECT * FROM Sentences WHERE cat_id = :cateId")
    public Flowable<List<Sentence>> getSentenceByCate(int cateId);

    @Query("SELECT * FROM Sentences WHERE is_favorite = :fav")
    public  Flowable<List<Sentence>> getSentenceFav(boolean fav);

    @Update
    void update(Sentence sentence);

}
