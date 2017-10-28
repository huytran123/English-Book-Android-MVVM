package com.i.englishbook.common;

import android.arch.persistence.room.ColumnInfo;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.i.englishbook.annotations.Column;
import com.i.englishbook.services.SQLiteMgr;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by huytran on 8/17/2017.
 */

public class SqlHelper {
    public static String getString(Cursor cursor, String colName) {
        if (cursor == null) return "";
        int colIndex = cursor.getColumnIndex(colName);
        if (colIndex < 0) return "";
        String value = cursor.getString(colIndex);
        if (CodeHelper.isEmptyOrNull(value)) return "";
        return value;
    }

    public static int getInt(Cursor cursor, String colName) {
        if (cursor == null) return 0;
        int colIndex = cursor.getColumnIndex(colName);
        if (colIndex < 0) return 0;
        return cursor.getInt(colIndex);
    }

    public static boolean getBool(Cursor cursor, String colName) {
        return getInt(cursor, colName) == 1;
    }

    public static Date getDate(Cursor cursor, String colName) {
        if (cursor == null) return Common.MIN_DATE;
        int colIndex = cursor.getColumnIndex(colName);
        if (colIndex < 0) return Common.MIN_DATE;
        return CodeHelper.getDate(cursor.getString(colIndex), Common.FORMAT_DATE);
    }

    public static <T> T getInstance(Cursor cursor, Class<?> cls) throws IllegalAccessException, InstantiationException {
        Object o = cls.newInstance();
        for (Field field : cls.getDeclaredFields()) {
            ColumnInfo column = field.getAnnotation(ColumnInfo.class);
            if (column == null) continue;
            String columnName = column.name();
            if (field.getType() == int.class) {
                field.setInt(o, SqlHelper.getInt(cursor, columnName));
            } else if (field.getType() == boolean.class) {
                field.setBoolean(o, SqlHelper.getBool(cursor, columnName));
            } else if (field.getType() == double.class) {
                field.setDouble(o, SqlHelper.getInt(cursor, columnName));
            } else if (field.getType() == Date.class) {
                field.set(o, SqlHelper.getDate(cursor, columnName));
            } else
                field.set(o, SqlHelper.getString(cursor, columnName));
        }
        return (T) o;
    }

    public static <T> ArrayList<T> getInstances(Cursor cursor, Class<?> cls) throws IllegalAccessException, InstantiationException {
        cursor.moveToFirst();
        ArrayList<T> result = new ArrayList<>();
        do {
            result.add(getInstance(cursor, cls));
        } while (cursor.moveToNext());
        return result;
    }

    public static <T> ArrayList<T> exeSql(Context c, String sql, Class<?> cls) throws InstantiationException, IllegalAccessException {
        SQLiteMgr sqLiteMgr = new SQLiteMgr(c);
        SQLiteDatabase db = sqLiteMgr.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        return getInstances(cursor, cls);
    }
}

