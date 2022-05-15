package com.example.mygoals.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mygoals.R;
import com.example.mygoals.model.DearDiaryArray;

import java.nio.ByteBuffer;
import java.util.List;

public class AddGoalAdapter extends RecyclerView.Adapter<AddGoalAdapter.ViewHolder> {
List<DearDiaryArray> dearDiaryArrayList;
Context context;

 public AddGoalAdapter (Context context, List<DearDiaryArray>dearDiaryArrayList){
     this.context = context;
     this.dearDiaryArrayList = dearDiaryArrayList;

 }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_diary_layout_inflator, parent, false);
         return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DearDiaryArray diaryArray = dearDiaryArrayList.get(position);

        int id = diaryArray.getId();
        String diaryText = diaryArray.getDiaryText();
        byte[] image = diaryArray.getImage();
        int picture = ByteBuffer.wrap(image).getInt();


        holder.diaryText.setText(diaryText);
        holder.image.setImageResource(picture);
    }

    @Override
    public int getItemCount() {
        return dearDiaryArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        EditText diaryText;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            diaryText = itemView.findViewById(R.id.text);
            image = itemView.findViewById(R.id.Image);

        }
    }
}
