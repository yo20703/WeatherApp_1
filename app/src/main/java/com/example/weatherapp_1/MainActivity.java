package com.example.weatherapp_1;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.weatherapp_1.data.DayForecast;
import com.example.weatherapp_1.data.HourlyForecast;
import com.example.weatherapp_1.data.WeatherData;
import com.example.weatherapp_1.fragment.WeatherFragment;
import com.example.weatherapp_1.view_pager.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager2 viewPager2 = findViewById(R.id.view_pager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        adapter.addFragment(new WeatherFragment(R.xml.current));
        adapter.addFragment(new WeatherFragment(R.xml.tainan));

        viewPager2.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager2, ((tab, position) -> {
            View customTabView = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
            tab.setCustomView(customTabView);
        })).attach();
    }




}