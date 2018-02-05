package com.example.mbds.barcodebattler;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Tom on 10/01/2018.
 */

public class Creature implements Parcelable {
    public int Id;
    public String Nom;
    public int PV;
    public int Attaque;
    public int Defense;
    public Bitmap Image;

    public Creature(){

    }

    public Creature(String nom, int pv, int defense, int attaque, Bitmap image){
        Nom = nom;
        PV = pv;
        Attaque = attaque;
        Defense = defense;
        Image = image;
    }
    public Creature(String nom, int pv, int defense, int attaque){
        Nom = nom;
        PV = pv;
        Attaque = attaque;
        Defense = defense;

    }

    protected Creature(Parcel in) {
        Nom = in.readString();
        int[] data = new int[3];
        in.readIntArray(data);
        PV = data[0];
        Attaque = data[1];
        Defense = data[2];
        Image = in.readParcelable(Bitmap.class.getClassLoader());
        Id = in.readInt();
    }

    public static final Creator<Creature> CREATOR = new Creator<Creature>() {
        @Override
        public Creature createFromParcel(Parcel in) {
            return new Creature(in);
        }

        @Override
        public Creature[] newArray(int size) {
            return new Creature[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Nom);
        dest.writeIntArray(new int[] {PV, Attaque, Defense});
        dest.writeParcelable(this.Image,0);
        dest.writeInt(Id);
    }

    @Override
    public String toString(){
        return Nom;
    }
    public void setImage(Bitmap image){
        this.Image = image;

    }
}

