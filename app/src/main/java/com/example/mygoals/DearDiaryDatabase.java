package com.example.mygoals;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {DearDiaryArray.class}, version = 1)
public abstract class DearDiaryDatabase extends RoomDatabase {

    public abstract DearDiaryDAO dearDiaryDAO();
}
