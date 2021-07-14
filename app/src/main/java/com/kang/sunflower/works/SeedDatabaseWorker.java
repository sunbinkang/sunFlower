package com.kang.sunflower.works;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.kang.sunflower.data.AppDatabase;
import com.kang.sunflower.data.Plant;
import com.kang.sunflower.utils.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by BinKang on 2021/7/14.
 * Des :
 */
public class SeedDatabaseWorker extends Worker {

    private static final String TAG = SeedDatabaseWorker.class.getSimpleName();

    public SeedDatabaseWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            InputStream input = getApplicationContext().getAssets().open(Constants.PLANT_DATA_FILENAME);
            JsonReader reader = new JsonReader(new InputStreamReader(input));
            Type plantType = new TypeToken<List<Plant>>() {
            }.getType();
            List<Plant> plantList = new Gson().fromJson(reader, plantType);
            input.close();

            AppDatabase database = AppDatabase.getInstance(getApplicationContext());

            // 把 assets/plants.json 所有的数据 插入到 ROOM plant数据表中去
            database.getPlantDao().insertAll(plantList);

            return Result.success();  // 本次任务成功

        } catch (IOException e) {
            Log.e(TAG, "Error seeding database  " +
                    "把 assets/plants.json 所有的数据 插入到 ROOM plant数据表中去失败了，错误详情是", e);
            return Result.failure(); // 本次任务失败
        }
    }
}
