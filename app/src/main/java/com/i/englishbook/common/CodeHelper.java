package com.i.englishbook.common;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Created by huytran on 8/17/2017.
 */

public class CodeHelper {
    public static boolean isEmptyOrNull(String value) {
        if (value == null || value.isEmpty()) return true;
        return false;
    }


    public static String getString(Object o) {
        if (o == null) return "";
        return o.toString();
    }

    public static int getInt(Object o, int defaultValue) {
        String str = getString(o);
        if (isEmptyOrNull(str) || !Pattern.compile("^[0-9]+").matcher(str).find())
            return defaultValue;
        return Integer.parseInt(str);
    }


    public static Date getDate(int day, int month, int year, int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);

        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        return cal.getTime();
    }

    public static Date getDate(int day, int month, int year) {
        return getDate(day, month, year, 12, 0, 0);
    }

    public static String getDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        return sdf.format(date);
    }

    public static Date getDate(String value, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Date convertedDate = Common.MIN_DATE;
        if (CodeHelper.isEmptyOrNull(value)) return convertedDate;
        try {
            convertedDate = dateFormat.parse(value);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return convertedDate;
    }

    public static int getResourceId(Context c, String resourceName, String defType) {
        return c.getResources().getIdentifier(resourceName, defType, c.getPackageName());
    }

    public static int getResourceId(Context c, String resourceName) {
        return c.getResources().getIdentifier(resourceName, "drawable", c.getPackageName());
    }

    private static InputStream getInputStreamFromAsset(Context c, String filePath) {
        try {
            return c.getAssets().open(filePath);
        } catch (Exception ex) {
            return null;
        }
    }

    public static String readFileFromAsset(Context c, String filePath) {
        BufferedReader reader = null;
        try {
            InputStream is = getInputStreamFromAsset(c, filePath);
            if (is == null) return "";
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line = "";
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            if (sb.length() > 0)
                return sb.substring(0, sb.length() - 1);
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            reader = null;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    //log the exception
                    e.printStackTrace();
                }
            }
        }

        return "";
    }

    public static String copyFileFromAsset(Context c, String filename, String pathDis) {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = getInputStreamFromAsset(c, filename);
            if (in == null) return "";
            File outFile = new File(pathDis, filename);
            if (outFile.exists())
                outFile.delete();
            out = new FileOutputStream(outFile);
            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
        } catch (Exception e) {
            return e.getMessage();
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (out != null)
                    out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }


}
