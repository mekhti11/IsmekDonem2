package com.ismek.ismekdonem.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Not implements Parcelable {

    private String baslik;
    private String not;
    private Date olusturulmaZamani;

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getNot() {
        return not;
    }

    public void setNot(String not) {
        this.not = not;
    }

    public Date getOlusturulmaZamani() {
        return olusturulmaZamani;
    }

    public void setOlusturulmaZamani(Date olusturulmaZamani) {
        this.olusturulmaZamani = olusturulmaZamani;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.baslik);
        dest.writeString(this.not);
        dest.writeLong(this.olusturulmaZamani != null ? this.olusturulmaZamani.getTime() : -1);
    }

    public Not() {
    }

    protected Not(Parcel in) {
        this.baslik = in.readString();
        this.not = in.readString();
        long tmpOlusturulmaZamani = in.readLong();
        this.olusturulmaZamani = tmpOlusturulmaZamani == -1 ? null : new Date(tmpOlusturulmaZamani);
    }

    public static final Parcelable.Creator<Not> CREATOR = new Parcelable.Creator<Not>() {
        @Override
        public Not createFromParcel(Parcel source) {
            return new Not(source);
        }

        @Override
        public Not[] newArray(int size) {
            return new Not[size];
        }
    };

    @Override
    public String toString() {
        return "Not{" +
                "baslik='" + baslik + '\'' +
                ", not='" + not + '\'' +
                ", olusturulmaZamani=" + olusturulmaZamani +
                '}';
    }
}
