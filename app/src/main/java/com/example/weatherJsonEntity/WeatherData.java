package com.example.weatherJsonEntity;

import java.io.Serializable;

/**
 * Created by 覃玉初 on 2017/6/11.
 */
public class WeatherData  implements Serializable {

    private Weather data;
    private int status;
    private String desc;


    public Weather getData() {
        return data;
    }

    public void setData(Weather data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
