package com.example.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.databasemanagement.WeatherType;
import com.example.entity.CityWeatherType;
import com.example.myweatherforecast.R;
import com.example.weatherJsonEntity.Weather;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.List;

/**
 * Created by 覃玉初 on 2017/6/12.
 */
public class CustomAnimationPages extends RelativeLayout {

    private TextView cityName_page;
    private TextView weatherType_page;
    private TextView weatherTemp_page;
    private TextView level_page;
    private TextView windDirection_page;
    private String cityNamePageText;
    private String weatherTypePageText;
    private String weatherTempPageText;
    private String levelPageText;
    private String windDirectionPage;
    private LinearLayout weeb_pagesItem;
    private Context contextSctivity;
    private Context context;
    private Handler mainHandler,childHandler;
    private  CustomEarlyWarningItem item;

    public CustomAnimationPages(Context context) {
        super(context);
        contextSctivity=context;
        newWidget(context);
    }

    public CustomAnimationPages(Context context, AttributeSet attrs) {
        super(context, attrs);
        contextSctivity=context;
        newTypeArray(context,attrs);
        newWidget(context);
    }

    public CustomAnimationPages(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        contextSctivity=context;
        newTypeArray(context,attrs);
        newWidget(context);
    }

    private void newWidget(Context context){
        LayoutInflater.from(context).inflate(R.layout.animation_pages,this,true);
        this.context=context;
        setCityName_page((TextView)this.findViewById(R.id.cityName_page));
        setWeatherType_page((TextView)this.findViewById(R.id.weatherType_page));
        setWeatherTemp_page((TextView)this.findViewById(R.id.weatherTemp_page));
        setLevel_page((TextView)this.findViewById(R.id.level_page));
        setWindDirection_page((TextView)this.findViewById(R.id.windDirection_page));
        weeb_pagesItem=(LinearLayout)this.findViewById(R.id.weeb_pagesItem);
        cityName_page.setText(cityNamePageText);
        weatherType_page.setText(weatherTypePageText);
        weatherTemp_page.setText(weatherTempPageText);
        level_page.setText(levelPageText);
        windDirection_page.setText(windDirectionPage);
    }

    private void newTypeArray(Context context, AttributeSet attrs){
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.CustomAnimationPages);
        cityNamePageText=typedArray.getString(R.styleable.CustomAnimationPages_cityNamePageText);
        weatherTypePageText=typedArray.getString(R.styleable.CustomAnimationPages_weatherTypePageText);
        weatherTempPageText=typedArray.getString(R.styleable.CustomAnimationPages_weatherTempPageText);
        levelPageText=typedArray.getString(R.styleable.CustomAnimationPages_levelPageText);
        windDirectionPage=typedArray.getString(R.styleable.CustomAnimationPages_windDirectionPage);
        typedArray.recycle();
    }

    public CustomAnimationPages completeView(Weather weather){
        completeAnimationPagesView(weather);
        return this;
    }

    private void completeAnimationPagesView(Weather weather){
        cityName_page.setText(weather.getCity());
        weatherType_page.setText(weather.getForecast().get(0).getType());
        weatherTemp_page.setText(String.valueOf(weather.getWendu())+"°");
        level_page.setText(weather.getForecast().get(0).getFengli());
        windDirection_page.setText(weather.getForecast().get(0).getFengxiang());
        try {
            completeEarlyWarningItemView(weather);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private void completeEarlyWarningItemView(Weather weather) throws NoSuchFieldException {
        for(int i=0;i<weather.getForecast().size()+1;i++){
            item=new CustomEarlyWarningItem(contextSctivity,null);
            getWeatherTypeImage();
            if(i==0){
                setWeatherTypeImage(item);
                ( item.getWeeb_number()).setText(selectDate(i));
                ( item. getLow_high_temp()).setText(processStr(weather.getYesterday().getHigh())+"/"+processStr(weather.getYesterday().getLow()));
                Message message=new Message();
                message.what=0;
                message.obj=weather.getYesterday().getType();
                childHandler.sendMessage(message);

            }else{
                ( item.getWeeb_number()).setText(selectDate(i));
                ( item. getLow_high_temp()).setText(processStr(weather.getForecast().get(i-1).getHigh())+"/"+processStr(weather.getForecast().get(i-1).getLow()));
                setWeatherTypeImage(item);
                Message message=new Message();
                message.what=0;
                message.obj=weather.getForecast().get(i-1).getType();
                childHandler.sendMessage(message);
            }
            weeb_pagesItem.addView(item);
        }
    }

    private String processStr(String str){
        String[] strs=str.split(" ");
        return (strs[1].split("℃"))[0]+"°";
    }

    private String selectDate(int flag){
        String date=null;
        Calendar cal = Calendar.getInstance();
        switch (flag){
            case 0:
                date="昨天";
                break;
            case 1:
                date="今天";
                break;
            case 2:
                date="明天";
                break;
            case 3:
                date="周"+(cal.get(Calendar.DAY_OF_WEEK)+1);
                break;
            case 4:
                date="周"+(cal.get(Calendar.DAY_OF_WEEK)+2);
                break;
            case 5:
                date="周"+(cal.get(Calendar.DAY_OF_WEEK)+3);
                break;
        }
        return date;
    }

    int i=0;
    private void setWeatherTypeImage(CustomEarlyWarningItem itemed) {
        final CustomEarlyWarningItem item=itemed;
        mainHandler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what==1&&msg.obj!=null) {
                    String imageName = String.valueOf(msg.obj);
                    Field field;
                    try {
                        field=R.drawable.class.getField(imageName.trim());
                        int resId=field.getInt(new R.drawable());
                        if(imageName==null){
                            Log.i("sumber", String.valueOf(i)+"我");
                            item.getWeather_image().setImageResource(R.drawable.ww35);
                        }else{
                            Log.i("sumber", String.valueOf(i)+"我不"+imageName.trim());
                            item.getWeather_image().setImageResource(resId);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    private void getWeatherTypeImage(){
        HandlerThread handlerThread=new HandlerThread("getImageName");
        handlerThread.start();
        childHandler=new Handler(handlerThread.getLooper()){
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0 && msg.obj != null) {
                    WeatherType weatherType = new WeatherType(context);
                    CityWeatherType weatherType1=weatherType.queryWeatherType(weatherType.getDatabaseSession(), (String) msg.obj);
                    Message message=new Message();
                    message.what=1;
                    message.obj=weatherType1.getSmallImage();
                    mainHandler.sendMessage(message);
                }
            }
        };
    }

    public TextView getCityName_page() {
        return cityName_page;
    }

    public void setCityName_page(TextView cityName_page) {
        this.cityName_page = cityName_page;
    }

    public TextView getWeatherType_page() {
        return weatherType_page;
    }

    public void setWeatherType_page(TextView weatherType_page) {
        this.weatherType_page = weatherType_page;
    }

    public TextView getWeatherTemp_page() {
        return weatherTemp_page;
    }

    public void setWeatherTemp_page(TextView weatherTemp_page) {
        this.weatherTemp_page = weatherTemp_page;
    }

    public TextView getLevel_page() {
        return level_page;
    }

    public void setLevel_page(TextView level_page) {
        this.level_page = level_page;
    }

    public TextView getWindDirection_page() {
        return windDirection_page;
    }

    public void setWindDirection_page(TextView windDirection_page) {
        this.windDirection_page = windDirection_page;
    }
}
