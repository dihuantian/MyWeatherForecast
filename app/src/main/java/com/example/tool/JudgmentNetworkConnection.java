package com.example.tool;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by 覃玉初 on 2017/6/13.
 */
public class JudgmentNetworkConnection {

    private ConnectivityManager network;
    private boolean connection=false;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public  boolean isNetworkAvalible(Context context) {
        boolean flag = false;
        //得到网络连接信息
        network = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //去进行判断网络是否连接
        if (network != null) {
            NetworkInfo info=network.getActiveNetworkInfo();;
            if(info!=null&&info.isConnected()){
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    connection=info.isAvailable();
                    connection= true;
                }
            }else{
                return connection;
            }
        }
        if(connection){
            return connection;
        }else{
            return connection;
        }
    }

}
