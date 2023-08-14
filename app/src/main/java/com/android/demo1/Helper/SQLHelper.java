package com.android.demo1.Helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.android.demo1.Models.Shoe;

import java.util.ArrayList;
import java.util.List;

public class SQLHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "SHOE_DB";
    static final int DB_VERSION = 1;
    static final String SHOE = "shoe";

    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;

    public SQLHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + SHOE + " (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, name TEXT, price INTEGER)";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS " + SHOE);
            onCreate(db);
        }
    }

    public void insertShoe(Shoe shoe){
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();

        contentValues.put("name", shoe.getName());
        contentValues.put("price", shoe.getPrice());

        sqLiteDatabase.insert(SHOE, null, contentValues);

        sqLiteDatabase.close();
    }

    public List<Shoe> getShoes(){
        List<Shoe> shoes = new ArrayList<>();

        sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + SHOE, new String[]{});
        while (cursor.moveToNext()){
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
            @SuppressLint("Range") int price = cursor.getInt(cursor.getColumnIndex("price"));

            shoes.add(new Shoe(id+"", name, price));
        }

        return shoes;
    }
}
