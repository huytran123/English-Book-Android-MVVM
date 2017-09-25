package com.i.englishbook.common;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Date;

/**
 * Created by huytran on 8/17/2017.
 */

public class JsonHelper {
    public static String getString(JsonObject js, String key, String defaultValue) {
        if (js == null || !js.has(key) || js.get(key).isJsonNull()) return defaultValue;
        return js.get(key).getAsString();
    }

    public static String getString(JsonObject js, String key) {
        return getString(js, key, "");
    }

    public static int getInt(JsonObject js, String key, int defaultValue) {
        if (js == null || !js.has(key) || js.get(key).isJsonNull()) return defaultValue;
        return CodeHelper.getInt(js.get(key).getAsString(), 0);
    }

    public static int getInt(JsonObject js, String key) {
        return getInt(js, key, 0);
    }

    public static boolean getBool(JsonObject js, String key) {
        if (js == null || !js.has(key) || js.get(key).isJsonNull()) return false;
        return js.get(key).getAsBoolean();
    }

    public static Date getDate(JsonObject js, String key) {
        if (js == null || !js.has(key) || js.get(key).isJsonNull()) return Common.MIN_DATE;
        try {
            String value = getString(js, key);
            return CodeHelper.getDate(value, Common.FORMAT_DATE_TIME_SERVICE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Common.MIN_DATE;
    }

    public static Date getDate(JsonObject js, String key, String format) {
        if (js == null || !js.has(key) || js.get(key).isJsonNull()) return Common.MIN_DATE;
        try {
            String value = getString(js, key);
            return CodeHelper.getDate(value, format);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Common.MIN_DATE;
    }

    public static double getDouble(JsonObject js, String key, int defaultValue) {
        if (js == null || !js.has(key) || js.get(key).isJsonNull()) return defaultValue;
        return js.get(key).getAsDouble();
    }

    public static double getDouble(JsonObject js, String key) {
        return getDouble(js, key, 0);
    }

    public static JsonArray getJsArray(JsonObject o, String key) {
        if (!o.has(key)) return new JsonArray();
        return o.get(key).getAsJsonArray();
    }

    public static JsonObject getJsObject(JsonObject o, String key) {
        if (!o.has(key)) return null;
        return o.getAsJsonObject(key);
    }
}