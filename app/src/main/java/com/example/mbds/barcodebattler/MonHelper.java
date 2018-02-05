package com.example.mbds.barcodebattler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by deptinfo on 05/02/2018.
 */

public class MonHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME ="barCodeBattler";

    private static final String TABLE_NAME = "creature";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOM = "name";
    private static final String COLUMN_PV = "pv";
    private static final String COLUMN_defense = "defense";
    private static final String COLUMN_attaque = "attaque";
    private static final String COLUMN_IMAGE = "image";

    private static  final String CREAT_DB="CREATE TABLE "+TABLE_NAME+"("+COLUMN_ID +" "+"INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "  "+COLUMN_NOM +"  "+ "TEXT NOT NULL,"+
            "  "+COLUMN_PV  +"  "+"INTEGER ,"+
            "  "+COLUMN_defense  +"  "+"INTEGER ,"+
            "  "+COLUMN_attaque  +"  "+"INTEGER ,"+
            "  "+COLUMN_IMAGE  +"  "+"BLOB NOT NULL"+");";




    public MonHelper(Context context) {
        super(context,TABLE_NAME,null,DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAT_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        db.execSQL(sql);
        onCreate(db);

    }
    public Boolean  addCreature(String name , Integer PV,Integer defense , Integer attaque , Bitmap image){
        ContentValues content = new ContentValues();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NOM, name);
        contentValues.put(COLUMN_PV,PV);
        contentValues.put(COLUMN_defense,defense);
        contentValues.put(COLUMN_attaque,attaque);



        ByteArrayOutputStream boas = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, boas ); //bm is the bitmap object
        byte[] byteArrayImage = boas .toByteArray();

        contentValues.put(COLUMN_IMAGE, byteArrayImage );
        SQLiteDatabase db = getWritableDatabase();
        return db.insert(TABLE_NAME, null, contentValues) != -1;

    }
    public ArrayList<Creature> getAllPersonnes() {
        ArrayList<Creature> creatures = new ArrayList<Creature>();
        SQLiteDatabase db =this.getReadableDatabase();

        Cursor creat = db.rawQuery(" SELECT * FROM " + TABLE_NAME, null);

        if (creat.moveToFirst())
        {
            do {
                Creature c = new Creature(creat.getString(1),creat.getInt(2),creat.getInt(3),creat.getInt(4));
                byte[] image = creat.getBlob(3);
                c.setImage(BitmapFactory.decodeByteArray(image,0, image.length));
                creatures.add(c);

            }while (creat.moveToNext());
        }
        creat.close();
        db.close();
        return  creatures;


    }
}
