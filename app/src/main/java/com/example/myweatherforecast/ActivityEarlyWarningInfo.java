package com.example.myweatherforecast;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.example.util.CustomTitleBar;

/**
 * Created by 覃玉初 on 2017/6/11.
 */
public class ActivityEarlyWarningInfo extends CustomTitleBar implements View.OnClickListener{

    private ImageView powerSwitch;
    private LinearLayout earlyWarningLayout;
    private static int flag=0;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earlywarning_info);
        newView();
    }

    private void newView(){
        powerSwitch=(ImageView)this.findViewById(R.id.powerSwitch);
        earlyWarningLayout=(LinearLayout)this.findViewById(R.id.earlyWarningLayout);
        returnButton=(ImageButton)this.findViewById(R.id.returnButton);
        earlyWarningLayout.setOnClickListener(this);
        returnButton.setOnClickListener(this);
        setSearchCityEditTextVisibity(false);
        setIncreaseCityVisibity(false);
        setReturnTitleText("预警管理");
        sharedPreferences=getConfigurationInfo();
        flag=sharedPreferences.getInt("PowerSwitch",0);
        setImageViewIcon();
    }

    private void setImageViewIcon(){
        if(flag==0){
            powerSwitch.setBackgroundResource(R.drawable.toggle_btn_unchecked);
            sharedPreferences.edit().putInt("PowerSwitch",flag).commit();
        }else{
            powerSwitch.setBackgroundResource(R.drawable.toggle_btn_checked);
            sharedPreferences.edit().putInt("PowerSwitch",flag).commit();
        }
    }

    private SharedPreferences getConfigurationInfo(){
        sharedPreferences=getSharedPreferences("EarlyWarningPowerSwitch",ActivityEarlyWarningInfo.MODE_PRIVATE);
        return sharedPreferences;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.earlyWarningLayout:
                if(flag==0){
                    flag=1;
                }else{
                    flag=0;
                }
                    setImageViewIcon();
                    break;
            case R.id.returnButton:
                ActivityEarlyWarningInfo.this.finish();
                break;
        }
    }
}
