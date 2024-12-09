package com.example.binder.activity;

import static com.example.binder.adapter.MainNavigationAdapter.SELECTED;
import static com.example.binder.adapter.MainNavigationAdapter.UNSELECTED;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.binder.R;
import com.example.binder.adapter.MainNavigationAdapter;
import com.example.binder.databinding.ActivityMainBinding;
import com.example.common.utils.DpPx;
import com.example.common.view.CenterImageSpan;

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
        binding.gbv.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, VedioActivity.class));
        });
        setNavigation();
        setFansNumber(binding.tvFansNumber, 2348734);
    }

    private void setFansNumber(TextView v, int fans) {
        StringBuilder builder = new StringBuilder();
        builder.append(" ");
        builder.append(fans);
        Drawable drawable = ContextCompat.getDrawable(this, R.mipmap.ic_main_meiyan);
        drawable.setBounds(0, 0, DpPx.dp2px(this, 30), DpPx.dp2px(this, 30));
        CenterImageSpan span = new CenterImageSpan(drawable);
        SpannableString spannableString = new SpannableString(builder.toString());
        spannableString.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        v.setText(spannableString);
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