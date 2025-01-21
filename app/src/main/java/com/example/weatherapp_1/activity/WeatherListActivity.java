package com.example.weatherapp_1.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp_1.R;
import com.example.weatherapp_1.adapter.ListWeatherAdapter;
import com.example.weatherapp_1.data.WeatherData;
import com.example.weatherapp_1.utils.WeatherParser;

import java.util.ArrayList;

public class WeatherListActivity extends AppCompatActivity {

    private RecyclerView rvList;
    private EditText etSearch;
    private Button btnBack;
    ListWeatherAdapter listWeatherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_list);

        initLayout();
        initData();
    }

    private void initLayout() {
        rvList = findViewById(R.id.rv_list);
        rvList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        etSearch = findViewById(R.id.et_search);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterList(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initData() {
        listWeatherAdapter = new ListWeatherAdapter(getOriginalList());
        rvList.setAdapter(listWeatherAdapter);
    }

    private void filterList(String query) {
        if (query.isEmpty()) {
            listWeatherAdapter.updateList(getOriginalList());
            return;
        }

        ArrayList<WeatherData> filteredList = new ArrayList<>();

        for(WeatherData weatherData : listWeatherAdapter.getWeatherDataArrayList()) {
            if(weatherData.getCity().toLowerCase().contains(query.toLowerCase())){
                filteredList.add(weatherData);
            }
        }

        listWeatherAdapter.updateList(filteredList);
    }

    private ArrayList<WeatherData> getOriginalList() {
        ArrayList<WeatherData> weatherDataArrayList = new ArrayList<>();
        weatherDataArrayList.add(WeatherParser.parseWeatherData(this, R.xml.current));
        weatherDataArrayList.add(WeatherParser.parseWeatherData(this, R.xml.taichung));
        weatherDataArrayList.add(WeatherParser.parseWeatherData(this, R.xml.tainan));
        weatherDataArrayList.add(WeatherParser.parseWeatherData(this, R.xml.taipei));
        weatherDataArrayList.add(WeatherParser.parseWeatherData(this, R.xml.taoyuan));

        return weatherDataArrayList;
    }
}