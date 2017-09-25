package com.i.englishbook.services;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by huytran on 8/17/2017.
 */

public class SQLiteMgr extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 3;   // database version number
    public static String DATA_DB = "data.db";


    public SQLiteMgr(Context context) {
        super(context, DATA_DB, null, DATABASE_VERSION);
    }

    public SQLiteMgr(Context context, String DbName) {
        super(context, DbName, null, DATABASE_VERSION);
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {

        return super.getReadableDatabase();
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {

        return super.getWritableDatabase();
    }

    @Override
    public synchronized void close() {
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}

