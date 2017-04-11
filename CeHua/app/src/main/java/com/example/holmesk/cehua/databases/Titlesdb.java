package com.example.holmesk.cehua.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 作者：holmes k
 * 时间：2017.04.11 09:47
 */

public class Titlesdb extends SQLiteOpenHelper {

    public Titlesdb(Context context) {
        super(context, "titles.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table titles(tid integer primary key autoincrement,title text unique)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
