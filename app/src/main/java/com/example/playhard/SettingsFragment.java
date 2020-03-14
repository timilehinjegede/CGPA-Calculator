package com.example.playhard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {

    TextView firstNameTextView , lastNameTextView ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_settings,container,false);

        intiViews(view);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE);

//        firstNameTextView.setText(sharedPreferences.getString("FIRST_NAME",""));
        lastNameTextView.setText(sharedPreferences.getString("LAST_NAME",""));

        return view ;
    }

    private void intiViews(View view) {
//        firstNameTextView = view.findViewById(R.id.firstNameTextView);
        lastNameTextView = view.findViewById(R.id.lastNameTextView);
    }
}
