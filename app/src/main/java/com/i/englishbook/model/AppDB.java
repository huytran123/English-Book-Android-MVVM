package com.i.englishbook.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.i.englishbook.common.Common;
import com.i.englishbook.model.dao.CategoryDAO;
import com.i.englishbook.model.dao.SentenceDAO;

/**
 * Created by huytran on 10/27/2017.
 */
@Database(entities = {Sentence.class, Category.class}, version = 3)
public abstract class AppDB extends RoomDatabase {
    public abstract SentenceDAO sentenceDAO();

    public abstract CategoryDAO categoryDAO();

    private static AppDB INSTANCE;

    public static AppDB getINSTANCE(Context c) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(c, AppDB.class, Common.DB_NAME).allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
