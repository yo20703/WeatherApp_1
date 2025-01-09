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

import com.example.weatherapp_1.DayWeatherAdapter;
import com.example.weatherapp_1.HourlyWeatherAdapter;
import com.example.weatherapp_1.R;
import com.example.weatherapp_1.data.DayForecast;
import com.example.weatherapp_1.data.HourlyForecast;
import com.example.weatherapp_1.data.WeatherData;

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

        WeatherData weatherData = parseWeatherData();
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

    private WeatherData parseWeatherData() {
        WeatherData weatherData = new WeatherData();

        try {
            XmlPullParser parser = getResources().getXml(xmlID);
            int eventType = parser.getEventType();
            String currentTag = "";

            HourlyForecast hourlyForecast = null;
            DayForecast dayForecast = null;

            boolean inHourlyForecast = false;
            boolean inDayForecast = false;

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        currentTag = parser.getName();
                        switch (currentTag) {
                            case "current_weather":
                                parser.next();
                                weatherData.setCity(parser.nextText());
                                break;
                            case "current_aqi":
                                parser.next();
                                weatherData.setAqi(Integer.parseInt(parser.getText()));
                                break;
                            case "hourly_forecast":
                                inHourlyForecast = true;
                                break;
                            case "hour":
                                if(inHourlyForecast) {
                                    hourlyForecast = new HourlyForecast();
                                }
                                break;
                            case "time":
                                if(inHourlyForecast && hourlyForecast != null) {
                                    parser.next();
                                    hourlyForecast.setTime(parser.getText());
                                }
                                break;
                            case "weather_condition":
                                if(inHourlyForecast && hourlyForecast != null) {
                                    parser.next();
                                    hourlyForecast.setWeatherCondition(parser.getText());
                                } else if(inDayForecast && dayForecast != null) {
                                    parser.next();
                                    dayForecast.setWeather(parser.getText());
                                }
                                break;
                            case "temperature":
                                if(inHourlyForecast && hourlyForecast != null) {
                                    parser.next();
                                    hourlyForecast.setTemperature(parser.getText());
                                }
                                break;
                            case "ten_day_forecast":
                                inDayForecast = true;
                                break;
                            case "day":
                                if (inDayForecast) {
                                    dayForecast = new DayForecast();
                                }
                                break;
                            case "date":
                                if (inDayForecast && dayForecast != null) {
                                    parser.next();
                                    dayForecast.setDate(parser.getText());
                                }
                                break;
                            case "high_temperature":
                                if(inDayForecast && dayForecast != null) {
                                    parser.next();
                                    dayForecast.setTemperatureMax(parser.getText());
                                }
                                break;
                            case "low_temperature":
                                if(inDayForecast && dayForecast != null) {
                                    parser.next();
                                    dayForecast.setTemperatureMin(parser.getText());
                                }
                                break;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        currentTag = parser.getName();
                        switch (currentTag) {
                            case "hour":
                                if (inHourlyForecast && hourlyForecast != null) {
                                    weatherData.getHourlyForecastArrayList().add(hourlyForecast);
                                    hourlyForecast = null;
                                }
                                break;
                            case "hourly_forecast":
                                inHourlyForecast = false;
                                break;
                            case "day":
                                if (inDayForecast && dayForecast != null) {
                                    weatherData.getDayForecastArrayList().add(dayForecast);
                                    dayForecast = null;
                                }
                                break;
                            case "ten_day_forecast":
                                inDayForecast = false;
                                break;
                        }
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception e){

        }

        return weatherData;
    }
}