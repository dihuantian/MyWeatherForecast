package com.example.util;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.example.myweatherforecast.R;

/**
 * Created by 覃玉初 on 2017/6/8.
 */
public class CustomTitleBar extends Activity {

    private LinearLayout withoutLayout;
    public ImageButton returnButton;
    public ImageButton increaseCity;
    private TextView returnTitle;
    public EditText searchCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.custom_title_bar);
        initWidget();
    }

    private void initWidget(){
        withoutLayout=(LinearLayout)this.findViewById(R.id.withoutLayout);
        returnButton=(ImageButton)this.findViewById(R.id.returnButton);
        increaseCity=(ImageButton)this.findViewById(R.id.increaseCity);
        returnTitle=(TextView)this.findViewById(R.id.returnTitle);
        searchCity=(EditText)this.findViewById(R.id.searchCity);
    }

    @Override
    public void setContentView(int layoutResID) {
        View view = getLayoutInflater().inflate(layoutResID, null);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.BELOW, R.id.withoutLayout);
        if (null != withoutLayout)
            withoutLayout.addView(view, layoutParams);
    }


    public void setReturnTitleText(String text){
        if(text!=null&&text!=""){
            getReturnTitle().setText(text);
        }
    }

    public void setReturnTitleTextVisibity(boolean b){
        if(b==false){
            returnTitle.setVisibility(View.INVISIBLE);
        }else{
            returnTitle.setVisibility(View.VISIBLE);
        }
    }

    public void setIncreaseCityVisibity(boolean b){
        if(b==false){
            getIncreaseCity().setVisibility(View.INVISIBLE);
        }else{
            getIncreaseCity().setVisibility(View.VISIBLE);
        }
    }

    public void setReturnTitleVisibity(boolean b){
        if(b==false){
            getReturnButton().setVisibility(View.INVISIBLE);
        }else{
            getReturnButton().setVisibility(View.VISIBLE);
        }
    }

    public void setSearchCityEditTextVisibity(boolean b){
        if(b==false){
            searchCity.setVisibility(View.INVISIBLE);
        }else{
            searchCity.setVisibility(View.VISIBLE);
        }
    }


    public LinearLayout getWithoutLayout() {
        return withoutLayout;
    }

    public ImageButton getReturnButton() {
        return returnButton;
    }

    public ImageButton getIncreaseCity() {
        return increaseCity;
    }

    public TextView getReturnTitle() {
        return returnTitle;
    }
}
