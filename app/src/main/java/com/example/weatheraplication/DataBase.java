package com.example.weatheraplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DataBase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1 ;
    private static final String DATABASE_NAME = "WT_BD";
    private static final String DICTONARY_TABLE = "weater";
    private static final String DEF_ID = "id";
    private static final String TITLE = "title";




    private static final String[] COLUNAS = {DEF_ID, DICTONARY_TABLE, DATABASE_NAME};


    public DataBase (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE weater ("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "title TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS weater");
        this.onCreate(db);
    }

    public void addData(Model model) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TITLE, model.getTitle());


        sqLiteDatabase.insert(DICTONARY_TABLE, null, values);
        sqLiteDatabase.close();
    }

    public List<Model> getEveryone(){
        List<Model> returnList = new ArrayList<Model>();

        String query = "SELECT * FROM " + DICTONARY_TABLE ;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                int customID = cursor.getInt(0);
                String customTitle =  cursor.getString(1);

                Model newModel = new Model(customID,  customTitle);
                returnList.add(newModel);

            }while (cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return returnList;
    }
}
