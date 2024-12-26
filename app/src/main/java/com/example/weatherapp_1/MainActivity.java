package com.example.weatherapp_1;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weatherapp_1.data.HourlyForecast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView tvCurrentCity;
    TextView tvCurrentTemperature;
    TextView tvCurrentWeather;
    TextView tvCurrentTempHL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        ArrayList<HourlyForecast> hourlyForecastArrayList = new ArrayList<>();

        HourlyWeatherAdapter adapter = new HourlyWeatherAdapter(hourlyForecastArrayList);
    }

    private void initView() {
        tvCurrentCity = findViewById(R.id.tv_current_city);
        tvCurrentTemperature = findViewById(R.id.tv_current_temperature);
        tvCurrentWeather = findViewById(R.id.tv_current_weather);
        tvCurrentTempHL = findViewById(R.id.tv_current_temp_hl);
    }
}