package com.example.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.example.myweatherforecast.R;

import java.util.List;

/**
 * Created by 覃玉初 on 2017/5/23.
 */
public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private List<String> groud;

    private List<List<String>> child;

    private Context context;

    public CustomExpandableListAdapter(Context context,List<String> groud,List<List<String>> child){
        this.context=context;
        this.groud=groud;
        this.child=child;
    }

    @Override
    public int getGroupCount() {
        return groud.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return child.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groud.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        /*嵌套使用外部布局的时候使用convertView进行布局设置获取*/
        TextView textView;
        convertView=LayoutInflater.from(context).inflate(R.layout.text_provicer,null);
        textView=(TextView) convertView.findViewById(R.id.textView_pro);
        textView.setText(groud.get(groupPosition));
        textView.setPadding(60, 0, 0, 0);
        convertView.setTag(textView);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Button button;
        button= (Button)LayoutInflater.from(context).inflate(R.layout.button_provicen,null);
        button.setText(child.get(groupPosition).get(childPosition));
/*        button.setFocusable(false);*/
        button.setClickable(false);
        return button;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
