package com.i.englishbook.model;

/**
 * Created by huytran on 9/1/2017.
 */

public enum ModePlay {
    LOOP("LOOP", 0),
    RANDOM("RANDOM", 1),
    NEXT("BOOL", 2);
    private String stringValue;
    private int intValue;

    private ModePlay(String toString, int value) {
        stringValue = toString;
        intValue = value;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}
