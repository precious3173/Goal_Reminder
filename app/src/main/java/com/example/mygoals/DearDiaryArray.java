package com.example.mygoals;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dearyDiary")
public class DearDiaryArray {

    @PrimaryKey(autoGenerate = true)
    int id;

    @NonNull
    @ColumnInfo (name = "diary_text")
    private String diaryText;



    @NonNull
    @ColumnInfo (name = "diary_date")
    private String diaryDate;

    public DearDiaryArray( @NonNull String diaryText, @NonNull String diaryDate) {
        this.diaryText = diaryText;
        this.diaryDate = diaryDate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getDiaryText() {
        return diaryText;
    }

    public void setDiaryText(@NonNull String diaryText) {
        this.diaryText = diaryText;
    }

    @NonNull
    public String getDiaryDate() {
        return diaryDate;
    }

    public void setDiaryDate(@NonNull String diaryDate) {
        this.diaryDate = diaryDate;
    }
}
