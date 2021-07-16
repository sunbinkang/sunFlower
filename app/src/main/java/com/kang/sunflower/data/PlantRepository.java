package com.kang.sunflower.data;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Created by BinKang on 2021/7/14.
 * Des :
 */
public class PlantRepository {

    // 单例模式用的
    private static PlantRepository instance;

    // 仓库才能对 dao进行增删改查
    private PlantDao plantDao;

    // 构造函数 必须接收一个dao， 仓库才能对 dao进行增删改查
    private PlantRepository(PlantDao gardenPlantingDao) {
        this.plantDao = gardenPlantingDao;
    }

    // 单例模式
    public static PlantRepository getInstance(PlantDao gardenPlantingDao) {
        if (instance == null) {
            synchronized (PlantRepository.class) {
                if (instance == null) {
                    instance = new PlantRepository(gardenPlantingDao);
                }
            }
        }
        return instance;
    }

    // 查询 plantDao数据库 的所有 植物目录数据
    public LiveData<List<Plant>> getPlants() {
        return this.plantDao.getPlants();
    }

    // 辅助 随机
    // 根据条件plantId查询 plantDao数据库 的所有 植物目录数据 （单个）
    public LiveData<List<Plant>> getPlantsWithGrowZoneNumber(int growZoneNumber) {
        return this.plantDao.getPlantsWithGrowZoneNumber(growZoneNumber);
    }

    // 根据条件plantId查询 plantDao数据库 的所有 植物目录数据 （单个）
    public LiveData<Plant> getPlant(String plantId) {
        return this.plantDao.getPlant(plantId);
    }
}
