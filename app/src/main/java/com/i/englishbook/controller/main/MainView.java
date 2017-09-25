package com.i.englishbook.controller.main;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.i.englishbook.R;
import com.i.englishbook.common.CodeHelper;
import com.i.englishbook.controll.CategoryAdapter;
import com.i.englishbook.controller.base.BaseView;
import com.i.englishbook.databinding.ActivityMainBinding;
import com.i.englishbook.model.Category;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainView extends BaseView {

    MainViewModel mainViewModel;
    ActivityMainBinding binding;
    ArrayList<Category> categories;
    CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = new MainViewModel(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        categories = new ArrayList<>();
        loadCategories();

        categoryAdapter = new CategoryAdapter(categories);
        binding.recycler.setAdapter(categoryAdapter);
    }

    void loadCategories() {
        try {

            mainViewModel.getCategories()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(next ->
                    {
                        categories.addAll(next);
                        categoryAdapter.notifyDataSetChanged();
                    }, error -> {

                    }, () -> {
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
