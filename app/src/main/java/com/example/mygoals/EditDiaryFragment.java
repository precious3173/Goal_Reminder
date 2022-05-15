package com.example.mygoals;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.room.Room;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mygoals.database.DearDiaryDatabase;
import com.example.mygoals.model.DearDiaryArray;
import com.example.mygoals.model.DiaryConverter;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.List;


public class EditDiaryFragment extends Fragment {


    EditText editDiary;
    String diaryText, diaryDate, diaryTextUpdate;
    int id, colour, pix;
    NavController navController;
    Bundle bundle;
    byte [] image;
    Uri uri;
    ImageView update, picture;
    DearDiaryDatabase dearDiaryDatabase;
    List<DearDiaryArray> dearDiaryArrays;

    public EditDiaryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        navController = NavHostFragment.findNavController(this);
        bundle = this.getArguments();
        if(bundle !=null){
        diaryText = bundle.getString("diaryText");
        colour = bundle.getInt("colour");
        id = bundle.getInt("id");
        image = bundle.getByteArray("image");


        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_diary, container, false);


        update = view.findViewById(R.id.update);
        editDiary = view.findViewById(R.id.editDiary);
        picture = view.findViewById(R.id.Image);


        picture.setImageBitmap(DiaryConverter.byteArrayToBitmap(image));
        diaryTextUpdate = editDiary.getText().toString();
        editDiary.setBackgroundColor(colour);
        diaryDate  = "" + System.currentTimeMillis();


        dearDiaryDatabase = Room.databaseBuilder(getContext(),
                DearDiaryDatabase.class,"DearDiary").allowMainThreadQueries().build();

        editDiary.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {


                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dearDiaryDatabase.dearDiaryDAO().updateDearDiary(id, editDiary.getText().toString());
                        Toast.makeText(getContext(), "Updated Diary", Toast.LENGTH_SHORT).show();
                        navController.navigate(R.id.action_editDiaryFragment_to_dearDiaryFragment);

                    }
                });
            }
        });

      if(diaryTextUpdate.matches("")){
        editDiary.setText(diaryText);

      }






        return view;
    }
}
