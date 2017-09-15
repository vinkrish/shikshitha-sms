package com.shikshitha.shikshithasms.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Vinay.
 */
public class SqlDbHelper extends SQLiteOpenHelper implements SqlConstant {
    private static SqlDbHelper dbHelper;
    public SQLiteDatabase sqliteDatabase;

    private SqlDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static SqlDbHelper getInstance(Context context) {
        if (dbHelper == null) {
            dbHelper = new SqlDbHelper(context.getApplicationContext());
        }
        return dbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TEACHER);
        db.execSQL(CREATE_SMS_INFO);
        db.execSQL(CREATE_ATTENDANCE);
        db.execSQL(CREATE_CLASS);
        db.execSQL(CREATE_SECTION);
        db.execSQL(CREATE_STUDENT);
        db.execSQL(CREATE_TIMETABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS teacher");
        db.execSQL("DROP TABLE IF EXISTS sms_info");
        db.execSQL("DROP TABLE IF EXISTS attendance");
        db.execSQL("DROP TABLE IF EXISTS class");
        db.execSQL("DROP TABLE IF EXISTS section");
        db.execSQL("DROP TABLE IF EXISTS student");
        db.execSQL("DROP TABLE IF EXISTS timetable");
        onCreate(db);
    }

    public void deleteTables() {
        sqliteDatabase = dbHelper.getWritableDatabase();
        sqliteDatabase.delete("teacher", null, null);
        sqliteDatabase.delete("sms_info", null, null);
        sqliteDatabase.delete("attendance", null, null);
        sqliteDatabase.delete("class", null, null);
        sqliteDatabase.delete("section", null, null);
        sqliteDatabase.delete("student", null, null);
        sqliteDatabase.delete("timetable", null, null);
    }
}
