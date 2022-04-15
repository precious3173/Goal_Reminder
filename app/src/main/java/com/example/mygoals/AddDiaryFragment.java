package com.example.mygoals;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;


public class AddDiaryFragment extends Fragment {

ImageView addDiary;
EditText editText;
String diaryDate;
int id;
NavController navController;

    public AddDiaryFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navController = NavHostFragment.findNavController(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_diary, container, false);

        addDiary = view.findViewById(R.id.addDiary);
        editText = view.findViewById(R.id.text);

        DearDiaryDatabase dearDiaryDatabase = Room.databaseBuilder(getContext(),
                DearDiaryDatabase.class,"DearDiary").allowMainThreadQueries().build();


        diaryDate  = "" + System.currentTimeMillis();
        addDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             dearDiaryDatabase.dearDiaryDAO().insertDearDiary
                     (new DearDiaryArray(editText.getText().toString(), diaryDate ));
                navController.navigate(R.id.action_addDiaryFragment_to_dearDiaryFragment);
            }
        });
    return view;
    }
}