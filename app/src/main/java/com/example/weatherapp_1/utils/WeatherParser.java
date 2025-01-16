package com.example.weatherapp_1.utils;

import android.content.Context;

import com.example.weatherapp_1.data.DayForecast;
import com.example.weatherapp_1.data.HourlyForecast;
import com.example.weatherapp_1.data.WeatherData;

import org.xmlpull.v1.XmlPullParser;

public class WeatherParser {
    public static WeatherData parseWeatherData(Context context, int xmlID) {
        WeatherData weatherData = new WeatherData();

        try {
            XmlPullParser parser = context.getResources().getXml(xmlID);
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
