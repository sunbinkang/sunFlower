package com.kang.sunflower.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.kang.sunflower.utils.Constants;

/**
 * Created by BinKang on 2021/7/13.
 * Des :
 */
@Database(entities = {GardenPlanting.class, Plant.class}, version = 1,exportSchema = false)
// 解决此错误：Cannot figure out how to save this field into database. You can consider adding a type
// 必须导入自己的 Converters
@TypeConverters(Converters.class)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                instance = Room.databaseBuilder(context, AppDatabase.class, Constants.DATABASE_NAME).build();
            }
        }
        return instance;
    }

    // 获取ROOM数据库的 DAO层，就可以对数据库 进行 增删改查了
    public abstract GardenPlantingDao getGardenPlantingDao();

}
