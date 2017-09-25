package com.i.englishbook.controller.splash;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;

import com.i.englishbook.R;
import com.i.englishbook.common.CodeHelper;
import com.i.englishbook.controller.base.BaseView;
import com.i.englishbook.controller.main.MainView;
import com.i.englishbook.databinding.ActivitySplashBinding;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by huytran on 9/3/2017.
 */

public class SplashView extends BaseView {
    ActivitySplashBinding binding;
    SplashViewModel splashViewModel;

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
                            new CountDownTimer(2000,1000){
                                @Override
                                public void onTick(long l) {

                                }

                                @Override
                                public void onFinish() {
                                    Intent i = new Intent(SplashView.this, MainView.class);
                                    startActivity(i);
                                }
                            }.start();
                        }
                    }, error -> {
                    }, () -> {
                    });
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
