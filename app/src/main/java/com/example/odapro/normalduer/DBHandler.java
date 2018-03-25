package com.example.odapro.normalduer;

/**
 * Created by oda on 09.01.18.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    //Not quite sure what this means
    private static final String DATABASE_NAME = "Statistikk";
    //
    private static final String TABLE_DATA = "datas";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SH_ADDR = "shop_address";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //This is the constructor.
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE" + TABLE_DATA + "(" + KEY_ID +
                "INTEGER PRIMARY KEY" + KEY_NAME + "TEXT," + KEY_SH_ADDR + "TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
        //Execute a single SQL statement that is NOT a SELECT or any other SQL statement that returns data.
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_DATA);
        onCreate(db);
    }

    public void addData(Data data) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, data.getName());
        values.put(KEY_SH_ADDR, data.getAddress());

        db.insert(TABLE_DATA, null, values);
        db.close();
    }

    public Data getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        // A cursor is a pointer, it returns all the rows from a database
        //
        //


        Cursor cursor = db.query(TABLE_DATA, new String[]{KEY_ID,
                        KEY_NAME, KEY_SH_ADDR}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Data contact = new Data(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));

        return contact;
    }

    public List<Data> getAllData(){
        List<Data> dataList = new ArrayList<Data>();
        String selectQuery = "SELECT * FROM" + TABLE_DATA;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                Data data = new Data();
                data.setId(Integer.parseInt(cursor.getString(0)));
                data.setName(cursor.getString(1));
                data.setAddress(cursor.getString(2));

            } while (cursor.moveToNext());
        }
        return dataList;
    }
}
