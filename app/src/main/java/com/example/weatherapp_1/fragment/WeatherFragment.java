package com.example.weatherapp_1.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weatherapp_1.adapter.DayWeatherAdapter;
import com.example.weatherapp_1.adapter.HourlyWeatherAdapter;
import com.example.weatherapp_1.R;
import com.example.weatherapp_1.data.DayForecast;
import com.example.weatherapp_1.data.HourlyForecast;
import com.example.weatherapp_1.data.WeatherData;
import com.example.weatherapp_1.utils.WeatherParser;

import org.xmlpull.v1.XmlPullParser;


public class WeatherFragment extends Fragment {

    TextView tvCurrentCity;
    TextView tvCurrentAqi;
    RecyclerView rvHourly;
    RecyclerView rvDay;

    private int xmlID = 0;
    public WeatherFragment(int xmlID) {
        // Required empty public constructor
        this.xmlID = xmlID;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        initView(view);

        WeatherData weatherData = WeatherParser.parseWeatherData(getContext(), xmlID);
        if(weatherData != null) {
            updateWeatherUI(weatherData);
        }
        return view;
    }

    private void initView(View view) {
        tvCurrentCity = view.findViewById(R.id.tv_current_city);
        tvCurrentAqi = view.findViewById(R.id.tv_current_aqi);
        rvHourly = view.findViewById(R.id.rv_hourly);
        rvDay = view.findViewById(R.id.rv_day);

        rvHourly.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvDay.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        //用SnapHelper 讓 RecyclerView 自動定位
        LinearSnapHelper snapHelper1 = new LinearSnapHelper();
        snapHelper1.attachToRecyclerView(rvHourly);

        LinearSnapHelper snapHelper2 = new LinearSnapHelper();
        snapHelper2.attachToRecyclerView(rvDay);
    }

    private void updateWeatherUI(WeatherData weatherData) {
        tvCurrentCity.setText(weatherData.getCity());
        tvCurrentAqi.setText("AQI:" + weatherData.getAqi());

        HourlyWeatherAdapter hourlyWeatherAdapter = new HourlyWeatherAdapter(weatherData.getHourlyForecastArrayList());
        rvHourly.setAdapter(hourlyWeatherAdapter);

        DayWeatherAdapter dayWeatherAdapter = new DayWeatherAdapter(weatherData.getDayForecastArrayList());
        rvDay.setAdapter(dayWeatherAdapter);
    }
}