package com.example.mygoals;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public  class DearDiaryFragment extends Fragment implements DearDiaryAdapter.onItemClick, DearDiaryAdapter.onItemDelete {

    Toolbar toolbar;
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    DearDiaryAdapter dearDiaryAdapter;
    NavController navController;
    DearDiaryDatabase dearDiaryDatabase;
    List<DearDiaryArray> dearDiaryArrays;
    Bundle b;
    int colour;

    public DearDiaryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dearDiaryDatabase = Room.databaseBuilder(getContext(),
                DearDiaryDatabase.class, "DearDiary").allowMainThreadQueries().build();
        navController = NavHostFragment.findNavController(this);
        dearDiaryArrays = dearDiaryDatabase.dearDiaryDAO().getAll();

        b = this.getArguments();

        if(b != null) {
            colour = b.getInt("colour");
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dear_diary, container, false);


        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("Dear Diary");

        floatingActionButton = view.findViewById(R.id.floatAction);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_dearDiaryFragment_to_addDiaryFragment);
            }
        });


        recyclerView = view.findViewById(R.id.recyclerview);
        dearDiaryAdapter = new DearDiaryAdapter(dearDiaryArrays, getContext(), this, this);
        recyclerView.setAdapter(dearDiaryAdapter);
        recyclerView.setHasFixedSize(true);
        return view;
    }

    @Override
    public void onItem(DearDiaryArray dearDiaryArray) {


        EditDiaryFragment editDiaryFragment = new EditDiaryFragment();
        Bundle bundle = new Bundle();
        bundle.putString("diaryText", dearDiaryArray.getDiaryText());
        bundle.putInt("id", dearDiaryArray.getId());
        bundle.putInt("colour", colour);
        editDiaryFragment.setArguments(bundle);
        navController.navigate(R.id.action_dearDiaryFragment_to_editDiaryFragment, bundle);
    }


    @Override
    public void onDelete(DearDiaryArray dearDiaryArray) {
        Toast.makeText( getContext(),"Diary has been deleted ", Toast.LENGTH_SHORT).show();
    }
}