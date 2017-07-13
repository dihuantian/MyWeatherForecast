package com.example.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.myweatherforecast.R;

/**
 * Created by 覃玉初 on 2017/6/10.
 */
public class CustomMenuChild extends RelativeLayout{

    public LinearLayout menuChild;
    public ImageView menuChildCityManageImage;
    public TextView cityManageText;
    private ImageView menuChildRightImage;
    private int cityManageTextSize;
    private int menuChildCityManageBackgroundImage;
    private String cityManageChildText;
    private int menuChildRightIcon;

    public CustomMenuChild(Context context) {
        super(context);
        newWidget(context);
    }

    public CustomMenuChild(Context context, AttributeSet attrs) {
        super(context, attrs);
        newTypeArray(context, attrs);
        newWidget(context);
    }

    public CustomMenuChild(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        newTypeArray(context, attrs);
        newWidget(context);
    }

    private void newWidget(Context context){
        LayoutInflater.from(context).inflate(R.layout.menu_child,this,true);
        menuChild=(LinearLayout)this.findViewById(R.id.menuChild);
        menuChildCityManageImage=(ImageView)this.findViewById(R.id.menuChildCityManageImage);
        cityManageText=(TextView)this.findViewById(R.id.cityManageText);
        menuChildRightImage=(ImageView)this.findViewById(R.id.menuChildRightImage);
/*        menuChildCityManageImage.setBackgroundResource(menuChildCityManageBackgroundImage);*/
        menuChildCityManageImage.setImageResource(menuChildCityManageBackgroundImage);
        menuChildRightImage.setImageResource(menuChildRightIcon);
        cityManageText.setTextSize(cityManageTextSize);
        cityManageText.setText(cityManageChildText);
    }

    private void newTypeArray(Context context, AttributeSet attrs){
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.CustomMenuChild);
        cityManageTextSize=typedArray.getInteger(R.styleable.CustomMenuChild_cityManageTextSize,16);
        menuChildCityManageBackgroundImage=typedArray.getResourceId(R.styleable.CustomMenuChild_menuChildCityManageBackgroundImage,R.drawable.location_icon);
        cityManageChildText=typedArray.getString(R.styleable.CustomMenuChild_cityManageChildText);
        menuChildRightIcon=typedArray.getInteger(R.styleable.CustomMenuChild_menuChildRightIcon,R.drawable.more_praise);
        typedArray.recycle();
    }

    public void setMenuChildListener(OnClickListener onClickListener){
        menuChild.setOnClickListener(onClickListener);
    }

    public void setCityManageText(String name){
        cityManageText.setText(name);
    }

}
