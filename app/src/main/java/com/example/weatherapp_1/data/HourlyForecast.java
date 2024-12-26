package com.example.weatherapp_1.data;

public class HourlyForecast {
    private String time;                    //時間
    private String weatherCondition;        //天氣狀況
    private String temperature;             //溫度

    public HourlyForecast(String time, String weatherCondition, String temperature) {
        this.time = time;
        this.weatherCondition = weatherCondition;
        this.temperature = temperature;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
