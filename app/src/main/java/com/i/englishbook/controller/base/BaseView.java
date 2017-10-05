package com.i.englishbook.controller.base;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by huytran on 9/3/2017.
 */

public class BaseView extends AppCompatActivity {

    public int MY_PERMISSIONS_REQUEST;

    public void showMess(String mess) {
        new AlertDialog.Builder(this).setMessage(mess).show();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public boolean checkPermission() {
        return true;
    }
}
