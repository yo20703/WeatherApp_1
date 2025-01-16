package com.example.weatherapp_1.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp_1.R;
import com.example.weatherapp_1.adapter.ListWeatherAdapter;
import com.example.weatherapp_1.data.WeatherData;
import com.example.weatherapp_1.utils.WeatherParser;

import java.util.ArrayList;

public class WeatherListActivity extends AppCompatActivity {

    private RecyclerView rvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_list);

        initLayout();
        initData();
    }

    private void initLayout() {
        rvList = findViewById(R.id.rv_list);
        rvList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    private void initData() {
        ArrayList<WeatherData> weatherDataArrayList = new ArrayList<>();
        weatherDataArrayList.add(WeatherParser.parseWeatherData(this, R.xml.current));
        weatherDataArrayList.add(WeatherParser.parseWeatherData(this, R.xml.taichung));
        weatherDataArrayList.add(WeatherParser.parseWeatherData(this, R.xml.tainan));
        weatherDataArrayList.add(WeatherParser.parseWeatherData(this, R.xml.taipei));
        weatherDataArrayList.add(WeatherParser.parseWeatherData(this, R.xml.taoyuan));

        ListWeatherAdapter listWeatherAdapter = new ListWeatherAdapter(weatherDataArrayList);
        rvList.setAdapter(listWeatherAdapter);
    }
}