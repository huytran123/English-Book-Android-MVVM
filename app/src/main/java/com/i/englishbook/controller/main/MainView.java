package com.i.englishbook.controller.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
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
import com.i.englishbook.common.PreferHelper;
import com.i.englishbook.controll.CategoryAdapter;
import com.i.englishbook.controller.base.BaseView;
import com.i.englishbook.controller.detail.DetailView;
import com.i.englishbook.databinding.ActivityMainBinding;
import com.i.englishbook.model.AppDB;
import com.i.englishbook.model.Category;

import java.util.ArrayList;
import java.util.List;

public class MainView extends BaseView implements MainNavigator {

    MainViewModel mainViewModel;
    ActivityMainBinding binding;
    ArrayList<Category> categories;
    CategoryAdapter categoryAdapter;
    int REQUEST_CODE_DETAIL = 1000;
    int cateSelectedIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = new MainViewModel(this);
        mainViewModel.setNavigator(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.recycler.setLayoutManager(PreferHelper.getBool(this, Keys.IS_LIST_LAYOUT) ?
                new LinearLayoutManager(this) :
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        categories = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(categories);
        binding.recycler.setAdapter(categoryAdapter);
        mainViewModel.getCategories();

        setSupportActionBar(binding.myToolbar);

        Category cate = AppDB.getINSTANCE(this)
                .categoryDAO()
                .getCategory(1);
        cate.Complete = 0;
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
        menu.getItem(0).setIcon(PreferHelper.getBool(this, Keys.IS_LIST_LAYOUT) ? R.drawable.ic_grid : R.drawable.ic_list);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.itemChangeLayout) {
            changeLayout();
            item.setIcon(PreferHelper.getBool(this, Keys.IS_LIST_LAYOUT) ? R.drawable.ic_grid : R.drawable.ic_list);
        }
        return true;
    }

    @Override
    public void getCategoriesError(String mess) {
        showMess(mess);
    }

    @Override
    public void cateOnClick(View view) {
        int position = CodeHelper.getInt(view.getTag(), 0);
        cateSelectedIndex = position;
        Intent i = new Intent(this, DetailView.class);
        i.putExtra(Keys.CATEGORIY_ID, categories.get(cateSelectedIndex).Id);
        startActivityForResult(i, REQUEST_CODE_DETAIL);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_DETAIL && resultCode == RESULT_OK) {
            updateCateInfoView();
        }
    }

    public void updateCateInfoView() {
        Category cate = AppDB.getINSTANCE(this)
                .categoryDAO()
                .getCategory(categories.get(cateSelectedIndex).Id);
        Category cateSelected = categories.get(cateSelectedIndex);
        cateSelected.Warning = cate.Warning;
        cateSelected.Error = cate.Error;
        cateSelected.Complete = cate.Complete;
        categoryAdapter.notifyItemChanged(cateSelectedIndex);
    }

    void changeLayout() {
        boolean isListLayout = PreferHelper.getBool(this, Keys.IS_LIST_LAYOUT);
        isListLayout = !isListLayout;
        PreferHelper.setBool(this, Keys.IS_LIST_LAYOUT, isListLayout);
        binding.recycler.setLayoutManager(isListLayout ? new LinearLayoutManager(this) : new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        binding.recycler.setAdapter(categoryAdapter);

    }
}
