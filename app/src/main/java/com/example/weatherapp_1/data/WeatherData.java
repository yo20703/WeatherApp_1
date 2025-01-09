package com.example.weatherapp_1.data;

import java.util.ArrayList;

public class WeatherData {
    private String city; //城市
    private int aqi;     //空氣品質
    private ArrayList<HourlyForecast>  hourlyForecastArrayList = new ArrayList<>();
    private ArrayList<DayForecast> dayForecastArrayList = new ArrayList<>();

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAqi() {
        return aqi;
    }

    public void setAqi(int aqi) {
        this.aqi = aqi;
    }

    public ArrayList<HourlyForecast> getHourlyForecastArrayList() {
        return hourlyForecastArrayList;
    }

    public void setHourlyForecastArrayList(ArrayList<HourlyForecast> hourlyForecastArrayList) {
        this.hourlyForecastArrayList = hourlyForecastArrayList;
    }

    public ArrayList<DayForecast> getDayForecastArrayList() {
        return dayForecastArrayList;
    }

    public void setDayForecastArrayList(ArrayList<DayForecast> dayForecastArrayList) {
        this.dayForecastArrayList = dayForecastArrayList;
    }
}
