package com.example.mygoals;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.TypeConverter;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.mygoals.adapter.AddGoalAdapter;
import com.example.mygoals.database.DearDiaryDatabase;
import com.example.mygoals.model.DearDiaryArray;
import com.example.mygoals.model.DiaryConverter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class AddDiaryFragment extends Fragment {

ImageView addDiary, background, addImage, photo;
EditText editText;
String diaryDate, diaryText;
int colour, id;
Bundle bundle;
ViewGroup.LayoutParams params;
byte [] diaryImage;
private static final int CAMERA_CODE = 1000;
private static final int LIBRARY_CODE = 1001;
LinearLayout linearLayout;
NavController navController;
Intent a;
Uri uri;
Bitmap imageStore;
    ActivityResultLauncher<String> activityResultLauncher;
    ActivityResultLauncher<Intent>activityResult;
    ActivityResultLauncher<String> activtLauncher;
List<Integer>backgroundColour = new ArrayList<>();


    public AddDiaryFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        navController = NavHostFragment.findNavController(this);

       activityResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {

                if(result) {
                    Toast.makeText(getContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Permission not granted", Toast.LENGTH_SHORT).show();
                }
            }
        });


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
        photo = view.findViewById(R.id.Image);



        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                image();
            }
        });


        activityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK ){

                    a = result.getData();
                    uri = a.getData();
                   if (uri !=null) {

                       try {
                           imageStore = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
                           photo.setImageBitmap(imageStore);

                       } catch (IOException e) {
                           e.printStackTrace();
                       }

                   }


                }
            }
        });




         //add background colour

        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Random random = new Random();

              colour =  Color.argb(255, random.nextInt(265),random.nextInt(265),random.nextInt(265));

              linearLayout.setBackgroundColor(colour);

                }
        });



        DearDiaryDatabase dearDiaryDatabase = Room.databaseBuilder(getContext(),
                DearDiaryDatabase.class,"DearDiary").allowMainThreadQueries().build();


        diaryDate  = "" + System.currentTimeMillis();
        addDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!editText.equals("")) {
                    dearDiaryDatabase.dearDiaryDAO().insertDearDiary
                            (new DearDiaryArray(editText.getText().toString(), diaryDate, DiaryConverter.ImageToByteArray(imageStore)));

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
                if((getActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)) {
                    activityResultLauncher.launch(Manifest.permission.CAMERA);
                }     else {
                    openCamera();
                }
        } else if (Items[i].equals("Library")) {
                if((getActivity().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                    activityResultLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                } else {
                    openLibrary();
                }
            }
        
        }
    });
    
alertDialog.show();
    }

    private void openLibrary() {
         a = new Intent(Intent.ACTION_PICK);
        a.setType("image/*");
        activityResult.launch(a);

    }

    private void openCamera() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE, "Profile Image");

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        uri= intent.getData();
        intent.putExtra(MediaStore.ACTION_IMAGE_CAPTURE, uri);
        activityResult.launch(intent);


    }



}