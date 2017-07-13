package com.example.tool;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.databasemanagement.WeatherType;
import com.example.entity.CityWeatherType;
import com.example.myweatherforecast.ActivityEarlyWarningInfo;

import java.io.*;

/**
 * Created by 覃玉初 on 2017/6/13.
 */
public class StorageWeatherType {

    private Context context;
    private SharedPreferences sharedPreferences;

    public StorageWeatherType(Context context){
        this.context=context;
        storageDatabaseWeatherTypeData();
    }

    public void storageDatabaseWeatherTypeData(){
        if(alreadyExist()==false){
            try {
                String str=null;
                InputStream in=context.getResources().getAssets().open("weather_type.txt");
                BufferedReader reader=new BufferedReader(new InputStreamReader(in));
                while((str=reader.readLine())!=null){
                    String[] strs=str.split(",");
                    CityWeatherType cityWeatherType=new CityWeatherType();
                    cityWeatherType.setWeatherType(strs[0]);
                    cityWeatherType.setBigImage(strs[1]);
                    cityWeatherType.setSmallImage(strs[2]);
                    WeatherType weatherType=new WeatherType(context);
                    weatherType.addWeatherType(weatherType.getDatabaseSession(),cityWeatherType);

                }
                reader.close();
                in.close();;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            return;
        }
    }

    private boolean alreadyExist(){
        sharedPreferences=context.getSharedPreferences("StorageDatabaseWeatherTypeData", ActivityEarlyWarningInfo.MODE_PRIVATE);
        if(sharedPreferences.getInt("exist",0)==0){
            sharedPreferences.edit().putInt("exist",1);
            return false;
        }else{
            return true;
        }
    }

}
