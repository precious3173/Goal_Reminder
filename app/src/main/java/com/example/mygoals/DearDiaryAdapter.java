package com.example.mygoals;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DearDiaryAdapter extends RecyclerView.Adapter<DearDiaryAdapter.DearDiary> {
    public List<DearDiaryArray>diaryArrays;
    EditDiaryFragment editDiaryFragment;
    Context context;
    int id;
    String diaryText;
    private onItemClick onItemClick;


    public DearDiaryAdapter(List<DearDiaryArray> diaryArrays, Context context, onItemClick onItemClick) {
        this.diaryArrays = diaryArrays;
        this.context = context;
        this.onItemClick = onItemClick;

    }

  public interface onItemClick {
        void onItem (DearDiaryArray dearDiaryArray);

  }

    @NonNull
    @Override
    public DearDiary onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dear_diary_recyclerview, parent, false);

        return new DearDiary(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DearDiaryAdapter.DearDiary holder, int position) {
     DearDiaryArray diaryArray = diaryArrays.get(position);

     diaryText = diaryArray.getDiaryText();
     String diaryDate = diaryArray.getDiaryDate();
      id = diaryArray.getId();


     Calendar cal = Calendar.getInstance(Locale.ENGLISH);

               cal.setTimeInMillis(Long.parseLong(diaryDate));
               String dateTime = DateFormat.format("dd-MM-yyyy (HH:mm aa)", cal).toString();
               holder.diaryDate.setText(dateTime);



        holder.diaryTextt.setText(diaryText);


     holder.itemView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

             onItemClick.onItem(diaryArray);

         }
     });

    }

    @Override
    public int getItemCount() {
        return diaryArrays.size();
    }

    public class DearDiary extends RecyclerView.ViewHolder {
        TextView diaryTextt;
        TextView diaryDate;

        public DearDiary(@NonNull View itemView) {
            super(itemView);
         diaryDate = itemView.findViewById(R.id.diaryDate);
         diaryTextt = itemView.findViewById(R.id.diaryText);

        }


    }
}
