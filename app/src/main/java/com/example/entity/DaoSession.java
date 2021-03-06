package com.example.entity;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.entity.CityWeatherType;
import com.example.entity.DatabaseMangement;

import com.example.entity.CityWeatherTypeDao;
import com.example.entity.DatabaseMangementDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig cityWeatherTypeDaoConfig;
    private final DaoConfig databaseMangementDaoConfig;

    private final CityWeatherTypeDao cityWeatherTypeDao;
    private final DatabaseMangementDao databaseMangementDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        cityWeatherTypeDaoConfig = daoConfigMap.get(CityWeatherTypeDao.class).clone();
        cityWeatherTypeDaoConfig.initIdentityScope(type);

        databaseMangementDaoConfig = daoConfigMap.get(DatabaseMangementDao.class).clone();
        databaseMangementDaoConfig.initIdentityScope(type);

        cityWeatherTypeDao = new CityWeatherTypeDao(cityWeatherTypeDaoConfig, this);
        databaseMangementDao = new DatabaseMangementDao(databaseMangementDaoConfig, this);

        registerDao(CityWeatherType.class, cityWeatherTypeDao);
        registerDao(DatabaseMangement.class, databaseMangementDao);
    }
    
    public void clear() {
        cityWeatherTypeDaoConfig.clearIdentityScope();
        databaseMangementDaoConfig.clearIdentityScope();
    }

    public CityWeatherTypeDao getCityWeatherTypeDao() {
        return cityWeatherTypeDao;
    }

    public DatabaseMangementDao getDatabaseMangementDao() {
        return databaseMangementDao;
    }

}
