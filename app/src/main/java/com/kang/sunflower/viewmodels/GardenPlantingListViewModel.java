package com.kang.sunflower.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;


import com.kang.sunflower.data.GardenPlanting;
import com.kang.sunflower.data.GardenPlantingRepository;
import com.kang.sunflower.data.PlantAndGardenPlantings;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的花园 List 列表的 ViewModel
 */
public class GardenPlantingListViewModel extends ViewModel {

    // 我的花园 的 是否 展示数据 不展示数据 的标记改变
    public LiveData<List<GardenPlanting>> gardenPlantings;

    // 我的花园+植物 的 RecycleView 的 列表数据
    public LiveData<List<PlantAndGardenPlantings>> plantAndGardenPlantings;

    /**
     * ViewModel只负责从仓库里面去获取数据
     * @param repository
     */
    public GardenPlantingListViewModel(@NonNull GardenPlantingRepository repository) { // VM 就有 仓库了
        // Room的dao-->查询 我的花园 数据
        this.gardenPlantings = repository.getGardenPlantings();

        // Room的dao--->查询 我的花园+植物 两者合并的数据
        this.plantAndGardenPlantings = Transformations.map(repository.getPlantAndGardenPlantings(), items -> {

            // 优化用的
            // 如果数据是空的，就移除掉空的数据
            List<PlantAndGardenPlantings> removeItems = new ArrayList<>();
            for (PlantAndGardenPlantings item:items) {
                if (item.getGardenPlantings().isEmpty()) {
                    removeItems.add(item);
                }
            }
            items.removeAll(removeItems);
            return items;
        });

        // 向服务器拿数据 （耗时操作）
        /*new Thread(){
            @Override
            public void run() {
                super.run();

                LiveData<List<GardenPlanting>> result = repository.getGardenPlantings();

                gardenPlantings.postValue(result);
            }
        }.start();*/

    }
}
