package com.example.android.keydroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
        if(id > 0){
            String selectQuery = "SELECT _id FROM USER WHERE Name=? AND Age=?";
            Cursor c = db.rawQuery(selectQuery, new String[] { user,age });
            if (c.moveToFirst()) {
                int t = c.getInt(c.getColumnIndex("_id"));
                //System.out.println("\n YOU SUCK THAT WHY ID IS "+t);
            return t;
            }
        }
        return id;
    }

    public static Users insertSamples(int userid,String latency, String duration,String valence,String arousal ) {

        SQLiteDatabase db=helper.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.UID,userid);
        contentValues.put(DatabaseHelper.LATENCY, latency);
        contentValues.put(DatabaseHelper.DURATION, duration);
        contentValues.put(DatabaseHelper.VALENCE, valence);
        contentValues.put(DatabaseHelper.AROUSAL, arousal);
        long id=db.insert(DatabaseHelper.TABLE_SAMPLE,null,contentValues);
        if(id > 0){
            Users u = new Users();
            u.setId(userid);
            u.setDuration(duration);
            u.setLatency(latency);
            u.setValence(valence);
            u.setArousal(arousal);
            return u;
        }
        return null;
    }



    static class DatabaseHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "Droiddb";
        private static final String TABLE_USER = "USER";
        private static final String TABLE_SAMPLE = "SAMPLE";
        private static final int DATABASE_VERSION = 9;
        private static final String UID = "_id";
        private static final String NAME = "Name";
        private static final String AGE = "Age";
        private static final String DURATION = "Duration";
        private static final String LATENCY = "Latency";
        private static final String VALENCE = "Valence";
        private static final String AROUSAL = "Arousal";



        private static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255), " + AGE + " INTEGER);";
        private static final String CREATE_TABLE_SAMPLE = "CREATE TABLE " + TABLE_SAMPLE + " (" + UID + " INTEGER , " + LATENCY + " VARCHAR(255), " + DURATION + " VARCHAR(255)," + VALENCE + " VARCHAR(255), " + AROUSAL +  " VARCHAR(255) )";
        private static final String SELECT_ID= "SELECT" +UID+ "FROM" + TABLE_USER + "WHERE" +UID+ "=(SELECT MAX(" + UID + " ) FROM" +TABLE_USER+ ");";

        //private static final String CREATE_TABLE_SAMPLE = "CREATE TABLE " + TABLE_SAMPLE + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + DURATION + " VARCHAR(255), " + LATENCY + " VARCHAR(255), " + AROUSAL + " INTEGER, " + VALENCE + " INTEGER);";
        private static final String DROP_TABLE_USER = "DROP TABLE IF EXISTS "+ TABLE_USER;
        private static final String DROP_TABLE_SAMPLE = "DROP TABLE IF EXISTS "+ TABLE_SAMPLE;
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
                db.execSQL(CREATE_TABLE_SAMPLE);
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
                db.execSQL(DROP_TABLE_SAMPLE);

                onCreate(db);
            } catch (SQLException e) {
                com.example.android.keydroid.Message.message(context, "" + e);
            }
        }

        /*public int getid(SQLiteDatabase db)
        {
           Cursor cursor=db.rawQuery(SELECT_ID);
            int id=cursor.getInt(0);
            return id;

        }*/


    }

}

