package com.dicoding.mynewsapp.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
  public static  String DATABASE_NAME = "dbBookmark";
  private static final  int DATABASE_VERSION = 1;
    private final String SQL_CREATE_TABLE_BOOKMARK_NEWS = String.format("CREATE TABLE %s"
            + "(%s INTEGER PRIMARY KEY AUTOINCREMENT," +
            " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
            " %s TEXT NOT NULL," +
            " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
            " %s TEXT NOT NULL)",
    DatabeseContract.TABLE_BOOKMARK_NEW,
    DatabeseContract.BookmarkColumns._ID,
    DatabeseContract.BookmarkColumns.TITLE,
    DatabeseContract.BookmarkColumns.DATE,
    DatabeseContract.BookmarkColumns.IMAGE,
    DatabeseContract.BookmarkColumns.AUTHOR,
    DatabeseContract.BookmarkColumns.URL,
    DatabeseContract.BookmarkColumns.CONTENT
    );
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_BOOKMARK_NEWS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ DatabeseContract.TABLE_BOOKMARK_NEW);
        onCreate(db);

    }
}
