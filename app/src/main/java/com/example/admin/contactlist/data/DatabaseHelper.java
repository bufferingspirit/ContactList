package com.example.admin.contactlist.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.admin.contactlist.model.StoredContact.StoredContact;

import java.util.ArrayList;

/**
 * Created by Admin on 10/13/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyDatabase";
    private static final int DATABASE_VERSION = 4;

    public static final String TABLE_NAME = "Contacts";
    public static final String FIRST_NAME = "FirstNames";
    public static final String LAST_NAME = "LastNames";
    public static final String STREET = "Street";
    public static final String CITY = "City";
    public static final String STATE = "State";
    public static final String POSTCODE = "Postcode";
    public static final String EMAIL = "Email";
    public static final String THUMBNAIL = "Thumbnail";
    public static final String LARGE = "Large";
    //put picture here
    public static final String TAG = "DatabaseHelper";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                FIRST_NAME + " TEXT , " +
                LAST_NAME + " TEXT, " +
                STREET + " TEXT, " +
                CITY + " TEXT, " +
                STATE + " TEXT, " +
                POSTCODE  + " TEXT, " +
                EMAIL + " TEXT, " +
                THUMBNAIL + " BLOB, " +
                LARGE + " BLOB, " +
                "PRIMARY KEY (" + FIRST_NAME + ", " + LAST_NAME + "))";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long SaveNewContact(StoredContact entry) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(FIRST_NAME, entry.getFirstName());
        cv.put(LAST_NAME, entry.getLastName());
        cv.put(STREET, entry.getStreet());
        cv.put(CITY, entry.getCity());
        cv.put(STATE, entry.getState());
        cv.put(POSTCODE, entry.getPostcode());
        cv.put(EMAIL, entry.getEmail());
        cv.put(THUMBNAIL, entry.getThumbnail());
        cv.put(LARGE, entry.getLarge());
        long saved = database.insert(TABLE_NAME, null, cv);
        Log.d(TAG, "SaveNewContact: ");
        return saved;
    }

    public boolean checkIfEntryExits(String firstName, String lastName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String Query = "SELECT * FROM " + TABLE_NAME + " WHERE "
                + FIRST_NAME + " = '" + firstName + "' AND "
                + LAST_NAME + " = '" + lastName + "'";
        Cursor cursor = db.rawQuery(Query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public boolean UpdateContact(StoredContact entry) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FIRST_NAME, entry.getFirstName());
        cv.put(LAST_NAME, entry.getLastName());
        cv.put(STREET, entry.getStreet());
        cv.put(CITY, entry.getCity());
        cv.put(STATE, entry.getState());
        cv.put(POSTCODE, entry.getPostcode());
        cv.put(EMAIL, entry.getEmail());
        cv.put(THUMBNAIL, entry.getThumbnail());
        cv.put(LARGE, entry.getLarge());
        db.update(TABLE_NAME, cv,
                FIRST_NAME + " = '" + entry.getFirstName() + "' AND " +
                        LAST_NAME + " = '" + entry.getLastName() + "'", null);
        return true;
    }

    public Integer DeleteContact(String firstName, String lastName) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, FIRST_NAME + " = '" + firstName + "' AND " +
                LAST_NAME + " = '" + lastName + "'", null);
    }

    public ArrayList<StoredContact> GetContactList() {
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        ArrayList<StoredContact> entryList = new ArrayList<>();
        Cursor cursor = database.rawQuery(query, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                StoredContact entry = new StoredContact(
                          cursor.getString(0)
                        , cursor.getString(1)
                        , cursor.getString(2)
                        , cursor.getString(3)
                        , cursor.getString(4)
                        , cursor.getString(5)
                        , cursor.getString(6)
                        , cursor.getBlob(7)
                        , cursor.getBlob(8));
                entryList.add(entry);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return entryList;
    }

    public StoredContact getEntry(String firstName, String lastName) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE "
                + FIRST_NAME + " = '" + firstName + "' AND "
                + LAST_NAME + " = '" + lastName + "'";

        Cursor cursor = db.rawQuery(query, null);
        if(cursor != null && cursor.moveToFirst()) {
            return new StoredContact(
                    cursor.getString(0)
                    , cursor.getString(1)
                    , cursor.getString(2)
                    , cursor.getString(3)
                    , cursor.getString(4)
                    , cursor.getString(5)
                    , cursor.getString(6)
                    , cursor.getBlob(7)
                    , cursor.getBlob(8));
        }
        else{
            return new StoredContact("","","","","","","",null,null);
        }
    }

}