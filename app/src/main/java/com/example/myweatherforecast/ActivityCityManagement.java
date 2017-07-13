package com.example.myweatherforecast;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.*;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.example.databasemanagement.CityWeather;
import com.example.entity.DatabaseMangement;
import com.example.tool.IntentConnection;
import com.example.tool.JsonAnalysis;
import com.example.weatherJsonEntity.Weather;
import com.example.weatherJsonEntity.WeatherData;
import com.example.util.CityManagementItem;
import com.example.util.CustomTitleBar;
import com.example.weatherJsonEntity.WeatherInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by 覃玉初 on 2017/6/8.
 */
public class ActivityCityManagement extends CustomTitleBar{

    private LinearLayout linearLayout;

    private CityManagementItem cityManagementItem;

    private List<CityManagementItem> cityManagementItemList=new ArrayList<CityManagementItem>();

    private DeleteCityData deleteCityData;

    private List<Weather> weathers;

    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_city_management);
        initWidget();
        setSearchCityEditTextVisibity(false);
        setReturnTitleText("城市管理");
    }


    private void initWidget(){
        linearLayout=(LinearLayout)this.findViewById(R.id.activity_city_management);
        increaseCity=(ImageButton)this.findViewById(R.id.increaseCity);
        returnButton=(ImageButton)this.findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new CityManagementListener());
        increaseCity.setOnClickListener(new CityManagementListener());
        Intent intent = this.getIntent();
        weathers = (List<Weather>) intent.getSerializableExtra("cityWeather");//获取list方式
        if(weathers!=null){
            setCityManagementItemList(weathers);
        }else{

        }
    }

    private void setCityManagementItemList(List<Weather> weathers){
        for(int i=0;i<weathers.size();i++){
            String nowTemperature= String.valueOf(weathers.get(i).getWendu());
            String temperature=weathers.get(i).getForecast().get(0).getLow()+"～"+(weathers.get(i).getForecast().get(0)).getHigh();
            String weatherType=weathers.get(i).getForecast().get(0).getType()+temperature;
            addWeatherCustomWidget(weathers.get(i).getCity(),nowTemperature,weatherType, Color.RED);
        }
    }

    public void addWeatherCustomWidget(String cityName,String cityTemp,String tempType,int color){
        cityManagementItem=new CityManagementItem(this, null);
        cityManagementItem.setCityNameText(cityName);
        cityManagementItem.setTemperatureNumber(tempType);
        cityManagementItem.setTemperatureText(cityTemp+"°");
        cityManagementItem.setTemperatureTextBackgroudColor(color);
        cityManagementItem.setImageButtonListeren(new OnClickListener());
        cityManagementItem.setImageButtonTag(cityName);
        cityManagementItem.setImageButtonFalg(cityName);
        cityManagementItemList.add(cityManagementItem);
        linearLayout.addView(cityManagementItem);
    }

    class OnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            for(int i=0;i<cityManagementItemList.size();i++){
                CityManagementItem cityManagementItem=cityManagementItemList.get(i);
                if((v.getTag()).equals(cityManagementItem.getImageButtonFalg())){
                    linearLayout.removeView(cityManagementItem);
                    deleteCityData= new DeleteCityData();
                    deleteCityData.start();
                    Message message=new Message();
                    message.what=1;
                    message.obj=cityManagementItem.getImageButtonFalg();
                    deleteCityData.getHandler().sendMessage(message);
                }else{
                    continue;
                }
            }
        }
    }

    class CityManagementListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.increaseCity:
                    Intent increaseCityIntent=new Intent(ActivityCityManagement.this,ActivityCitySelect.class);
                    startActivity(increaseCityIntent);
                    break;
                case R.id.returnButton:
                    ActivityCityManagement.this.finish();
                    break;
            }
        }
    }

    class DeleteCityData extends Thread{

        public Handler handler;
        private final Object mSync = new Object();

        @Override
        public void run() {
            super.run();
            Looper.prepare();
            synchronized (mSync){
                handler=new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        if(msg.what==1){
                            String cityName= (String) msg.obj;
                            CityWeather cityWeather=new CityWeather(ActivityCityManagement.this);
                            DatabaseMangement mangement;
                            mangement=cityWeather.queryData(cityWeather.getDatabaseSession(),cityName);
                            cityWeather.deleteData(cityWeather.getDatabaseSession(),mangement);
                            sharedPreferences=(ActivityCityManagement.this).getSharedPreferences("Offline",MODE_PRIVATE);
                            sharedPreferences.edit().remove(cityName).commit();
                            Log.i("key",cityName+"我在");
                        }else{
                            Log.i("key","我不在");
                        }
                    }
                };
                mSync.notifyAll();
            }
            Looper.loop();
        }

        public Handler getHandler() {
            synchronized (mSync) {
                if (handler == null) {
                    try {
                        mSync.wait();
                    } catch (InterruptedException e) {
                    }
                }
                return handler;
            }
        }
    }
}
