package com.example.mygoals;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;


public class EditDiaryFragment extends Fragment {


    EditText editDiary;
    String diaryText;
    int id;
    Bundle bundle;

    public EditDiaryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bundle = this.getArguments();
        if(bundle !=null){
        diaryText = bundle.getString("diaryText");}
        else {

            Toast.makeText(getContext(),"Bundle empty", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_diary, container, false);



        editDiary = view.findViewById(R.id.editDiary);


        editDiary.setText(diaryText);
        editDiary.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return view;
    }
}
