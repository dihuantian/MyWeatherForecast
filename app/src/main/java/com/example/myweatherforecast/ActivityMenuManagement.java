package com.example.myweatherforecast;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import com.example.util.CustomMenuChild;
import com.example.util.CustomTitleBar;
import com.example.weatherJsonEntity.Weather;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 覃玉初 on 2017/6/10.
 */
public class ActivityMenuManagement extends CustomTitleBar{

    private com.example.util.CustomMenuChild cityWeatherManagement,earlyWarningManagement;
    private List<Weather> weathers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_management);
        setReturnTitleText("天气");
        setIncreaseCityVisibity(false);
        setSearchCityEditTextVisibity(false);
        newView();
    }

    private void newView(){
        cityWeatherManagement=(CustomMenuChild)this.findViewById(R.id.cityWeatherManagement);
        earlyWarningManagement=(CustomMenuChild)this.findViewById(R.id.earlyWarningManagement);
        returnButton=(ImageButton)this.findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new ManagementLinsteren());
        cityWeatherManagement.setOnClickListener(new ManagementLinsteren());
        earlyWarningManagement.setOnClickListener(new ManagementLinsteren());
        Intent intent = this.getIntent();
        weathers = (List<Weather>) intent.getSerializableExtra("cityWeather");//获取list方式
    }

    class ManagementLinsteren implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.cityWeatherManagement:
                    Intent cityWeatherManagementIntent=new Intent(ActivityMenuManagement.this,ActivityCityManagement.class);
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("cityWeather", (Serializable) weathers);
                    cityWeatherManagementIntent.putExtras(bundle);
                    startActivity(cityWeatherManagementIntent);
                    break;
                case R.id.earlyWarningManagement:
                    Intent earlyWarningManagementIntent=new Intent(ActivityMenuManagement.this,ActivityEarlyWarningInfo.class);
                    startActivity(earlyWarningManagementIntent);
                    break;
                case R.id.returnButton:
                    Intent mainIntent=new Intent(ActivityMenuManagement.this,ActivityMain.class);
                    startActivity(mainIntent);
                    break;
            }
        }
    }

}
