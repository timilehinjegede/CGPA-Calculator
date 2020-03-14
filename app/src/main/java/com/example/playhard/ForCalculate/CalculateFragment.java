package com.example.playhard.ForCalculate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.playhard.R;
import com.example.playhard.RecyclerviewAdapters.CourseViewAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class CalculateFragment extends Fragment {
    public ViewPager viewPager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calculate,container,false);
        TabLayout gpaTabs = v.findViewById(R.id.gpaTabs);
        viewPager = v.findViewById(R.id.viewpager);
        viewPager.setAdapter(new SectionsPager(getActivity(), Objects.requireNonNull(getActivity()).getSupportFragmentManager()));
        gpaTabs.setupWithViewPager(viewPager);


        return v;
    }


}
