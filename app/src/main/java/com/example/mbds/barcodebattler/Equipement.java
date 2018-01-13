package com.example.mbds.barcodebattler;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by deptinfo on 13/01/2018.
 */

public class Equipement implements Parcelable {
    public int Id;
    public String Nom;
    public Bitmap Image;
    public Equipement(String nom, Bitmap image){
        Nom = nom;
        Image = image;
    }
    public Equipement(){

    }

    protected Equipement(Parcel in) {
        Id = in.readInt();
        Nom = in.readString();
        Image = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<Equipement> CREATOR = new Creator<Equipement>() {
        @Override
        public Equipement createFromParcel(Parcel in) {
            return new Equipement(in);
        }

        @Override
        public Equipement[] newArray(int size) {
            return new Equipement[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(Id);
        parcel.writeString(Nom);
        parcel.writeParcelable(Image, i);
    }
}
