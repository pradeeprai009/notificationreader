package com.xcellity.yournotification;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SQLiteDataBaseListner extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "yournotification";

    public static final String TABLE_NAME_EXPENSE = "yournotification_Data";
    public static final String ID_NOTI = "id";
    public static final String TITLE = "title";
    public static final String PACKAGE = "Package";
    public static final String MESSAGE = "message";
    public static final String DATE = "date";


    String YOURNOTIFICATION = "CREATE TABLE " + TABLE_NAME_EXPENSE + "(" + ID_NOTI + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + TITLE + " TEXT," + PACKAGE + " TEXT," + MESSAGE + " TEXT," + DATE + " TEXT" + ")";

    public SQLiteDataBaseListner(@Nullable Context context) {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(YOURNOTIFICATION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_EXPENSE);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String title, String packages, String message, String date) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLiteDataBaseListner.TITLE, title);
        contentValues.put(SQLiteDataBaseListner.PACKAGE, packages);
        contentValues.put(SQLiteDataBaseListner.MESSAGE, message);
        contentValues.put(SQLiteDataBaseListner.DATE, date);
        db.insert(SQLiteDataBaseListner.TABLE_NAME_EXPENSE, null, contentValues);
        return true;
    }

    public ArrayList<NotiModel> getExpenseData() {
        ArrayList<NotiModel> arrayList =new ArrayList<>();
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM " + TABLE_NAME_EXPENSE,null);

        while (cursor.moveToNext()){
            String id=cursor.getString(0);
            String title=cursor.getString(1);
            String message=cursor.getString(2);
            String packagesNa=cursor.getString(3);
            String date=cursor.getString(4);
            NotiModel expense=new NotiModel(id,title,message,packagesNa,date);
            arrayList.add(expense);
        }
        return arrayList;
    }
}
