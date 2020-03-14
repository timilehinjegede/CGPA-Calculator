package com.example.playhard.RecyclerviewAdapters;

import android.content.Context;
import android.database.Cursor;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.playhard.Course;
import com.example.playhard.Database.DatabaseHelper;
import com.example.playhard.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class CourseViewAdapter extends RecyclerView.Adapter<CourseViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Course> courses;
    private DatabaseHelper databaseHelper;


    private boolean isComputerThatSetUnit = false;
    private boolean isComputerThatSetGrade = false;
    private boolean isComputerThatSetCourse = false;

    private boolean [] isComputerThatSetUnits, isComputerThatSetGrades, isComputerThatSetCourses;


    public CourseViewAdapter(ArrayList<Course> courses, DatabaseHelper databaseHelper) {
        this.courses = courses;
        this.databaseHelper = databaseHelper;
        isComputerThatSetUnits = new boolean[courses.size()];
        isComputerThatSetGrades = new boolean[courses.size()];
        isComputerThatSetCourses = new boolean[courses.size()];
        fillArraysWithFalse();
    }

    private void fillArraysWithFalse(){
        for (int i=0;i<isComputerThatSetUnits.length;i++){
            isComputerThatSetUnits[i]=false;
            isComputerThatSetCourses[i]=false;
            isComputerThatSetGrades[i]=false;
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_course_view, parent, false));
    }

    private void setComputerThatSetUnit(ViewHolder holder) {
        isComputerThatSetUnits[holder.getAdapterPosition()]=true;
        ArrayAdapter<CharSequence> unitValueAdapter = ArrayAdapter.createFromResource(context, R.array.units, android.R.layout.simple_list_item_1);
        unitValueAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.unitValue.setAdapter(unitValueAdapter);
    }

    private void setComputerThatSetGrade(ViewHolder holder) {
        isComputerThatSetGrades[holder.getAdapterPosition()] = true;
        ArrayAdapter<CharSequence> gradeValueAdapter = ArrayAdapter.createFromResource(context, R.array.grades, android.R.layout.simple_list_item_1);
        gradeValueAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.gradeValue.setAdapter(gradeValueAdapter);
    }

    private void setComputerThatSetCourse(ViewHolder holder) {
        holder.textInputLayout.getEditText().setText(" ".trim());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//        setComputerThatSetCourse(holder);
        setComputerThatSetUnit(holder);
        setComputerThatSetGrade(holder);

        isComputerThatSetCourses[holder.getAdapterPosition()] = true;


        holder.course.setText(String.valueOf(courses.get(holder.getAdapterPosition()).getCourse()));
        holder.unitValue.setSelection(courses.get(holder.getAdapterPosition()).getUnitPosition());
        holder.gradeValue.setSelection(courses.get(holder.getAdapterPosition()).getGradePosition());
        holder.textInputLayout.setHint("Course " + (holder.getAdapterPosition() + 1));

        holder.course.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (isComputerThatSetCourses[holder.getAdapterPosition()]) {
                    isComputerThatSetCourses[holder.getAdapterPosition()] = false;
                } else {
                    String text = charSequence.toString();
                    int unitPosition = holder.unitValue.getSelectedItemPosition();
                    int gradePosition = holder.gradeValue.getSelectedItemPosition();
                    int id = courses.get(holder.getAdapterPosition()).getId();
                    courses.get(holder.getAdapterPosition()).setCourse(text);
                    Log.d("mPoisition",""+holder.getAdapterPosition());
                    Log.d("mposid",""+id+"\n");
                    databaseHelper.updateCourse(new Course(id, text, unitPosition, gradePosition));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        holder.unitValue.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (isComputerThatSetUnits[holder.getAdapterPosition()]) {
                    isComputerThatSetUnits[holder.getAdapterPosition()] = false;
                } else {
                    String text = holder.textInputLayout.getEditText().getText().toString();
                    int unitPosition = holder.unitValue.getSelectedItemPosition();
                    int gradePosition = holder.gradeValue.getSelectedItemPosition();
                    int id = courses.get(holder.getAdapterPosition()).getId();
                    courses.get(holder.getAdapterPosition()).setUnitPosition(unitPosition);
                    databaseHelper.updateCourse(new Course(id, text, unitPosition, gradePosition));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        holder.gradeValue.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (isComputerThatSetGrades[holder.getAdapterPosition()]) {
                    isComputerThatSetGrades[holder.getAdapterPosition()] = false;
                } else {
                    String text = holder.textInputLayout.getEditText().getText().toString();
                    int unitPosition = holder.unitValue.getSelectedItemPosition();
                    int gradePosition = holder.gradeValue.getSelectedItemPosition();
                    int id = courses.get(holder.getAdapterPosition()).getId();
                    courses.get(holder.getAdapterPosition()).setGradePosition(gradePosition);
                    databaseHelper.updateCourse(new Course(id, text, unitPosition, gradePosition));

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private Spinner unitValue, gradeValue;
        private TextInputEditText course;
        private TextInputLayout textInputLayout;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            unitValue = itemView.findViewById(R.id.unitValue);
            gradeValue = itemView.findViewById(R.id.gradeValue);
            course = itemView.findViewById(R.id.course);
            textInputLayout = itemView.findViewById(R.id.textss);
        }
    }
}
