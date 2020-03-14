package com.example.playhard.Database.WholeCourse;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WholeCourseDAO {
    @Insert
    void insertIntoWholeCourse(WholeCourse wholeCourse);
    @Delete
    void deleteFromWholeCourse(WholeCourse wholeCourse);
    @Query("DELETE FROM whole_course")
    void clearWholeCourseList();
    @Query("SELECT * FROM whole_course")
    LiveData<List<WholeCourse>> getAllWholeCourse();
    @Update
    void updateWholeCourse(WholeCourse wholeCourse);
}
