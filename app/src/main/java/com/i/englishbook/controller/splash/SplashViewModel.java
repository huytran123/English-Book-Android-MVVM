package com.i.englishbook.controller.splash;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.SystemClock;

import com.i.englishbook.common.CodeHelper;
import com.i.englishbook.common.Common;
import com.i.englishbook.controller.base.BaseViewModel;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by huytran on 9/3/2017.
 */

public class SplashViewModel extends BaseViewModel {
    public SplashViewModel(Context c) {
        this.context = c;
    }

    public Observable<String> initDatabase() throws PackageManager.NameNotFoundException {
        return Observable.create(e -> {
            String dataPath = context.getApplicationInfo().dataDir;
            String dbPath = dataPath + "/databases";
            File dbFolder = new File(dbPath);
            if (!dbFolder.exists())
                dbFolder.mkdir();
            Common.DBPATH = dbPath + "/data.db";
            File dbFile = new File(Common.DBPATH);
            String mess = "";
            if (!dbFile.exists()) {
                mess = CodeHelper.copyFileFromAsset(context, "data.db", dbPath);
            }
            if (CodeHelper.isEmptyOrNull(mess)) {
                e.onNext("");
            } else {
                e.onNext(mess);
            }
        });

    }
}
