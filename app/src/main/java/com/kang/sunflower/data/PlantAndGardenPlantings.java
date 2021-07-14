package com.kang.sunflower.data;



import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的花园 与 植物 合并的实体Bean对象
 */
public class PlantAndGardenPlantings {

    @Embedded
    private Plant plant; // 我的植物Bean

    @Relation(parentColumn = "id", entityColumn = "plant_id")
    private List<GardenPlanting> gardenPlantings; // 我的花园Bean

    public PlantAndGardenPlantings() {
        this.gardenPlantings = new ArrayList<>();
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public List<GardenPlanting> getGardenPlantings() {
        return gardenPlantings;
    }

    public void setGardenPlantings(List<GardenPlanting> gardenPlantings) {
        this.gardenPlantings = gardenPlantings;
    }
}
