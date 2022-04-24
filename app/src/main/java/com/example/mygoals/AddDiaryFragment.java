package com.example.mygoals;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.room.Room;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class AddDiaryFragment extends Fragment {

ImageView addDiary, background, addImage;
EditText editText;
String diaryDate, diaryText;
int colour, id;
Bundle bundle;
private static final int CAMERA_CODE = 1000;
private static final int LIBRARY_CODE = 1001;
private String[] cameraPermissions;
private String[] libraryPermissions;
LinearLayout linearLayout;
NavController navController;

List<Integer>backgroundColour = new ArrayList<>();
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
        background = view.findViewById(R.id.background);
        linearLayout = view.findViewById(R.id.linear);
        addImage = view.findViewById(R.id.addImage);


        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                image();
            }
        });


         //add background colour

        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Random random = new Random();

              colour =  Color.argb(255, random.nextInt(265),random.nextInt(265),random.nextInt(265));

              editText.setBackgroundColor(colour);

                }
        });


        //add image to edit text




        DearDiaryDatabase dearDiaryDatabase = Room.databaseBuilder(getContext(),
                DearDiaryDatabase.class,"DearDiary").allowMainThreadQueries().build();


        diaryDate  = "" + System.currentTimeMillis();
        addDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!editText.equals("")) {
                    dearDiaryDatabase.dearDiaryDAO().insertDearDiary
                            (new DearDiaryArray(editText.getText().toString(), diaryDate));

                    Bundle bundle = new Bundle();
                    bundle.putInt("colour", colour);
                    navController.navigate(R.id.action_addDiaryFragment_to_dearDiaryFragment, bundle);

                } else {
                    Toast.makeText(getContext(), "Enter note", Toast.LENGTH_SHORT).show();

                }
            }
        });
    return view;
    }

    private void image() {
        String [] Items = {"Camera","Library"};
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());

    View view  = LayoutInflater.from(getContext()).inflate(R.layout.add_diary_image, null, false);

    alertDialog.setView(view).setTitle("Select Option").setItems(Items, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {

            if(Items[i].equals("Camera")){
                if((ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)) {
                    ActivityCompat.requestPermissions(getActivity(), cameraPermissions, CAMERA_CODE );
                }     else {
                    openCamera();
                }
        } else if (Items[i].equals("Library")) {
                if((ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)){
                 ActivityCompat.requestPermissions(getActivity(), libraryPermissions, LIBRARY_CODE );
                } else {
                    
                    openLibrary();
                }
            }
        
        }
    });
    
alertDialog.show();
    }

    private void openLibrary() {
    }

    private void openCamera() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
   if(requestCode == 1 ) {
       
       if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
           Toast.makeText(getContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
          Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
          startActivityForResult(intent, 1);
       }
   }
   
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


    if(requestCode == 1 && requestCode == Activity.RESULT_OK){
        Bitmap photo = (Bitmap) data.getExtras().get("data");

    }
    }
}