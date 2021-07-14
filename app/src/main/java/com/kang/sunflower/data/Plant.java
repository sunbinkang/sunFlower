package com.kang.sunflower.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;
import java.util.Objects;

/**
 * 这个是 植物 的表实体
 * 数据表 plants == Plant实体类
 * （plants表：存储了所有植物的信息）
 *
 * 同学们注意：
 *  这里数据的获取并不是来源于网络，而是来自于事先已经创建好了的assets目录下的json数组（plants.json），
 *  并在数据库创建时，通过WorkManger发送这个请求，把所有Plant的信息insert到plants表中
 *
 * Created by Shawn Wang on 3/26/19.
 */
@Entity(tableName = "plants") // ROOM
public final class Plant {
    private static final int DEFAULT_WATERING_INTERVAL = 7;

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private final String plantId;

    @NonNull
    private final String name;

    @NonNull
    private final String description; // 描述详情，很长很长的文字信息

    private final int growZoneNumber;

    // 植物应该多久浇水一次，以天为单位
    private final int wateringInterval; // how often the plant should be watered, in days

    @NonNull
    private final String imageUrl;

    public Plant(@NonNull String plantId, @NonNull String name, @NonNull String description,
                 int growZoneNumber, int wateringInterval, @NonNull String imageUrl) {
        this.plantId = plantId;
        this.name = name;
        this.description = description;
        this.growZoneNumber = growZoneNumber;
        this.wateringInterval = wateringInterval > 0 ? wateringInterval : DEFAULT_WATERING_INTERVAL;
        this.imageUrl = imageUrl;
    }

    /**
     * Determines if the plant should be watered.  Returns true if [since]'s date > date of last
     * watering + watering Interval; false otherwise.
     */
    public boolean shouldBeWatered(Calendar since, Calendar lastWateringDate) {
        lastWateringDate.add(Calendar.DAY_OF_YEAR, wateringInterval);
        return since.compareTo(lastWateringDate) > 0;
    }

    @NonNull
    public String getPlantId() {
        return plantId;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public int getGrowZoneNumber() {
        return growZoneNumber;
    }

    public int getWateringInterval() {
        return wateringInterval;
    }

    @NonNull
    public String getImageUrl() {
        return imageUrl;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }

    /**
     * As [Plant.kt] is declared as [Data class], {@link Object#equals(Object)} implicit implemented.
     * So we explicit implemented {@link Object#equals(Object)} in [Plant.java]
     * see: https://kotlinlang.org/docs/reference/data-classes.html
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        return obj instanceof Plant
                && this.plantId.equals(((Plant) obj).plantId);
    }

    /**
     * As [Plant.kt] is declared as [Data class], {@link Object#hashCode()} implicit implemented.
     * So we explicit implemented {@link Object#hashCode()} in [Plant.java]
     * see: https://kotlinlang.org/docs/reference/data-classes.html
     */
    @Override
    public int hashCode() {
        return Objects.hash(plantId);
    }

    /**
     * As [Plant.kt] is declared as [Data class], {copy()} implicit implemented.
     * So we explicit implemented {@link Object#clone()} in [Plant.java]
     * see: https://kotlinlang.org/docs/reference/data-classes.html
     */
    @Override
    protected Object clone() {
        return new Plant(plantId, name, description, growZoneNumber, wateringInterval, imageUrl);
    }
}
