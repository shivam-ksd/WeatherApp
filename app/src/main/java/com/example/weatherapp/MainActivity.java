package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public void weatherbutton(View view) {
        EditText cityName=findViewById(R.id.editText);
        String CityName=cityName.getText().toString();
        WeatherInfo weatherInfo=new WeatherInfo();
        TextView Weather=findViewById(R.id.Weather);
        TextView Temperature=findViewById(R.id.Temperature);
        TextView Pressure=findViewById(R.id.Pressure);
        TextView Humidity=findViewById(R.id.Humidity);

        try {
            String weatherApiDetails=weatherInfo.execute("https://api.openweathermap.org/data/2.5/weather?q="+CityName+"&appid=8675d49369549990b7855b2ec176f524").get();
            Log.i("APIdetails",weatherApiDetails);
            JSONObject jsonObject=new JSONObject(weatherApiDetails);
            String jsonweather=jsonObject.getString("weather");
            JSONArray jsonArray=new JSONArray(jsonweather);
            int len=jsonArray.length();
            String main="";
            String description="";

            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonArrayObject=jsonArray.getJSONObject(i);
                main=jsonArrayObject.getString("main");
                description=jsonArrayObject.getString("description");

            }
            JSONObject jsontemp=jsonObject.getJSONObject("main");

            String temp="";
            String pressure="";
            String humidity="";

                temp=jsontemp.getString("temp");
                pressure=jsontemp.getString("pressure");
                humidity=jsontemp.getString("humidity");


            Weather.setText(main);
            Temperature.setText(temp);
            Pressure.setText(pressure);
            Humidity.setText(humidity);
            Intent intent =new Intent(this,SeondActivity.class);
            intent.putExtra("Weather",main);
            intent.putExtra("temp",temp);
            intent.putExtra("pressure",pressure);
            intent.putExtra("humidity",humidity);
            intent.putExtra("city",CityName);
            startActivity(intent);


    }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    class WeatherInfo extends AsyncTask<String,Void,String>{

       @Override
       protected String doInBackground(String... params) {
           try{
               URL url=new URL(params[0]);
               HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
               httpURLConnection.connect();
               InputStream is =httpURLConnection.getInputStream();
               InputStreamReader inputStreamReader=new InputStreamReader(is);

               int data=inputStreamReader.read();
               String apiDetails="";
               char current;
               while(data!=-1){
                   current=(char)data;
                   apiDetails+=current;
                   data=inputStreamReader.read();
               }
               return apiDetails;
           }
           catch(Exception e){
               e.printStackTrace();
           }
           return null;
       }
   }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
