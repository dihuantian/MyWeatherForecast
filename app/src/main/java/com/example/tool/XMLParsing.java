package com.example.tool;


import android.content.Context;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 覃玉初 on 2017/5/20.
 */
public class XMLParsing {

    /*获得程序的上下文*/
    public Context context;

    public XMLParsing(Context context){
        this.context=context;
    }

    public  List<Map<String,Map<String , String>>> readXmlFile(){
            /*XML文件名字*/
        InputStream XmlFileName= context.getClass().getClassLoader().getResourceAsStream("assets/cityNumber.xml");
        return parsingXMLFile(XmlFileName);
    }

    public List<Map<String,Map<String , String>>> parsingXMLFile(InputStream inputStream){
/*        long startTime  = System.nanoTime() ; //开始時間*/
        XmlPullParserFactory factory;
        XmlPullParser pullParser;

        /*国*/
        List<Map<String,Map<String , String>>> getCountries=new ArrayList<>();
        String CountriesName=null;
        /*省*/
        Map<String,Map<String , String>> getProvince=new HashMap<>();
        String ProvinceName=null;
        /*城市*/
        Map<String , String> getCity=new HashMap<>();

        try {
            factory = XmlPullParserFactory.newInstance();
            pullParser=factory.newPullParser();
            pullParser.setInput(inputStream,"UTF-8");
            int xmlEvenTypeFlag=pullParser.getEventType();
            while(xmlEvenTypeFlag!=pullParser.END_DOCUMENT){
                switch (xmlEvenTypeFlag){
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        if ("countries".equals(pullParser.getName())){
                            CountriesName=pullParser.getAttributeValue(null,"name").toString();
                            break;
                        }else if ("province".equals(pullParser.getName())){
                            ProvinceName=pullParser.getAttributeValue(null,"name").toString();
                            break;
                        }else if("city".equals(pullParser.getName())) {
                            break;
                        }else if("county".equals(pullParser.getName())){
                            getCity.put(pullParser.getAttributeValue(null,"name").toString(),pullParser.nextText().toString());
                            break;
                        }
                    case XmlPullParser.END_TAG:
                        if("countries".equals(pullParser.getName())){
                            break;
                        } else if("province".equals(pullParser.getName())){
                            Map<String,String> getCountys=new HashMap<>(getCity);
                            getProvince.put(ProvinceName,getCountys);
/*                            System.out.println(CityName+"市有"+getCounty.size()+"个县城");*/
                            getCity.clear();
                            break;
                        }else if("city".equals(pullParser.getName())){
                            break;
                        }
                }
                xmlEvenTypeFlag=pullParser.next();
            }
            getCountries.add(getProvince);
/*            long consumingTime = System.nanoTime() - startTime; //消耗時間
            System.out.println("一共消耗了： "+consumingTime);*/
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return getCountries;
    }

}
