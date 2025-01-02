package com.example.weatherapp_1;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp_1.data.DayForecast;
import com.example.weatherapp_1.data.HourlyForecast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView tvCurrentCity;
    TextView tvCurrentTemperature;
    TextView tvCurrentWeather;
    TextView tvCurrentTempHL;
    RecyclerView rvHourly;
    RecyclerView rvDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initHourlyWeather();
        initRvDay();
    }

    private void initView() {
        tvCurrentCity = findViewById(R.id.tv_current_city);
        tvCurrentTemperature = findViewById(R.id.tv_current_temperature);
        tvCurrentWeather = findViewById(R.id.tv_current_weather);
        tvCurrentTempHL = findViewById(R.id.tv_current_temp_hl);
        rvHourly = findViewById(R.id.rv_hourly);
        rvDay = findViewById(R.id.rv_day);
    }

    private void initHourlyWeather() {
        ArrayList<HourlyForecast> forecasts = new ArrayList<>();
        HourlyForecast forecast1 = new HourlyForecast(
                "00:00",
                "sunny",
                "24 °C"
        );
        forecasts.add(forecast1);

        HourlyForecast forecast2 = new HourlyForecast(
                "01:00",
                "cloudy",
                "23 °C"
        );
        forecasts.add(forecast2);

        HourlyForecast forecast3 = new HourlyForecast(
                "02:00",
                "overcast",
                "22 °C"
        );
        forecasts.add(forecast3);

        HourlyForecast forecast4 = new HourlyForecast(
                "03:00",
                "thunder",
                "25 °C"
        );
        forecasts.add(forecast4);

        HourlyForecast forecast5 = new HourlyForecast(
                "04:00",
                "rain",
                "26 °C"
        );
        forecasts.add(forecast5);

        HourlyWeatherAdapter hourlyWeatherAdapter = new HourlyWeatherAdapter(forecasts);
        rvHourly.setLayoutManager(new LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false)
        );
        rvHourly.setAdapter(hourlyWeatherAdapter);
    }

    private void initRvDay() {
        ArrayList<DayForecast> dayForecasts = new ArrayList<>();

        DayForecast dayForecast1 = new DayForecast(
                "00:00",
                "sunny",
                "24 °C",
                "30 °C"
        );
        dayForecasts.add(dayForecast1);

        DayForecast dayForecast2 = new DayForecast(
                "01:00",
                "cloudy",
                "24 °C",
                "30 °C"
        );
        dayForecasts.add(dayForecast2);

        DayForecast dayForecast3 = new DayForecast(
                "02:00",
                "overcast",
                "24 °C",
                "30 °C"
        );
        dayForecasts.add(dayForecast3);

        DayForecast dayForecast = new DayForecast(
                "03:00",
                "rain",
                "24 °C",
                "30 °C"
        );
        dayForecasts.add(dayForecast);

        DayWeatherAdapter dayWeatherAdapter = new DayWeatherAdapter(dayForecasts);
        rvDay.setLayoutManager(new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false)
        );
        rvDay.setAdapter(dayWeatherAdapter);
    }
}