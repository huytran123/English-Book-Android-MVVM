package com.i.englishbook.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.i.englishbook.model.Category;
import com.i.englishbook.model.Sentence;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by huytran on 10/25/2017.
 */

@Dao
public interface CategoryDAO {
    @Query("SELECT * FROM Categories")
    Flowable<List<Category>> getCategories();

    @Query("Select * from Categories where id = :catId")
    Category getCategory(int catId);
}
