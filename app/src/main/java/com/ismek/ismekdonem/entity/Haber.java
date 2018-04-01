package com.ismek.ismekdonem.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Haber implements Parcelable {

    private String imageUrl;
    private String title;
    private String summary;
    private String published;
    private String link;
    private String author;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.imageUrl);
        dest.writeString(this.title);
        dest.writeString(this.summary);
        dest.writeString(this.published);
        dest.writeString(this.link);
        dest.writeString(this.author);
    }

    public Haber() {
    }

    protected Haber(Parcel in) {
        this.imageUrl = in.readString();
        this.title = in.readString();
        this.summary = in.readString();
        this.published = in.readString();
        this.link = in.readString();
        this.author = in.readString();
    }

    public static final Parcelable.Creator<Haber> CREATOR = new Parcelable.Creator<Haber>() {
        @Override
        public Haber createFromParcel(Parcel source) {
            return new Haber(source);
        }

        @Override
        public Haber[] newArray(int size) {
            return new Haber[size];
        }
    };
}
