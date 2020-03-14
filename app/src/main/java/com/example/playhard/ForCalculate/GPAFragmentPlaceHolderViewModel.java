package com.example.playhard.ForCalculate;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

import com.example.playhard.ForCalculate.GPAFragments.CumulativeGPAFragment;
import com.example.playhard.ForCalculate.GPAFragments.SemesterGPAFragment;

public class GPAFragmentPlaceHolderViewModel extends ViewModel {
    private static GPAFragmentPlaceHolderViewModel instance = null;
    public Fragment placeFragment(int position){
        return position == 0 ? new SemesterGPAFragment() : new CumulativeGPAFragment();
    }

    private GPAFragmentPlaceHolderViewModel(){}

    public static GPAFragmentPlaceHolderViewModel getInstance(){
        if (instance==null){
            instance = new GPAFragmentPlaceHolderViewModel();
        }
        return instance;
    }
}
