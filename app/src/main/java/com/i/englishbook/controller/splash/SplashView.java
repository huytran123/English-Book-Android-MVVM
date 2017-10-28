package com.i.englishbook.controller.splash;

import android.Manifest;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.i.englishbook.R;
import com.i.englishbook.common.CodeHelper;
import com.i.englishbook.common.Common;
import com.i.englishbook.controller.base.BaseView;
import com.i.englishbook.controller.main.MainView;
import com.i.englishbook.databinding.ActivitySplashBinding;
import com.i.englishbook.model.AppDB;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by huytran on 9/3/2017.
 */

public class SplashView extends BaseView {
    ActivitySplashBinding binding;
    SplashViewModel splashViewModel;

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
// Since we didn't alter the table, there's nothing else to do here.
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splashViewModel = new SplashViewModel(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        try {
            splashViewModel.initDatabase()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(next -> {
                        if (CodeHelper.isEmptyOrNull(next)) {
                            new CountDownTimer(2000, 1000) {
                                @Override
                                public void onTick(long l) {

                                }

                                @Override
                                public void onFinish() {
                                    Intent i = new Intent(SplashView.this, MainView.class);
                                    startActivity(i);
                                    finish();
                                }
                            }.start();
                        }
                    }, error -> {
                    }, () -> {
                    });
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        checkPermission();
    }
}
