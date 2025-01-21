package com.example.weatherapp_1.activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.weatherapp_1.R;

import org.osmdroid.tileprovider.tilesource.XYTileSource;
import org.osmdroid.views.MapView;

public class MapActivity extends AppCompatActivity {
    private MapView mapView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        initLayout();
    }

    private void initLayout() {
        mapView = findViewById(R.id.mapview);
        mapView.setUseDataConnection(false);
        mapView.setTileSource(
                new XYTileSource(
                        "Google Maps HD",
                        7,
                        12,
                        256,
                        ".png",
                        new String[] { "" }
                )
        );
    }
}