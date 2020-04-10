package com.example.budgetmanagementdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {

    private static String dbname = "My Database";
    private static int dbversion =1;
    Context context;

    MyDatabase(Context context)
    {
        super(context, dbname, null,dbversion);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String s = "create table info(acquisition text, price integer, description text, text int)";

        db.execSQL(s);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertValues(String s1, String s2, long l, String s3)
    {

        SQLiteDatabase database = getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("acquisition", s1);
        cv.put("price", l);
        cv.put("description",s2);
        cv.put("date", s3);

        database.insert("info", null, cv);

        Toast.makeText(context, "Saved Successfully", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<String> showValues()

    {
        Toast.makeText(context, "Inside Show Value", Toast.LENGTH_SHORT).show();
        SQLiteDatabase database = getReadableDatabase();
        String s = "select *from info";

        Cursor cursor = database.rawQuery(s, null);

        ArrayList<String> arrayList = new ArrayList<>();

        while (cursor.moveToNext())
        {
            String s1 = cursor.getString(0);
            Long l = cursor.getLong(1);
            String s2 = cursor.getString(2);
            String s3 = cursor.getString(3);

            String s4 = "acquisition: "+ s1 +"\nprice : "+ l +"\ndescription : "+s2+ "date: "+s3;

            arrayList.add(s4);
        }
        return arrayList;
    }

}
