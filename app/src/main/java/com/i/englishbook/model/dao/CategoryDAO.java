package com.i.englishbook.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

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

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Category category);

    @Query("Update Categories set error = (select count(1) from Sentences WHERE cat_id = :cateId and status_read='ERROR')"
            +",complete = (select count(1) from Sentences WHERE cat_id = :cateId and status_read='COMPLETE')"
            +",warning = (select count(1) from Sentences WHERE cat_id = :cateId and status_read='WARNING')"
            +"Where id=:cateId")
    void updateStatusSentence(int cateId);
}
