package com.i.englishbook.common;

import java.util.Date;

/**
 * Created by huytran on 8/17/2017.
 */

public class Common {
    public static Date MIN_DATE = CodeHelper.getDate(1, 1, 1900);
    public static String DBPATH = "";
    public static String FORMAT_DATE_TIME_SERVICE = "yyyy-MM-dd'T'HH:mm:ss";
    public static String FORMAT_DATE_TIME = "MM/dd/yyyy HH:mm:ss";
    public static String FORMAT_DATE = "MM/dd/yyyy";
    public static String FORMAT_TIME = "HH:mm:ss";
    public static String FORMAT_SCAN = "UPC_A,UPC_E,EAN_8,EAN_13,CODE_39,CODE_93,CODE_128,CODABAR,ITF,RSS_14,QR_Code,Data_Matrix";
    public static int MAX_LOOP_PLAY = 2;
}