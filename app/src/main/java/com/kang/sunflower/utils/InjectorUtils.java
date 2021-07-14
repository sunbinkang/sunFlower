package com.kang.sunflower.utils;

import android.content.Context;

import com.kang.sunflower.data.AppDatabase;
import com.kang.sunflower.data.GardenPlantingDao;
import com.kang.sunflower.data.GardenPlantingRepository;
import com.kang.sunflower.viewmodels.GardenPlantingListViewModelFactory;


/**
 * TODO 数据获取源
 *
 * Static methods used to inject classes needed for various Activities and Fragments.
 * 用于注入各种活动和片段所需的类的静态方法
 */
public class InjectorUtils {

    // 获取 我的花园 仓库
    private static GardenPlantingRepository getGardenPlantingRepository(Context context) {
        // 获取 我的花园 增删改查操作-DAO
        GardenPlantingDao dao = AppDatabase.getInstance(context.getApplicationContext()).getGardenPlantingDao();

        // 把 我的花园dao加入进去 并 创建 我的花园 仓库层
        return GardenPlantingRepository.getInstance(dao);
    }


    // 暴漏 我的花园 列表 ViewModel 工厂
    public static GardenPlantingListViewModelFactory provideGardenPlantingListViewModelFactory(Context context) {

        // 获取 我的花园 仓库
        GardenPlantingRepository gardenPlantingRepository = getGardenPlantingRepository(context);

        // 把 我的花园 仓库 传入 并 创建 --> 我的花园 List 列表的 ViewModel】 的工厂
        return new GardenPlantingListViewModelFactory(gardenPlantingRepository);
    }


}
