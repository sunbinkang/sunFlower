package com.kang.sunflower.data;

import androidx.room.TypeConverter;

import java.util.Calendar;

/**
 * AppDatabase 唯一使用了当前此类 @TypeConverters(Converters.class)
 *
 * 类型转换器，允许房间引用复杂的数据类型
 *
 * Type converters to allow Room to reference complex data types.
 *
 * 此转换器，为了解决：
 * Cannot figure out how to save this field into database. You can consider adding a type
 *
 *
 *  例如：
 *   class GardenPlanting{
 *       @ColumnInfo(name = "plant_date") private final Calendar plantDate;   编译时报错
 *       @ColumnInfo(name = "last_watering_date") private final Calendar lastWateringDate; 编译时报错
 *   }
 *
 */
public class Converters {

    @TypeConverter
    public long calendarToDatestamp(Calendar calendar) {
        return calendar.getTimeInMillis();
    }

    @TypeConverter
    public Calendar datestampToCalendar(long value) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(value);
        return calendar;
    }
}
