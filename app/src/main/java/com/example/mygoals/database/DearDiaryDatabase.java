package com.example.mygoals.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.mygoals.model.DearDiaryArray;

@Database(entities = {DearDiaryArray.class}, version = 1, exportSchema = false)

@TypeConverters
public abstract class DearDiaryDatabase extends RoomDatabase {

    public abstract DearDiaryDAO dearDiaryDAO();
}
