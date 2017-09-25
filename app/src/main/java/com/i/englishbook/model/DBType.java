package com.i.englishbook.model;

/**
 * Created by huytran on 9/1/2017.
 */

public enum DBType {
    DOUBLE("DOUBLE",0),
    REAL("REAL", 1),
    BOOL("BOOL", 2),
    INTEGER("INTEGER", 3),
    FLOAT("FLOAT", 4),
    DATETIME("DATETIME", 5),
    NUMERIC("NUMERIC", 5),
    BLOB("BLOB", 5),
    VARCHAR("VARCHAR", 5),
    CHAR("CHAR", 5),
    TEXT("TEXT", 5),;

    private String stringValue;
    private int intValue;
    private DBType(String toString, int value) {
        stringValue = toString;
        intValue = value;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}
