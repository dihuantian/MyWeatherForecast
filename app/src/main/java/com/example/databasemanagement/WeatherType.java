package com.example.databasemanagement;

import android.content.Context;
import com.example.entity.*;

import java.util.List;

/**
 * Created by 覃玉初 on 2017/6/13.
 */
public class WeatherType {

    private Context context;
    private DaoMaster.OpenHelper openHelper;
    private DatabaseMangementDao mangementDao;

    public WeatherType(Context context){
        this.context=context;
        openHelper=new DaoMaster.DevOpenHelper(context,"weather.db",null);
    }

    public DaoSession getDatabaseSession(){
        DaoMaster daoMaster=new DaoMaster(openHelper.getReadableDb());
        DaoSession session=daoMaster.newSession();
        return session;
    }

    public void addWeatherType(DaoSession session,CityWeatherType weatherType){
        CityWeatherTypeDao weatherTypeDao=session.getCityWeatherTypeDao();
        weatherTypeDao.insert(weatherType);
        colseDatabase(session);
    }

    public CityWeatherType queryWeatherType(DaoSession session,String weatherType){
        CityWeatherTypeDao weatherTypeDao=session.getCityWeatherTypeDao();
        List<CityWeatherType> weatherTypeList=weatherTypeDao.queryBuilder()
                .where(CityWeatherTypeDao.Properties.WeatherType.eq(weatherType))
                .build().list();
        if(weatherTypeList.size()==0||weatherTypeList==null){
            return null;
        }else{
            colseDatabase(session);
            return weatherTypeList.get(0);
        }
    }

    public void colseDatabase(DaoSession daoSession){
        daoSession.clear();
        openHelper.close();
    }
}
