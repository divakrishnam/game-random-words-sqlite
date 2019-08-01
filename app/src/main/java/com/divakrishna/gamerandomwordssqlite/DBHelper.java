package com.divakrishna.gamerandomwordssqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_WORDS = "words_data";
    public static final String TABLE_SCORES = "scores_data";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_QUESTION = "question";
    public static final String COLUMN_ANSWER = "answer";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SCORE = "score";
    private static final String db_name = "game.db";
    private static final int db_version = 1;

    private static final String db_create_words = "create table "
            +TABLE_WORDS+" ("
            +COLUMN_ID+" integer primary key autoincrement, "
            +COLUMN_QUESTION+" varchar(100) not null, "
            +COLUMN_ANSWER+" varchar(100) not null"
            +");";

    private static final String db_create_scores = "create table "
            +TABLE_SCORES+" ("
            +COLUMN_ID+" integer primary key autoincrement, "
            +COLUMN_NAME+" varchar(100) not null, "
            +COLUMN_SCORE+" varchar(5) not null"
            +");";

    private static final String db_insert_words = "insert into "
            +TABLE_WORDS+" (question, answer) values "
            +"('hewan berkaki dua?', 'bebek'),"
            +"('hewan berkaki dua lagi?', 'ayam'),"
            +"('hewan berkaki dua lagi lagi?', 'kangguru'),"
            +"('hewan berkaki dua lagi lagi lagi?', 'komodo berdiri')"
            +";";

    private static final String db_insert_scores = "insert into "
            +TABLE_SCORES+" (name, score) values"
            +"('vania', '200'),"
            +"('riska', '300')"
            +";";

    public DBHelper(Context context) {
        super(context, db_name, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(db_create_words);
        db.execSQL(db_create_scores);
        db.execSQL(db_insert_words);
        db.execSQL(db_insert_scores);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_WORDS);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_SCORES);
    }
}
