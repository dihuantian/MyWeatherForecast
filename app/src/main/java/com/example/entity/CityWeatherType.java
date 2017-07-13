package com.example.entity;

import org.greenrobot.greendao.annotation.*;

/**
 * Created by 覃玉初 on 2017/6/13.
 */
@Entity
public class CityWeatherType {
    @Id(autoincrement = true)
    private Long id;
    @Property
    @NotNull
    private String weatherType;
    @Property
    @NotNull
    private String smallImage;
    @Property
    @NotNull
    private String bigImage;
    public String getBigImage() {
        return this.bigImage;
    }
    public void setBigImage(String bigImage) {
        this.bigImage = bigImage;
    }
    public String getSmallImage() {
        return this.smallImage;
    }
    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }
    public String getWeatherType() {
        return this.weatherType;
    }
    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1607561747)
    public CityWeatherType(Long id, @NotNull String weatherType,
            @NotNull String smallImage, @NotNull String bigImage) {
        this.id = id;
        this.weatherType = weatherType;
        this.smallImage = smallImage;
        this.bigImage = bigImage;
    }
    @Generated(hash = 9054800)
    public CityWeatherType() {
    }
}
