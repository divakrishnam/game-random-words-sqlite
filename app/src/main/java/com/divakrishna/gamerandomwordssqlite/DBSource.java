package com.divakrishna.gamerandomwordssqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DBSource {
    private SQLiteDatabase database;

    private DBHelper dbHelper;

    private String[] allWord = {DBHelper.COLUMN_ID, DBHelper.COLUMN_QUESTION, DBHelper.COLUMN_ANSWER};
    private String[] allScore = {DBHelper.COLUMN_ID, DBHelper.COLUMN_NAME, DBHelper.COLUMN_SCORE};

    public DBSource(Context context){
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public void insertScore(String name, String score){
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NAME, name);
        values.put(DBHelper.COLUMN_SCORE, score);

        long insertId = database.insert(DBHelper.TABLE_SCORES, null, values);

        Cursor cursor = database.query(DBHelper.TABLE_SCORES, allScore, DBHelper.COLUMN_ID+" = "+insertId, null, null, null, null);
        cursor.close();
    }

    private Scores cursorToScore(Cursor cursor){
        Scores scores = new Scores();

        scores.setId(cursor.getLong(0));
        scores.setName(cursor.getString(1));
        scores.setScore(cursor.getString(2));

        return scores;
    }

    public ArrayList<Scores> getAllScore(){
        ArrayList<Scores> listScore = new ArrayList<Scores>();
        Cursor cursor = database.query(DBHelper.TABLE_SCORES, allScore, null, null, null, null, "score DESC");
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            Scores scores = cursorToScore(cursor);
            listScore.add(scores);
            cursor.moveToNext();
        }

        cursor.close();
        return listScore;
    }

    private Words cursorToWords(Cursor cursor){
        Words words = new Words();

        words.setId(cursor.getLong(0));
        words.setQuestion(cursor.getString(1));
        words.setAnswer(cursor.getString(2));

        return words;
    }

    public ArrayList<Words> getAllWord(){
        ArrayList<Words> listWord = new ArrayList<Words>();
        Cursor cursor = database.query(DBHelper.TABLE_WORDS, allWord, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            Words words = cursorToWords(cursor);
            listWord.add(words);
            cursor.moveToNext();
        }

        cursor.close();
        return listWord;
    }

    public Words getWords(long id){
        Words words = new Words();
        Cursor cursor = database.query(DBHelper.TABLE_WORDS, allWord, "_id = "+id, null, null, null, null);
        cursor.moveToFirst();
        words = cursorToWords(cursor);
        cursor.close();
        return words;
    }

}
