package com.example.playhard.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.playhard.Database.WholeCourse.WholeCourse;
import com.example.playhard.Database.WholeCourse.WholeCourseDAO;

@androidx.room.Database(entities = WholeCourse.class, version = 1)
public abstract class Database extends RoomDatabase {
    private static Database instance = null;
    public abstract WholeCourseDAO wholeCourseDAO();

    public static synchronized Database getInstance(Context context){
        if (instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),Database.class,"database")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new InitialPopulationAsyncTask(instance).execute();
        }
    };

    private static class InitialPopulationAsyncTask extends AsyncTask<Void,Void,Void>{
        private WholeCourseDAO wholeCourseDAO;
        private InitialPopulationAsyncTask(Database database){
            wholeCourseDAO = database.wholeCourseDAO();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            wholeCourseDAO.insertIntoWholeCourse(new WholeCourse("",0,0));
//            wholeCourseDAO.insertIntoWholeCourse(new WholeCourse("CIT 455",2,0));
//            wholeCourseDAO.insertIntoWholeCourse(new WholeCourse("CIT 410",3,0));
            return null;
        }
    }

}
