package com.example.mygoals.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.TypeConverter;

import java.io.ByteArrayOutputStream;

public class DiaryConverter {

    @TypeConverter
    public static byte[] ImageToByteArray (Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,0, byteArrayOutputStream);

        byte [] pixs = byteArrayOutputStream.toByteArray();
        return pixs;

    }

    @TypeConverter
    public static Bitmap byteArrayToBitmap (byte [] byteArray){

        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);


    }
}
