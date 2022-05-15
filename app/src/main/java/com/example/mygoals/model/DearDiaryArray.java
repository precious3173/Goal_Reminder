package com.example.mygoals.model;

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

    @NonNull
    @ColumnInfo (name = "diary_image",typeAffinity = ColumnInfo.BLOB)
    private byte [] image;

    public DearDiaryArray( @NonNull String diaryText, @NonNull String diaryDate, byte [] image) {
        this.diaryText = diaryText;
        this.diaryDate = diaryDate;
        this.image = image;
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

    @NonNull
    public byte[] getImage() {
        return image;
    }

    public void setImage(@NonNull byte[] image) {
        this.image = image;
    }
}
