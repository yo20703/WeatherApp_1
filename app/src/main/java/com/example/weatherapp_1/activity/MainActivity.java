package com.example.weatherapp_1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.weatherapp_1.R;
import com.example.weatherapp_1.fragment.WeatherFragment;
import com.example.weatherapp_1.view_pager.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity";

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    ImageView ivMap;
    ImageView ivList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initLayout();
        initViewPager();
    }

    private void initLayout() {
        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.view_pager);
        ivMap = findViewById(R.id.iv_map);
        ivList = findViewById(R.id.iv_list);

        ivMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMapActivity();
            }
        });

        ivList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToWeatherListActivity();
            }
        });
    }

    private void initViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        adapter.addFragment(new WeatherFragment(R.xml.current));
        adapter.addFragment(new WeatherFragment(R.xml.tainan));
        adapter.addFragment(new WeatherFragment(R.xml.taichung));
        adapter.addFragment(new WeatherFragment(R.xml.taipei));
        adapter.addFragment(new WeatherFragment(R.xml.taoyuan));

        viewPager2.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager2, ((tab, position) -> {
            View customTabView = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
            tab.setCustomView(customTabView);
        })).attach();
    }

    private void goToMapActivity() {
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }

    private void goToWeatherListActivity() {
        Intent intent = new Intent(this, WeatherListActivity.class);
        startActivity(intent);
    }
}