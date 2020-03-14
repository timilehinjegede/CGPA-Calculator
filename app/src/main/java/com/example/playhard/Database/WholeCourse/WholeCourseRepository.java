package com.example.playhard.Database.WholeCourse;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.playhard.Database.Database;

import java.util.List;

public class WholeCourseRepository {
    private WholeCourseDAO wholeCourseDAO;
    private LiveData<List<WholeCourse>> allWholeCourses;

    public WholeCourseRepository(Application application){
        Database database = Database.getInstance(application);
        wholeCourseDAO = database.wholeCourseDAO();
        allWholeCourses = wholeCourseDAO.getAllWholeCourse();
    }

    public LiveData<List<WholeCourse>> getAllWholeCourses() {
        return allWholeCourses;
    }
    public void insertWholeCourse(WholeCourse wholeCourse){
        new InsertWholeCourseAsyncTask(wholeCourseDAO).execute(wholeCourse);
    }
    public void deleteWholeCourse(WholeCourse wholeCourse){
        new DeleteWholeCourseAsyncTask(wholeCourseDAO).execute(wholeCourse);
    }
    public void clearWholeCourseList(){
        new ClearWholeCourseListAsyncTask(wholeCourseDAO).execute();
    }
    public void updateWholeCourse(WholeCourse wholeCourse){
        new UpdateWholeCourseAsyncTask(wholeCourseDAO).execute(wholeCourse);
    }

    public static class InsertWholeCourseAsyncTask extends AsyncTask<WholeCourse,Void,Void>{
        private WholeCourseDAO wholeCourseDAO;

        private InsertWholeCourseAsyncTask(WholeCourseDAO wholeCourseDAO){
            this.wholeCourseDAO = wholeCourseDAO;
        }

        @Override
        protected Void doInBackground(WholeCourse... wholeCourses) {
            wholeCourseDAO.insertIntoWholeCourse(wholeCourses[0]);
            return null;
        }
    }
    public static class DeleteWholeCourseAsyncTask extends AsyncTask<WholeCourse,Void,Void>{
        private WholeCourseDAO wholeCourseDAO;

        public DeleteWholeCourseAsyncTask(WholeCourseDAO wholeCourseDAO) {
            this.wholeCourseDAO = wholeCourseDAO;
        }

        @Override
        protected Void doInBackground(WholeCourse... wholeCourses) {
            wholeCourseDAO.deleteFromWholeCourse(wholeCourses[0]);
            return null;
        }
    }
    public static class ClearWholeCourseListAsyncTask extends AsyncTask<Void,Void,Void>{
        private WholeCourseDAO wholeCourseDAO;

        public ClearWholeCourseListAsyncTask(WholeCourseDAO wholeCourseDAO) {
            this.wholeCourseDAO = wholeCourseDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            wholeCourseDAO.clearWholeCourseList();
            return null;
        }
    }
    public static class UpdateWholeCourseAsyncTask extends AsyncTask<WholeCourse,Void,Void>{
        private WholeCourseDAO wholeCourseDAO;

        public UpdateWholeCourseAsyncTask(WholeCourseDAO wholeCourseDAO) {
            this.wholeCourseDAO = wholeCourseDAO;
        }

        @Override
        protected Void doInBackground(WholeCourse... wholeCourses) {
            wholeCourseDAO.updateWholeCourse(wholeCourses[0]);
            return null;
        }
    }

}
