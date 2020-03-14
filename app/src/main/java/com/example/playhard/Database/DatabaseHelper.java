package com.example.playhard.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.playhard.Course;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "course_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "course";
    private static final String COL3 = "unit_position";
    private static final String COL4 = "grade_position";
    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable =
                "CREATE TABLE "+TABLE_NAME+"("+
                        COL1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        COL2+" TEXT, "+
                        COL3+" INTEGER, "+
                        COL4+" INTEGER);";
        sqLiteDatabase.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME+";");
        onCreate(sqLiteDatabase);
    }

    public void insertCourse(Course course){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,course.getCourse());
        contentValues.put(COL3,course.getUnitPosition());
        contentValues.put(COL4,course.getGradePosition());

        long result = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

        if(result==-1){
        }else{
        }
    }

    public Cursor getCourses(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return cursor;
    }

    public Course getCourse(int ID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE ID = "+ID+";",null);
        Course course = new Course();
        while (cursor.moveToNext()){
            course.setCourse(cursor.getString(1));
            course.setUnitPosition(Integer.parseInt(cursor.getString(2)));
            course.setGradePosition(Integer.parseInt(cursor.getString(3)));
        }
        return course;
    }

    public void deleteCourse(String ID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME,"id = ?",new String[]{ID});
    }

    public void updateCourse(Course course){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,course.getCourse());
        contentValues.put(COL3,course.getUnitPosition());
        contentValues.put(COL4,course.getGradePosition());
        sqLiteDatabase.update(TABLE_NAME,contentValues,"id=?",new String[]{String.valueOf(course.getId())});

    }
}
