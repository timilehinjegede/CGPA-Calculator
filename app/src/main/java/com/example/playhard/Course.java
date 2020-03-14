package com.example.playhard;

public class Course {
    private int id;
    private String course;
    private int unitPosition;
    private int gradePosition;

    public Course() {
        this.course = "";
        this.unitPosition = 0;
        this.gradePosition = 0;
    }

    public Course(int id, String course, int unitPosition, int gradePosition) {
        this.id = id;
        this.course = course;
        this.unitPosition = unitPosition;
        this.gradePosition = gradePosition;
    }

    public int getId() {
        return id;
    }

    public String getCourse() {
        return course;
    }
    public int getUnitPosition() {
        return unitPosition;
    }
    public int getGradePosition() {
        return gradePosition;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setUnitPosition(int unitPosition) {
        this.unitPosition = unitPosition;
    }

    public void setGradePosition(int gradePosition) {
        this.gradePosition = gradePosition;
    }
}
