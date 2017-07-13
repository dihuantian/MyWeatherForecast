package com.example.entity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import org.greenrobot.greendao.annotation.*;

/**
 * Created by 覃玉初 on 2017/6/9.
 */
@Entity
public class DatabaseMangement{

    @Id(autoincrement = true)
    private Long cityId;
    @Property
    @NotNull
    @Unique
    private String cityName;
    @Property
    @NotNull
    @Unique
    private String cityNumber;
    public String getCityNumber() {
        return this.cityNumber;
    }
    public void setCityNumber(String cityNumber) {
        this.cityNumber = cityNumber;
    }
    public String getCityName() {
        return this.cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public Long getCityId() {
        return this.cityId;
    }
    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }
    @Generated(hash = 758688023)
    public DatabaseMangement(Long cityId, @NotNull String cityName,
            @NotNull String cityNumber) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.cityNumber = cityNumber;
    }
    @Generated(hash = 246788866)
    public DatabaseMangement() {
    }

}
