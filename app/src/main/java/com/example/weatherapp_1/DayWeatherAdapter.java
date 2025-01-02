package com.example.weatherapp_1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp_1.data.DayForecast;

import java.util.ArrayList;

public class DayWeatherAdapter extends RecyclerView.Adapter<DayWeatherAdapter.DayViewHolder> {
    private ArrayList<DayForecast> dayForecasts = new ArrayList<>();

    public DayWeatherAdapter(ArrayList<DayForecast> dayForecasts) {
        this.dayForecasts = dayForecasts;
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_day_weather, parent, false);
        return new DayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
        DayForecast dayForecast = dayForecasts.get(position);

        holder.tvDate.setText(dayForecast.getDate());
        holder.tvMaxTemp.setText(dayForecast.getTemperatureMax());
        holder.tvMinTemp.setText(dayForecast.getTemperatureMin());

        switch (dayForecast.getWeather()) {
            case "sunny":
                holder.ivIcon.setImageResource(R.drawable.ic_sunny);
                break;
            case "cloudy":
                holder.ivIcon.setImageResource(R.drawable.ic_cloudy);
                break;
            case "overcast":
                holder.ivIcon.setImageResource(R.drawable.ic_overcast);
                break;
            case "rain":
                holder.ivIcon.setImageResource(R.drawable.ic_rain);
                break;
            case "ic_thunder":
                holder.ivIcon.setImageResource(R.drawable.ic_thunder);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return dayForecasts.size();
    }

    static class DayViewHolder extends RecyclerView.ViewHolder{
        TextView tvDate;
        TextView tvMaxTemp;
        TextView tvMinTemp;
        ImageView ivIcon;

        public DayViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDate = itemView.findViewById(R.id.tv_date);
            tvMaxTemp = itemView.findViewById(R.id.tv_max_temp);
            tvMinTemp = itemView.findViewById(R.id.tv_min_temp);
            ivIcon = itemView.findViewById(R.id.iv_icon);
        }
    }
}
