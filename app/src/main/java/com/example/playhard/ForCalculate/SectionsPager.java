package com.example.playhard.ForCalculate;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.playhard.R;

public class SectionsPager extends FragmentStatePagerAdapter {

    private static final int[] TAB_TITLES = new int[]{R.string.semester_gpa, R.string.cumulative_gpa};
    private final Context context;

    public SectionsPager(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return GPAFragmentPlaceHolderViewModel.getInstance().placeFragment(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {

        return 2;
    }
}
