package com.example.binder;

import static com.example.binder.adapter.MainNavigationAdapter.SELECTED;
import static com.example.binder.adapter.MainNavigationAdapter.UNSELECTED;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.binder.adapter.MainAdapter;
import com.example.binder.adapter.MainNavigationAdapter;
import com.example.binder.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private HashMap<String, Integer> pictures = new HashMap<>();
    private int selectPosition = 0;
    private MainNavigationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //setRecyclerView();
        setNavigation();
    }

    private void setRecyclerView() {
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            String s = "这是第" + i + "个数据";
            data.add(s);
        }
        MainAdapter adapter = new MainAdapter(this, data, R.layout.item_main);
        binding.rvMain.setLayoutManager(new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false));
        binding.rvMain.setAdapter(adapter);
        binding.rvMain.setVisibility(View.GONE);
    }

    private void setNavigation() {
        pictures.put(SELECTED + "0", R.mipmap.ic_dance_selected);
        pictures.put(SELECTED + "1", R.mipmap.ic_swap_selected);
        pictures.put(SELECTED + "2", R.mipmap.ic_money_selected);
        pictures.put(SELECTED + "3", R.mipmap.ic_me_selected);
        pictures.put(UNSELECTED + "0", R.mipmap.ic_dance_unselected);
        pictures.put(UNSELECTED + "1", R.mipmap.ic_swap_unselected);
        pictures.put(UNSELECTED + "2", R.mipmap.ic_money_unselected);
        pictures.put(UNSELECTED + "3", R.mipmap.ic_me_unselected);
        ArrayList<String> data = new ArrayList<>();
        data.add("Dance");
        data.add("Swap");
        data.add("Money");
        data.add("Me");
        adapter = new MainNavigationAdapter(this, data, R.layout.item_main_navigation);
        adapter.setItemHeight(50);
        adapter.setSelectPosition(selectPosition);
        adapter.setPicture(pictures);
        binding.rvNavigation.setLayoutManager(new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false));
        binding.rvNavigation.setAdapter(adapter);
    }

}