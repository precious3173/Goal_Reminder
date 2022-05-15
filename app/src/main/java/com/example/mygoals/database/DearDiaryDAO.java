package com.example.mygoals.database;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mygoals.model.DearDiaryArray;

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
