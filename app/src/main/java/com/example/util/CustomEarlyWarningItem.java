package com.example.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.myweatherforecast.R;

/**
 * Created by 覃玉初 on 2017/6/12.
 */
public class CustomEarlyWarningItem extends RelativeLayout {

    private TextView weeb_number;
    private TextView low_high_temp;
    private ImageView weather_image;
    private String weebNumberItemText,lowHighTempItem;
    private int weatherImageItem;

    public CustomEarlyWarningItem(Context context) {
        super(context);
        newWidget(context);
    }

    public CustomEarlyWarningItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        newTypeArray(context,attrs);
        newWidget(context);
    }

    public CustomEarlyWarningItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        newTypeArray(context,attrs);
        newWidget(context);
    }

    private void newWidget(Context context){
        LayoutInflater.from(context).inflate(R.layout.earlywarning_item,this,true);
        setWeeb_number((TextView)this.findViewById(R.id.weeb_number));
        setLow_high_temp((TextView)this.findViewById(R.id.low_high_temp));
        setWeather_image((ImageView)this.findViewById(R.id.weather_image));
        weeb_number.setText(weebNumberItemText);
        low_high_temp.setText(lowHighTempItem);
        weather_image.setImageResource(weatherImageItem);
    }

    private void newTypeArray(Context context, AttributeSet attrs){
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.CustomEarlyWarningItem);
        weebNumberItemText=typedArray.getString(R.styleable.CustomEarlyWarningItem_weebNumberItemText);
        weatherImageItem=typedArray.getResourceId(R.styleable.CustomEarlyWarningItem_weatherImageItem,R.drawable.ww2);
        lowHighTempItem=typedArray.getString(R.styleable.CustomEarlyWarningItem_lowHighTempItem);
        typedArray.recycle();
    }


    public TextView getWeeb_number() {
        return weeb_number;
    }

    public void setWeeb_number(TextView weeb_number) {
        this.weeb_number = weeb_number;
    }

    public TextView getLow_high_temp() {
        return low_high_temp;
    }

    public void setLow_high_temp(TextView low_high_temp) {
        this.low_high_temp = low_high_temp;
    }

    public ImageView getWeather_image() {
        return weather_image;
    }

    public void setWeather_image(ImageView weather_image) {
        this.weather_image = weather_image;
    }
}
