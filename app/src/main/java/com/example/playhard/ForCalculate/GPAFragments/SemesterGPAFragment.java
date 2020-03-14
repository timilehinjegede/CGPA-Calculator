package com.example.playhard.ForCalculate.GPAFragments;

import android.animation.ObjectAnimator;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playhard.Course;
import com.example.playhard.Database.DatabaseHelper;
import com.example.playhard.R;
import com.example.playhard.RecyclerviewAdapters.CourseViewAdapter;
import com.example.playhard.RecyclerviewAdapters.ResultCourseViewAdapter;
import com.google.android.material.button.MaterialButton;


import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SemesterGPAFragment extends Fragment {

    //vars
    private DatabaseHelper databaseHelper;
    private ArrayList<Course> courses = new ArrayList<>();
    private ObjectAnimator objectAnimator1 = new ObjectAnimator();
    private ObjectAnimator objectAnimator2 = new ObjectAnimator();
    private ObjectAnimator objectAnimator3 = new ObjectAnimator();
    private ObjectAnimator objectAnimator4 = new ObjectAnimator();
    private ObjectAnimator objectAnimator5 = new ObjectAnimator();
    private final int DURATION = 500;

    //uis
    //calculate panel
    private MaterialCardView changeGradingSystemBanner;
    private MaterialButton okayButtonFromBanner;
    private RecyclerView recyclerView;
    private LinearLayout bannerAndRecyclerViewLinearLayout;
    private ExtendedFloatingActionButton calculateSemesterGPAButton;
    private LinearLayout calculatePanel;

    //nothing panel
    private LinearLayout nothingLinearLayout;

    //result panel
    private RecyclerView semesterResultRecyclerView;
    private TextView tcValue, twgpValue, gpaValue;
    private LinearLayout resultPanel;
    private FloatingActionButton closeResultPanelButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_semester_gpa, container, false);

        //initializations
        {
            //vars
            databaseHelper = new DatabaseHelper(getContext());

            //uis
            //calculate panel
            calculatePanel = v.findViewById(R.id.calculatePanel);

            bannerAndRecyclerViewLinearLayout = v.findViewById(R.id.bannerAndRecyclerViewLinearLayout);
            changeGradingSystemBanner = v.findViewById(R.id.changeGradingSystemBanner);
            okayButtonFromBanner = v.findViewById(R.id.okayButtonFromBanner);

            calculateSemesterGPAButton = v.findViewById(R.id.calculateSemesterGPAButton);
            recyclerView = v.findViewById(R.id.recyclerview);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            //nothing panel
            nothingLinearLayout = v.findViewById(R.id.nothingLinearLayout);

            //result panel
            resultPanel = v.findViewById(R.id.resultPanel);
            closeResultPanelButton = v.findViewById(R.id.closeResultPanelButton);

            semesterResultRecyclerView = v.findViewById(R.id.semesterResultRecyclerView);
            semesterResultRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            semesterResultRecyclerView.setHasFixedSize(true);

            tcValue = v.findViewById(R.id.tcValue);
            twgpValue = v.findViewById(R.id.twgpValue);
            gpaValue = v.findViewById(R.id.gpaValue);

            getData();
        }

        okayButtonFromBanner.setOnClickListener(view -> {
            ObjectAnimator objectAnimator = new ObjectAnimator();
            objectAnimator.setTarget(changeGradingSystemBanner);
            objectAnimator.setDuration(200);
            objectAnimator.setFloatValues((float) 1, (float) 0);
            objectAnimator.setPropertyName("alpha");
            objectAnimator.setInterpolator(new LinearInterpolator());
            objectAnimator.start();

            new Handler().postDelayed(() -> changeGradingSystemBanner.setVisibility(View.GONE), 200+10);
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                databaseHelper.deleteCourse(String.valueOf(courses.get(viewHolder.getAdapterPosition()).getId()));
                getData();
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);

        ExtendedFloatingActionButton addButtonFAB = v.findViewById(R.id.addButtonFAB);
        addButtonFAB.setOnClickListener(view -> {
            databaseHelper.insertCourse(new Course());
            getData();
        });


        calculateSemesterGPAButton.setOnClickListener(view -> {
            //5 Object Animators

            //objectAnimator3 is tied to the calculate panel
            objectAnimator3.setTarget(calculatePanel);
            objectAnimator3.setDuration(DURATION);
            objectAnimator3.setFloatValues(1, (float) 0);
            objectAnimator3.setPropertyName("alpha");
            objectAnimator3.setInterpolator(new LinearInterpolator());
            objectAnimator3.start();
            //objectAnimator4 is tied to the addFab
            objectAnimator4.setTarget(addButtonFAB);
            objectAnimator4.setDuration(DURATION);
            objectAnimator4.setFloatValues((float) 1, (float) 0);
            objectAnimator4.setPropertyName("alpha");
            objectAnimator4.setInterpolator(new LinearInterpolator());
            objectAnimator4.start();
            //objectAnimator5 is tied to the calculate button
            objectAnimator5.setTarget(calculateSemesterGPAButton);
            objectAnimator5.setDuration(DURATION);
            objectAnimator5.setFloatValues((float) 1, (float) 0);
            objectAnimator5.setPropertyName("alpha");
            objectAnimator5.setInterpolator(new LinearInterpolator());
            objectAnimator5.start();
            new Handler().postDelayed(() -> {
                //close all useless panels
                calculatePanel.setVisibility(View.GONE);
                addButtonFAB.setVisibility(View.GONE);
                calculateSemesterGPAButton.setVisibility(View.GONE);
                //show useful panels
                closeResultPanelButton.setVisibility(View.VISIBLE);
                resultPanel.setVisibility(View.VISIBLE);
                //objectAnimator1 is tied to the cancel fab from result panel
                objectAnimator1.setTarget(closeResultPanelButton);
                objectAnimator1.setDuration(DURATION * 2);
                objectAnimator1.setFloatValues((float) 0, (float) 1);
                objectAnimator1.setPropertyName("alpha");
                objectAnimator1.setInterpolator(new LinearInterpolator());
                objectAnimator1.start();
                //objectAnimator2 is tied to the result panel
                objectAnimator2.setTarget(resultPanel);
                objectAnimator2.setDuration(DURATION * 2);
                objectAnimator2.setFloatValues((float) 0, (float) 1);
                objectAnimator2.setPropertyName("alpha");
                objectAnimator2.setInterpolator(new LinearInterpolator());
                objectAnimator2.start();


                semesterResultRecyclerView.setAdapter(new ResultCourseViewAdapter(databaseHelper));

                fillBottomValues();

            }, DURATION + 50);


        });
        closeResultPanelButton.setOnClickListener(view -> {


            //objectAnimator1 is tied to the cancel fab from result panel
            objectAnimator1.setTarget(closeResultPanelButton);
            objectAnimator1.setDuration(DURATION);
            objectAnimator1.setFloatValues((float) 1, (float) 0);
            objectAnimator1.setPropertyName("alpha");
            objectAnimator1.setInterpolator(new LinearInterpolator());
            objectAnimator1.start();
            //objectAnimator2 is tied to the result panel
            objectAnimator2.setTarget(resultPanel);
            objectAnimator2.setDuration(DURATION);
            objectAnimator2.setFloatValues((float) 1, (float) 0);
            objectAnimator2.setPropertyName("alpha");
            objectAnimator2.setInterpolator(new LinearInterpolator());
            objectAnimator2.start();


            new Handler().postDelayed(() -> {
                //close all useless panels
                calculatePanel.setVisibility(View.VISIBLE);
                addButtonFAB.setVisibility(View.VISIBLE);
                calculateSemesterGPAButton.setVisibility(View.VISIBLE);
                //show useful panels
                closeResultPanelButton.setVisibility(View.GONE);
                resultPanel.setVisibility(View.GONE);


                //objectAnimator3 is tied to the calculate panel
                objectAnimator3.setTarget(calculatePanel);
                objectAnimator3.setDuration(DURATION * 2);
                objectAnimator3.setFloatValues(0, (float) 1);
                objectAnimator3.setPropertyName("alpha");
                objectAnimator3.setInterpolator(new LinearInterpolator());
                objectAnimator3.start();
                //objectAnimator4 is tied to the addFab
                objectAnimator4.setTarget(addButtonFAB);
                objectAnimator4.setDuration(DURATION * 2);
                objectAnimator4.setFloatValues((float) 0, (float) 1);
                objectAnimator4.setPropertyName("alpha");
                objectAnimator4.setInterpolator(new LinearInterpolator());
                objectAnimator4.start();
                //objectAnimator5 is tied to the calculate button
                objectAnimator5.setTarget(calculateSemesterGPAButton);
                objectAnimator5.setDuration(DURATION * 2);
                objectAnimator5.setFloatValues((float) 0, (float) 1);
                objectAnimator5.setPropertyName("alpha");
                objectAnimator5.setInterpolator(new LinearInterpolator());
                objectAnimator5.start();

            }, DURATION + 50);
        });


        return v;

    }

    private void getDataForFillBottomValues(ArrayList<Course> courseList) {
        Cursor cursor = databaseHelper.getCourses();
        while (cursor.moveToNext()) {
            int id = Integer.parseInt(cursor.getString(0));
            String course = cursor.getString(1);
            int unitPosition = Integer.parseInt(cursor.getString(2));
            int gradePosition = Integer.parseInt(cursor.getString(3));
            courseList.add(new Course(id, course, unitPosition, gradePosition));
        }
    }

    private int getTotalUnits(ArrayList<Course> courseList) {
        int sum = 0;
        for (Course course : courseList) {
            sum += extractUnit(course.getUnitPosition());
        }
        return sum;
    }

    private int getTotalWeightedGradePointTWGP(ArrayList<Course> courseList) {
        int twgp = 0;
        for (Course course : courseList) {
            int unit = extractUnit(course.getUnitPosition());
            int grade = extractGradeToInt(extractGrade(course.getGradePosition()));
            int wgp = unit * grade;
            twgp += wgp;
        }
        return twgp;
    }


    private int extractUnit(int position) {
        return position + 1;
    }

    private String extractGrade(int position) {
        String grade = "A";
        switch (position) {
            case 0:
                grade = "A";
                break;
            case 1:
                grade = "B";
                break;
            case 2:
                grade = "C";
                break;
            case 3:
                grade = "D";
                break;
            case 4:
                grade = "E";
                break;
            case 5:
                grade = "F";
                break;
        }
        return grade;
    }

    private int extractGradeToInt(String grade) {
        int gradeInInt = 0;
        switch (grade) {
            case "A":
                gradeInInt = 5;
                break;
            case "B":
                gradeInInt = 4;
                break;
            case "C":
                gradeInInt = 3;
                break;
            case "D":
                gradeInInt = 2;
                break;
            case "E":
                gradeInInt = 1;
                break;
            case "F":
                gradeInInt = 0;
                break;
        }
        return gradeInInt;
    }


    private void fillBottomValues() {
        ArrayList<Course> courseList = new ArrayList<>();
        getDataForFillBottomValues(courseList);
        int tc = getTotalUnits(courseList);
        int twgp = getTotalWeightedGradePointTWGP(courseList);
        double gpa = (double) twgp / (double) tc;
        tcValue.setText(String.valueOf(tc));
        twgpValue.setText(String.valueOf(twgp));
        gpaValue.setText(new DecimalFormat("0.00").format(gpa));
    }

    private void getData() {
        Cursor cursor = databaseHelper.getCourses();
        courses.clear();
        while (cursor.moveToNext()) {
            int id = Integer.parseInt(cursor.getString(0));
            String course = cursor.getString(1);
            int unitPosition = Integer.parseInt(cursor.getString(2));
            int gradePosition = Integer.parseInt(cursor.getString(3));
            courses.add(new Course(id, course, unitPosition, gradePosition));
        }
        CourseViewAdapter courseViewAdapter = new CourseViewAdapter(courses, databaseHelper);
        recyclerView.setAdapter(courseViewAdapter);

        if (courses.size() == 0) {
            nothingLinearLayout.setVisibility(View.VISIBLE);
            bannerAndRecyclerViewLinearLayout.setVisibility(View.GONE);
            calculateSemesterGPAButton.setClickable(false);
            calculateSemesterGPAButton.setAlpha((float) 0.5);
        } else {
            nothingLinearLayout.setVisibility(View.GONE);
            bannerAndRecyclerViewLinearLayout.setVisibility(View.VISIBLE);
            calculateSemesterGPAButton.setClickable(true);
            calculateSemesterGPAButton.setAlpha((float) 1);
        }
    }


}
