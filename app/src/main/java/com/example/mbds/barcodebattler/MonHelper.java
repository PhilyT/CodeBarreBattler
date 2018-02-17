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

    private static final String TABLE_Creature = "creature";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOM = "name";
    private static final String COLUMN_PV = "pv";
    private static final String COLUMN_defense = "defense";
    private static final String COLUMN_attaque = "attaque";
    private static final String COLUMN_IMAGE = "image";
    private static final String COLUMN_EQUIPEMENT="idE";

    private static final String TABLE_Equipement = "equipement";
    private static final String COLUMN_IDE = "idE";
    private static final String COLUMN_NAME = "nameE";
    private static final String COLUMN_Point = "point";
    private static final String COLUMN_attribut = "attribut";
    private static final String COLUMN_IMAGEE = "imageE";
    private static final String COLUMN_equipementID  = "attribut";
    private static final String COLUMN_creatureID="cretureID";

    private static  final String tableCreature="CREATE TABLE "+TABLE_Creature+"("+COLUMN_ID +" "+"INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "  "+COLUMN_NOM +"  "+ "TEXT NOT NULL,"+
            "  "+COLUMN_PV  +"  "+"INTEGER ,"+
            "  "+COLUMN_defense  +"  "+"INTEGER ,"+
            "  "+COLUMN_attaque  +"  "+"INTEGER ,"+
            "  "+COLUMN_IMAGE  +"  "+"BLOB NOT NULL"+ ")";


        /* "  "+COLUMN_equipementID  +"  "+"INTEGER ,"+
            "   "+COLUMN_EQUIPEMENT+""+" FOREIGN KEY("+COLUMN_equipementID+" ) REFERENCES equipement(COLUMN_IDE),"+*/


    private static  final String tableEquipement ="CREATE TABLE "+TABLE_Equipement+"("+COLUMN_IDE +" "+"INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "  "+COLUMN_NAME +"  "+ "TEXT NOT NULL,"+
            "  "+COLUMN_Point  +"  "+"INTEGER ,"+
            "  "+COLUMN_IMAGEE +"  "+"BLOB NOT NULL,"+
            "  "+COLUMN_attribut  +"  "+"TEXT NOT NULL ,"+
            "  "+COLUMN_creatureID  +"  "+"INTEGER NOT NULL,FOREIGN KEY("+COLUMN_creatureID+" ) REFERENCES creature(COLUMN_IDE)"+")";
    //"  "+COLUMN_attribut  +"  "+"TEXT NOT NULL ,"+






    public MonHelper(Context context) {
        super(context,DATABASE_NAME ,null,DATABASE_VERSION );

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tableEquipement);
        db.execSQL(tableCreature);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlcreat = "DROP TABLE IF EXISTS " + TABLE_Creature+ ";";
        String sqlEquipement = "DROP TABLE IF EXISTS " + TABLE_Equipement + ";";
        db.execSQL(sqlcreat);
        db.execSQL(sqlEquipement);

        onCreate(db);

    }
    public int addCreature(String name , Integer PV,Integer defense , Integer attaque , Bitmap image){
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
        return (int)db.insert(TABLE_Creature, null, contentValues) ;

    }
    public Boolean  addEquipement(String nom , int point  , Bitmap image, String attribut ,int creatureID){

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, nom);

        contentValues.put(COLUMN_Point,point);
        contentValues.put(COLUMN_attribut,attribut);
        contentValues.put(COLUMN_creatureID, creatureID);

        ByteArrayOutputStream boas = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, boas ); //bm is the bitmap object
        byte[] byteArrayImage = boas.toByteArray();

        contentValues.put(COLUMN_IMAGEE, byteArrayImage );
        SQLiteDatabase db = getWritableDatabase();
        return db.insert(TABLE_Equipement, null, contentValues) != -1;

    }
    public ArrayList<Creature> getAllCreatures() {
        ArrayList<Creature> creatures = new ArrayList<Creature>();
        SQLiteDatabase db =this.getReadableDatabase();

        Cursor creat = db.rawQuery(" SELECT * FROM " + TABLE_Creature, null);

        if (creat.moveToFirst())
        {
            do {
                Creature c = new Creature(creat.getInt(0),creat.getString(1),creat.getInt(2),creat.getInt(3),creat.getInt(4));
                byte[] image = creat.getBlob(5);
                c.setImage(BitmapFactory.decodeByteArray(image,0, image.length));
                creatures.add(c);

            }while (creat.moveToNext());
        }
        creat.close();
        db.close();
        return  creatures;
    }



    public ArrayList<Equipement> getAllEquipements() {
        ArrayList<Equipement> equipements = new ArrayList<Equipement>();
        SQLiteDatabase db =this.getReadableDatabase();

        Cursor equip = db.rawQuery("SELECT * FROM" +"  "+ TABLE_Equipement, null);

        if (equip.moveToFirst())
        {
            do {
                Equipement q = new Equipement (equip.getString(1),equip.getInt(2),equip.getString(4));
                q.Id=equip.getInt(0);
                q.CreatureID=equip.getInt(5);
                byte[] image = equip.getBlob(3);
                q.setImage(BitmapFactory.decodeByteArray(image,0, image.length));
                equipements.add(q);

            }while (equip.moveToNext());
        }
        equip.close();
        db.close();
        return  equipements;


    }

}
