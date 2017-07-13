package com.example.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.myweatherforecast.R;

/**
 * Created by 覃玉初 on 2017/6/7.
 */
public class CityManagementItem extends RelativeLayout {

    private ImageView imageViewIcon;
    private int imageViewIconImage;

    /*Item项的温度图片*/
    private TextView temperatureText;
    private String cityNameText;
    private int temperatureImageColor;

    /*城市的名字*/
    private TextView cityName;
    private int cityNameFont;
    private String cityTemperatureNumberText;

    /*城市的温度*/
    private TextView temperatureNumber;
    private int cityTemperatureNumberFont;

    /*删除城市Item的图片按钮*/
    private ImageButton deleteCity;
    private int deleteBackgroundImage;
    private String imageButtonFalg;


    /*整个城市项Item的外围布局*/
    private RelativeLayout overallLayout;
    private int cityItemBackground;

    public CityManagementItem(Context context) {
        super(context);
        newView(context);
    }

    public CityManagementItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        newTypeArray(context,attrs);
        newView(context);
    }

    public CityManagementItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        newTypeArray(context,attrs);
        newView(context);
    }

    private void newView(Context context){
        LayoutInflater.from(context).inflate(R.layout.city_item,this,true);
        cityName=(TextView)this.findViewById(R.id.cityName);
        temperatureNumber=(TextView)this.findViewById(R.id.temperatureNumber);
        temperatureText=(TextView)this.findViewById(R.id.temperatureText);
        deleteCity=(ImageButton)this.findViewById(R.id.deleteCity);
        overallLayout=(RelativeLayout)this.findViewById(R.id.overallLayout);
        imageViewIcon=(ImageView)this.findViewById(R.id.imageViewIcon) ;
        cityName.setTextSize(cityNameFont);
        temperatureNumber.setTextSize(cityTemperatureNumberFont);
        overallLayout.setBackgroundColor(cityItemBackground);
        cityName.setText(cityNameText);
        deleteCity.setBackgroundResource(deleteBackgroundImage);
        imageViewIcon.setBackgroundResource(imageViewIconImage);
        temperatureNumber.setText(cityTemperatureNumberText);
        temperatureText.setBackgroundResource(R.drawable.textview_temperature);
        GradientDrawable drawable= (GradientDrawable) temperatureText.getBackground();
        drawable.setColor(temperatureImageColor);
    }

    private void newTypeArray(Context context, AttributeSet attrs){
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.CityItem);
        cityNameFont=typedArray.getInteger(R.styleable.CityItem_cityNameFont,20);
        cityTemperatureNumberFont=typedArray.getInteger(R.styleable.CityItem_cityTemperatureNumberFont,14);
        cityItemBackground=typedArray.getColor(R.styleable.CityItem_cityItemBackground, Color.TRANSPARENT);
        temperatureImageColor=typedArray.getColor(R.styleable.CityItem_temperatureImageColor,Color.CYAN);
        cityNameText=typedArray.getString(R.styleable.CityItem_cityNameText);
        deleteBackgroundImage=typedArray.getResourceId(R.styleable.CityItem_deleteBackgroundImage,R.drawable.photo_share_close);
        imageViewIconImage=typedArray.getResourceId(R.styleable.CityItem_imageViewIconImage,R.drawable.photo_share_pressed);
        cityTemperatureNumberText=typedArray.getString(R.styleable.CityItem_cityTemperatureNumberText);
        typedArray.recycle();
    }

    public void setImageViewListeren(OnClickListener onClickListener){
        temperatureText.setOnClickListener(onClickListener);
    }

    public void setImageButtonListeren(OnClickListener onClickListener){
        deleteCity.setOnClickListener(onClickListener);
    }

    public void setImageButtonTag(String falg){
        deleteCity.setTag(falg);
    }

    public void setCityNameText(String cityNameText){
        cityName.setText(cityNameText);
    }

    public void setTemperatureNumber(String cityTemperatureNumberText){
        temperatureNumber.setText(cityTemperatureNumberText);
    }

    public void setTemperatureText(String temperature){
        temperatureText.setText(temperature);
    }

    public void setTemperatureTextBackgroudColor(int color){
        GradientDrawable drawable= (GradientDrawable) temperatureText.getBackground();
        drawable.setColor(color);
    }

    public String getImageButtonFalg() {
        return imageButtonFalg;
    }

    public void setImageButtonFalg(String imageButtonFalg) {
        this.imageButtonFalg = imageButtonFalg;
    }
}
