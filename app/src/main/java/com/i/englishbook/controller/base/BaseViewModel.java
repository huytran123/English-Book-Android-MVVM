package com.i.englishbook.controller.base;

import android.content.Context;

/**
 * Created by huytran on 9/3/2017.
 */

public class BaseViewModel<N>{
   public Context context;
   private N mNavigator;
   public void setNavigator(N navigator) {
      this.mNavigator = navigator;
   }
   public N getNavigator() {
      return mNavigator;
   }
}
