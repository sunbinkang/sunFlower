package com.kang.sunflower.viewmodels;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.core.util.Preconditions;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.ViewModel;

import com.kang.sunflower.data.GardenPlanting;
import com.kang.sunflower.data.Plant;
import com.kang.sunflower.data.PlantAndGardenPlantings;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by BinKang on 2021/7/13.
 * Des :
 */
public class PlantAndGardenPlantingsViewModel extends ViewModel {

    // ObservableField 感应数据变化   ---->  LiveData 感应数据变化
    // private LiveData

    public ObservableField<String> waterDateString; // 浇水时间 浇水日 信息数据成员
    public ObservableInt wateringInterval;
    public ObservableField<String> imageUrl; // 图片的URL
    public ObservableField<String> plantName; // 花的名称
    public ObservableField<String> plantDateString; // 应该是 种植于 什么 什么 时间 的信息

    public PlantAndGardenPlantingsViewModel(@NonNull PlantAndGardenPlantings plantings) {
        @SuppressLint("RestrictedApi") final Plant plant = Preconditions.checkNotNull(plantings.getPlant());
        final GardenPlanting gardenPlanting = plantings.getGardenPlantings().get(0);
        final DateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy", Locale.US);

        this.waterDateString = new ObservableField<>(dateFormat.format(gardenPlanting.getLastWateringDate().getTime()));
        this.wateringInterval = new ObservableInt(plant.getWateringInterval());
        this.imageUrl = new ObservableField<>(plant.getImageUrl()); // 给它值
        this.plantName = new ObservableField<>(plant.getName());
        this.plantDateString = new ObservableField<>(dateFormat.format(gardenPlanting.getPlantDate().getTime()));
    }

}
