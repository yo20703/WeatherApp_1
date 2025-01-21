package com.example.weatherapp_1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp_1.R;
import com.example.weatherapp_1.data.HourlyForecast;
import com.example.weatherapp_1.data.WeatherData;

import java.nio.file.Watchable;
import java.util.ArrayList;
import java.util.Calendar;

public class ListWeatherAdapter extends RecyclerView.Adapter<ListWeatherAdapter.ListWeatherAdapterHolder> {

    ArrayList<WeatherData> weatherDataArrayList = new ArrayList<>();

    public ListWeatherAdapter(ArrayList<WeatherData> weatherDataArrayList) {
        this.weatherDataArrayList = weatherDataArrayList;
    }

    @NonNull
    @Override
    public ListWeatherAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_weather_card, parent, false);
        return new ListWeatherAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListWeatherAdapterHolder holder, int position) {
        WeatherData weatherData = weatherDataArrayList.get(position);
        holder.tvLocation.setText(weatherData.getCity());

        HourlyForecast hourlyForecast = getCurrentHourlyForecast(weatherData.getCity());
        if(hourlyForecast != null){
            holder.tvTemperature.setText(hourlyForecast.getTemperature());
            holder.tvWeatherStatus.setText(hourlyForecast.getWeatherCondition());
        }

        switch (hourlyForecast.getWeatherCondition()) {
            case "sunny":
                holder.ivBackground.setImageResource(R.drawable.sunny);
                break;
            case "cloudy":
                holder.ivBackground.setImageResource(R.drawable.cloudy);
                break;
            case "overcast":
                holder.ivBackground.setImageResource(R.drawable.overcast);
                break;
            case "rain":
                holder.ivBackground.setImageResource(R.drawable.rain);
                break;
            case "thunder":
                holder.ivBackground.setImageResource(R.drawable.thunder);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return weatherDataArrayList.size();
    }

    public ArrayList<WeatherData> getWeatherDataArrayList() {
        return weatherDataArrayList;
    }

    public void updateList(ArrayList<WeatherData> weatherDataArrayList) {
        this.weatherDataArrayList = weatherDataArrayList;
        notifyDataSetChanged();
    }

    private HourlyForecast getCurrentHourlyForecast(String city) {
        for(WeatherData weatherData : weatherDataArrayList) {
            if(weatherData.getCity().equals(city)){
                Calendar calendar = Calendar.getInstance();
                int currentHour = calendar.get(Calendar.HOUR_OF_DAY);

                for(HourlyForecast hourlyForecast : weatherData.getHourlyForecastArrayList()) {
                    int hourlyForecastHour = Integer.parseInt(hourlyForecast.getTime().split(":")[0]);
                    if(currentHour == hourlyForecastHour) {
                        return hourlyForecast;
                    }
                }
            }
        }

        return null;
    }

    static class ListWeatherAdapterHolder extends RecyclerView.ViewHolder{
        ImageView ivBackground;
        TextView tvLocation;
        TextView tvWeatherStatus;
        TextView tvTemperature;
        TextView tvMaxMinTemperature;

        public ListWeatherAdapterHolder(@NonNull View itemView) {
            super(itemView);

            ivBackground = itemView.findViewById(R.id.iv_weather_background);
            tvLocation = itemView.findViewById(R.id.tv_current_location_title);
            tvWeatherStatus = itemView.findViewById(R.id.tv_weather_status);
            tvTemperature = itemView.findViewById(R.id.tv_temperature);
            tvMaxMinTemperature = itemView.findViewById(R.id.tv_hl_temperature);
        }
    }
}
