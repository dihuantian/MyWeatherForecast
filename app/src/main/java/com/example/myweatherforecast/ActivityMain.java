package com.example.myweatherforecast;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.GestureDetector;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewFlipper;
import com.example.databasemanagement.CityWeather;
import com.example.entity.DatabaseMangement;
import com.example.tool.IntentConnection;
import com.example.tool.JsonAnalysis;
import com.example.tool.StorageWeatherType;
import com.example.util.CustomAnimationPages;
import com.example.util.CustomTitleBar;
import com.example.weatherJsonEntity.Weather;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 覃玉初 on 2017/6/11.
 */
public class ActivityMain extends CustomTitleBar implements View.OnClickListener{

    private ViewFlipper viewFlipper;
    private LinearLayout layout_main;
    private Context context;
    public Handler handlerMain;
    private GestureDetector mDetector;
    private final static int MIN_MOVE = 200;   //最小距离
    private MyGestureListener mgListener;
    private List<CustomAnimationPages> customAnimationPagesList;
    private SharedPreferences sharedPreferences;

    private List<Weather> weathers=new ArrayList<Weather>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mgListener = new MyGestureListener();
        mDetector = new GestureDetector(this, mgListener);
        newWidget();
        getCityData();
        getCilentThreadMeassage();
    }

    private void newWidget(){
        viewFlipper=(ViewFlipper)this.findViewById(R.id.viewFlipperAnim);
        context=ActivityMain.this;
        layout_main=(LinearLayout)this.findViewById(R.id.main_layout);
        returnButton=(ImageButton)this.findViewById(R.id.returnButton);
        returnButton.setOnClickListener(this);
        setReturnTitleTextVisibity(false);
        setIncreaseCityVisibity(false);
        setReturnTitleText("");
        setSearchCityEditTextVisibity(false);
        StorageWeatherType storageWeatherType =new StorageWeatherType(ActivityMain.this);
    }

    //重写onTouchEvent触发MyGestureListener里的方法
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }

    //自定义一个GestureListener,这个是View类下的，别写错哦！！！
    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float v, float v1) {
            if(e1.getX() - e2.getX() > MIN_MOVE){
                viewFlipper.setInAnimation(context,R.animator.right_in);
                viewFlipper.setOutAnimation(context, R.animator.right_out);
                viewFlipper.showNext();
            }else if(e2.getX() - e1.getX() > MIN_MOVE){
                viewFlipper.setInAnimation(context,R.animator.left_in);
                viewFlipper.setOutAnimation(context,R.animator.left_out);
                viewFlipper.showPrevious();
            }
            return true;
        }
    }

    /*主线程获取数据库子线程传递的消息（改成获取网络子线程的数据）*/
    private void getCilentThreadMeassage(){
        handlerMain=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.i("key","简直");
                if(msg.what==3&&msg.obj!=null){
                    weathers= (List<Weather>) msg.obj;
                    customAnimationPagesList=new ArrayList<CustomAnimationPages>();
                    for(int i=0;i<weathers.size();i++){
                        CustomAnimationPages customAnimationPages=new CustomAnimationPages(context,null);
                        customAnimationPages=customAnimationPages.completeView(weathers.get(i));
                        customAnimationPagesList.add(customAnimationPages);
                        viewFlipper.addView(customAnimationPages);
                    }
                }else{
                    weathers=null;
                }
            }
        };
    }


    /*数据库子线程获取数据库数据*/
    private void getCityData(){
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                Message message=new Message();
                CityWeather cityWeather=new CityWeather(ActivityMain.this);
                List<DatabaseMangement> mangementList;
                mangementList=cityWeather.queryAllData(cityWeather.getDatabaseSession());
                if(mangementList.size()>0){
                    List<Weather> weathers = new ArrayList<Weather>();
                    sharedPreferences=(ActivityMain.this).getSharedPreferences("Offline",MODE_PRIVATE);
                    for(int i=0;i<mangementList.size();i++){
                        IntentConnection intentConnection=new IntentConnection();
                        String json=intentConnection.okHttpConnection(context,mangementList.get(i).getCityNumber());
                        if(json!=null){
                            sharedPreferences.edit().putString(mangementList.get(i).getCityName(),json).commit();
                            JsonAnalysis jsonAnalysis=new JsonAnalysis();
                            Weather weather =jsonAnalysis.analysisGson(json).getData();
                            weathers.add(weather);
                        }else{
                            json=sharedPreferences.getString(mangementList.get(i).getCityName(),null);
                            JsonAnalysis jsonAnalysis=new JsonAnalysis();
                            Weather weather =jsonAnalysis.analysisGson(json).getData();
                            weathers.add(weather);
                        }
                    }
                    message.what=3;
                    message.obj=weathers;
                    handlerMain.sendMessage(message);
                }else{
                    message.what=3;
                    message.obj=null;
                    handlerMain.sendMessage(message);
                }
            }
        });
        thread.start();
    }

    @Override
    public void onClick(View v) {
        Intent menuManagementIntent=new Intent(this,ActivityMenuManagement.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("cityWeather", (Serializable) weathers);
        menuManagementIntent.putExtras(bundle);
        startActivity(menuManagementIntent);
    }
}
