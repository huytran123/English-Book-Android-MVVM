package com.i.englishbook.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.i.englishbook.model.Sentence;

import java.util.ArrayList;

import io.reactivex.Flowable;

/**
 * Created by huytran on 10/25/2017.
 */

@Dao
public interface SentenceDAO {
    @Query("SELECT * FROM Sentence WHERE cate_id = :userIds")
    Flowable<ArrayList<Sentence>> getSentenceByCate(int cateId);

    @Update
    Flowable updateSentence(Sentence sentence);

    @Query("SELECT * FROM Sentence WHERE is_favorite = :fav")
    Flowable<ArrayList<Sentence>> getSentenceFav(boolean fav);
}
