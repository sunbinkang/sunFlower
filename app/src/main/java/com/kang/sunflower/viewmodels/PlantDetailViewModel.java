package com.kang.sunflower.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.kang.sunflower.data.GardenPlanting;
import com.kang.sunflower.data.GardenPlantingRepository;
import com.kang.sunflower.data.Plant;
import com.kang.sunflower.data.PlantRepository;
import com.kang.sunflower.utils.AppExecutors;

/**
 * Created by BinKang on 2021/7/15.
 * Des :
 */
public class PlantDetailViewModel extends ViewModel {

    // 以后 ViewModel 的 MutableLiveData 尽量使用这个，可追逐性 清晰  （代码少，好找bug）

    private String plantId; // 靠此ID去查询 plant植物表里面的数据 展示

    public LiveData<Plant> plant; // TODO　这个就是暴漏 给Fragment显示用的 数据    [真正的数据]

    // 在植物介绍详情页  用户点击 + 号的时候， 加入到 我的花园GardenPlantings Room数据库里面去（就这个用途）
    private GardenPlantingRepository gardenPlantingRepository;

    // + 号 的 显示 和 隐藏
    private LiveData<Boolean> isPlanted; // 判断是不是有植物信息，如果有 就要显示 （+号按钮）     否则      就不显示（+号按钮）

    PlantDetailViewModel(PlantRepository plantRepository, GardenPlantingRepository gardenPlantingRepository, String plantId) {
        super();
        this.gardenPlantingRepository = gardenPlantingRepository;
        this.plantId = plantId;

        // 我的花园 寻找     植物目录 寻找
        /* The getGardenPlantingForPlant method returns a LiveData from querying the database. The
         * method can return null in two cases: when the database query is running and if no records
         * are found. In these cases isPlanted is false. If a record is found then isPlanted is
         * true. */
        /**
         * getGardenPlantingForPlant方法通过查询数据库返回LiveData。
         *
         * 该方法在两种情况下可以返回null：数据库查询运行时和没有记录时
         *
         * 找到了。在这些情况下，isPlanted是错误的。如果找到记录，则isPlanted为真。
         */
        LiveData<GardenPlanting> gardenPlantingForPlant = gardenPlantingRepository.getGardenPlantingForPlant(plantId);
        this.isPlanted = Transformations.map(gardenPlantingForPlant, it -> it != null); // // + 号 的 显示 和 隐藏
        this.plant = plantRepository.getPlant(plantId);
    }

    /**
     * 在植物介绍详情页  用户点击 + 号的时候， 加入到 我的花园GardenPlantings Room数据库里面去
     */
    public void addPlantToGarden() {
        // 异步线程只想
        // AppExecutors.getInstance().diskIO().execute(() -> gardenPlantingRepository.createGardenPlanting(plantId));

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                gardenPlantingRepository.createGardenPlanting(plantId);
            }
        });
    }

    /**
     * 判断是不是有植物信息，如果有 就要显示 （+号按钮）     否则      就不显示（+号按钮）
     * @return
     */
    public LiveData<Boolean> getIsPlanted() {
        return isPlanted;
    }

}
