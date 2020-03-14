package com.example.playhard.Database.WholeCourse;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "whole_course")
public class WholeCourse {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String code;
    private int unitPosition;
    private int gradePosition;

    public WholeCourse(String code, int unitPosition, int gradePosition) {
        this.code = code;
        this.unitPosition = unitPosition;
        this.gradePosition = gradePosition;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public int getUnitPosition() {
        return unitPosition;
    }

    public int getGradePosition() {
        return gradePosition;
    }
}
