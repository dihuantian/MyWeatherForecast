package com.example.tool;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Network;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import com.squareup.okhttp.*;

import java.io.IOException;
import java.net.NetworkInterface;

/**
 * Created by 覃玉初 on 2017/6/9.
 */
public class IntentConnection {

    private String urlStr="http://wthrcdn.etouch.cn/weather_mini?citykey=";

    /*OKHttp连接*/
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public String okHttpConnection(Context context, String cityCode){
        urlStr+=cityCode;
        /*创建一个OkHttpClick对象*/
        OkHttpClient okHttpClient=new OkHttpClient();
        String getData=null;
        /*构建request*/
        Request request=new Request.Builder()
                .url(urlStr)
                .build();
        Call call=okHttpClient.newCall(request);
        Response response=null;
        JudgmentNetworkConnection connection=new JudgmentNetworkConnection();
        if(connection.isNetworkAvalible(context)==true){
            try {
                response=call.execute();
                if (response.isSuccessful()){
                    getData=response.body().string();
                }else{
                    Log.i("struts", String.valueOf(response.code()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return getData;
        }else{
            return null;
        }
    }

}
