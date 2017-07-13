package com.example.weatherJsonEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 覃玉初 on 2017/6/9.
 */
public class Weather implements Serializable {

    private WeatherYesterday yesterday;
    private String city;
    private String aqi;
    private List<WeatherInfo> forecast;
    private String ganmao;
    private int wendu;


    public WeatherYesterday getYesterday() {
        return yesterday;
    }

    public void setYesterday(WeatherYesterday yesterday) {
        this.yesterday = yesterday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public List<WeatherInfo> getForecast() {
        return forecast;
    }

    public void setForecast(List<WeatherInfo> forecast) {
        this.forecast = forecast;
    }

    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public int getWendu() {
        return wendu;
    }

    public void setWendu(int wendu) {
        this.wendu = wendu;
    }
}
