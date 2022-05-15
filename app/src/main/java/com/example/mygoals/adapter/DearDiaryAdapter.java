package com.example.mygoals.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.mygoals.model.DearDiaryArray;
import com.example.mygoals.EditDiaryFragment;
import com.example.mygoals.R;
import com.example.mygoals.database.DearDiaryDatabase;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DearDiaryAdapter extends RecyclerView.Adapter<DearDiaryAdapter.DearDiary> {
    public List<DearDiaryArray>diaryArrays;
    EditDiaryFragment editDiaryFragment;
    Context context;
    int id;
    String diaryText;
    DearDiaryDatabase dearDiaryDatabase;
    private onItemClick onItemClick;
    private onItemDelete onItemDelete;

    public DearDiaryAdapter(List<DearDiaryArray> diaryArrays, Context context, onItemClick onItemClick, onItemDelete onItemDelete) {
        this.diaryArrays = diaryArrays;
        this.context = context;
        this.onItemClick = onItemClick;
       this.onItemDelete = onItemDelete;

        dearDiaryDatabase = Room.databaseBuilder(context,
                DearDiaryDatabase.class,"DearDiary").allowMainThreadQueries().build();


    }

  public interface onItemClick {
        void onItem (DearDiaryArray dearDiaryArray);
  }

   public interface onItemDelete{
        void onDelete (DearDiaryArray dearDiaryArray);
   }
    @NonNull
    @Override
    public DearDiary onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dear_diary_recyclerview, parent, false);

        return new DearDiary(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DearDiaryAdapter.DearDiary holder, @SuppressLint("RecyclerView") int position) {
     DearDiaryArray diaryArray = diaryArrays.get(position);

     diaryText = diaryArray.getDiaryText();
     String diaryDate = diaryArray.getDiaryDate();
      id = diaryArray.getId();
      byte [] image = diaryArray.getImage();


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

     holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
         @Override
         public boolean onLongClick(View view) {

             PopupMenu popupMenu = new PopupMenu(context, view);

             popupMenu.getMenuInflater().inflate(R.menu.delete_menu, popupMenu.getMenu());

             popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                 @Override
                 public boolean onMenuItemClick(MenuItem menuItem) {
                     switch (menuItem.getItemId()){

                         case R.id.delete:
                             deleteDialog(diaryArray);
                     }


                     return true;
                 }
             });

             popupMenu.show();


             return true;
         }
     });


    }

    private void deleteDialog(DearDiaryArray dearDiaryArray) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete");
        builder.setMessage("Are you sure you want to delete?");

        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                onItemDelete.onDelete(dearDiaryArray);
                diaryArrays.remove(dearDiaryArray);
                dearDiaryDatabase.dearDiaryDAO().deleteDearDiary(dearDiaryArray);
                notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
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
