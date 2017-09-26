package com.i.englishbook.controller.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.i.englishbook.R;
import com.i.englishbook.common.CodeHelper;
import com.i.englishbook.common.Keys;
import com.i.englishbook.controll.CategoryAdapter;
import com.i.englishbook.controller.base.BaseView;
import com.i.englishbook.controller.detail.DetailView;
import com.i.englishbook.databinding.ActivityMainBinding;
import com.i.englishbook.model.Category;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainView extends BaseView implements MainNavigator {

    MainViewModel mainViewModel;
    ActivityMainBinding binding;
    ArrayList<Category> categories;
    CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = new MainViewModel(this);
        mainViewModel.setNavigator(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        categories = new ArrayList<>();

        categoryAdapter = new CategoryAdapter(categories);
        binding.recycler.setAdapter(categoryAdapter);
        mainViewModel.getCategories();
    }

    @Override
    public void getCategoriesComplete(ArrayList<Category> categories) {
        this.categories.addAll(categories);
        categoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void getCategoriesError(String mess) {
        showMess(mess);
    }

    @Override
    public void cateOnClick(View view) {
        int position = CodeHelper.getInt(view.getTag(), 0);
        int cateId = categories.get(position).Id;
        Intent i = new Intent(this, DetailView.class);
        i.putExtra(Keys.CATEGORIY_ID, cateId);
        startActivity(i);
    }
}
