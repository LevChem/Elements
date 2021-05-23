package com.letovo.tableview;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavHost;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class Start extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private DatabaseReference mDataBase;

    private EditText editText;
    private String mParam1;
    private String mParam2;

    public Start() {
    }

    // TODO: Rename and change types and number of parameters
    public static Start newInstance(String param1, String param2) {
        Start fragment = new Start();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public void addNewElem(){
        Element el = new Element("1", "Hydrogen", "1.001", "2.01", "1s^1", "H");
        mDataBase.child("elems").child("1").setValue(el);
    }

    public void showElem(Button btn, String ids){
        DatabaseReference mRef = mDataBase.child("elems").child(ids);
        mRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<DataSnapshot> task) {
                String name = task.getResult().child("shortName").getValue(String.class);
                btn.setText(name);
            }
        });
    }

    public void setInfo(int id, View view){
        String idClass = "i" + Integer.toString(id);
        Button elem = view.findViewById(getResources().getIdentifier(idClass, "id", "com.letovo.tableview"));
        showElem(elem, Integer.toString(id));
        elem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ids = Integer.toString(id);
                show(view, ids);
            }
        });
    }

    public void show(View view, String ids){
        Bundle bundle = new Bundle();
        bundle.putString("ids", ids);
        NavHostFragment.findNavController(this).navigate(R.id.action_start2_to_description, bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start, container, false);
        mDataBase = FirebaseDatabase.getInstance().getReference();
        for (int i = 1; i < 119; ++i){
            setInfo(i, view);
        }
        return view;
    }
}