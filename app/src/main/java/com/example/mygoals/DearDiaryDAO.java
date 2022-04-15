package com.example.mygoals;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface DearDiaryDAO {


    @Query("SELECT * FROM dearyDiary")
    List<DearDiaryArray> getAll();

    @Insert
    void insertDearDiary (DearDiaryArray dearDiaryArray);

    @Update
    void  updateDearDiary (DearDiaryArray dearDiaryArray);

    @Delete
    void deleteDearDiary (DearDiaryArray dearDiaryArray);


}
