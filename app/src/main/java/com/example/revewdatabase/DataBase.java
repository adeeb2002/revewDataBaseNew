package com.example.revewdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {

    final static String nameData="ProductData";
    final static String nameTable="productTable";
    final static String idColumn="idColumn";
    final static String nameColumn="nameColumn";
    final static String categoryColumn="categoryColumn";
    final static String priceColumn="priceColumn";
    final static String imageColumn="imageColumn";


    public DataBase(@Nullable Context context) {
        super(context, nameData, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+nameTable+" ("+idColumn+" INTEGER PRIMARY KEY AUTOINCREMENT , "+nameColumn+" TEXT , "+categoryColumn+" TEXT , "+priceColumn+" REAL , "+imageColumn+" TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table "+nameTable);
        onCreate(db);
    }


    public  long insertProductInDataBase(Item questions){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(nameColumn,questions.getName());
        contentValues.put(categoryColumn,questions.getCategory());
        contentValues.put(priceColumn,questions.getPrice());
        contentValues.put(imageColumn,questions.getImage());
        return database.insert(nameTable,null,contentValues);
    }

    public ArrayList<Item> getDataInDataBaseInProduct(){
        SQLiteDatabase database=getReadableDatabase();
        ArrayList<Item> questions=new ArrayList<>();
        Cursor cursor=database.rawQuery("SELECT * FROM "+nameTable,null);
        while (cursor.moveToNext()){
            int id=cursor.getInt(cursor.getColumnIndexOrThrow(idColumn));
            String name=cursor.getString(cursor.getColumnIndexOrThrow(nameColumn));
            String cate=cursor.getString(cursor.getColumnIndexOrThrow(categoryColumn));
            String price=cursor.getString(cursor.getColumnIndexOrThrow(priceColumn));
            String image=cursor.getString(cursor.getColumnIndexOrThrow(imageColumn));
            questions.add(new Item(id,name,cate,price,image));
        }
        return questions;
    }

    public int deleteQuestion(int id){
        SQLiteDatabase database=getWritableDatabase();
        return database.delete(nameTable,idColumn+" =?",new String[]{String.valueOf(id)});
    }


    public int editQuestion(int id,Item questions){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(idColumn,questions.getId());
        contentValues.put(nameColumn,questions.getName());
        contentValues.put(categoryColumn,questions.getCategory());
        contentValues.put(priceColumn,questions.getPrice());
        contentValues.put(imageColumn,questions.getImage());
        int result=database.update(nameTable,contentValues,idColumn+" =? ",new String[]{String.valueOf(id)});
        return result;
    }

}
