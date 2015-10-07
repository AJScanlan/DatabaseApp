package com.ajscanlan.databaseapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ajscanlan.databaseapp.person.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander Scanlan on 07/10/2015
 */
public class DatabaseHandler {
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private DBOpenHelper mDBOpenHelper;

    public DatabaseHandler(Context context){
        mContext = context;
    }

    public List<Person> getAllPeople(){
        List<Person> people = new ArrayList<>();

        mDBOpenHelper = new DBOpenHelper(mContext);
        mDatabase = mDBOpenHelper.getReadableDatabase();

        Cursor cursor = mDatabase.rawQuery("SELECT * FROM " + DBFeederContract.PersonTable.TABLE_NAME, null);

        if(cursor != null && cursor.moveToFirst()){
            do{
                int tempID = cursor.getInt(cursor.getColumnIndex(DBFeederContract.PersonTable._ID));
                String tempName = cursor.getString(cursor.getColumnIndex(DBFeederContract.PersonTable.COLUMN_NAME));
                String tempEmail = cursor.getString(cursor.getColumnIndex(DBFeederContract.PersonTable.COLUMN_EMAIL));
                String tempPhoneNumber = cursor.getString(cursor.getColumnIndex(DBFeederContract.PersonTable.COLUMN_PHONE_NUMBER));

                people.add(new Person(tempID, tempName, tempEmail, tempPhoneNumber));

            }while(cursor.moveToNext());

            cursor.close();
        }

        mDatabase.close(); mDBOpenHelper.close();
        mDatabase = null; mDBOpenHelper = null;

        return new ArrayList<Person>(people);
    }

    public Person getPerson(int id){
        mDBOpenHelper = new DBOpenHelper(mContext);
        mDatabase = mDBOpenHelper.getReadableDatabase();

        Cursor cursor = mDatabase.rawQuery("SELECT * FROM "
                + DBFeederContract.PersonTable.TABLE_NAME
                + " WHERE " + DBFeederContract.PersonTable._ID + " = " + id , null);

        if(cursor != null && cursor.moveToFirst()){

            String tempName = cursor.getString(cursor.getColumnIndex(DBFeederContract.PersonTable.COLUMN_NAME));
            String tempEmail = cursor.getString(cursor.getColumnIndex(DBFeederContract.PersonTable.COLUMN_EMAIL));
            String tempPhoneNumber = cursor.getString(cursor.getColumnIndex(DBFeederContract.PersonTable.COLUMN_PHONE_NUMBER));

            cursor.close();

            mDatabase.close(); mDBOpenHelper.close();
            mDatabase = null; mDBOpenHelper = null;

            return new Person(id, tempName, tempEmail, tempPhoneNumber);
        } else {
            return null;
        }
    }

    public void addPerson(Person person){
        mDBOpenHelper = new DBOpenHelper(mContext);
        mDatabase = mDBOpenHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBFeederContract.PersonTable.COLUMN_NAME, person.getName());
        contentValues.put(DBFeederContract.PersonTable.COLUMN_EMAIL, person.getEmail());
        contentValues.put(DBFeederContract.PersonTable.COLUMN_PHONE_NUMBER, person.getPhoneNumber());

        mDatabase.insert(DBFeederContract.PersonTable.TABLE_NAME, null, contentValues);
        mDatabase.close(); mDBOpenHelper.close();
        mDatabase = null; mDBOpenHelper = null;
    }
}
