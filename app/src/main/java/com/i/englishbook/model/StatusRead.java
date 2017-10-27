package com.i.englishbook.model;

import android.content.Context;
import android.graphics.Color;

import com.i.englishbook.R;

/**
 * Created by huytran on 10/23/2017.
 */

public class StatusRead {
    public static String COMPLETE = "COMPLETE", WARNING = "WARNING", ERROR = "ERROR";

    public static int getColor(Context c, String status) {
        if (status.equals(COMPLETE))
            return c.getResources().getColor(R.color.complete_color);
        else if( status.equals(WARNING))
            return c.getResources().getColor(R.color.warning_color);
        return c.getResources().getColor(R.color.error_color);
    }
}
