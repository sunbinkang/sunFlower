package com.kang.sunflower.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

/**
 * 这个是 我的花园 增删改查操作-DAO  ---> 增删改查     garden_plantings==GardenPlanting（我的花园）
 *
 * The Data Access Object for the [GardenPlanting] class.
 */
@Dao
public interface GardenPlantingDao {

    // 查询 我的花园数据
    @Query("SELECT * FROM garden_plantings")
    LiveData<List<GardenPlanting>> getGardenPlantings();

    // gardenPlantingId条件查询 我的花园数据
    @Query("SELECT * FROM garden_plantings WHERE id = :gardenPlantingId")
    LiveData<GardenPlanting> getGardenPlanting(long gardenPlantingId);

    // plantId条件查询 我的花园数据
    @Query("SELECT * FROM garden_plantings WHERE plant_id = :plantId")
    LiveData<GardenPlanting> getGardenPlantingForPlant(@NonNull String plantId);

    /**
     * This query will tell Room to query both the [Plant] and [GardenPlanting] tables and handle the object mapping.
     * 这个查询将告诉Room查询[Plant]和[gardenplating]表并处理对象映射。
     */
    @Transaction
    @Query("SELECT * FROM plants")
    LiveData<List<PlantAndGardenPlantings>> getPlantAndGardenPlantings();

    // 插入一条 我的花园数据 进去到Room数据库
    @Insert
    long insertGardenPlanting(@NonNull GardenPlanting gardenPlanting);

    // 从Room数据库 删除一条 我的花园数据
    @Delete
    void deleteGardenPlanting(@NonNull GardenPlanting gardenPlanting);
}
