package com.example.june_test.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "database.db";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UserProfile.Users.TABLE_NAME + " (" +
                    UserProfile.Users._ID + " INTEGER PRIMARY KEY," +
                    UserProfile.Users.COLUMN_1 + " TEXT," +
                    UserProfile.Users.COLUMN_2 + " TEXT," +
                    UserProfile.Users.COLUMN_3 + " TEXT," +
                    UserProfile.Users.COLUMN_4 + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UserProfile.Users.TABLE_NAME;



    //add user details to the database
    public long addInfo(String username, String age, String password, String gender){


        SQLiteDatabase db = getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(UserProfile.Users.COLUMN_1, username);
        values.put(UserProfile.Users.COLUMN_2, age);
        values.put(UserProfile.Users.COLUMN_3, password);
        values.put(UserProfile.Users.COLUMN_4, gender);



        long newRowId = db.insert(UserProfile.Users.TABLE_NAME, null, values);

        return newRowId;

    }


    public List readALlInfo(){
        SQLiteDatabase db = getReadableDatabase();

        String username = "ushani";


        String[] projection = {
                UserProfile.Users.COLUMN_1,
                UserProfile.Users.COLUMN_2,
                UserProfile.Users.COLUMN_4,

        };

// Filter results WHERE "title" = 'My Title'
        //String selection = UserProfile.Users.COLUMN_1 + " = ?";
        //String[] selectionArgs = { username };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                UserProfile.Users.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                UserProfile.Users.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                 null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List usernames = new ArrayList<>();
       // List ages = new ArrayList<>();
       // List genders = new ArrayList<>();
        while(cursor.moveToNext()) {
            String user = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_1));
           // String age = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_2));
            //String gender = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_4));

            usernames.add(user);//0
           // userInfo.add(age);//1
           // userInfo.add(gender);//2


        }
        cursor.close();

        return usernames;
    }





    //retrieve user information
    public List readALlInfo(String username){
        SQLiteDatabase db = getReadableDatabase();



// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                UserProfile.Users.COLUMN_1,
                UserProfile.Users.COLUMN_2,
                UserProfile.Users.COLUMN_4,

        };

//Filter results WHERE "title" = 'My Title'
        String selection = UserProfile.Users.COLUMN_1 + " = ?";
        String[] selectionArgs = { username };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                UserProfile.Users.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                UserProfile.Users.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List userInfo = new ArrayList<>();
        // List ages = new ArrayList<>();
        // List genders = new ArrayList<>();
        while(cursor.moveToNext()) {
            String user = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_1));
            String age = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_2));
            String gender = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_4));

               userInfo.add(user);//0
               userInfo.add(age);//1
               userInfo.add(gender);//2


        }
        cursor.close();

        return userInfo;
    }



    //get login details
    public Boolean LoginUser (String username, String password){

        SQLiteDatabase db = getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                UserProfile.Users.COLUMN_1,
                UserProfile.Users.COLUMN_3
        };

// Filter results WHERE "title" = 'My Title'
        String selection = UserProfile.Users.COLUMN_1 + " = ? AND "+ UserProfile.Users.COLUMN_3 + " = ?";
        String[] selectionArgs = { username , password };

// How you want the results sorted in the resulting Cursor
        String sortOrder =
                UserProfile.Users.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                UserProfile.Users.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List validUser = new ArrayList();

        while(cursor.moveToNext()){
           String user = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_1));
           validUser.add(user);
        }

        cursor.close();

        if(validUser.isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }
}
