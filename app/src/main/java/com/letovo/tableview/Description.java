package com.letovo.tableview;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Description extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private DatabaseReference mDataBase;

    private TextView id, name, mm, en, ec;

    public Description() {
    }

    public static Description newInstance(String param1, String param2) {
        Description fragment = new Description();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void show_data(){
        id.setText(getArguments().getString("ids"));
        DatabaseReference mRef = mDataBase.child("elems").child(id.getText().toString());
        mRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<DataSnapshot> task) {
                name.setText("Name: " + task.getResult().child("name").getValue(String.class));
                mm.setText("MM: " + task.getResult().child("mm").getValue(String.class));
                en.setText("EN: " + task.getResult().child("en").getValue(String.class));
                ec.setText("EC: " + task.getResult().child("ec").getValue(String.class));
                id.setText("ID: " + id.getText().toString());
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_description, container, false);
        id = view.findViewById(R.id.idView);
        mDataBase = FirebaseDatabase.getInstance().getReference();
        name = view.findViewById(R.id.nameView);
        mm = view.findViewById(R.id.mmView);
        en = view.findViewById(R.id.enView);
        ec = view.findViewById(R.id.ecView);
        show_data();
        return view;
    }
}