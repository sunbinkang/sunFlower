package com.kang.sunflower.data;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * 这个是 我的花园 仓库层    服务 ----  Room(Dao)
 */
public class GardenPlantingRepository {

    // 此类 单例模式 成员
    private static GardenPlantingRepository instance;

    // 此类 我的花园的dao 用于对此数据的 增删改查
    private GardenPlantingDao gardenPlantingDao;

    // 构造函数 接收 DAO
    private GardenPlantingRepository(GardenPlantingDao gardenPlantingDao) {
        this.gardenPlantingDao = gardenPlantingDao;
    }

    // 单例模式
    public static GardenPlantingRepository getInstance(GardenPlantingDao gardenPlantingDao) {
        if (instance == null) {
            synchronized (GardenPlantingRepository.class) {
                if (instance == null) {
                    instance = new GardenPlantingRepository(gardenPlantingDao);
                }
            }
        }
        return instance;
    }

    /**
     * Room的dao-->查询 我的花园 数据
     * @return
     */
    public LiveData<List<GardenPlanting>> getGardenPlantings() {
        return gardenPlantingDao.getGardenPlantings(); // 获取数据库了
    }

    /**
     * Room的dao--->查询 我的花园+植物 两者合并的数据
     * @return
     */
    public LiveData<List<PlantAndGardenPlantings>> getPlantAndGardenPlantings() {
        return gardenPlantingDao.getPlantAndGardenPlantings(); // 获取数据库了


    }

}
