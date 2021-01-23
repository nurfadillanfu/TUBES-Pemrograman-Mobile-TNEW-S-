package com.dicoding.mynewsapp.db;

import android.provider.BaseColumns;

public class DatabeseContract {
    static String TABLE_BOOKMARK_NEW = "bookmark_news";

    static final class BookmarkColumns implements BaseColumns{
        static String TITLE = "title";
        static String DATE = "date";
        static String IMAGE = "image";
        static String AUTHOR = "author";
        static String CONTENT = "content";
        static String URL = "url";
    }
}
