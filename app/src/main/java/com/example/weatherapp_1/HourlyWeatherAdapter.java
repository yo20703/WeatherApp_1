package com.example.weatherapp_1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp_1.data.HourlyForecast;

import java.util.ArrayList;

public class HourlyWeatherAdapter
        extends RecyclerView.Adapter<HourlyWeatherAdapter.HourlyWeatherHolder>  {

    //1:宣告HourlyForecast陣列
    private ArrayList<HourlyForecast> hourlyForecastArrayList = new ArrayList<>();

    //2:建構子，讓外部傳入HourlyForecast陣列，並且存入內部的HourlyForecast陣列
    public HourlyWeatherAdapter(ArrayList<HourlyForecast> hourlyForecastArrayList) {
        this.hourlyForecastArrayList = hourlyForecastArrayList;
    }

    @NonNull
    @Override
    public HourlyWeatherHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_hourly_weather, parent, false);
        return new HourlyWeatherHolder(view);
    }

    @Override//0~hourlyForecastArrayList.size()
    public void onBindViewHolder(@NonNull HourlyWeatherHolder holder, int position) {
        HourlyForecast forecast = hourlyForecastArrayList.get(position);

        holder.tvTime.setText(forecast.getTime());
        holder.tvTemperature.setText(forecast.getTemperature());

    }

    @Override
    public int getItemCount() {
        return hourlyForecastArrayList.size();
    }

    static class HourlyWeatherHolder extends RecyclerView.ViewHolder {
        TextView tvTime;
        ImageView ivIcon;
        TextView tvTemperature;

        public HourlyWeatherHolder(@NonNull View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tv_time);
            ivIcon = itemView.findViewById(R.id.iv_icon);
            tvTemperature = itemView.findViewById(R.id.tv_temperature);
        }
    }
}
