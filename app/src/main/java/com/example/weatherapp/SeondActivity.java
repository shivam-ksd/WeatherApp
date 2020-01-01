package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SeondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seond);
        TextView Weather=findViewById(R.id.Weather);
        TextView Temperature=findViewById(R.id.Temperature);
        TextView Pressure=findViewById(R.id.Pressure);
        TextView Humidity=findViewById(R.id.Humidity);
        TextView City=findViewById(R.id.City);
        Intent intent=getIntent();
        String main=intent.getStringExtra("Weather");
        String temp=intent.getStringExtra("temp");
        String pressure=intent.getStringExtra("pressure");
        String humidity=intent.getStringExtra("humidity");
        String city=intent.getStringExtra("city");
        Weather.setText(main);
        Temperature.setText(temp);
        Pressure.setText(pressure);
        Humidity.setText(humidity);
        City.setText(city);



    }
}
