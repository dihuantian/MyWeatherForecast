package com.example.tool;

import com.example.weatherJsonEntity.WeatherData;
import com.google.gson.Gson;

/**
 * Created by 覃玉初 on 2017/6/9.
 */
public class JsonAnalysis {

    public WeatherData analysisGson(String jsonString){

        /*创建一个Gson对象*/
        Gson gson=new Gson();
        WeatherData data =gson.fromJson(jsonString,WeatherData.class);
        return data;
    }
}
