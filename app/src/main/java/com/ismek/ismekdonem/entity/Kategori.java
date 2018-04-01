package com.ismek.ismekdonem.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Kategori implements Parcelable {

    private String adi;
    private String link;

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.adi);
        dest.writeString(this.link);
    }

    public Kategori() {
    }

    protected Kategori(Parcel in) {
        this.adi = in.readString();
        this.link = in.readString();
    }

    public static final Parcelable.Creator<Kategori> CREATOR = new Parcelable.Creator<Kategori>() {
        @Override
        public Kategori createFromParcel(Parcel source) {
            return new Kategori(source);
        }

        @Override
        public Kategori[] newArray(int size) {
            return new Kategori[size];
        }
    };
}
