package com.kang.sunflower.data;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import com.kang.sunflower.utils.Constants;
import com.kang.sunflower.works.SeedDatabaseWorker;

/**
 * Created by BinKang on 2021/7/13.
 * Des :
 */
@Database(entities = {GardenPlanting.class, Plant.class}, version = 1,exportSchema = false)
// 解决此错误：Cannot figure out how to save this field into database. You can consider adding a type
// 必须导入自己的 Converters
@TypeConverters(Converters.class)
public abstract class AppDatabase extends RoomDatabase {

    private static final String TAG = AppDatabase.class.getSimpleName();

    // 植物的 Dao
    public abstract PlantDao getPlantDao();

    // 获取ROOM数据库的 DAO层，就可以对数据库 进行 增删改查了
    public abstract GardenPlantingDao getGardenPlantingDao();

    private static volatile AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                Log.i(TAG, "getInstance: " + "1111111");
//                instance = Room.databaseBuilder(context, AppDatabase.class, Constants.DATABASE_NAME).build();
                instance = buildDatabase(context); // 一次
            }
        }
        return instance;
    }

    private static AppDatabase buildDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, Constants.DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);

                        // 此任务没有任何约束条件， 马上执行

                        // 指定一个任务，让WorkManager，（assets/plants.json 所有的数据 插入到 ROOM plant数据表中去）
                        WorkManager.getInstance(context).enqueue(OneTimeWorkRequest.from(SeedDatabaseWorker.class));
                    }
                })

                // 到这里之后：就已经去插入数据到数据库了

                .build();
    }

}
