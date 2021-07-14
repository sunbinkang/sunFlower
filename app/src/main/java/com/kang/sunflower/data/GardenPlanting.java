package com.kang.sunflower.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Calendar;
import java.util.Objects;

/**
 * 这个是 我的花园 的表实体
 * 数据表 garden_plants == GardenPlanting实体类
 *
 * [GardenPlanting] represents when a user adds a [Plant] to their garden, with useful metadata.
 * Properties such as [lastWateringDate] are used for notifications (such as when to water the
 * plant).
 *
 * Declaring the column info allows for the renaming of variables without implementing a
 * database migration, as the column name would not change.
 */
@Entity(tableName = "garden_plantings",
        foreignKeys = {@ForeignKey(entity = Plant.class, parentColumns = {"id"}, childColumns = {"plant_id"})},
        indices = {@Index("plant_id")})
public final class GardenPlanting {
    @ColumnInfo(name = "plant_id") private final String plantId;

    /**
     * Indicates when the [Plant] was planted. Used for showing notification when it's time
     * to harvest the plant.
     */
    @ColumnInfo(name = "plant_date") private final Calendar plantDate;

    /**
     * Indicates when the [Plant] was last watered. Used for showing notification when it's
     * time to water the plant.
     */
    @ColumnInfo(name = "last_watering_date") private final Calendar lastWateringDate;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long gardenPlantingId = 0L;

    public GardenPlanting(String plantId, Calendar plantDate, Calendar lastWateringDate) {
        this.plantId = plantId;
        this.plantDate = plantDate == null ? Calendar.getInstance() : plantDate;
        this.lastWateringDate = lastWateringDate == null ? Calendar.getInstance() : lastWateringDate;
    }

    public String getPlantId() {
        return plantId;
    }

    public Calendar getPlantDate() {
        return plantDate;
    }

    public Calendar getLastWateringDate() {
        return lastWateringDate;
    }

    public long getGardenPlantingId() {
        return gardenPlantingId;
    }

    public void setGardenPlantingId(long gardenPlantingId) {
        this.gardenPlantingId = gardenPlantingId;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("GardenPlanting(plantId=%s, plantDate=%s, lastWateringDate=%s)",
                plantId, plantDate.toString(), lastWateringDate.toString());
    }

    /**
     * As [Plant.kt] is declared as [Data class], {@link Object#equals(Object)} implicit implemented.
     * So we explicit implemented {@link Object#equals(Object)} in [Plant.java]
     * see: https://kotlinlang.org/docs/reference/data-classes.html
     */
    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        GardenPlanting target = (GardenPlanting) obj;

        return this.plantId.equals(target.plantId)
                && this.plantDate.equals(this.plantDate)
                && this.lastWateringDate.equals(this.lastWateringDate);
    }

    /**
     * As [Plant.kt] is declared as [Data class], {@link Object#hashCode()} implicit implemented.
     * So we explicit implemented {@link Object#hashCode()} in [Plant.java]
     * see: https://kotlinlang.org/docs/reference/data-classes.html
     */
    @Override
    public int hashCode() {
        return Objects.hash(gardenPlantingId);
    }

    /**
     * As [Plant.kt] is declared as [Data class], {copy()} implicit implemented.
     * So we explicit implemented {@link Object#clone()} in [Plant.java]
     * see: https://kotlinlang.org/docs/reference/data-classes.html
     */
    @Override
    protected Object clone() {
        return new GardenPlanting(plantId, plantDate, lastWateringDate);
    }
}
