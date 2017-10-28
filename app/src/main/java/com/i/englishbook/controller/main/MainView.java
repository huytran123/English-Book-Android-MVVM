package com.i.englishbook.controller.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.i.englishbook.R;
import com.i.englishbook.common.CodeHelper;
import com.i.englishbook.common.Keys;
import com.i.englishbook.common.PreferencesHelper;
import com.i.englishbook.controll.CategoryAdapter;
import com.i.englishbook.controller.base.BaseView;
import com.i.englishbook.controller.detail.DetailView;
import com.i.englishbook.databinding.ActivityMainBinding;
import com.i.englishbook.model.Category;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainView extends BaseView implements MainNavigator {

    MainViewModel mainViewModel;
    ActivityMainBinding binding;
    ArrayList<Category> categories;
    CategoryAdapter categoryAdapter;
    boolean isListLayout = true;

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

        setSupportActionBar(binding.myToolbar);
    }

    @Override
    public void getCategoriesComplete(List<Category> categories) {
        this.categories.addAll(categories);
        categoryAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        isListLayout = !isListLayout;
        item.setIcon(isListLayout ? R.drawable.ic_list : R.drawable.ic_grid);
        categoryAdapter.IsListLayout = isListLayout;
        binding.recycler.setLayoutManager(isListLayout ? new LinearLayoutManager(this) : new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        binding.recycler.setAdapter(categoryAdapter);
        return true;
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
