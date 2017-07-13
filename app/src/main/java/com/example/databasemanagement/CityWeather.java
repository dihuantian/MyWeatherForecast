package com.example.databasemanagement;

import android.app.Application;
import android.content.Context;
import com.example.entity.DaoMaster;
import com.example.entity.DaoSession;
import com.example.entity.DatabaseMangement;
import com.example.entity.DatabaseMangementDao;
import org.greenrobot.greendao.query.Query;

import java.util.List;

/**
 * Created by 覃玉初 on 2017/6/9.
 */

public class CityWeather{

    private Context context;
    private DaoMaster.OpenHelper openHelper;
    private DatabaseMangementDao mangementDao;

    public CityWeather(Context context){
        this.context=context;
        openHelper=new DaoMaster.DevOpenHelper(context,"weather.db",null);
    }

    public DaoSession getDatabaseSession(){
        DaoMaster daoMaster=new DaoMaster(openHelper.getReadableDb());
        DaoSession session=daoMaster.newSession();
        return session;
    }

    public boolean addData(DaoSession daoSession ,DatabaseMangement mangement){
        DatabaseMangementDao databaseMangement=daoSession.getDatabaseMangementDao();
        databaseMangement.insert(mangement);
        colseDatabase(daoSession);
        return true;
    }

    public boolean deleteData(DaoSession daoSession ,DatabaseMangement mangement){
        DatabaseMangementDao databaseMangement=daoSession.getDatabaseMangementDao();
        databaseMangement.delete(mangement);
        colseDatabase(daoSession);
        return true;
    }

    public List<DatabaseMangement> queryAllData(DaoSession daoSession){
        DatabaseMangementDao databaseMangement=daoSession.getDatabaseMangementDao();
        List<DatabaseMangement> mangement = databaseMangement.loadAll();
        colseDatabase(daoSession);
        return mangement;
    }

    public DatabaseMangement queryData(DaoSession daoSession,String cityName){
        DatabaseMangementDao databaseMangement=daoSession.getDatabaseMangementDao();
        Query<DatabaseMangement> mangementQuery=databaseMangement.queryBuilder()
                .where(DatabaseMangementDao.Properties.CityName.eq(cityName))
                .build();
        DatabaseMangement mangement;
        if(mangementQuery.list().size()==0){
            mangement=null;
        }else{
            mangement=mangementQuery.list().get(0);
        }
        return mangement;
    }

    public boolean updateData(DaoSession daoSession ,DatabaseMangement mangement){
        DatabaseMangementDao databaseMangement=daoSession.getDatabaseMangementDao();
        databaseMangement.update(mangement);
        colseDatabase(daoSession);
        return true;
    }

    public void colseDatabase(DaoSession daoSession){
        daoSession.clear();
        openHelper.close();
    }

}
