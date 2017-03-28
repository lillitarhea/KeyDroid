package com.example.android.keydroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Lill_Rhea on 28/03/2017.
 */
public class DatabaseAdapter {

    static DatabaseHelper helper;

    public DatabaseAdapter(Context context)
    {
        helper=new DatabaseHelper(context);

    }

    public static long insertData(String user, String age) {

        SQLiteDatabase db=helper.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.NAME,user);
        contentValues.put(DatabaseHelper.AGE, age);
        long id=db.insert(DatabaseHelper.TABLE_USER,null,contentValues);
        return id;
    }

    static class DatabaseHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "Droiddb";
        private static final String TABLE_USER = "USER";
        private static final int DATABASE_VERSION = 3;
        private static final String UID = "_id";
        private static final String NAME = "Name";
        private static final String AGE = "Age";
        private static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255), " + AGE + " INTEGER);";
        private static final String DROP_TABLE_USER = "DROP TABLE IF EXISTS"+ TABLE_USER;
        private Context context;

        public DatabaseHelper(Context context) {
            super (context,DATABASE_NAME,null,DATABASE_VERSION);
            this.context=context;
            com.example.android.keydroid.Message.message(context,"constructor called");
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE_USER);
                com.example.android.keydroid.Message.message(context, "OnCreate is called");
            } catch (SQLException e) {
                com.example.android.keydroid.Message.message(context, "" + e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                com.example.android.keydroid.Message.message(context, "OnUpgrade is called");
                db.execSQL(DROP_TABLE_USER);
                onCreate(db);
            } catch (SQLException e) {
                com.example.android.keydroid.Message.message(context, "" + e);
            }
        }


    }

}

