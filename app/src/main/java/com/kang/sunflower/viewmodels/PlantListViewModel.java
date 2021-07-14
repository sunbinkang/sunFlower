package com.kang.sunflower.viewmodels;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.kang.sunflower.data.Plant;
import com.kang.sunflower.data.PlantRepository;

import java.util.List;

/**
 * Created by BinKang on 2021/7/14.
 * Des :
 */
public class PlantListViewModel extends ViewModel {

    private static final int NO_GROW_ZONE = -1;

    // ViewModel只负责操作仓库，向仓库拿数据
    private PlantRepository plantRepository;

    // 此LiveData是为了 （点击右上角按钮时 随机刷新数据的效果用的）
    private MutableLiveData<Integer> growZoneNumber;

    // 此LiveData 才是真正的 植物目录 的数据
    public LiveData<List<Plant>> plants;

    PlantListViewModel(@NonNull PlantRepository plantRepository) {
        super();
        this.plantRepository = plantRepository;
        this.growZoneNumber = new MutableLiveData<>(-1);
//        this.plants = Transformations.switchMap(growZoneNumber, it -> {
//            if (it == NO_GROW_ZONE) {  // 辅助 随机
//                return this.plantRepository.getPlants(); // 仓库.getxxx
//            } else {
//                return this.plantRepository.getPlantsWithGrowZoneNumber(it); // 辅助 随机
//            }
//        });
        this.plants = Transformations.switchMap(growZoneNumber, new Function<Integer, LiveData<List<Plant>>>() {
            @Override
            public LiveData<List<Plant>> apply(Integer input) {
                if (input == NO_GROW_ZONE) {  // 辅助 随机
                    return plantRepository.getPlants(); // 仓库.getxxx
                } else {
                    return plantRepository.getPlantsWithGrowZoneNumber(input); // 辅助 随机
                }
            }
        });
    }

}
