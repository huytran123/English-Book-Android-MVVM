package com.i.englishbook.controller.base;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by huytran on 9/3/2017.
 */

public class BaseView extends AppCompatActivity {
    public void showMess(String mess){
        new AlertDialog.Builder(this).setMessage(mess).show();
    }
}
