package com.example.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import com.example.myweatherforecast.R;

/**
 * Created by 覃玉初 on 2017/5/23.
 */
public class CustomizeAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private String[] list;
    private Context context;

    public CustomizeAdapter(Context context,String[] list){
        this.context = context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button button=new Button(context);
        button.setText(list[position]);
        button.setBackgroundResource(R.drawable.button_change);
        return button;
    }
}
