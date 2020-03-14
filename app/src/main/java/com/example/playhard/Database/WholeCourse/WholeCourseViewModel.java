package com.example.playhard.Database.WholeCourse;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WholeCourseViewModel extends AndroidViewModel {
    private WholeCourseRepository repository;
    private LiveData<List<WholeCourse>> wholeCourseList;
    public WholeCourseViewModel(@NonNull Application application) {
        super(application);
        repository = new WholeCourseRepository(application);
        wholeCourseList = repository.getAllWholeCourses();
    }

    public void insertIntoWholeCourse(WholeCourse wholeCourse){
        repository.insertWholeCourse(wholeCourse);
    }
    public void delete(WholeCourse wholeCourse){
        repository.deleteWholeCourse(wholeCourse);
    }
    public void clearWholeCourseList(){
        repository.clearWholeCourseList();
    }
    public LiveData<List<WholeCourse>> getWholeCourseList() {
        return wholeCourseList;
    }
    public void updateWholeCourse(WholeCourse wholeCourse){
        repository.updateWholeCourse(wholeCourse);
    }
}
