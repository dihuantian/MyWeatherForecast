package com.example.myweatherforecast;

import android.content.Context;
import android.content.Intent;
import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.Toast;
import com.example.databasemanagement.CityWeather;
import com.example.entity.DatabaseMangement;
import com.example.tool.JudgmentNetworkConnection;
import com.example.tool.XMLParsing;
import com.example.util.CustomExpandableListAdapter;
import com.example.util.CustomTitleBar;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ActivityCitySelect extends CustomTitleBar {


    /*获得当前Activity的Context*/
    private Context context;

    List<Map<String,Map<String , String>>> getCountries;

    /*expandablelistview使用下列列表*/
    private ExpandableListView expandableListView;

    List<String> group=new ArrayList<>();

    List< List<String>> childs=new ArrayList<>();

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_select);
        initContext();
        newWidget();
        getListData();
        expandableListView.setAdapter(new CustomExpandableListAdapter(ActivityCitySelect.this,group,childs));
        expandableListView.setOnChildClickListener(new OnChlidListener());
        returnButton.setOnClickListener(new ReturnButtonClickListener());
        setIncreaseCityVisibity(false);
        setReturnTitleTextVisibity(false);//设置返回标题栏为空
    }

    /*初始化Context的方法*/
    private void initContext(){
        context=this;
        XMLParsing parsing=new XMLParsing(context);
        getCountries=parsing.readXmlFile();
    }

    /*初始化所有的控件*/
    private void newWidget(){
        expandableListView=(ExpandableListView)this.findViewById(R.id.provicen);
        returnButton=(ImageButton)this.findViewById(R.id.returnButton);
    }

    private void getListData(){
        Iterator iteratorGroup=getCountries.get(0).entrySet().iterator();
        while(iteratorGroup.hasNext()){
            Map.Entry entry= (Map.Entry) iteratorGroup.next();
            String name=entry.getKey().toString();
            group.add(name);
        }

        for(int i=0;i<group.size();i++){
            Iterator iteratorChild=(getCountries.get(0).get(group.get(i))).entrySet().iterator();
            List<String> child=new ArrayList<>();
            while(iteratorChild.hasNext()){
                Map.Entry entry= (Map.Entry) iteratorChild.next();
                String name=entry.getKey().toString();
                child.add(name);
            }
            childs.add(child);
        }

    }

    private Message packageData(String cityName,String cityNumber){
        DatabaseMangement mangement=new DatabaseMangement();
        mangement.setCityName(cityName);
        mangement.setCityNumber(cityNumber);
        Message message=new Message();
        message.what=1;
        message.obj=mangement;
        return message;
    }

    class OnChlidListener implements ExpandableListView.OnChildClickListener{

        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
            String cityName=childs.get(groupPosition).get(childPosition);
            String cityNumber=((getCountries.get(0)).get(group.get(groupPosition))).get(cityName);
            Message message=packageData(cityName,cityNumber);
            HandlerThread handlerThread=new HandlerThread("database thread");
            handlerThread.start();
            handler=new Handler(handlerThread.getLooper()){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    JudgmentNetworkConnection connection=new JudgmentNetworkConnection();
                    if(connection.isNetworkAvalible(ActivityCitySelect.this)==true){
                        CityWeather cityWeather=new CityWeather(ActivityCitySelect.this);
                        DatabaseMangement mangement=cityWeather.queryData(cityWeather.getDatabaseSession(),((DatabaseMangement) msg.obj).getCityName());
                        if(mangement!=null){

                        }else{
                            cityWeather.addData(cityWeather.getDatabaseSession(), (DatabaseMangement) msg.obj);
                        }
                    }else{
                        Toast.makeText(ActivityCitySelect.this,"你断网了,不能添加城市",Toast.LENGTH_LONG).show();
                    }
                }
            };
            handler.sendMessage(message);
            return true;
        }
    }

    class ReturnButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            ActivityCitySelect.this.finish();
        }
    }

}
