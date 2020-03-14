package com.example.playhard.RecyclerviewAdapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.playhard.Course;
import com.example.playhard.Database.DatabaseHelper;
import com.example.playhard.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class ResultCourseViewAdapter extends RecyclerView.Adapter<ResultCourseViewAdapter.ViewHolder> {
    private ArrayList<Course> courses = new ArrayList<>();

    public ResultCourseViewAdapter(DatabaseHelper databaseHelper) {
        Cursor cursor = databaseHelper.getCourses();
        while (cursor.moveToNext()) {
            int id = Integer.parseInt(cursor.getString(0));
            String course = cursor.getString(1);
            int unitPosition = Integer.parseInt(cursor.getString(2));
            int gradePosition = Integer.parseInt(cursor.getString(3));
            courses.add(new Course(id, course, unitPosition, gradePosition));
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.adapter_result_course_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.course.setText(courses.get(holder.getAdapterPosition()).getCourse());
        int unit = extractUnit(courses.get(holder.getAdapterPosition()).getUnitPosition());
        holder.unit.setText("Unit: " + unit);
        String grade = extractGrade(courses.get(holder.getAdapterPosition()).getGradePosition());
        holder.grade.setText("Grade: "+grade);
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

    @Override
    public int getItemCount() {
        return courses.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextInputEditText course;
        private TextView unit;
        private TextView grade;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            course = itemView.findViewById(R.id.course);
            unit = itemView.findViewById(R.id.unit);
            grade = itemView.findViewById(R.id.grade);
        }
    }
}
