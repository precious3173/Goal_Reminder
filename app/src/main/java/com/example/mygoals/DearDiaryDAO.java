package com.example.mygoals;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface DearDiaryDAO {


    @Insert (onConflict = REPLACE)
    void insertDearDiary (DearDiaryArray dearDiaryArray);

    @Query("SELECT * FROM dearyDiary")
    List<DearDiaryArray> getAll();

    @Query("UPDATE dearyDiary SET diary_text = :diary_text WHERE id = :id")
    void  updateDearDiary (int id, String diary_text);


    @Delete
    void deleteDearDiary (DearDiaryArray dearDiaryArray);


}
